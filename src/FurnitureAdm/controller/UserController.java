package FurnitureAdm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import FurnitureAdm.bean.user;
import FurnitureAdm.service.user.UserService;

@Controller
public class UserController {
	
	@Autowired
    @Qualifier("UserService")
    private UserService userService;
	
	/**
     * 返回/user查询列表
     */	
	@RequestMapping("/getuserList")
	@ResponseBody  
	public Map<String, Object> getuserList(user User, Integer page, Integer pageSize, String query) {
//		System.out.println(queryDetail+","+page+","+pageSize+","+query+","+userId);
		List<user> userList = new ArrayList<user>();
		List<user> AlluserList = new ArrayList<user>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code,total;
		String state,message;	
		try{
			if(page != 0 & pageSize != 0){
				if(query == "" ){
					userList = userService.userList(User, page, pageSize);
					AlluserList = userService.Alluser(User);
//					System.out.println(AlluserList.get(0));
				}else{
					
					userList = userService.searchUserList(User, page, pageSize, query);
					AlluserList = userService.searchAlluser(User, query);
				}
				total = AlluserList.size();
				result.put("total", total);
            	result.put("list", userList);
            	code=200;
    			state="success";
    			message="成功";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
//    			System.out.println(JSON.toJSONString(map));
        		return map;
				
				
				
			}else{
				total=0;
        		result.put("total", total);
            	result.put("list", userList);
            	code=0;
    			state="fail";
    			message="失败";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
//    			System.out.println(map);
        		return map;
			}
			
		}catch(Exception e){
			System.out.println(e);
        	total=0;
    		result.put("total", total);
        	result.put("list", userList);
        	code=0;
			state="fail";
			message="失败";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", result);
//			System.out.println(map);
    		return map;
		}
	}
	/**`
     * 返回/user修改详情
     */
	@RequestMapping("/getuserDetail")
	@ResponseBody  
	public Map<String, Object> getUserDetail(user User, Integer id) {
//		System.out.println(id);
		Map<String, Object> userDetail = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state,message;
		try{
			if(id != 0){
				userDetail = userService.userDetail(User, id);
				code=200;
    			state="success";
    			message="成功";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", userDetail);
//    			System.out.println(JSON.toJSONString(map));
        		return map;
			}else{
				code=0;
    			state="fail";
    			message="失败";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", userDetail);
//    			System.out.println(map);
        		return map;
			}
			
		}catch(Exception e){
			System.out.println(e);
        	code=0;
			state="fail";
			message="失败";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", userDetail);
//			System.out.println(map);
    		return map;
		}
	}
	
	/**
     * 实现/user修改功能
     */	
	@RequestMapping("/userUpdate")
	@ResponseBody  
	public Map<String, Object> updateUser(Integer userId, String userName, String pwd, String addr, String phone) {
//		System.out.println(userId+","+userName+","+pwd+","+addr+","+phone);
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state, message;		
		try{
			if(userId != 0 ){
				Integer id = userId;
				userService.updateuser(id, userName, pwd, addr, phone);
				message = "成功";
				result.put("message", message);
            	code=200;
    			state="success";
    			message="成功";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
//    			System.out.println(JSON.toJSONString(map));
        		return map;
			}else{
				message = "失败";
		
				result.put("message", message);
            	code=0;
    			state="fail";
    			message="失败";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
//    			System.out.println(map);
        		return map;
			}
			
		}catch(Exception e ){
			System.out.println(e);
			message = "失败";
			result.put("message", message);
        	code=0;
			state="fail";
			message="失败";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", result);
//			System.out.println(map);
    		return map;
		}
	}
	
	/**
     * 实现/user添加功能
     */	
	@RequestMapping("/addUser")
	@ResponseBody  
	public Map<String, Object> addUser(String userName, String pwd, String addr, String phone) {
//		System.out.println(userName+","+pwd+","+addr+","+phone);
		Map<String, Object> searchUserName = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state, message;
		Boolean isExist = false;
		try{
			if(userName != null){
				searchUserName = userService.SearchuserName(userName);
//        		System.out.println(searchUserName);
				if(searchUserName == null){
					Date date = new Date();
					SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String creatDate = formatDate.format(date);
					userService.Insertuser(userName, pwd, addr, phone, creatDate);
					message = "成功";
					result.put("message", message);
					result.put("isExist", isExist);
	            	code=200;
	    			state="success";
	    			message="成功";
	    			map.put("code", code);
	    			map.put("state", state);	
	    			map.put("message", message);
	    			map.put("result", result);
//	    			System.out.println(JSON.toJSONString(map));
	        		return map;
				}else{
					isExist = true;
					message = "此用户名已存在！";
					result.put("isExist", isExist);
					result.put("message", message);
	            	code=0;
	    			state="fail";
	    			message="失败";
	    			map.put("code", code);
	    			map.put("state", state);	
	    			map.put("message", message);
	    			map.put("result", result);
//	    			System.out.println(map);
	        		return map;
				}
			}else{
				message = "失败";
				result.put("message", message);
            	code=0;
    			state="fail";
    			message="失败";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
//    			System.out.println(map);
        		return map;
			}
		}catch(Exception e){
			System.out.println(e);
        	
			message = "失败";
	
			result.put("message", message);
        	code=0;
			state="fail";
			message="失败";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", result);
//			System.out.println(map);
    		return map;
		}
		
		
	}
	
	
	
	/**
     * 实现/user删除功能
     */	
	@RequestMapping("/Deleteuser")
	@ResponseBody  
	public Map<String, Object> Deleteuser(Integer id) {
//		Map<String, Object> searchuser = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state, message;	
		try{
			if(id != 0){
				userService.Deleteuser(id);
				message = "成功";
				result.put("message", message);
            	code=200;
    			state="success";
    			message="成功";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
//    			System.out.println(JSON.toJSONString(map));
        		return map;
			}else{
				message = "此用户不存在";
				result.put("message", message);
            	code=0;
    			state="fail";
    			message="失败";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
//    			System.out.println(map);
        		return map;
			}
			
		}catch(Exception e){
			System.out.println(e);
			message = "失败";
			result.put("message", message);
        	code=0;
			state="fail";
			message="失败";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", result);
//			System.out.println(map);
    		return map;
		}
	}
	
	
	
}
