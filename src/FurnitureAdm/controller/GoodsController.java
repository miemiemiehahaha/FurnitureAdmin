package FurnitureAdm.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import FurnitureAdm.service.category.CategoryService;
import FurnitureAdm.bean.goods;
import FurnitureAdm.service.goods.GoodsService;

@Controller
public class GoodsController {

	@Autowired
    @Qualifier("GoodsService")
    private GoodsService goodsService;
	@Autowired
	@Qualifier("CategoryService")
	private CategoryService categoryService;
	/**
     * 返回/goods列表
     */	
	@RequestMapping("/getgoodsList")
	@ResponseBody  
	public Map<String, Object> getgoodsList(goods Goods,Integer page, Integer pageSize, String query) {
		List<goods> goodsList = new ArrayList<goods>();
		List<goods> AllgoodsList = new ArrayList<goods>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code,total;
		String state,message;
		try{
			if(page != 0 & pageSize != 0){
				if(query == ""){
					goodsList = goodsService.GoodsList(Goods, page, pageSize);
					AllgoodsList = goodsService.AllGoodsList(Goods);
				}else{
					goodsList = goodsService.SearchGoodsList(Goods, page, pageSize, query);
					AllgoodsList = goodsService.SearchAllGoodsList(Goods, query);
				}
				total = AllgoodsList.size();
				result.put("total", total);
            	result.put("goodsList", goodsList);
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
            	result.put("goodsList", goodsList);
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
        	result.put("goodsList", goodsList);
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
     * 返回/goods详情
     */	
	@RequestMapping("/getgoodsDetail")
	@ResponseBody  
	public Map<String, Object> getgoodsDetail(goods Goods, Integer id) {
//		System.out.println(id);
		Map<String, Object> goodsDetail = new HashMap<String, Object>();
		Map<String, Object> category;
		Map<String, Object> goodsDetailList = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state,message;		
		try{
			if(id != 0){
				goodsDetail = goodsService.GoodsDetail(Goods, id);
				Integer categoryID = 0;
				categoryID = Integer.parseInt(goodsDetail.get("categoryID").toString());
				category = categoryService.GetCategoryName(categoryID);
				goodsDetailList.putAll(goodsDetail);
				goodsDetailList.putAll(category);
				code=200;
    			state="success";
    			message="成功";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", goodsDetailList);
//    			System.out.println(JSON.toJSONString(map));
        		return map;
			}else{
				code=0;
    			state="fail";
    			message="失败";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", goodsDetailList);
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
			map.put("result", goodsDetailList);
//			System.out.println(map);
    		return map;
		}
	}
	
	
	/**
     * 实现/goods修改功能
     */	
	@RequestMapping("/updateGoods")
	@ResponseBody  
	public Map<String, Object> updateGoods(Integer id, String goodsName, String categoryID, String picInfo, String inPrice, String quantity,  String detail, String size, String color, String remark) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state, message;	
		try{
			if(id != 0){
				goodsService.UpdateGoods(id, goodsName, categoryID, picInfo, inPrice, quantity, detail, size, color, remark);
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
     * 实现/goods添加功能
     */	
	@RequestMapping("/AddGoods")
	@ResponseBody  
	public Map<String, Object> AddGoods(String goodsName, String categoryID, String picInfo,String inPrice, String quantity,  String detail, String size, String color, String remark) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state, message;
		try{
			if(goodsName != null){

				goodsService.AddGoods(goodsName, categoryID, picInfo, inPrice, quantity, detail, size, color, remark);
				message = "成功";
				result.put("message", message);
	            code=200;
	    		state="success";
	    		message="成功";
	    		map.put("code", code);
	    		map.put("state", state);	
	    		map.put("message", message);
	    		map.put("result", result);
//	    		System.out.println(JSON.toJSONString(map));
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
//	    			System.out.println(map);
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
     * 实现/goods删除功能
     */	
	@RequestMapping("/DeleteGoods")
	@ResponseBody  
	public Map<String, Object> DeleteGoods(Integer id) {
		Map<String, Object> searchGoods = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state, message;	
		try{
			if(id != 0){
				searchGoods = goodsService.SearchGoods(id);
				if(searchGoods != null){
					goodsService.DeleteGoods(id);
					
				}
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
