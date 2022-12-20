package com.jasper_report.repository;

import com.jasper_report.model.StyleBuilderDuplicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleBuilderDuplicateRepository extends JpaRepository<StyleBuilderDuplicate,Long> {

}
