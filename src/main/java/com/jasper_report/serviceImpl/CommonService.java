package com.jasper_report.serviceImpl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class CommonService {

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

}
