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

	@RequestMapping("/hexaAdd.do")  //logApply
	public String apply(HttpServletRequest res) {
//http://70.12.114.143/Server/hexaAdd.do?HEXSEQ=1&HEXSTART=0&HEXDECEL=1&HEXSTOP=0&HEXACCEL=0&HEXSAFETYDIS=0&HEXSNOOZE=0&CARID=123B
		String HEXSEQ = res.getParameter("HEXSEQ");
		String HEXSTART = res.getParameter("HEXSTART");
		String HEXDECEL = res.getParameter("HEXDECEL");
		String HEXSTOP = res.getParameter("HEXSTOP");
		String HEXACCEL = res.getParameter("HEXACCEL");
		String HEXSAFETYDIS = res.getParameter("HEXSAFETYDIS");
		String HEXSNOOZE = res.getParameter("HEXSNOOZE");
		String CARID = res.getParameter("CARID");
		System.out.println(HEXSEQ + "," + HEXSTART + "," + HEXDECEL +"," + HEXSTOP + "," + HEXACCEL + "," + HEXSAFETYDIS + "," + HEXSNOOZE + "," + CARID);
		log.debug(HEXSEQ + "," + HEXSTART + "," + HEXDECEL +"," + HEXSTOP + "," + HEXACCEL + "," + HEXSAFETYDIS + "," + HEXSNOOZE + "," + CARID);
		return "hexaAdd";
	}
	
	@RequestMapping("/userAdd.do")
	public String userAdd(HttpServletRequest res, User user) {
		// http://70.12.114.143/Server/userAdd.do?USERID=hhw1990&USERPW=1234&USERPHONE=01093471926&USERBIRTH=900525&USERADDR=seoul&CATE=0
		biz.register(user);
		return "userAdd";
	}
	
}
