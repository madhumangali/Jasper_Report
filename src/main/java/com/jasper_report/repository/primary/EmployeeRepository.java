package com.jasper_report.repository.primary;

import com.jasper_report.model.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}
