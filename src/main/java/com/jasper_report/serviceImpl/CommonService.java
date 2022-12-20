package com.jasper_report.serviceImpl;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonService {

    public List<String> rename(List<String> list){

        List<String> renameList=new ArrayList<>();

        list.stream().forEach(tableName ->{
            renameList.add(WordUtils.capitalize(tableName.toString().replace("_"," ")));
        });

        return renameList;
    }

    public String convertToDatabaseName(String st){

        st=st.replace(" ","_").toLowerCase();

        return st;
    }

    public String rename(String st){

        st=WordUtils.capitalize(st.replace("_"," "));

        return st;
    }

}
