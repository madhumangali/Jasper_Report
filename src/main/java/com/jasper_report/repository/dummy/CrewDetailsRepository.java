package com.jasper_report.repository.dummy;

import com.jasper_report.model.dummy.CrewDetails;
import com.jasper_report.model.dummy.TreatmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewDetailsRepository extends JpaRepository<CrewDetails,Long> {
    @Query(value = "select * from  kr_production_report.crew_details  where crew_details_id >= :min and crew_details_id <= :max",nativeQuery = true)
    public List<CrewDetails> crewDetails(@Param("min") long min, @Param("max")  long max);

}
