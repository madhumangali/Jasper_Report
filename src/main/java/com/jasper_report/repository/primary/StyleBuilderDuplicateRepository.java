package com.jasper_report.repository.primary;

import com.jasper_report.model.domain.StyleBuilderDuplicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleBuilderDuplicateRepository extends JpaRepository<StyleBuilderDuplicate,Long> {

}
