package FurnitureAdm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import FurnitureAdm.bean.Category;
import FurnitureAdm.service.category.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	@Qualifier("CategoryService")
	private CategoryService categoryService;
	
	/**
     * 返回/category的查询列表
     */	
    @RequestMapping("/getcategroyList")
    @ResponseBody
    public Map<String, Object> getCategroyList(Category category, Integer page, Integer pageSize, String query){
		List<Category> categoryList = new ArrayList<Category>();
		List<Category> AllCategoryList = new ArrayList<Category>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code,total;
		String state,message;
		try{
			if(page != 0 & pageSize != 0){
				if(query == ""){
					categoryList = categoryService.CategoryList(category, page, pageSize);
					AllCategoryList = categoryService.AllCategoryList(category);
					
				}else{
					categoryList = categoryService.CategoryNameCategoryList(category, page, pageSize, query);
					AllCategoryList = categoryService.CategoryNameAllCategoryList(category, query);
				}
				total = AllCategoryList.size();
				result.put("total", total);
            	result.put("list", categoryList);
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
            	result.put("list", categoryList);
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
        	result.put("list", categoryList);
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
     * 返回/category拿到详情
     */	
    @RequestMapping("/getCategoryDetail")
	@ResponseBody  
	public Map<String, Object> getCategoryDetail(Category category, Integer id) {
		Map<String, Object> categoryDetail = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state,message;	
		try{
			if( id != 0){
				categoryDetail = categoryService.CategoryDetail(category, id);
				code=200;
    			state="success";
    			message="成功";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", categoryDetail);
//    			System.out.println(JSON.toJSONString(map));
        		return map;
			}else{
				code=0;
    			state="fail";
    			message="失败";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", categoryDetail);
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
			map.put("result", categoryDetail);
//			System.out.println(map);
    		return map;
		}
		
    }
    
	/**
     * 实现/category修改
     */	
    @RequestMapping("/categoryUpdate")
	@ResponseBody  
	public Map<String, Object> updateCategory(Integer id, String categoryName, String remark) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state, message;	
		try{
			if(id != 0){
				categoryService.UpdateCategory(id, categoryName, remark);
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
     * 实现/category添加功能
     */	
	@RequestMapping("/addCategory")
	@ResponseBody  
	
	public Map<String, Object> addCategory(String categoryName, String remark) {
//		System.out.println(categoryName+","+remark);
		Map<String, Object> searchCategoryName = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state, message;	
		try{
			if(categoryName != null){
				searchCategoryName = categoryService.SearchCategoryName(categoryName);
				if(searchCategoryName == null){
					categoryService.AddCategory(categoryName, remark);
					message = "成功";
					result.put("message", message);
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
					message = "此图书类别已存在！";
//					result.put("flag", flag);
					result.put("message", message);
	            	code=200;
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
     * 实现/category删除功能
     */	
	@RequestMapping("/deleteCategory")
	@ResponseBody  
	public Map<String, Object> deleteCategory(Integer id) {
//		System.out.println(id);
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state, message;
		try{
			if(id != 0){
				categoryService.DeleteCategory(id);
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
