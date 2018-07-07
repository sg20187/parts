package com.kahoot.robot.inventory.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kahoot.robot.inventory.app.entity.Employee;


public interface EmployeeRepository extends JpaRepository < Employee, Long >
{
	

}
