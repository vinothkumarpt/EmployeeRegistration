package com.employee.registration.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.registration.exception.EmployeeRegistrationException;
import com.employee.registration.service.EmpService;
import com.employee.registration.service.entity.Employee;
import com.employee.registration.service.entity.Empresponse;
import com.employee.registration.util.EmpRegistrationUtil;

/**
 * @author Vinothkumar P T
 *
 */

@RestController
@RequestMapping("/employee")
public class EmpRegistController {
	private final  String CLASS_NAME = "EmpRegistController";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpRegistController.class);
	
	@Autowired
	private EmpService employeeService;
	
	
	@RequestMapping(value = "/save", method= RequestMethod.POST, 
			consumes = MediaType.APPLICATION_XML_VALUE,
			produces = MediaType.APPLICATION_XML_VALUE)
	public Empresponse saveEmployee(@RequestBody @Valid final Employee employee)  {
		long startTime = System.currentTimeMillis();
		
		String methodName="saveEmployee";
		LOGGER.debug(EmpRegistrationUtil.getMethodEnterMessage(CLASS_NAME, methodName));  
		
		String inputXml;
		Empresponse response = new Empresponse();
		try {
			inputXml = EmpRegistrationUtil.transformXML(employee);
			Employee empObj = EmpRegistrationUtil.validateEmpRequest(inputXml);
			
			if(EmpRegistrationUtil.isNotNull(empObj)){
				//To-Do save empObj Employee table
				response = employeeService.saveEmployee(empObj);
			}
		} 		
		catch (EmployeeRegistrationException e) {
			response.setMessage(e.getMessage());
			//To-Do save in Error table
		}
		catch(Exception e){
			response.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		LOGGER.debug(EmpRegistrationUtil.getMethodExitMessage(CLASS_NAME, methodName));  
		long endTime = System.currentTimeMillis();
		
		EmpRegistrationUtil.logTimeElapsed(CLASS_NAME, methodName, startTime, endTime);
		return response;
	}

}
