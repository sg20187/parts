package com.kahoot.robot.inventory.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RobotPartsWebController
{
	@GetMapping ( value = "/" )
	public String homepage ()
	{

		return "index";
	}

}
