package com.jasper_report.repository.dummy;

import com.jasper_report.model.dummy.BdsDetails;
import com.jasper_report.model.dummy.ProcessTimeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BdsDetailsRepository extends JpaRepository<BdsDetails,Long> {

    @Query(value = "select * from  kr_production_report.bds_details where bds_details_id >= :min and bds_details_id <= :max",nativeQuery = true)
    List<BdsDetails> bdsDetails(long min, long max);

}
