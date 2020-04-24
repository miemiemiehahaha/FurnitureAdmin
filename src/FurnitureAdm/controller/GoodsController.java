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
     * 锟斤拷锟斤拷/goods锟叫憋拷
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
    			message="失锟斤拷";
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
			message="失锟斤拷";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", result);
//			System.out.println(map);
    		return map;
		}
	}
	
	/**
     * 锟斤拷锟斤拷/goods锟斤拷锟斤拷
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
    			message="锟缴癸拷";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", goodsDetailList);
//    			System.out.println(JSON.toJSONString(map));
        		return map;
			}else{
				code=0;
    			state="fail";
    			message="失锟斤拷";
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
			message="失锟斤拷";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", goodsDetailList);
//			System.out.println(map);
    		return map;
		}
	}
	
	
	/**
     * 实锟斤拷/goods锟睫改癸拷锟斤拷
     */	
	@RequestMapping("/updateGoods")
	@ResponseBody  
	public Map<String, Object> updateGoods(Integer goodsid, String goodsName, String categoryID, String picInfo, String inPrice, String quantity,  String detail, String goodsize, String color, String remark) {
//		System.out.println(goodsid+","+goodsName+","+categoryID+","+picInfo+","+inPrice+","+quantity+","+detail+","+size+","+color);
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state, message;	
		try{

			if(goodsid != 0){
				Integer id = goodsid;
//				System.out.println(getType(id));
				goodsService.UpdateGoods(id, goodsName, categoryID, picInfo, inPrice, quantity, detail, goodsize,color, remark);
				message = "成功";

				result.put("message", message);
            	code=200;
    			state="success";
    			message="锟缴癸拷";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
    			System.out.println(JSON.toJSONString(map));
        		return map;
			}else{
				message = "失锟斤拷";
				result.put("message", message);
            	code=0;
    			state="fail";
    			message="失锟斤拷";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
    			System.out.println(map);
        		return map;
				}
		}catch(Exception e){
			System.out.println(e);
			message = "失锟斤拷";
			result.put("message", message);
        	code=0;
			state="fail";
			message="失锟斤拷";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", result);
			System.out.println(map);
    		return map;
		}
	}

	/**
     * 实锟斤拷/goods锟斤拷庸锟斤拷锟�
     */	
	@RequestMapping("/AddGoods")
	@ResponseBody  
	public Map<String, Object> AddGoods(String goodsName, String categoryID, String picInfo,String inPrice, String quantity,  String detail, String goodsize, String color, String remark) {
//		System.out.println(getType(goodsize));
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		int code;
		String state, message;
		try{
			if(goodsName != null){

				goodsService.AddGoods(goodsName, categoryID, picInfo, inPrice, quantity, detail, goodsize, color, remark);
				message = "成功";

				result.put("message", message);
	            code=200;
	    		state="success";
	    		message="锟缴癸拷";
	    		map.put("code", code);
	    		map.put("state", state);	
	    		map.put("message", message);
	    		map.put("result", result);
//	    		System.out.println(JSON.toJSONString(map));
	        	return map;
				}else{
					message = "失锟斤拷";
					result.put("message", message);
	            	code=0;
	    			state="fail";
	    			message="失锟斤拷";
	    			map.put("code", code);
	    			map.put("state", state);	
	    			map.put("message", message);
	    			map.put("result", result);
//	    			System.out.println(map);
	        		return map;
				}
			
		}catch(Exception e){
			System.out.println(e);
			message = "失锟斤拷";
			result.put("message", message);
        	code=0;
			state="fail";
			message="失锟斤拷";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", result);
//			System.out.println(map);
    		return map;
		}
	}
	/**
     * 实锟斤拷/goods删锟斤拷锟斤拷
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
				message = "锟缴癸拷";
				result.put("message", message);
            	code=200;
    			state="success";
    			message="锟缴癸拷";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
//    			System.out.println(JSON.toJSONString(map));
        		return map;
			}else{
				message = "失锟斤拷";
				result.put("message", message);
            	code=0;
    			state="fail";
    			message="失锟斤拷";
    			map.put("code", code);
    			map.put("state", state);	
    			map.put("message", message);
    			map.put("result", result);
//    			System.out.println(map);
        		return map;
			}
		}catch(Exception e){
			System.out.println(e);
			message = "失锟斤拷";
			result.put("message", message);
        	code=0;
			state="fail";
			message="失锟斤拷";
			map.put("code", code);
			map.put("state", state);	
			map.put("message", message);
			map.put("result", result);
//			System.out.println(map);
    		return map;
		}
	}
}
