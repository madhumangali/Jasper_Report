package com.jasper_report.repository.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.JpaHelper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.List;

@Service
public class KPR {

    @PersistenceContext
//    @Autowired
    private EntityManager entityManager;

    public  List<Object[]> getMetaData(
                                          int min, int max) throws SQLException {
        String sql="select rs.* from\n" +
                "  (select t.*, row_number() OVER () as rnum from kr_production_report.kr_production_report t \n" +
                "  ) rs\n" +
                "where rs.rnum >= "+min+ " and rs.rnum  < "+max+"\n" +
                "order by rs.rnum";
        javax.persistence.Query query = entityManager.createNativeQuery(sql);
        List<Object[]> queryResultList = query.getResultList();

        return queryResultList;
    }
}
