package com.employee.registration.util;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.employee.registration.dao.entity.EmployeeTable;
import com.employee.registration.exception.EmployeeRegistrationException;
import com.employee.registration.service.entity.Employee;

public class EmpRegistrationUtil {
	
	private final static String CLASS_NAME = "EmpRegistrationUtil";
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpRegistrationUtil.class);
	
	public static Employee validateEmpRequest(String inputEmpString) throws EmployeeRegistrationException {
		String methodName="validateEmpRequest";
		LOGGER.debug(EmpRegistrationUtil.getMethodEnterMessage(CLASS_NAME, methodName));  
		LOGGER.debug(EmpRegistrationUtil.getMethodEnterMessage(CLASS_NAME, methodName));  
		Employee emp = null;

		try {
			SchemaFactory sfac = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			StringReader reader = new StringReader(inputEmpString);
			Schema schema = sfac.newSchema(new File("src/main/resources/employee.xsd"));
			JAXBContext jaxBCon = JAXBContext.newInstance(Employee.class);
			Unmarshaller unMarshaller = jaxBCon.createUnmarshaller();
			unMarshaller.setSchema(schema);
			emp = (Employee)unMarshaller.unmarshal(reader);
		} catch (SAXException e) {
			throw new EmployeeRegistrationException("Malformed Employee XSD, check employee.xsd", e);
		} catch (JAXBException e) {
			
			String customDptMessage = null;
			if(e.getLinkedException().getMessage().contains("One of '{department}' is expected")){
				customDptMessage = "The allowed Depatment values are hr, finance, it, operations. Please check the input";
			}
			else{
				customDptMessage = e.getLinkedException().getMessage();
			}
			
			throw new EmployeeRegistrationException(customDptMessage, e);
		}

		LOGGER.debug(EmpRegistrationUtil.getMethodExitMessage(CLASS_NAME, methodName));  
		return emp;
	}
	
	public static String transformXML(Object inputEmpObj) throws EmployeeRegistrationException {
		String methodName="transformXML";
		LOGGER.debug(EmpRegistrationUtil.getMethodEnterMessage(CLASS_NAME, methodName));  
		String xmlString = null;
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
	        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        StringWriter sw = new StringWriter();
	        jaxbMarshaller.marshal(inputEmpObj, sw);
	        xmlString = sw.toString();
		}
		catch(JAXBException e){
			throw new EmployeeRegistrationException("Invalid Employee XML", e);
		}

		LOGGER.debug(EmpRegistrationUtil.getMethodExitMessage(CLASS_NAME, methodName));  
        return xmlString;
	}
	
	 /*This method was created to log the event method enters*/	
	  public static String getMethodEnterMessage(String className, String methodName){
			StringBuffer message = new StringBuffer(); 
			message.append(className)
			.append(EmpRegistAppConstants.DOT)
			.append(methodName)
			.append(EmpRegistAppConstants.STR_METHOD_STARTS)
			.append(EmpRegistAppConstants.STR_SPACE)
			.append(timeStampNow());
			
			return message.toString();
	  } 
	  
	  /*This method was created to log method exits*/	
	  public static String getMethodExitMessage(String className, String methodName){
			StringBuffer message = new StringBuffer(); 
			message.append(className)
			.append(EmpRegistAppConstants.DOT)
			.append(methodName)
			.append(EmpRegistAppConstants.STR_METHOD_ENDS)
			.append(EmpRegistAppConstants.STR_SPACE)
			.append(timeStampNow());
			
			return message.toString();
	  }
	  
	  public static Boolean isNull(Object obj){
		  if(obj != null){
			  return false;
		  }
		  else{
			  return true;
		  }
	  }
	  
	  public static Boolean isNotNull(Object obj){
		  return !isNull(obj);
	  }
	  
	  public static Timestamp timeStampNow(){
		  return new Timestamp(new Date().getTime());
	  }
	  
	  public static void logTimeElapsed(String className, String methodName, long startTime, long endTime){
		  long totalTime = endTime - startTime;
		  
		  StringBuffer timetaken = new StringBuffer();
		  timetaken.append(className)
		  .append(EmpRegistAppConstants.DOT)
		  .append(methodName)
		  .append(EmpRegistAppConstants.STR_SPACE)
		  .append(EmpRegistAppConstants.TOTAL_TIME_TAKEN)
		  .append(EmpRegistAppConstants.STR_SPACE)
		  .append(totalTime)
		  .append(EmpRegistAppConstants.MILLE_SECOND);
		  LOGGER.debug(timetaken.toString());
	  }
	  
	  public static Boolean is24hoursElasped(EmployeeTable empObj){
		  
		  long registTime = empObj.getRequestTimeStamp().getTime();
		  long currentTime = System.currentTimeMillis();
		  long diff = currentTime - registTime;
		  
		  long elapSeconds = diff/1000;
		  long totalSecIn24Hrs = 86400;
		  if(elapSeconds  > totalSecIn24Hrs)
		   return true;
	
		  else return false;
	  }
}
