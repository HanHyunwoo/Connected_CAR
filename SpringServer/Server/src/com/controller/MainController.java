package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.Biz;
import com.vo.Analyzed;
import com.vo.HexaDate;
import com.vo.Score;
import com.vo.User;

@Controller
public class MainController {

	@Resource(name = "userBiz")
	Biz<User, String, Integer> uBiz;

	@Resource(name = "analyzedBiz")
	Biz<Analyzed, String, Integer> aBiz;

	@Resource(name = "hexaDateBiz")
	Biz<HexaDate, String, Integer> hBiz;

	Logger log = Logger.getLogger("sensor");

	@RequestMapping("/main.do")
	public String main() {

		return "main";
	}

	@RequestMapping("/range.do")
	public void range(HttpServletResponse response, HttpServletRequest request) throws IOException {

		String id = request.getParameter("id");
		List<HexaDate> hd = hBiz.selectRg(id);
		for (HexaDate index : hd)
			System.out.println(index.toString());

	}

	@RequestMapping("/donut.do")
	public void donut(HttpServletResponse response, HttpServletRequest request) throws IOException {

		String text[] = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
		int values[] = { 10, 12, 14, 15, 9, 20, 5 };
		String bgColor[] = { "#FF5656", "#ff9933", "#ffcc00", "#00cc44", "#50ADF5", "#000066", "#660066" };
		

		String id = request.getParameter("id");
		List<HexaDate> result = hBiz.selectId(id);
		// System.out.println(result.get(1).getScore());

		JSONArray jArr = new JSONArray();
		for (int i = 0; i < 7; i++) {
			JSONObject jo = new JSONObject();
			jo.put("backgroundColor", bgColor[i]);
			jo.put("text", text[i]);
			// jo.put("values", result.get(i).getScore());
			JSONArray a = new JSONArray();
			a.add(values[i]);
			jo.put("values", a);
			jArr.add(jo);
		}
		System.out.println("---[dount.do]----------------");
		System.out.println(jArr.toString());
		// System.out.println(jsonArr.toString());
		ServletOutputStream out1 = response.getOutputStream();
		out1.println(jArr.toJSONString());
		out1.close();

	}

	@RequestMapping("/effi1.do")
	public void effi(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("EUC-KR");
		response.setContentType("application/json");

		String id = request.getParameter("id");
		List<HexaDate> resultEffi = hBiz.selectEffi(id);
		JSONArray jArr = new JSONArray();

		for (int i = 0; i < resultEffi.size(); i++) {
			jArr.add(resultEffi.get(i).getDate());
		}
		System.out.println("---[Effi1.do]-----------------");
		System.out.println(jArr.toString());
		ServletOutputStream out2 = response.getOutputStream();
		 
		out2.println(jArr.toJSONString());
		out2.close();
	}


	@RequestMapping("wifi.do")
	public void wifi(HttpServletRequest request, HttpServletResponse response, Model m) throws IOException {
		
		System.out.println("wifi.do");	ServletOutputStream out = response.getOutputStream();
		
		ServletOutputStream wifiOut = response.getOutputStream();
		wifiOut.println("1");
		wifiOut.close();
//		request.setAttribute("state", "1");
//		return "wifiRequest";
	}
	
	
	@RequestMapping("/effi2.do")
	public void effi2(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("EUC-KR");
		response.setContentType("application/json");

		String id = request.getParameter("id");
		String names[] = { id, "STANDARD" };

		List<HexaDate> resultEffi2 = hBiz.selectEffi(id);

		JSONArray jArr = new JSONArray();
		for (int i = 0; i < 2; i++) {
			JSONObject jo = new JSONObject();
			jo.put("name", names[i]);
			JSONArray jArrInner = new JSONArray();
			for (int j = 0; j < resultEffi2.size(); j++) {
				if(i==0)
					jArrInner.add(resultEffi2.get(j).getEfficiency());
				else if(i==1)
					jArrInner.add(60);
			}
			jo.put("data", jArrInner);
			jArr.add(jo);
		}

		System.out.println("---[Effi2.do]-----------------");
		System.out.println(jArr.toString());
		ServletOutputStream out3 = response.getOutputStream();
		 
		out3.println(jArr.toJSONString());
		out3.close();
	}

	
	

