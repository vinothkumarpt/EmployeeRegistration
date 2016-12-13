package com.employee.registration.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class EmployeeTable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(length = 15)
    private String eid;
	
	@Column(length = 50)
    private String name;
	
    private Timestamp joiningDate;
    
    private Timestamp requestTimeStamp;
    
    /**
	 * @return the requestTimeStamp
	 */
	public Timestamp getRequestTimeStamp() {
		return requestTimeStamp;
	}
	/**
	 * @param requestTimeStamp the requestTimeStamp to set
	 */
	public void setRequestTimeStamp(Timestamp requestTimeStamp) {
		this.requestTimeStamp = requestTimeStamp;
	}
	
    
    @Column(length = 15)
    private String department;
	/**
	 * @return the eid
	 */
	public String getEid() {
		return eid;
	}
	/**
	 * @param eid the eid to set
	 */
	public void setEid(String eid) {
		this.eid = eid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the joiningdate
	 */
	public Timestamp getJoiningdate() {
		return joiningDate;
	}
	/**
	 * @param joiningdate the joiningdate to set
	 */
	public void setJoiningdate(Timestamp joiningdate) {
		this.joiningDate = joiningdate;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
}
