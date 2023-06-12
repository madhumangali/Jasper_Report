package com.jasper_report.repository.dummy;

import com.jasper_report.dto.dummy.KrProductionReportDto;
import com.jasper_report.model.dummy.KrProductionReport;
import com.jasper_report.model.dummy.TreatmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Repository
public interface KrProductionReportRepository extends JpaRepository<KrProductionReport,Long> {

    @Query(value = "select rs.* from\n" +
            "  (select t.*, row_number() OVER () as rnum from kr_production_report.kr_production_report t \n" +
            "  ) rs\n" +
            "where rs.rnum >= :min and rs.rnum  < :max\n" +
            "order by rs.rnum",nativeQuery = true)
    @Transactional
    public  List<KrProductionReport> paginationData(int min,int max);


    @Query(value = "SELECT count(*) FROM kr_production_report.kr_production_report",nativeQuery = true)
    public long countOfData();

    @Query(value = "select * from  kr_production_report.kr_production_report  where kr_production_report_id >= :min and kr_production_report_id <= :max",nativeQuery = true)
    public List<KrProductionReport> krProductionReportList(@Param("min") long min, @Param("max")  long max);

}
