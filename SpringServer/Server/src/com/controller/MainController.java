package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.Biz;
import com.vo.User;

@Controller
public class MainController {

	@Resource(name = "userBiz")
	Biz<User> biz;

	Logger log = Logger.getLogger("sensor");

	@RequestMapping("/main.do")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/hexa.do")
	public String hexa() {
		return "hexa";
	}
	
	@RequestMapping("/hexa3.do")
	public String hexa3() {
		return "hexa3";
	}

	@RequestMapping("/logAdd.do")  //logApply
	public String apply(HttpServletRequest res) {
//http://70.12.114.143/Server/logAdd.do?CARID=1234&ACCEL=0&DECEL=1&SAFETYDIS=0&SNOOZE=0&SPEED=0&BATTERY=0&RPM=3
//http://70.12.114.143/Server/hexaAdd.do?HEXSEQ=1&HEXSTART=0&HEXDECEL=1&HEXSTOP=0&HEXACCEL=0&HEXSAFETYDIS=0&HEXSNOOZE=0&CARID=123B
		
		String CARID = res.getParameter("CARID");
		String ACCEL = res.getParameter("ACCEL");
		String DECEL = res.getParameter("DECEL");
		String SAFETYDIS = res.getParameter("SAFETYDIS");
		String SNOOZE = res.getParameter("SNOOZE");
		String SPEED = res.getParameter("SPEED");
		String BATTERY = res.getParameter("BATTERY");
		
		System.out.println(CARID +"," + ACCEL + "," + DECEL + "," + SAFETYDIS + "," + SNOOZE + "," + SPEED + "," + BATTERY);
		log.debug(CARID +"," + ACCEL + "," + DECEL + "," + SAFETYDIS + "," + SNOOZE + "," + SPEED + "," + BATTERY);
		return "hexaAdd";
	}
	
	@RequestMapping("/userAdd.do")
	public String userAdd(HttpServletRequest res, User user) {
		// http://70.12.114.143/Server/userAdd.do?USERID=hhw1990&USERPW=1234&USERPHONE=01093471926&USERBIRTH=900525&USERADDR=seoul&CATE=0
		biz.register(user);
		return "userAdd";
	}
	
}
