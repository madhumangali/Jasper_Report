package com.jasper_report.repository.dummy;

import com.jasper_report.model.dummy.BdsDetails;
import com.jasper_report.model.dummy.Other;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherRepository extends JpaRepository<Other,Long> {

    @Query(value = "select * from  kr_production_report.other where other_id >= :min and other_id <= :max",nativeQuery = true)
    List<Other> otherDetails(long min, long max);

}
