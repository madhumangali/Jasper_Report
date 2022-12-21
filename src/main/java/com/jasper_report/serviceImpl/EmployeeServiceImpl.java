package com.jasper_report.serviceImpl;

import com.jasper_report.dto.*;
import com.jasper_report.exception.EmployeeException;
import com.jasper_report.mapper.EntityMapper;
import com.jasper_report.mapper.EntityPropertyMapper;
import com.jasper_report.mapper.MultiTableColumnsResultMapper;
import com.jasper_report.mapper.StyleBuilderMapper;
import com.jasper_report.model.Enum.PdfTitle;
import com.jasper_report.repository.*;
import com.jasper_report.service.EmployeeService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


@Service
@PropertySource(value={"classpath:application.properties"})
public class EmployeeServiceImpl implements EmployeeService {

    @Value("${spring.datasource.url:}")
    private String databaseUrl;

    @Value("${spring.datasource.username:}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StyleBuilderMapper styleBuilderMapper;

    @Autowired
    private StyleBuilderRepository styleBuilderRepository;

    @Autowired
    private StyleBuilderDuplicateRepository styleBuilderDuplicateRepository;

    @Autowired
    private JasperReportServiceImpl jasperReportService;

    @Autowired
    private EntityPropertyMapper entityPropertyMapper;

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private MultiTableColumnsResultMapper multiTableColumnsResultMapper;

    @Override
    public Entities getTablesList(String schemaName) {

        List<String> entitiesList=commonRepository.findSchemaTables(commonService.convertToDatabaseName(schemaName));

        if(entitiesList.isEmpty()){
            throw new EmployeeException("In Schema  "+schemaName+" Tables are not found, pls add");
        }

        Entities entities=new Entities();
        entities.setEntities(commonService.rename(entitiesList));

        return entities;
    }

    @Override
    public Entity getTableColumnsAndChildTables(String schemaName,String tableName) throws SQLException {

        List<EntityProperty> entityProperties= new ArrayList<>();

        List childTableNames = new ArrayList();

        ResultSetMetaData rsmd =commonRepository.getMetaData(databaseUrl,username,password,
                commonService.convertToDatabaseName(schemaName),commonService.convertToDatabaseName(tableName));

        int count = rsmd.getColumnCount();

        if(count == 0 ){
            throw new EmployeeException("Columns not found for table : "+tableName+" and schemaName : "+schemaName);
        }

        for(int i=1;i <= count;i++){

            String childTableName = commonRepository.findChildTableName(rsmd.getColumnLabel(i),
                    commonService.convertToDatabaseName(tableName));

            if(childTableName == null) {

                entityProperties.add(
                        entityPropertyMapper.map(rsmd.getColumnLabel(i),rsmd.getColumnClassName(i),
                               commonService.rename(rsmd.getColumnLabel(i))));

            }else {
                childTableNames.add(commonService.rename(childTableName));
            }

        }

        return entityMapper.map(schemaName,commonService.rename(tableName),
                entityProperties,childTableNames);
    }

    @Override
    public String getJasperReport(boolean recentStyle,MultiTableColumnsParams multiTableColumnsParams) throws JRException, ClassNotFoundException {

        List<String> columnHeaders=new ArrayList<>();
        List<Map> finalRows = new ArrayList<>();

        if(multiTableColumnsParams.getEntityList().isEmpty()){
            throw new EmployeeException("Passing Entity list as empty");
        }

       String mainTable= commonService.convertToDatabaseName( multiTableColumnsParams.getEntityList().get(0).getTableName() );
       String schemaName=commonService.convertToDatabaseName(multiTableColumnsParams.getEntityList().get(0).getSchemaName() );

        AtomicReference<String> sql = new AtomicReference<>("select ");
        AtomicReference<String> join = new AtomicReference<>(" ");


        multiTableColumnsParams.getEntityList().stream().forEach(entity ->{

            if(entity.getEntityPropertyList().isEmpty()){
                throw new EmployeeException("Entity Property is empty, pls add entityProperties to Entity: "
                        +entity.getTableName());
            }

            String aliasName= commonService.convertToDatabaseName(entity.getTableName());

            entity.getEntityPropertyList().stream().forEach(entityProperty -> {

                columnHeaders.add(entityProperty.getColumnName());

                if(entityProperty.getColumnName().isEmpty()){
                    throw new EmployeeException("pls Add column Name for a Table Name : "+entity.getTableName());
                }

                if(entity.getOrder()==1){
                    sql.set(sql.get()+entityProperty.getColumnName()+",");
                }else {
                        sql.set(sql.get()+aliasName+"."+entityProperty.getColumnName()+",");
                }

            });

            if(entity.getOrder() !=1 ) {

                String primaryKey=commonRepository.getPrimaryKey(aliasName);

                if(primaryKey.isEmpty()){
                    throw  new EmployeeException("Primary key not found for table : "+ entity.getTableName());
                }

                String sqlString = "LEFT JOIN " + commonService.convertToDatabaseName(entity.getSchemaName())
                        +"."+aliasName + " As " + aliasName + " ON master." + primaryKey + "=" + aliasName + "." + primaryKey + " ";

                join.set(join.get() + sqlString);
            }

        });

        sql.set(sql.get().substring(0,sql.toString().length()-1));

        sql.set(sql.get() +" from " +schemaName+"."+mainTable+ " As master " + join.get());

        System.out.println(sql.get());

        List<Object[]> queryResultList =commonRepository.getSelectedColumns(entityManager,sql.get());

        if(queryResultList.isEmpty()){
            throw  new EmployeeException("The Data is empty in database, pls add some data at schema : "+schemaName
            +" tableName : "+mainTable);
        }

        queryResultList.stream().forEach(objects -> {

                    Map<String, Object> mapObject = new HashMap<>();
                    AtomicInteger ordinal = new AtomicInteger(0);

            columnHeaders.stream().forEach(column->{
                mapObject.put( column,
                        objects[ordinal.getAndIncrement()]);

            });
                    finalRows.add(mapObject);
                }
        );

        MultiTableColumnsResult multiTableColumnsResult= multiTableColumnsResultMapper.mapToResult(
                multiTableColumnsParams.getFileName(),multiTableColumnsParams.getReportHeader(),
                multiTableColumnsParams.getDescription(),multiTableColumnsParams.getSubject(),
                multiTableColumnsParams.getTitleName(), multiTableColumnsParams.getSubtitleName());

        multiTableColumnsResult.setColumnHeaders(columnHeaders);
        multiTableColumnsResult.setRows(finalRows);
        multiTableColumnsResult.setStyleBuilderParamsList(multiTableColumnsParams.getStyleBuilderParamsList());

        String path=jasperReportService.getReport(recentStyle,multiTableColumnsResult);

        return path;
    }

    @Override
    public Schemas getSchemas() {

        Schemas schemas=new Schemas();

        List<String> schemaNames=commonRepository.findSchemas();

        if(schemaNames.isEmpty()){
            throw new EmployeeException("There is no schemas found in your base, pls add schemas");
        }

        schemas.setSchemaName(commonService.rename(schemaNames));

        return schemas;
    }

    @Override
    public List<Entity> getMultipleTableColumnsAndChildTables(String schemaName,List<String> tableNames) {

        List<Entity> entityList = new ArrayList<>();

        if(tableNames.isEmpty()){
            throw  new EmployeeException("TableNames List is empty, pls add some table names");
        }

        tableNames.stream().forEach(tableName -> {

            try {
                entityList.add(getTableColumnsAndChildTables(schemaName,tableName));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

        return entityList;
    }

}


