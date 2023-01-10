package com.jasper_report.repository.primary;

import com.jasper_report.model.domain.StyleBuilderParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleBuilderRepository extends JpaRepository<StyleBuilderParams,Long> {
}
