package FurnitureAdm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import FurnitureAdm.bean.Admin;
import FurnitureAdm.service.admin.AdminService;

@Controller
public class AdminController {
	@Autowired
    @Qualifier("AdminService")
    private AdminService adminService;
	
	
	/**
     * ʵ��/adminLogin����Ա��¼
     */	
	@PostMapping("/adminLogin")
	@ResponseBody  
	public Map<String, Object> adminLogin(String userName, String pwd, HttpSession session) {
//		System.out.println(userName+","+pwd);
		Map<String, Object> admin;
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state,message;	
		try{
			if(userName != null & pwd != null){
				admin = adminService.Login(userName, pwd);
				if(admin != null){
					session.setAttribute("admin", admin);
					Integer id = Integer.parseInt(admin.get("id").toString());
					message = "�ɹ�";
//    				result.put("flag", flag);
    				result.put("message", message);
    				result.put("id", id);
                	code=200;
        			state="success";
        			message="�ɹ�";
        			map.put("code", code);
        			map.put("state", state);	
        			map.put("message", message);
        			map.put("result", result);
//        			System.out.println(JSON.toJSONString(map));
            		return map;
				}else{
//					flag = 0;
    				message = "ʧ��";
//    				result.put("flag", flag);
    				result.put("message", message);
                	code=0;
        			state="fail";
        			message="ʧ��";
        			map.put("code", code);
        			map.put("state", state);	
        			map.put("message", message);
        			map.put("result", result);
//        			System.out.println(map);
            		return map;
				}
			}else{
//				flag = 0;
				message = "ʧ��";
//				result.put("flag", flag);
				result.put("message", message);
            	code=0;
    			state="fail";
    			message="ʧ��";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
//    			System.out.println(map);
        		return map;
			}
			
		}catch(Exception e){
			System.out.println(e);
//        	flag = 0;
			message = "ʧ��";
//			result.put("flag", flag);
			result.put("message", message);
        	code=0;
			state="fail";
			message="ʧ��";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", result);
//			System.out.println(map);
    		return map;
		}
		
	}
	/**
     * ʵ��/loginOut����Ա�˳�
     */		
	@PostMapping("/loginExit")
	@ResponseBody  
	public Map<String, Object> adminExit(Integer id, HttpSession session) {
//		System.out.println(id);
		Map<String, Object> admin;
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state,message;	
		try{
			if(id != 0){
				admin = adminService.Exit(id);
				if(admin != null){
					//����ע�������session
					session.invalidate();
//        			flag = 1;
    				message = "�ɹ�";
//    				result.put("flag", flag);
    				result.put("message", message);
                	code=200;
        			state="success";
        			message="�ɹ�";
        			map.put("code", code);
        			map.put("state", state);	
        			map.put("message", message);
        			map.put("result", result);
//        			System.out.println(JSON.toJSONString(map));
            		return map;
				}else{
					message = "ʧ��";
//    				result.put("flag", flag);
    				result.put("message", message);
                	code=0;
        			state="fail";
        			message="ʧ��";
        			map.put("code", code);
        			map.put("state", state);	
        			map.put("message", message);
        			map.put("result", result);
//        			System.out.println(map);
            		return map;
				}
			}else{
				message = "ʧ��";
//				result.put("flag", flag);
				result.put("message", message);
            	code=0;
    			state="fail";
    			message="ʧ��";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
//    			System.out.println(map);
        		return map;
			}
		}catch(Exception e){
			System.out.println(e);
//        	flag = 0;
			message = "ʧ��";
//			result.put("flag", flag);
			result.put("message", message);
        	code=0;
			state="fail";
			message="ʧ��";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", result);
//			System.out.println(map);
    		return map;
		}
	}
	
	
	
	
}
