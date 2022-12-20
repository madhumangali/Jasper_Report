package com.jasper_report.repository;

import com.jasper_report.model.StyleBuilderParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleBuilderRepository extends JpaRepository<StyleBuilderParams,Long> {
}
