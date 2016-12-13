package com.employee.registration.dao.entity;

import javax.persistence.EmbeddedId;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeRepository extends CrudRepository<EmployeeTable, String>{
	
	EmployeeTable findOne(String eid);	
}
