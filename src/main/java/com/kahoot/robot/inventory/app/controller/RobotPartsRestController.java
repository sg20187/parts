package com.kahoot.robot.inventory.app.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kahoot.robot.inventory.app.bean.RobotPartBean;
import com.kahoot.robot.inventory.app.entity.Employee;
import com.kahoot.robot.inventory.app.entity.RobotParts;
import com.kahoot.robot.inventory.app.exception.RobotPartNotFoundException;
import com.kahoot.robot.inventory.app.repository.EmployeeRepository;
import com.kahoot.robot.inventory.app.repository.RobotPartRepository;


@RestController
@RequestMapping(path="/robot/parts")
public class RobotPartsRestController
{

	@Autowired
	private RobotPartRepository robotPartRepository;

	//TODO temp delete
	@Autowired
	private EmployeeRepository empRepo;

/*	@PostMapping 
	public ResponseEntity < Object > add ( @RequestBody RobotParts robotParts )
	{

		RobotParts savedRobot = robotPartRepository.save ( robotParts );

		URI location = ServletUriComponentsBuilder.fromCurrentRequest ( ).path ( "/{id}" )
		        .buildAndExpand ( savedRobot.getSerialNumber ( ) ).toUri ( );

		return ResponseEntity.created ( location ).build ( );

	}*/

	@PostMapping 
	public ResponseEntity < Object > add ( @RequestBody RobotPartBean partBean )
	{
		RobotParts robotParts = new RobotParts();
		robotParts.setName(partBean.getName());
		robotParts.setWeight(partBean.getWeight());
		robotParts.setManufacturer(partBean.getManufacturer());
		Set < RobotParts > compatibleParts = new HashSet<>();
		for(Long partId : partBean.getCompatibleParts()){
			
			Optional < RobotParts > robotOptional = robotPartRepository.findById ( partId );
			if ( robotOptional.isPresent ( ) ){
				RobotParts part = robotOptional.get();
				compatibleParts.add(part);
			}
		}
		robotParts.setCompatibleParts(compatibleParts);
		
		RobotParts savedRobot = robotPartRepository.save( robotParts );

		URI location = ServletUriComponentsBuilder.fromCurrentRequest ( ).path ( "/{id}" )
		        .buildAndExpand ( savedRobot.getSerialNumber ( ) ).toUri ( );

		return ResponseEntity.created ( location ).build ( );

	}
	
/*	@PutMapping ( "/{id}" )
	public ResponseEntity < Object > update ( @RequestBody RobotParts robotParts, @PathVariable long id )
	{

		Optional < RobotParts > robotOptional = robotPartRepository.findById ( id );

		if ( !robotOptional.isPresent ( ) )
			return ResponseEntity.notFound ( ).build ( );

		robotParts.setSerialNumber ( id );

		robotPartRepository.save ( robotParts );

		return ResponseEntity.ok().build ( );
	}*/

	
	@PutMapping 
	public ResponseEntity < Object > update ( @RequestBody RobotPartBean partBean )
	{

		Optional < RobotParts > robotOptional = robotPartRepository.findById ( partBean.getSerialNumber() );

		if ( !robotOptional.isPresent ( ) )
			return ResponseEntity.notFound ( ).build ( );

		RobotParts part = robotOptional.get();
		part.setWeight(partBean.getWeight());
		part.setName(partBean.getName());
		part.setManufacturer(partBean.getManufacturer());
		Set < RobotParts > compatibleParts = new HashSet<>();
		if(null != partBean.getCompatibleParts() && !partBean.getCompatibleParts().isEmpty()){
			Iterator<Long> itr = partBean.getCompatibleParts().iterator();
			while(itr.hasNext()){
				Optional < RobotParts > partOptional = robotPartRepository.findById ( itr.next() );
				if ( partOptional.isPresent ( ) ){
					compatibleParts.add(partOptional.get());
				}
			}
		}	
		part.setCompatibleParts(compatibleParts);
		
		robotPartRepository.save ( part );

		return ResponseEntity.ok().build ( );
	}
	
/*	@GetMapping ( "/{serialNumber}" )
	public RobotParts read ( @PathVariable long serialNumber )
	{

		Optional < RobotParts > RobotParts = robotPartRepository.findById ( serialNumber );

		if ( !RobotParts.isPresent ( ) )
		{
			throw new RobotPartNotFoundException ( "Serial Number: " + serialNumber );
		}
		return RobotParts.get ( );

	}*/

