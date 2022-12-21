package com.jasper_report.repository;

import com.jasper_report.exception.EmployeeException;
import com.jasper_report.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.List;

@Repository
public interface CommonRepository extends JpaRepository<Employee,Long> {

    @Query(value="select schema_name from information_schema.schemata where schema_name " +
            "NOT IN ('pg_toast','pg_catalog','information_schema')\n",nativeQuery = true)
    List<String> findSchemas();

    @Query(value="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE' And " +
            "table_schema= :schemaName",nativeQuery = true)
    List<String> findSchemaTables(@Param("schemaName") String schemaName);

    @Query(value = "select table_name from INFORMATION_SCHEMA.KEY_COLUMN_USAGE " +
            "where column_name= :columnName and table_name!= :tableName " +
            "and position_in_unique_constraint ISNULL",nativeQuery = true)
    String findChildTableName(@Param("columnName") String columnName,@Param("tableName") String tableName);

    @Query(value = "select column_name from INFORMATION_SCHEMA.KEY_COLUMN_USAGE \n" +
            "where  table_name = :tableName and position_in_unique_constraint ISNULL;",nativeQuery = true)
    String getPrimaryKey(@Param("tableName") String tableName);

    default ResultSetMetaData getMetaData(String databaseUrl,String username,String password,
                                          String schemaName, String tableName) throws SQLException {

        Connection con = DriverManager.getConnection(databaseUrl, username, password);
        Statement stmt =con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from "+schemaName+"."+tableName);
        ResultSetMetaData resultSetMetaData = rs.getMetaData();

        return resultSetMetaData;
    }

    default List<Object[]> getSelectedColumns(EntityManager entityManager,String sql){

        if(sql.isEmpty()){
            throw new EmployeeException("Sql statement is not proper ");
        }
        javax.persistence.Query query = entityManager.createNativeQuery(sql);
        List<Object[]> queryResultList = query.getResultList();

        return queryResultList;
    }

}
