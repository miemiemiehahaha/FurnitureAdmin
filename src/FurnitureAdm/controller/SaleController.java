package FurnitureAdm.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import javassist.bytecode.Descriptor.Iterator;

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
	
	public List<Map<String,Object>> SaleView(orderItem OrderItem) {

		List<Map<String, Object>> SaleList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> CategoryIDList = new ArrayList<Map<String, Object>>();
//		List<Map<String,Object>> GoodsNameList = new ArrayList<Map<String,Object>>(); 
//		Map<String, Object> CategoryNameList = new HashMap<String, Object>();
		List<Map<String,Object>> CategoryNameList = new ArrayList<Map<String,Object>>(); 
		List<Map<String, Object>> FinaList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> AllList = new ArrayList<Map<String, Object>>();
		Map<String, Object> GoodsNameList = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();

		Integer goodsid ;
		Integer FinalcategoryID = null ;
		Integer finall = null;
		int code,flag;
		String state,message;
		try{
		
			SaleList = saleService.SaleList(OrderItem);
//			System.out.println(JSON.toJSONString(SaleList));
			if(SaleList != null){
				for(int i = 0; i <SaleList.size(); i ++){
					goodsid = Integer.parseInt(SaleList.get(i).get("goodsid").toString());
					GoodsNameList = saleService.GetgoodsName(goodsid);
//					SaleList.addAll(CategoryNameList);
					SaleList.get(i).putAll(GoodsNameList);
//					System.out.println(JSON.toJSONString(SaleList));
				}
				AllList.addAll(SaleList);
//				System.out.println(JSON.toJSONString(AllList));

//				flag = 1;
//				code = 200;
//				message = "成功";
//				state="success";
//				map.put("图表", AllList);
//				map.put("code", code);
//				map.put("state", state);	
//				map.put("message", message);
//				map.put("flag", flag);
//		//		req.setAttribute("图表", FinaList);
//		//		System.out.println(JSON.toJSONString(map));
				return AllList;
			}else{

				return null;
			}

			
//			for(int i = 0 ; i<SaleList.size();i++){
//
//				goodsid = Integer.parseInt(SaleList.get(i).get("goodsid").toString());
//				CategoryIDList = saleService.GetcategoryID(goodsid);
////				System.out.println(JSON.toJSONString(CategoryIDList));
//
//				for(int j = 0;j<SaleList.size();j++){
//					FinalcategoryID = Integer.parseInt(CategoryIDList.get(j).get("categoryID").toString());
//
////					Category.add(j, FinalcategoryID);
////					System.out.println(FinalcategoryID);
//					CategoryNameList = saleService.GetcategoryName(FinalcategoryID);
//					
//					for(Entry<String,Object> key: CategoryNameList.get(j).entrySet()){
//						String categoryName = "categoryName";
//						
//						System.out.println(key);
//						
//						if(categoryName.equals(key.getKey())){
//							finall = Integer.parseInt((String) key.getValue());
//							System.out.println(finall);
//							}
//				
//					}
//					
//				}
//				
//				}
//			
//			System.out.println(JSON.toJSONString(CategoryNameList));
//			SaleList.addAll(CategoryNameList);
//			AllList=SaleList;

//			System.out.println(JSON.toJSONString(AllList));
//						System.out.println(JSON.toJSONString(CategoryIDList));
//						String category = "categoryID";
//				
//						for(Entry<String,Object> key1: CategoryIDList.get(j).entrySet()){
//							System.out.println(key1);
//							if(category.equals(key1.getKey())){
//								FinalcategoryID = Integer.parseInt((String) key1.getValue());
//								
//								System.out.println(FinalcategoryID);
//								CategoryNameList = saleService.GetcategoryName(FinalcategoryID);
////								System.out.println(JSON.toJSONString(CategoryNameList));
//							}
//											
//						}
//						
//					}
//					
//				
			
//			for (int i =0;i<SaleList.size();i++){
//				
//				String goodsid = "goodsid";
//				String goodsNum = "num";
//				int goodsID = 0;
//				int num = 0;
//				goodsID  = Integer.parseInt(SaleList.get(i).get("goodsid").toString());
//				num  = Integer.parseInt(SaleList.get(i).get("num").toString());
//
//											
//					for(int j = i+1;j<SaleList.size();j++){
//						String eualid = "goodsid";
//						String eualNUM = "num";
//						int equalID = 0;
//						int eualnum = 0;
//						equalID  = Integer.parseInt(SaleList.get(j).get("goodsid").toString());
//						eualnum  = Integer.parseInt(SaleList.get(j).get("num").toString());
//	//					for(Entry<String,Object> key1: SaleList.get(j).entrySet()){
//	////						System.out.println(key1);
//	//						if(eualid.equals(key1.getKey())){
//	//							equalID = Integer.parseInt((String) key1.getValue());
//	////							System.out.println(equalID);
//	//						}
//	//						if(eualNUM.equals(key1.getKey())){
//	//							eualnum = Integer.parseInt((String) key1.getValue());
//	////							System.out.println(eualnum);
//	//						}	
//	//					}
////						System.out.println(num);
//						if(goodsID == equalID){	
//							
//							num += eualnum;
//		
//	//						System.out.println(num);
//							//遍历完成后，要删除User的name相同的数据
//							SaleList.remove(SaleList.get(j));
//							
//							NumList.put("goodsid", goodsID);
//							NumList.put("num", num);
//							
//							
//							numList.add(NumList);
//							
//							System.out.println(JSON.toJSONString(numList));
//							SaleList.addAll(numList);
//							FinaList.addAll(SaleList);
////							System.out.println(JSON.toJSONString(SaleList));
//							//remove一个元素时，要把遍历的指针减一
//							j--;
//	//						System.out.println(j);
//							}
//			
//							
//					}
//					}

//			
//			for(int k = 0 ; k<FinaList.size();k++){
//				String categoryid = "goodsid";
//				Integer FinalcategoryID;
//				for(Entry<String,Object> key2: FinaList.get(k).entrySet()){
//	//				System.out.println(key2);
//					if(categoryid.equals(key2.getKey())){
//						categoryID = Integer.parseInt((String) key2.getValue());
//	//					System.out.println(categoryID);
//						CategoryIDList = saleService.GetcategoryID(categoryID);
//						for(int h = 0;h<CategoryIDList.size();h++){
//							FinalcategoryID = Integer.parseInt(CategoryIDList.get(h).get("categoryID").toString());
//							CategoryNameList = saleService.GetcategoryName(FinalcategoryID);
//						
//						}
//						
//	//					FinaList.addAll(CategoryNameList);
//	//					System.out.println(JSON.toJSONString(CategoryNameList));
//					}
//					
//				}
//			}
//			AllList.addAll(CategoryNameList);
//			AllList.addAll(FinaList);
	//		System.out.println(JSON.toJSONString(FinaList));
	//		System.out.println(JSON.toJSONString(AllList));
			
		}catch(Exception e){
//			System.out.println(e);
//			flag = 0;
//			code = 0;
//			message = "失败";
//			state="fail";
//			
//			map.put("code", code);
//			map.put("state", state);	
//			map.put("message", message);
//			map.put("flag", flag);
//			System.out.println(JSON.toJSONString(map));
			return null;
		}
	}
	
}