	@GetMapping ( "/{serialNumber}" )
	public RobotPartBean read ( @PathVariable long serialNumber )
	{
		Optional < RobotParts > RobotParts = robotPartRepository.findById ( serialNumber );

		if ( !RobotParts.isPresent ( ) )
		{
			throw new RobotPartNotFoundException ( "Serial Number: " + serialNumber );
		}
		RobotPartBean bean = new RobotPartBean();
		RobotParts part = RobotParts.get ( );
		bean.setSerialNumber(part.getSerialNumber());
		bean.setName(part.getName());
		bean.setManufacturer(part.getManufacturer());
		bean.setWeight(part.getWeight());
		List<Long> compatibleParts = new ArrayList<>();
		Iterator<RobotParts> itr = part.getCompatibleParts().iterator();
		while(itr.hasNext()){
			compatibleParts.add(itr.next().getSerialNumber());
		}
		bean.setCompatibleParts(compatibleParts);
		
		List<Long> partsCompatibleWith = new ArrayList<>();
		Iterator<RobotParts> itr1 = part.getPartsCompatibleWith().iterator();
		while(itr1.hasNext()){
			partsCompatibleWith.add(itr1.next().getSerialNumber());
		}
		bean.setPartsCompatibleWith(partsCompatibleWith);
		return bean;
	}	
	
/*	@GetMapping 
	public List < RobotParts > listAll ()
	{

		return robotPartRepository.findAll ( );
	}*/
	
	@GetMapping 
	public List < RobotPartBean > listAll ()
	{

		List < RobotParts > robotParts =  robotPartRepository.findAll ( );
		List < RobotPartBean > partsList = new ArrayList<>();
		for(RobotParts part : robotParts){
			RobotPartBean bean = new RobotPartBean();
			bean.setSerialNumber(part.getSerialNumber());
			bean.setName(part.getName());
			bean.setManufacturer(part.getManufacturer());
			bean.setWeight(part.getWeight());
			List<Long> compatibleParts = new ArrayList<>();
			Iterator<RobotParts> itr = part.getCompatibleParts().iterator();
			while(itr.hasNext()){
				compatibleParts.add(itr.next().getSerialNumber());
			}
			bean.setCompatibleParts(compatibleParts);
			
			List<Long> partsCompatibleWith = new ArrayList<>();
			Iterator<RobotParts> itr1 = part.getPartsCompatibleWith().iterator();
			while(itr1.hasNext()){
				partsCompatibleWith.add(itr1.next().getSerialNumber());
			}
			bean.setPartsCompatibleWith(partsCompatibleWith);
			
			partsList.add(bean);
		}
		return partsList;
	}

	@DeleteMapping ( "/{serialNumber}" )
	public ResponseEntity < Object > deleteStudent ( @PathVariable long serialNumber )
	{

		robotPartRepository.deleteById ( serialNumber );
		return ResponseEntity.ok().build ( );
	}

	@GetMapping ( "/unique" )
	public List < RobotParts > listUnique ()
	{

		// TO DO
		return null;
	}
	
	@GetMapping ( "/temp" )
	public void temp ()
	{
		Employee employee1 = new Employee("Sergey", "Brin");
        Employee employee2 = new Employee("Larry", "Page");
        Employee employee3 = new Employee("Marrisa", "Mayer");
        Employee employee4 = new Employee("Matt", "Cutts");

        employee1.getColleagues().add(employee3);
        employee1.getColleagues().add(employee4);
        employee2.getColleagues().add(employee4);
        employee3.getColleagues().add(employee4);
        employee4.getColleagues().add(employee1);
        employee4.getColleagues().add(employee3);

		empRepo.save (employee1 );
		empRepo.save (employee2 );
		empRepo.save (employee3 );
		empRepo.save (employee4 );
	}

	@GetMapping ("/tempget")
	public List < Employee > tempget ()
	{

		return empRepo.findAll ( );
	}
	
	@GetMapping ("/tempget/{id}")
	public void tempgetid ( @PathVariable long id)
	{

		Optional < Employee > empl = empRepo.findById ( id );
		if ( empl.isPresent ( ) ){
			Employee emp = empl.get();
			System.out.println(emp.getFirstname());
			System.out.println("colleagues: ");
			Set<Employee> empSet = emp.getColleagues();
			empSet.stream().forEach((e) -> System.out.println(e.getFirstname()));
			System.out.println("teammates: ");
			Set<Employee> teamSet = emp.getTeammates();
			teamSet.stream().forEach((e) -> System.out.println(e.getFirstname()));	
		}else{
			System.out.println("not found *******");
		}

	}
}
