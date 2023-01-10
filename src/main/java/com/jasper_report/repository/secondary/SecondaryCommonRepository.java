package com.jasper_report.repository.secondary;

import com.jasper_report.exception.EmployeeException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.List;

@Repository
public interface SecondaryCommonRepository extends AddressRepository{

    @Query(value="select schema_name from information_schema.schemata where schema_name " +
            "NOT IN ('pg_toast','pg_catalog','information_schema')\n",nativeQuery = true)
    List<String> findSchemas();

    @Query(value="select catalog_name from information_schema.schemata where schema_name IN\n" +
            "('pg_catalog')",nativeQuery = true)
    String findCatalogName();

    @Query(value = "SELECT table_name FROM information_schema.tables\n" +
            "                      WHERE table_schema Not In ('pg_toast','pg_catalog','information_schema')",nativeQuery = true)
    List<String> findTableNames();

    @Query(value = "SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME IN (:tableName) ORDER BY ordinal_position ASC",nativeQuery = true)
    List<String> findTableColumns(@Param("tableName") String tableName);

    @Query(value = "SELECT table_schema FROM information_schema.tables\n" +
            " WHERE table_name = :tableName ",nativeQuery = true)
    String findSchemaForTable(@Param("tableName")String tableName);

    @Query(value = "Select s from :schemaName . :tableName as s",nativeQuery = true)
    List<Object[]> getData(@Param("schemaName")String schemaName, @Param("tableName") String tableName);

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
