package com.jasper_report.serviceImpl;

import com.google.gson.Gson;
import com.jasper_report.dto.*;
import com.jasper_report.exception.EmployeeException;
import com.jasper_report.mapper.*;
import com.jasper_report.repository.CommonRepository;
import com.jasper_report.repository.StyleBuilderDuplicateRepository;
import com.jasper_report.repository.StyleBuilderRepository;
import com.jasper_report.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JRException;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.png.PngIOMetadata;
import net.sourceforge.plantuml.SourceStringReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


/**
 * This EmployeeServiceImpl Class consists of several Methods which helps to generate Jasper_Report
 */
@Service
@PropertySource(value={"classpath:application.properties"})
@Log4j2
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

    @Autowired
    private GitEntityPropertiesMapper gitEntityPropertiesMapper;

    @Autowired
    private GitSingleEntityMapper gitSingleEntityMapper ;

    @Autowired
    private GitEntityMapper gitEntityMapper ;



    /**
     * This Method takes input as SchemaName
     * returns list of tables in that schema
     */
    @Override
    public Entities getTablesList(String schemaName) {

        /**
         * Converting the variable(schemaName) in to database name
         * Query->(findSchemaTables),fetch the list of tables in the given schema
         */
        List<String> entitiesList=commonRepository.findSchemaTables(commonService.convertToDatabaseName(schemaName));

        if(entitiesList.isEmpty()){
            log.error("In Schema  "+schemaName+" Tables are not found, pls add");
            throw new EmployeeException("In Schema  "+schemaName+" Tables are not found, pls add");
        }

        /**
         * Creating Entity object and storing the query result list in the object
         */
        Entities entities=new Entities();
        entities.setEntities(commonService.rename(entitiesList));

        log.info("The entity list is fetched from the schema : "+ schemaName);

        return entities;
    }





