/**
 * 
 */
package com.employee.registration.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employee.registration.dao.entity.EmployeeRepository;
import com.employee.registration.dao.entity.EmployeeTable;
import com.employee.registration.exception.EmployeeRegistrationException;
import com.employee.registration.service.entity.Department;
import com.employee.registration.service.entity.Employee;
import com.employee.registration.service.entity.Empresponse;
import com.employee.registration.util.EmpRegistrationUtil;

/**
 * @author Vinothkumar P T
 *
 */
@Component
public class EmpServiceImpl implements EmpService{

	  @Autowired
	  private EmployeeRepository repository;
	  
	@Override
	public Empresponse saveEmployee(Employee empObj) {
		// TODO Auto-generated method stub
		Empresponse response = new Empresponse();
		response.setMessage("Employee "+empObj.getName()+" Saved in DB");

		EmployeeTable et = new EmployeeTable();
		et.setName(empObj.getName());
		et.setDepartment(empObj.getDepartment().value());
		et.setEid(empObj.getEid());
		et.setJoiningdate(new Timestamp(empObj.getJoiningdate().toGregorianCalendar().getTimeInMillis()));
		et.setRequestTimeStamp(new Timestamp(System.currentTimeMillis()));
		
		EmployeeTable etableRec = repository.findOne(et.getEid());
		
		if(etableRec != null && !EmpRegistrationUtil.is24hoursElasped(etableRec)){
			throw new EmployeeRegistrationException("Employee already registered within 24 hours", new IllegalArgumentException(""));
		}
		else{
			repository.save(et);
		}
		
		return response;
	}

}
