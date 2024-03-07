package com.example.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="employee")

public class Employee {

	@Id
	@Column(name="emp_id")
	private Integer id;
	
	@Column(name="employee_name")
	private String employeeName;

	@Column(name="email", unique = true)
	private String email;

	@Column(name="address")
	private String address;
	@Column(name="salary")
	private int salary;

	@Column(name="blood_group")
	private String bloodGroup;
	@Column(name="reporting_manager")
	private String reportingManager;
	@Column(name="active")
	private Boolean active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	public Employee() {

	}

	public Employee(Integer id,String employeeName, String email, String address, int salary, String bloodGroup,
			String reportingManager, Boolean active) {
		this.id=id;
		this.employeeName = employeeName;
		this.email = email;
		this.address = address;
		this.salary = salary;
		this.bloodGroup = bloodGroup;
		this.reportingManager = reportingManager;
		this.active = active;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer empId) {
		this.id = empId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