	@RequestMapping("/iviEffi1.do")
	public void iviEffi(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("EUC-KR");
		response.setContentType("application/json");

		String id = "1001";
		List<HexaDate> resultEffi = hBiz.selectEffi(id);
		JSONArray jArr = new JSONArray();

		for (int i = 0; i < resultEffi.size(); i++) {
			jArr.add(resultEffi.get(i).getDate());
		}
		System.out.println("---[iviEffi1.do]-----------------");
		System.out.println(jArr.toString());
		ServletOutputStream out4 = response.getOutputStream();
		 
		out4.println(jArr.toJSONString());
		out4.close();
	}
	

	
	@RequestMapping("/iviEffi2.do")
	public void iviEffi2(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("EUC-KR");
		response.setContentType("application/json");

		String id = "1001";
		String names[] = { id, "STANDARD" };

		List<HexaDate> resultEffi2 = hBiz.selectEffi(id);

		JSONArray jArr = new JSONArray();
		for (int i = 0; i < 2; i++) {
			JSONObject jo = new JSONObject();
			jo.put("name", names[i]);
			JSONArray jArrInner = new JSONArray();
			for (int j = 0; j < resultEffi2.size(); j++) {
				if(i==0)
					jArrInner.add(resultEffi2.get(j).getEfficiency());
				else if(i==1)
					jArrInner.add(70);
			}
			jo.put("data", jArrInner);
			jArr.add(jo);
		}

		System.out.println("---[iviEffi2.do]-----------------");
		System.out.println(jArr.toString());
		ServletOutputStream out5 = response.getOutputStream();
		 
		out5.println(jArr.toJSONString());
		out5.close();
	}
	
	
	
	@RequestMapping("/score1.do")
	public void score(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("EUC-KR");
		response.setContentType("application/json");

		System.out.println("[score1]");

		List<Score> scoreResult = hBiz.selectScore("1001");

		JSONArray jArr = new JSONArray();
		for (int i = 0; i < scoreResult.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("name", scoreResult.get(i).getDate());
			jo.put("y", Integer.parseInt(scoreResult.get(i).getScore()));
			jo.put("drilldown", scoreResult.get(i).getDate());
			jArr.add(jo);
		}

		System.out.println(jArr.toString());

		ServletOutputStream out6 = response.getOutputStream();
		 
		out6.println(jArr.toJSONString());

		out6.close();
	}

	@RequestMapping("/score2.do")
	public void score2(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("EUC-KR");
		response.setContentType("application/json");

		String names[] = { "SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT" };
		String colNames[] = { "c_Burst", "c_Deceleration", "c_QuickStart", "c_SuddenStop", "c_SafetyDis", "c_Snooze" };
		int datas[] = { 1, 2, 3, 4, 5, 6 };

		List<HexaDate> scoreResult2 = hBiz.selectScore2("1001");

		/*
		 * [ { "name" : "Firefox", "id" : "Firefox", "data" : [ [ "v58.0", 1.02 ], [
		 * "v57.0", 7.36 ], [ "v56.0", 0.35 ], [ "v55.0", 0.11 ], [ "v54.0", 0.1 ], [
		 * "v52.0", 0.95 ], [ "v51.0", 0.15 ], [ "v50.0", 0.1 ], [ "v48.0", 0.31 ], [
		 * "v47.0", 0.12 ] ] } ]
		 * 
		 * ,c_Burst, c_Deceleration, c_QuickStart, c_SuddenStop, c_SafetyDis, c_Snooze
		 *
		 */ JSONArray jArr = new JSONArray();

		for (int i = 0; i < names.length; i++) {
			JSONObject jo = new JSONObject();
			jo.put("name", scoreResult2.get(i).getDate());
			jo.put("id", scoreResult2.get(i).getDate());
			JSONArray ja = new JSONArray();
			for (int j = 0; j < datas.length; j++) {
				JSONArray ja2 = new JSONArray();
				ja2.add(colNames[j]);
				switch (j) {
				case 0:
					ja2.add(scoreResult2.get(i).getC_Burst());
					break;
				case 1:
					ja2.add(scoreResult2.get(i).getC_Deceleration());
					break;
				case 2:
					ja2.add(scoreResult2.get(i).getC_QuickStart());
					break;
				case 3:
					ja2.add(scoreResult2.get(i).getC_SuddenStop());
					break;
				case 4:
					ja2.add(scoreResult2.get(i).getC_SafetyDis());
					break;
				case 5:
					ja2.add(scoreResult2.get(i).getC_Snooze());
					break;
				}
				ja.add(ja2);
			}
			jo.put("data", ja);
			jArr.add(jo);
		}

		System.out.println(jArr.toString());
		ServletOutputStream out7 = response.getOutputStream();
		 
		out7.println(jArr.toJSONString());
		out7.close();
	}

	@RequestMapping("/dist1.do")
	public void dist1(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("EUC-KR");
		response.setContentType("application/json");
		
		List<HexaDate> distResult = hBiz.selectMaxMin();

		JSONArray jArr = new JSONArray();

		for (int i = 0; i < distResult.size(); i++) {
			JSONArray ja = new JSONArray();
			ja.add(Long.parseLong(distResult.get(i).getDate()));
			ja.add(distResult.get(i).getMin_efficiency());
			ja.add(distResult.get(i).getMax_efficiency());
			jArr.add(ja);
		}
		System.out.println("dist1 : ");
		System.out.println(jArr.toString());
		ServletOutputStream out8 = response.getOutputStream();
		 
		out8.println(jArr.toJSONString());
		out8.close();
	}

