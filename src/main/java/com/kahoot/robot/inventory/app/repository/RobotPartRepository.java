package com.kahoot.robot.inventory.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kahoot.robot.inventory.app.entity.RobotParts;


public interface RobotPartRepository extends JpaRepository < RobotParts, Long >
{
	

}
