//package com.jasper_report.config;
//
//import com.jasper_report.dto.dummy.TreatmentDetailsDto;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.batch.item.database.JdbcCursorItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Reader implements ItemReader<String> {
//
//    private String[] messages = { "javainuse.com",
//            "Welcome to Spring Batch Example",
//            "We use H2 Database for this example" };
//
//    private int count = 0;
//
//    static long  min;
//
//    static long max;
//
//    @Autowired
//    public DataSource dataSource;
//
//    @Bean
//    public DataSource dataSource() {
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/JasperReport");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("0367");
//
//        return dataSource;
//    }
//
//    @Override
//    public JdbcCursorItemReader<TreatmentDetailsDto> read() throws Exception, UnexpectedInputException,
//            ParseException, NonTransientResourceException {
//
//        JdbcCursorItemReader<TreatmentDetailsDto> reader = new JdbcCursorItemReader<TreatmentDetailsDto>();
//        reader.setDataSource(dataSource);
//        reader.setSql("SELECT bds_details.*,crew_details.*,other.*,post_kr_details.*,pouring_details.*,process_details.*,process_time_details.*,treatment_details.*,trs_details.*  FROM kr_production_report.kr_production_report As master\n" +
//                "LEFT JOIN kr_production_report.bds_details as bds_details ON c1.bds_details_id = master.bds_details_id \n" +
//                "LEFT JOIN kr_production_report.crew_details as crew_details ON c2.crew_details_id = master.crew_details_id\n" +
//                "LEFT JOIN kr_production_report.other as other ON other.other_id = master.other_id\n" +
//                "LEFT JOIN kr_production_report.post_kr_details as post_kr_details ON post_kr_details.post_kr_details_id = master.post_kr_details_id\n" +
//                "LEFT JOIN kr_production_report.pouring_details as pouring_details ON pouring_details.pouring_details_id = master.pouring_details_id\n" +
//                "LEFT JOIN kr_production_report.process_details as process_details ON process_details.process_details_id = master.process_details_id\n" +
//                "LEFT JOIN kr_production_report.process_time_details as process_time_details ON process_time_details.process_time_details_id = master.process_time_details_id\n" +
//                "LEFT JOIN kr_production_report.treatment_details as treatment_details ON treatment_details.treatment_details_id = master.treatment_details_id\n" +
//                "LEFT JOIN kr_production_report.trs_details as trs_details ON trs_details.trs_details_id = master.trs_details_id \n" +
//                "where kr_production_report_id >="+min+" and kr_production_report_id <="+max);
//        reader.setRowMapper(new UserRowMapper());
//
//        return reader;
////
////
////        if (count < messages.length) {
////            return messages[count++];
////        } else {
////            count = 0;
////        }
////        return null;
//    }
//
//}