//
//
//    @Override
//    public GitSingleEntity getTableColumnsAndChildTables(String schemaName, String tableName) throws SQLException {
//
//
//
//
//        GitEntity childTableList = new GitEntity();
//
//        /**
//         * Converting the variables(schemaName,tableName) in to database names
//         * Query->(getMetaData),fetch the list of columns in the given table (tableName)
//         */
//        ResultSetMetaData rsmd =commonRepository.getMetaData(databaseUrl,username,password,
//                commonService.convertToDatabaseName(schemaName),commonService.convertToDatabaseName(tableName));
//
//        int count = rsmd.getColumnCount();
//
//        if(count == 0 ){
//            log.error("Columns not found for table : "+tableName+" and schemaName : "+schemaName);
//            throw new EmployeeException("Columns not found for table : "+tableName+" and schemaName : "+schemaName);
//        }
//
//        /**
//         * Iterating the query result list (rsmd)
//         */
//        for(int i=1;i <= count;i++){
//
//            /**
//             * Converting the variable(tableName) in to database name
//             * Query->(findChildTableName),fetch the childTableName in the given table (tableName)
//             * if childName is null than the particular column is Property to the table (tableName)
//             * if childName is not null than the particular column is childTable to the table (tableName)
//             */
//            String childTableName = commonRepository.findChildTableName(rsmd.getColumnLabel(i),
//                    commonService.convertToDatabaseName(tableName));
//
//            List<GitEntityProperties> entityProperties= new ArrayList<>();
//
//            if(childTableName == null) {
//
//                log.info("Entity Property : "+rsmd.getColumnLabel(i)+"is added to Entity :"+tableName);
//                entityProperties.add(
//                        gitEntityPropertiesMapper.map(rsmd.getColumnLabel(i),rsmd.getColumnClassName(i),
//                                commonService.rename(rsmd.getColumnLabel(i))));
//
//            }else {
////                childTableList.add(
//                        /**
//                         * Recursive Calling of same method to getvariables
//                         * */
//                        gitEntityMapper.map(schemaName,childTableName, entityProperties);
//                        getTableColumnsAndChildTables(schemaName,childTableName);
////                );
////                childTableNames.add(commonService.rename(childTableName));
//                log.info("Child Table : "+childTableName+" is added to Entity :"+tableName);
//            }
//
//        }
//
//        /**
//         * Passing the variables to map method which consists in EntityMapper Interface
//         * Returns the Entity Object
//         */
////        return gitSingleEntityMapper.map(childTableList,);
//        return null;
//    }





 @Override
  public Entity getUml(String schemaName,String tableName) throws SQLException, IOException {

    Entity entity= getTableColumnsAndChildTables(schemaName,tableName);

//    String umlcode="@startuml{\n" +
//            "\t\"_type\": \"Project\",\n" +
//            "\t\"_id\": \"AAAAAAFF+h6SjaM2Hec=\",\n" +
//            "\t\"name\": \"Untitled\",\n" +
//            "\t\"ownedElements\": [\n" +
//            "\t\t{\n" +
//            "\t\t\t\"_type\": \"UMLModel\",\n" +
//            "\t\t\t\"_id\": \"AAAAAAFF+qBWK6M3Z8Y=\",\n" +
//            "\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\"$ref\": \"AAAAAAFF+h6SjaM2Hec=\"\n" +
//            "\t\t\t},\n" +
//            "\t\t\t\"name\": \"Model\",\n" +
//            "\t\t\t\"ownedElements\": [\n" +
//            "\t\t\t\t{\n" +
//            "\t\t\t\t\t\"_type\": \"UMLClassDiagram\",\n" +
//            "\t\t\t\t\t\"_id\": \"AAAAAAFF+qBtyKM79qY=\",\n" +
//            "\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\"$ref\": \"AAAAAAFF+qBWK6M3Z8Y=\"\n" +
//            "\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\"name\": \"Main\",\n" +
//            "\t\t\t\t\t\"defaultDiagram\": true,\n" +
//            "\t\t\t\t\t\"ownedViews\": [\n" +
//            "\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\"_type\": \"UMLClassView\",\n" +
//            "\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPW/No2uUPg=\",\n" +
//            "\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAFF+qBtyKM79qY=\"\n" +
//            "\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\"model\": {\n" +
//            "\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/M42szOU=\"\n" +
//            "\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\"subViews\": [\n" +
//            "\t\t\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\t\t\"_type\": \"UMLNameCompartmentView\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPW/No2vuoM=\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2uUPg=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"model\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/M42szOU=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"subViews\": [\n" +
//            "\t\t\t\t\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_type\": \"LabelView\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPW/No2wDi8=\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2vuoM=\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"visible\": false,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"font\": \"Arial;13;0\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"left\": -16,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"height\": 13\n" +
//            "\t\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_type\": \"LabelView\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPW/No2x7Xs=\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2vuoM=\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"font\": \"Arial;13;1\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"left\": 221,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"top\": 159,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"width\": 310,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"height\": 13,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"text\": \"Employee\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_type\": \"LabelView\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPW/N42yswo=\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2vuoM=\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"visible\": false,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"font\": \"Arial;13;0\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"left\": -16,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"width\": 73.67724609375,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"height\": 13,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"text\": \"(from Model)\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_type\": \"LabelView\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPW/N42z0wI=\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2vuoM=\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"visible\": false,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"font\": \"Arial;13;0\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"left\": -16,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"height\": 13,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"horizontalAlignment\": 1\n" +
//            "\t\t\t\t\t\t\t\t\t\t}\n" +
//            "\t\t\t\t\t\t\t\t\t],\n" +
//            "\t\t\t\t\t\t\t\t\t\"font\": \"Arial;13;0\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"left\": 216,\n" +
//            "\t\t\t\t\t\t\t\t\t\"top\": 152,\n" +
//            "\t\t\t\t\t\t\t\t\t\"width\": 320,\n" +
//            "\t\t\t\t\t\t\t\t\t\"height\": 25,\n" +
//            "\t\t\t\t\t\t\t\t\t\"stereotypeLabel\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2wDi8=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"nameLabel\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2x7Xs=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"namespaceLabel\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/N42yswo=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"propertyLabel\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/N42z0wI=\"\n" +
//            "\t\t\t\t\t\t\t\t\t}\n" +
//            "\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\t\t\"_type\": \"UMLAttributeCompartmentView\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPW/N420vn0=\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2uUPg=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"model\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/M42szOU=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"subViews\": [\n" +
//            "\t\t\t\t\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_type\": \"UMLAttributeView\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPYSMI3aNwg=\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/N420vn0=\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"model\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPYSGo3X1eg=\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"font\": \"Arial;13;0\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"left\": 221,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"top\": 182,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"width\": 310,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"height\": 13,\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"text\": \"+Id\",\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\"horizontalAlignment\": 0\n" +
//            "\t\t\t\t\t\t\t\t\t\t}\n" +
//            "\t\t\t\t\t\t\t\t\t],\n" +
//            "\t\t\t\t\t\t\t\t\t\"font\": \"Arial;13;0\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"left\": 216,\n" +
//            "\t\t\t\t\t\t\t\t\t\"top\": 177,\n" +
//            "\t\t\t\t\t\t\t\t\t\"width\": 320,\n" +
//            "\t\t\t\t\t\t\t\t\t\"height\": 23\n" +
//            "\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\t\t\"_type\": \"UMLOperationCompartmentView\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPW/N421aW8=\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2uUPg=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"model\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/M42szOU=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"font\": \"Arial;13;0\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"left\": 216,\n" +
//            "\t\t\t\t\t\t\t\t\t\"top\": 200,\n" +
//            "\t\t\t\t\t\t\t\t\t\"width\": 320,\n" +
//            "\t\t\t\t\t\t\t\t\t\"height\": 10\n" +
//            "\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\t\t\"_type\": \"UMLReceptionCompartmentView\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPW/N422KDc=\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2uUPg=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"model\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/M42szOU=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"visible\": false,\n" +
//            "\t\t\t\t\t\t\t\t\t\"font\": \"Arial;13;0\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"left\": -8,\n" +
//            "\t\t\t\t\t\t\t\t\t\"width\": 10,\n" +
//            "\t\t\t\t\t\t\t\t\t\"height\": 10\n" +
//            "\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\t\t\"_type\": \"UMLTemplateParameterCompartmentView\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPW/OI23E+Y=\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2uUPg=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"model\": {\n" +
//            "\t\t\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/M42szOU=\"\n" +
//            "\t\t\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\t\t\"visible\": false,\n" +
//            "\t\t\t\t\t\t\t\t\t\"font\": \"Arial;13;0\",\n" +
//            "\t\t\t\t\t\t\t\t\t\"left\": -8,\n" +
//            "\t\t\t\t\t\t\t\t\t\"width\": 10,\n" +
//            "\t\t\t\t\t\t\t\t\t\"height\": 10\n" +
//            "\t\t\t\t\t\t\t\t}\n" +
//            "\t\t\t\t\t\t\t],\n" +
//            "\t\t\t\t\t\t\t\"font\": \"Arial;13;0\",\n" +
//            "\t\t\t\t\t\t\t\"containerChangeable\": true,\n" +
//            "\t\t\t\t\t\t\t\"left\": 216,\n" +
//            "\t\t\t\t\t\t\t\"top\": 152,\n" +
//            "\t\t\t\t\t\t\t\"width\": 320,\n" +
//            "\t\t\t\t\t\t\t\"height\": 232,\n" +
//            "\t\t\t\t\t\t\t\"nameCompartment\": {\n" +
//            "\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/No2vuoM=\"\n" +
//            "\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\"attributeCompartment\": {\n" +
//            "\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/N420vn0=\"\n" +
//            "\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\"operationCompartment\": {\n" +
//            "\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/N421aW8=\"\n" +
//            "\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\"receptionCompartment\": {\n" +
//            "\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/N422KDc=\"\n" +
//            "\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\"templateParameterCompartment\": {\n" +
//            "\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/OI23E+Y=\"\n" +
//            "\t\t\t\t\t\t\t}\n" +
//            "\t\t\t\t\t\t}\n" +
//            "\t\t\t\t\t]\n" +
//            "\t\t\t\t},\n" +
//            "\t\t\t\t{\n" +
//            "\t\t\t\t\t\"_type\": \"UMLClass\",\n" +
//            "\t\t\t\t\t\"_id\": \"AAAAAAGHvPW/M42szOU=\",\n" +
//            "\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\"$ref\": \"AAAAAAFF+qBWK6M3Z8Y=\"\n" +
//            "\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\"name\": \"Employee\",\n" +
//            "\t\t\t\t\t\"attributes\": [\n" +
//            "\t\t\t\t\t\t{\n" +
//            "\t\t\t\t\t\t\t\"_type\": \"UMLAttribute\",\n" +
//            "\t\t\t\t\t\t\t\"_id\": \"AAAAAAGHvPYSGo3X1eg=\",\n" +
//            "\t\t\t\t\t\t\t\"_parent\": {\n" +
//            "\t\t\t\t\t\t\t\t\"$ref\": \"AAAAAAGHvPW/M42szOU=\"\n" +
//            "\t\t\t\t\t\t\t},\n" +
//            "\t\t\t\t\t\t\t\"name\": \"Id\"\n" +
//            "\t\t\t\t\t\t}\n" +
//            "\t\t\t\t\t]\n" +
//            "\t\t\t\t}\n" +
//            "\t\t\t]\n" +
//            "\t\t}\n" +
//            "\t]\n" +
//            "}@enduml";

     String umlCode = "@startuml\n" +

             "class Person {\n" +
             "  +name: String\n" +
             "  +age: int\n" +
             "  +setName(name: String): void\n" +
             "  +setAge(age: int): void\n" +
             "}\n" +
             "@enduml";

     // Create a ByteArrayOutputStream to store the generated image data
     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

     // Create a SourceStringReader to read the UML code string
     SourceStringReader reader = new SourceStringReader(umlCode);

     // Generate the UML diagram as a PNG image and write it to the ByteArrayOutputStream
//     File outputFile = new File("output.mdj"); // Output MDJ file
//
//     FileOutputStream output = new FileOutputStream(outputFile);
//     reader.generateImage(output); // Generate MDJ file
//
//     output.close();

     // Write the generated image data to a file
     try (FileOutputStream fileOutputStream = new FileOutputStream("uml_diagram.mdj")) {
        String s= reader.generateImage(outputStream);
         fileOutputStream.write(outputStream.toByteArray());
        log.info("Message Hello");
     }catch (IOException exception){
         log.error("fudfh");
     }


     return entity;
 }







    /**
     *This Method takes input as (schemaName,tableName)
     * returns the (columns, childTables) for the given table (tableName)
     */
    @Override
    public Entity getTableColumnsAndChildTables(String schemaName,String tableName) throws SQLException, IOException {

        List<EntityProperty> entityProperties= new ArrayList<>();

        List<Entity> childTableNames = new ArrayList();

        /**
         * Converting the variables(schemaName,tableName) in to database names
         * Query->(getMetaData),fetch the list of columns in the given table (tableName)
         */
        ResultSetMetaData rsmd =commonRepository.getMetaData(databaseUrl,username,password,
                commonService.convertToDatabaseName(schemaName),commonService.convertToDatabaseName(tableName));

        int count = rsmd.getColumnCount();

        if(count == 0 ){
            log.error("Columns not found for table : "+tableName+" and schemaName : "+schemaName);
            throw new EmployeeException("Columns not found for table : "+tableName+" and schemaName : "+schemaName);
        }

        /**
         * Iterating the query result list (rsmd)
         */
        for(int i=1;i <= count;i++){

            /**
             * Converting the variable(tableName) in to database name
             * Query->(findChildTableName),fetch the childTableName in the given table (tableName)
             * if childName is null than the particular column is Property to the table (tableName)
             * if childName is not null than the particular column is childTable to the table (tableName)
             */
            String childTableName = commonRepository.findChildTableName(rsmd.getColumnLabel(i),
                    commonService.convertToDatabaseName(tableName));

            if(childTableName == null) {

                log.info("Entity Property : "+rsmd.getColumnLabel(i)+"is added to Entity :"+tableName);
                entityProperties.add(
                        entityPropertyMapper.map(rsmd.getColumnLabel(i),rsmd.getColumnClassName(i),
                               commonService.rename(rsmd.getColumnLabel(i))));

            }else {
                childTableNames.add(
                        /**
                         * Recursive Calling of same method to getvariables
                         * */
                        getTableColumnsAndChildTables(schemaName,childTableName)
                );
//                childTableNames.add(commonService.rename(childTableName));
                log.info("Child Table : "+childTableName+"is added to Entity :"+tableName);
            }

        }

        /**
         * Passing the variables to map method which consists in EntityMapper Interface
         * Returns the Entity Object
         */

//        try {
//            // Output to PNG image file
//            String outputFileName = "class_diagram.mdj";
//            OutputStream pngOutputStream = new FileOutputStream(outputFileName);
//
//            reader.generateImage(new File("class_diagram.mdj"));
//
//            pngOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return entityMapper.map(schemaName,commonService.rename(tableName),
                entityProperties,childTableNames);
    }


    /**
     *This Method takes input as (recentStyle,multiTableColumnsParams)
     * returns the (Path) where the Jasper_Report is generated
     */

    public MultiTableColumnsResult getJasperReport(boolean recentStyle,MultiTableColumnsParams multiTableColumnsParams) throws JRException, ClassNotFoundException, IOException {

        List<String> columnHeaders=new ArrayList<>();
        List<Map> finalRows = new ArrayList<>();

        if(multiTableColumnsParams.getEntityList().isEmpty()){
            log.error("Passing Entity list as empty");
            throw new EmployeeException("Passing Entity list as empty");
        }

        /**
         * converting the variables(tableName,schemaName) in to database names
         * Storing in the variables(mainTable,schemaName)
         */
       String mainTable= commonService.convertToDatabaseName( multiTableColumnsParams.getEntityList().get(0).getTableName() );
       String schemaName=commonService.convertToDatabaseName(multiTableColumnsParams.getEntityList().get(0).getSchemaName() );

        /**
         * The variables(sql,join) are used to construct a query
         */
        AtomicReference<String> sql = new AtomicReference<>("select ");
        AtomicReference<String> join = new AtomicReference<>(" ");


        /**
         * Iterating the Entity List
         */
        multiTableColumnsParams.getEntityList().stream().forEach(entity ->{

            if(entity.getEntityPropertyList().isEmpty()){
                log.error("Entity Property is empty, pls add entityProperties to Entity: "+entity.getTableName());
                throw new EmployeeException("Entity Property is empty, pls add entityProperties to Entity: "+entity.getTableName());
            }

            /**
             * Variable (aliasName) is the aliasName for the TableName
             */
            String aliasName= commonService.convertToDatabaseName(entity.getTableName());

            /**
             * Iterating the entity properties
             * columnNames are stored in columnHeaders list which helps for jasper report columnHeaders
             * Concatenating columnNames in sql String
             */
            entity.getEntityPropertyList().stream().forEach(entityProperty -> {

                columnHeaders.add(entityProperty.getColumnName());

                if(entityProperty.getColumnName().isEmpty()){
                    log.error("pls Add column Name for a Table Name : "+entity.getTableName());
                    throw new EmployeeException("pls Add column Name for a Table Name : "+entity.getTableName());
                }

                if(entity.getOrder()==1){
                    sql.set(sql.get()+entityProperty.getColumnName()+",");
                }else {
                        sql.set(sql.get()+aliasName+"."+entityProperty.getColumnName()+",");
                }

            });

            /**
             * Here Fetching the PrimaryKey for the table using query->(getPrimaryKey)
             * PrimaryKey is used for the join queries
             * Building Query using variables (sql ,join)
             */
            if(entity.getOrder() !=1 ) {

                String primaryKey=commonRepository.getPrimaryKey(aliasName);

                if(primaryKey.isEmpty()){
                    log.error("Primary key not found for table : "+ entity.getTableName());
                    throw  new EmployeeException("Primary key not found for table : "+ entity.getTableName());
                }

                log.info("Primary Key found for table : "+entity.getTableName());
                String sqlString = "LEFT JOIN " + commonService.convertToDatabaseName(entity.getSchemaName())
                        +"."+aliasName + " As " + aliasName + " ON master." + primaryKey + "=" + aliasName + "." + primaryKey + " ";

                join.set(join.get() + sqlString);
            }

        });

        sql.set(sql.get().substring(0,sql.toString().length()-1));

        /**
         * Finally Building of query is completed by using variables (sql ,join)
         */
        sql.set(sql.get() +" from " +schemaName+"."+mainTable+ " As master " + join.get());

        log.info(sql.get());

        /**
         * method->(getSelectedColumns)
         * input -> (entityManager, sql-(query in string format) )
         * output ->fetch the data of selected columns from multiple tables
         */
        List<Object[]> queryResultList =commonRepository.getSelectedColumns(entityManager,sql.get());

        if(queryResultList.isEmpty()){
            log.error("The Data is empty in database, pls add some data at schema : "+schemaName +" tableName : "+mainTable);
            throw  new EmployeeException("The Data is empty in database, pls add some data at schema : "+schemaName
            +" tableName : "+mainTable);
        }

        /**
         * The variable(queryResultList) is iterating
         * Mapping columnHeaders and result data, ANd finally storing variable(finalRows)
         * The variable(finalRows) is uses in jasper_report generation while mapping data to the Headers of the report
         */
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

        log.info("columnsHeaders and Rows are formed for the jasper_report");

        /**
         * Preparing Object(multiTableColumnsResult) by setting all the required fields
         * This Object(multiTableColumnsResult) is a parameter to the Method (getReport)
         */
        MultiTableColumnsResult multiTableColumnsResult= multiTableColumnsResultMapper.mapToResult(
                multiTableColumnsParams.getFileName(),multiTableColumnsParams.getReportHeader(),
                multiTableColumnsParams.getDescription(),multiTableColumnsParams.getSubject(),
                multiTableColumnsParams.getTitleName(), multiTableColumnsParams.getSubtitleName());

        multiTableColumnsResult.setColumnHeaders(columnHeaders);
        multiTableColumnsResult.setRows(finalRows);
        multiTableColumnsResult.setStyleBuilderParamsList(multiTableColumnsParams.getStyleBuilderParamsList());

        log.info("The multiTableColumnsResult param is prepared for the generator jasper_report Method");

        /**
         * Here calling the method(getReport) which generates the actual jasper_report
         */
        String path=jasperReportService.getReport(recentStyle,multiTableColumnsResult);

        log.info("The file is generated at path : "+path);

        return multiTableColumnsResult;
    }

    /**
     * This Method returns the schemas of a database
     */
    @Override
    public Schemas getSchemas() {

        Schemas schemas=new Schemas();

        /**
         * query->(findSchemas) fetch the schemas from the database
         * Renaming the schemaNames using the Method (rename)
         */
        List<String> schemaNames=commonRepository.findSchemas();

        if(schemaNames.isEmpty()){
            log.error("There is no schemas found in your base, pls add schemas");
            throw new EmployeeException("There is no schemas found in your base, pls add schemas");
        }

        schemas.setSchemaName(commonService.rename(schemaNames));

        log.info("The schemas are fetched from database");

        return schemas;
    }

    /**
     * input variables(schemaName, tableNames)
     *  returns the (columns, childTables) for the Multiple tables (tableNames)
     */
    @Override
    public List<Entity> getMultipleTableColumnsAndChildTables(String schemaName,List<String> tableNames) {

        List<Entity> entityList = new ArrayList<>();

        if(tableNames.isEmpty()){
            log.error("TableNames List is empty, pls add some table names");
            throw  new EmployeeException("TableNames List is empty, pls add some table names");
        }

        /**
         * Iterating the variable(tableNames)
         * Calling Method(getTableColumnsAndChildTables), this method returns the (columns, childTables) for the given table (tableName)
         * Adding the above Method result in entityList
         * Return the variable(entityList) which contains (columns, childTables) for the Multiple tables
         */
        tableNames.stream().forEach(tableName -> {

            try {
                entityList.add(getTableColumnsAndChildTables(schemaName,tableName));
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }

        });


        log.info("The Entity Property and child Tables are fetched for the multiple tables");

        return entityList;
    }




}


