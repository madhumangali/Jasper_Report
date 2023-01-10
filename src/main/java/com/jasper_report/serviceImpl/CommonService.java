package com.jasper_report.serviceImpl;

import com.jasper_report.repository.primary.PrimaryCommonRepository;
import com.jasper_report.repository.secondary.SecondaryCommonRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Service
@Log4j2
public class CommonService {

    @Autowired
    private PrimaryCommonRepository primaryCommonRepository;

    @Autowired
    private SecondaryCommonRepository secondaryCommonRepository;

//    @Autowired
//    @Qualifier("entityManager")
    @PersistenceContext(unitName = "primary")
    private EntityManager primaryEntityManager;

//    @Autowired
//    @Qualifier("customEntityManagerFactory")
    @PersistenceContext(unitName = "secondary")
    private EntityManager secondaryEntityManager;


    public List<String> rename(List<String> list){

        List<String> renameList=new ArrayList<>();

        list.stream().forEach(tableName ->{
            renameList.add(WordUtils.capitalize(tableName.toString().replace("_"," ")));
        });

        log.info("The given list is renamed");

        return renameList;
    }

    public String convertToDatabaseName(String st){

        st=st.replace(" ","_").toLowerCase();

        log.info("Parameter is converted in to database Name");
        return st;
    }

    public String rename(String st){

        st=WordUtils.capitalize(st.replace("_"," "));

        log.info("The given parameter is renamed");

        return st;
    }

    Function<String,List<Map>> primaryDatabaseFunction = (tableName)->{

       List<Map> data= new ArrayList<>();

       List<String> tableNames=primaryCommonRepository.findTableNames();
       List<String> renameTableNames=rename(tableNames);
       if(renameTableNames.contains(rename(tableName))){
           String schemaName=primaryCommonRepository.findSchemaForTable(convertToDatabaseName(tableName));
           String sql= "select * from "+schemaName+"."+convertToDatabaseName(tableName);
           List<Object[]> rs= primaryCommonRepository.getSelectedColumns(primaryEntityManager,sql);

           List<String> columns=primaryCommonRepository.findTableColumns(convertToDatabaseName(tableName));

           rs.stream().forEach(object ->{

               Map<String,Object> map=new HashMap<>();
               AtomicInteger inc=new AtomicInteger(0);

               columns.forEach(columnName ->{
                   map.put(columnName,object[inc.get()]);
                   inc.getAndIncrement();
               });

               data.add(map);

           });

       }else {
           try {
               throw new Exception("fkiuirfo");
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }

       return data;
    };


      Function<String,List<Map>> secondaryDatabaseFunction= (tableName)->{

        List<Map> data= new ArrayList<>();

        List<String> tableNames=secondaryCommonRepository.findTableNames();
        List<String> renameTableNames=rename(tableNames);
        if(renameTableNames.contains(rename(tableName))){
            String schemaName=secondaryCommonRepository.findSchemaForTable(convertToDatabaseName(tableName));
            String sql= "select * from "+schemaName+"."+convertToDatabaseName(tableName);
            List<Object[]> rs= secondaryCommonRepository.getSelectedColumns(secondaryEntityManager,sql);

            List<String> columns=secondaryCommonRepository.findTableColumns(convertToDatabaseName(tableName));

            rs.stream().forEach(object ->{

                Map<String,Object> map=new HashMap<>();
                AtomicInteger inc=new AtomicInteger(0);

                columns.forEach(columnName ->{
                    map.put(columnName,object[inc.get()]);
                    inc.getAndIncrement();
                });

                data.add(map);

            });

        }

        return data;
    };

}
