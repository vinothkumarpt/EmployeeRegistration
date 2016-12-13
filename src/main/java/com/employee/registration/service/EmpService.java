/**
 * 
 */
package com.employee.registration.service;

import com.employee.registration.service.entity.Employee;
import com.employee.registration.service.entity.Empresponse;

/**
 * @author Vinothkumar P T
 *
 */
public interface EmpService {
	
	Empresponse saveEmployee(Employee empObj);
}
