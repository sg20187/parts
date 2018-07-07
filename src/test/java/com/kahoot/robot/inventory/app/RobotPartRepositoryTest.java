package com.kahoot.robot.inventory.app;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.kahoot.robot.inventory.app.repository.RobotPartRepository;


@RunWith ( SpringRunner.class )
@DataJpaTest
public class RobotPartRepositoryTest
{

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RobotPartRepository robotPartRepository;


	@Before
	public void setUp () throws Exception
	{

	}

	@After
	public void tearDown () throws Exception
	{

	}

	@Test
	public void test ()
	{

		fail ( "Not yet implemented" ); // TODO
	}

}
