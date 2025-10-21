package com.jbbcch.smarttaskmanager.department.repository;

import com.jbbcch.smarttaskmanager.department.model.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