	@RequestMapping("/dist2.do")
	public void dist2(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		response.setCharacterEncoding("EUC-KR");
		response.setContentType("application/json");


		String id = request.getParameter("id");
		JSONArray jArr = new JSONArray();
		List<HexaDate> effiResult2 = hBiz.selectDistEffi(id);
		
		for (int i = 0; i < effiResult2.size(); i++) {
			JSONArray ja = new JSONArray();
			ja.add(Long.parseLong(effiResult2.get(i).getDate()));
			ja.add(effiResult2.get(i).getEfficiency());
			jArr.add(ja);
		}
		System.out.println("dist2 : ");
		System.out.println(jArr.toString());
		ServletOutputStream out9 = response.getOutputStream();
		 
		out9.println(jArr.toJSONString());
		out9.close();
	}

	@RequestMapping("/ivihexa.do")
	public String ivihexa(Model m, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("ivihexa.do");
		response.setCharacterEncoding("EUC-KR");
		response.setContentType("application/json");

		String CarId = "1001";
		System.out.println("ivihexa.do] id : " + CarId);
		Analyzed anId = new Analyzed();
		anId.setCarId(CarId);
		HashMap<String, Integer> result = aBiz.selectCnt(anId.getCarId());
		System.out.println(result.toString());
		int a[] = new int[6];
		a[0] = Integer.parseInt(String.valueOf(result.get("C_BURST")));
		a[1] = Integer.parseInt(String.valueOf(result.get("C_QUICK")));
		a[2] = Integer.parseInt(String.valueOf(result.get("C_SUDDEN")));
		a[3] = Integer.parseInt(String.valueOf(result.get("C_DECEL")));
		a[4] = Integer.parseInt(String.valueOf(result.get("C_SNOOZE")));
		a[5] = Integer.parseInt(String.valueOf(result.get("C_SAFETY")));

		System.out.println("C_BURST : " + result.get("C_BURST") + a[0]);

		JSONArray jArr = new JSONArray();
		// SELECT sum(Burst) c_BURST, sum(Deceleration) c_DECEL, sum(QuickStart)
		// c_QUICK, sum(SuddenStop) c_SUDDEN, sum(SafetyDis) c_SAFETY, sum(Snooze)
		// c_SNOOZE FROM ANALYZED WHERE CarId = #{obj}

		for (Integer index : a) {
			jArr.add(80 - index);
		}

		JSONArray jsonArr = new JSONArray();
		jsonArr.add(jArr);

		// System.out.println(jsonArr.toString());
		ServletOutputStream out10 = response.getOutputStream();
		 
		out10.println(jsonArr.toJSONString());
		out10.close();

			return "charts/hexaChart2";
	}
	
	@RequestMapping("/hexa.do")
	public String hexa(Model m, HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("EUC-KR");
		response.setContentType("application/json");

		String CarId = request.getParameter("id");
		System.out.println("hexa.do] id : " + CarId);
		Analyzed anId = new Analyzed();
		anId.setCarId(CarId);
		HashMap<String, Integer> result = aBiz.selectCnt(anId.getCarId());
		System.out.println(result.toString());
		int a[] = new int[6];
		a[0] = Integer.parseInt(String.valueOf(result.get("C_BURST")));
		a[1] = Integer.parseInt(String.valueOf(result.get("C_QUICK")));
		a[2] = Integer.parseInt(String.valueOf(result.get("C_SUDDEN")));
		a[3] = Integer.parseInt(String.valueOf(result.get("C_DECEL")));
		a[4] = Integer.parseInt(String.valueOf(result.get("C_SNOOZE")));
		a[5] = Integer.parseInt(String.valueOf(result.get("C_SAFETY")));

		System.out.println("C_BURST : " + result.get("C_BURST") + a[0]);

		JSONArray jArr = new JSONArray();
		// SELECT sum(Burst) c_BURST, sum(Deceleration) c_DECEL, sum(QuickStart)
		// c_QUICK, sum(SuddenStop) c_SUDDEN, sum(SafetyDis) c_SAFETY, sum(Snooze)
		// c_SNOOZE FROM ANALYZED WHERE CarId = #{obj}

		for (Integer index : a) {
			jArr.add(80 - index);
		}

		JSONArray jsonArr = new JSONArray();
		jsonArr.add(jArr);

		// System.out.println(jsonArr.toString());
		ServletOutputStream out11 = response.getOutputStream();
		 
		out11.println(jsonArr.toJSONString());
		out11.close();

			return "main";
	}

