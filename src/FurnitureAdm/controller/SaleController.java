package FurnitureAdm.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus.Series;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import FurnitureAdm.bean.goods;
import FurnitureAdm.bean.orderItem;
import FurnitureAdm.service.sale.SaleService;

import com.alibaba.fastjson.JSON;



@Controller
public class SaleController {
	
	@Autowired
	@Qualifier("SaleService")
	private SaleService saleService;
	
	@RequestMapping("/SaleView")
	@ResponseBody
	
	public Map<String,Object> SaleView(orderItem OrderItem ,HttpServletRequest req) {
//		List<orderItem> SaleList = new ArrayList<orderItem>();
//		Map<String, Object> SaleList = new HashMap<String, Object>();
		List<Map<String, Object>> SaleList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> CategoryIDList = new ArrayList<Map<String, Object>>();
		List<Map<String,Object>> CategoryName = new ArrayList<Map<String,Object>>(); 
		List<Map<String,Object>> CategoryNameList = new ArrayList<Map<String,Object>>(); 
		List<Map<String, Object>> FinaList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> AllList = new ArrayList<Map<String, Object>>();
//		ArrayList<String> FinaList = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		Integer querygoodsID,categoryID ;
		int code,flag;
		String state,message;
		
		
		SaleList = saleService.SaleList(OrderItem);
		System.out.println(JSON.toJSONString(SaleList));
//		for(int i = 0; i<SaleList.size();i++){
//			querygoodsID = Integer.parseInt(SaleList.get(i).get("goodsID").toString());
//			CategoryIDList = saleService.GetcategoryID(OrderItem, querygoodsID);
//		}
//		System.out.println(JSON.toJSONString(CategoryIDList));
		
//		for(int i = 0; i <CategoryIDList.size();i++){
//			categoryID = Integer.parseInt(CategoryIDList.get(i).get("categoryID").toString());
//			CategoryNameList = saleService.GetcategoryName(OrderItem, categoryID);
//		}
//		
		for (int i =0;i<SaleList.size();i++){
			String goodsid = "goodsid";
			String goodsNum = "num";
			int goodsID = 0;
			int num = 0;
			for(Entry<String,Object> key: SaleList.get(i).entrySet() ){
//				System.out.println(key);
				if(goodsid.equals(key.getKey())){
					goodsID = Integer.parseInt((String) key.getValue());
					System.out.println(goodsID);
				}
				if(goodsNum.equals(key.getKey())){
					num = Integer.parseInt((String) key.getValue());
				 
				}
						
				for(int j = i+1;j<SaleList.size();j++){
					String eualid = "goodsid";
					String eualNUM = "num";
					int equalID = 0;
					int eualnum = 0;
					for(Entry<String,Object> key1: SaleList.get(j).entrySet()){
	//					System.out.println(key1);
						if(eualid.equals(key1.getKey())){
							equalID = Integer.parseInt((String) key1.getValue());
							
						}
						if(eualNUM.equals(key1.getKey())){
							eualnum = Integer.parseInt((String) key1.getValue());
							 System.out.println(equalID);
						}
					}
					
					if(goodsID == equalID){
//						FinaList.put("商品ID", equalID);
						num += eualnum;
//						FinaList.put("数量", num);
					}
					
					//遍历完成后，要删除User的name相同的数据
					SaleList.remove(SaleList.get(j));
					
					//remove一个元素时，要把遍历的指针减一
					j--;
			
				}
				
			}	
			FinaList.addAll(SaleList);
			
		}

		for(int k = 0 ; k<FinaList.size();k++){
			String categoryid = "goodsid";
			Integer FinalcategoryID;
			for(Entry<String,Object> key2: FinaList.get(k).entrySet()){
				if(categoryid.equals(key2.getKey())){
					categoryID = Integer.parseInt((String) key2.getValue());
					CategoryIDList = saleService.GetcategoryID(categoryID);
					for(int h = 0;h<CategoryIDList.size();h++){
						FinalcategoryID = Integer.parseInt(CategoryIDList.get(h).get("categoryID").toString());
						CategoryNameList = saleService.GetcategoryName(OrderItem, FinalcategoryID);
					}
//					FinaList.addAll(CategoryNameList);

				}
				
			}
		}
		AllList.addAll(CategoryNameList);
		AllList.addAll(FinaList);
		
		System.out.println(JSON.toJSONString(AllList));
		flag = 1;
		code = 200;
		message = "成功";
		state="success";
		map.put("图标", AllList);
		map.put("code", code);
		map.put("state", state);	
		map.put("message", message);
		map.put("flag", "flag");
//		req.setAttribute("图表", FinaList);
		return map;
		
	}
}
