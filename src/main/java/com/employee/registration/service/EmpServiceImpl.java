/**
 * 
 */
package com.employee.registration.service;

import org.springframework.stereotype.Component;

import com.employee.registration.service.entity.Department;
import com.employee.registration.service.entity.Employee;
import com.employee.registration.service.entity.Empresponse;

/**
 * @author Vinothkumar P T
 *
 */
@Component
public class EmpServiceImpl implements EmpService{

	@Override
	public Empresponse saveEmployee(Employee empObj) {
		// TODO Auto-generated method stub
		Empresponse response = new Empresponse();
		response.setMessage("Employee "+empObj.getName()+" Saved in DB");
	/*	e.setName("Mr.John");
		e.setEmployeeID("1234");
		e.setDepartment(Department.FINANCE);
		e.setJoiningDate(null);*/
		return response;
	}

}