	@RequestMapping("/hexa2.do")
	public void hexa2(Model m, HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("EUC-KR");
		response.setContentType("application/json");

		String CarId = request.getParameter("id");
		System.out.println("hexa.do] id : " + CarId);
		Analyzed anId = new Analyzed();
		anId.setCarId(CarId);
		HashMap<String, Integer> result = aBiz.selectCnt(anId.getCarId());
		System.out.println(result.toString());
		int a[] = new int[6];
		a[0] = Integer.parseInt(String.valueOf(result.get("C_BURST")));
		a[1] = Integer.parseInt(String.valueOf(result.get("C_QUICK")));
		a[2] = Integer.parseInt(String.valueOf(result.get("C_SUDDEN")));
		a[3] = Integer.parseInt(String.valueOf(result.get("C_DECEL")));
		a[4] = Integer.parseInt(String.valueOf(result.get("C_SNOOZE")));
		a[5] = Integer.parseInt(String.valueOf(result.get("C_SAFETY")));

		System.out.println("C_BURST : " + result.get("C_BURST") + a[0]);

		JSONArray jArr = new JSONArray();
		// SELECT sum(Burst) c_BURST, sum(Deceleration) c_DECEL, sum(QuickStart)
		// c_QUICK, sum(SuddenStop) c_SUDDEN, sum(SafetyDis) c_SAFETY, sum(Snooze)
		// c_SNOOZE FROM ANALYZED WHERE CarId = #{obj}

		for (Integer index : a) {
			jArr.add(80 - index);
		}

		JSONArray jsonArr = new JSONArray();
		jsonArr.add(jArr);
		// System.out.println(jsonArr.toString());
		ServletOutputStream out12 = response.getOutputStream();
		 
		out12.println(jsonArr.toJSONString());
		out12.close();

	}
	
	
	

	@RequestMapping("/logAdd.do") // logApply
	public String apply(HttpServletRequest res) {
		// http://70.12.114.143/Server/logAdd.do?CARID=1234&ACCEL=0&DECEL=1&SAFETYDIS=0&SNOOZE=0&SPEED=0&BATTERY=0&RPM=3
		// http://70.12.114.143/Server/hexaAdd.do?HEXSEQ=1&HEXSTART=0&HEXDECEL=1&HEXSTOP=0&HEXACCEL=0&HEXSAFETYDIS=0&HEXSNOOZE=0&CARID=123B

		String CARID = res.getParameter("CARID");
		String ACCEL = res.getParameter("ACCEL");
		String DECEL = res.getParameter("DECEL");
		String SAFETYDIS = res.getParameter("SAFETYDIS");
		String SNOOZE = res.getParameter("SNOOZE");
		String SPEED = res.getParameter("SPEED");
		String BATTERY = res.getParameter("BATTERY");

		// System.out.println(CARID +"," + ACCEL + "," + DECEL + "," + SAFETYDIS + "," +
		// SNOOZE + "," + SPEED + "," + BATTERY);
		log.debug(CARID + "," + ACCEL + "," + DECEL + "," + SAFETYDIS + "," + SNOOZE + "," + SPEED + "," + BATTERY);
		return "hexaAdd";
	}

	@RequestMapping("/userAdd.do")
	public String userAdd(HttpServletRequest res, User user) {
		// http://70.12.114.143/Server/userAdd.do?USERID=hhw1990&USERPW=1234&USERPHONE=01093471926&USERBIRTH=900525&USERADDR=seoul&CATE=0
		uBiz.register(user);
		System.out.println(user.toString());
		return "userAdd";
	}

	@RequestMapping("/analyzedAdd.do")
	public void analyzedAdd(HttpServletRequest res, User user) {
		// http://70.12.114.143/Server/userAdd.do?USERID=hhw1990&USERPW=1234&USERPHONE=01093471926&USERBIRTH=900525&USERADDR=seoul&CATE=0
		HadoopDataToss hdt = new HadoopDataToss();
		List<Analyzed> list = new ArrayList<Analyzed>();
		Analyzed an = new Analyzed();
		System.out.println("üũ1");
		try {
			list = hdt.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("üũ2");
		// System.out.println(list.toString());
		/*
		 * an.setBattery(777); an.setBurst(0); an.setCarId("333");
		 * an.setDateEnd("2018-06-01 08:17:45"); an.setDateStart("2018-06-01 08:17:45");
		 * an.setDeceleration(1); an.setQuickStart(2); an.setSafetyDis(3);
		 * an.setSnooze(3); an.setSuddenStop(2); aBiz.register(an);
		 * System.out.println("Regist OK!!!");
		 */
		System.out.println("üũ3");
		aBiz.registerAll(list);
		System.out.println("üũ4");
	}

}