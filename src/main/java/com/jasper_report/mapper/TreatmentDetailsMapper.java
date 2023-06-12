package com.jasper_report.mapper;

import com.jasper_report.dto.dummy.TreatmentDetailsDto;
import com.jasper_report.model.dummy.*;
import org.mapstruct.Mapper;

import java.sql.ResultSet;

@Mapper
public interface TreatmentDetailsMapper {

    TreatmentDetailsDto map(TreatmentDetails treatmentDetails, ProcessTimeDetails processTimeDetails, CrewDetails crewDetails,
                            BdsDetails bdsDetails, Other other,PostKrDetails postKrDetails,PouringDetails pouringDetails,
                            ProcessDetails processDetails,TrsDetails trsDetails);

    TreatmentDetailsDto map(KrProductionReport krProductionReport);

}
