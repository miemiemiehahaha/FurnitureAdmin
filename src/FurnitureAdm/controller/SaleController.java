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


import FurnitureAdm.bean.Category;
import FurnitureAdm.bean.goods;
import FurnitureAdm.bean.order;
import FurnitureAdm.bean.orderItem;
import FurnitureAdm.service.echartscategory.EchartsCategoryService;
import FurnitureAdm.service.orderEcharts.OrderEchartsService;
import FurnitureAdm.service.sale.SaleService;

import com.alibaba.fastjson.JSON;



@Controller
public class SaleController {
	
	@Autowired
	@Qualifier("SaleService")
	private SaleService saleService;
	
	@Autowired
	@Qualifier("OrderEchartsService")
	private OrderEchartsService orderEchartsService;
	
	@Autowired
	@Qualifier("EchartsCategoryService")
	private EchartsCategoryService echartsCategoryService;
	
	@RequestMapping("/SaleView")
	@ResponseBody
	
	public List<Map<String,Object>> SaleView(orderItem OrderItem,order Order) {

		List<Map<String, Object>> SaleList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> CategoryIDList = new ArrayList<Map<String, Object>>();
		List<Map<String,Object>> List = new ArrayList<Map<String,Object>>(); 
//		Map<String, Object> CategoryNameList = new HashMap<String, Object>();
		List<Map<String, Object>> OrderList = new ArrayList<Map<String, Object>>();
		List<Map<String,Object>> SaleList1 = new ArrayList<Map<String,Object>>(); 
		List<Map<String, Object>> FinaList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> AllList = new ArrayList<Map<String, Object>>();
		Map<String, Object> GoodsNameList = new HashMap<String, Object>();
		List<Map<String,Object>> Category = new ArrayList<Map<String,Object>>(); 
		Map<String, Object> CategoryNameList = new HashMap<String, Object>();
		Integer goodsid ;
		Integer FinalcategoryID = null ;
		Integer finall = null;
	
		String num;
		Integer categoryID;
		int code;
		String state, message;
	

		try{
		
			SaleList = saleService.SaleList(OrderItem);
			OrderList = orderEchartsService.getorderList(Order);
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
				AllList.addAll(OrderList);
				SaleList1 = echartsCategoryService.getSaleList(OrderItem);
//				System.out.println(JSON.toJSONString(SaleList));
			
					for(int i=0;i<SaleList1.size();i++){
						goodsid = Integer.parseInt(SaleList1.get(i).get("goodsid").toString());
						num = SaleList1.get(i).get("num").toString();
						int idx = num.lastIndexOf(".");
						String strNum = num.substring(0,idx);
						int Newnum = Integer.valueOf(strNum);
//						System.out.println(Newnum);
						CategoryIDList = echartsCategoryService.getcategoryID(goodsid);

						for(int j = 0;j<CategoryIDList.size();j++){
						
							FinalcategoryID = Integer.parseInt(CategoryIDList.get(j).get("categoryID").toString());
//							System.out.println(FinalcategoryID);
							Category = echartsCategoryService.getcategoryName(FinalcategoryID);
							for(int k =0;k<Category.size();k++){
								String categoryname = Category.get(k).get("categoryName").toString();
								CategoryNameList.put("categoryName", categoryname);
							}
							
						
							CategoryNameList.put("newnum", Newnum);
//							System.out.println(JSON.toJSONString(CategoryNameList));
							CategoryIDList.get(j).putAll(CategoryNameList);

						}

						List.addAll(CategoryIDList);
//						System.out.println(JSON.toJSONString(AllList));
						List.get(i).remove("categoryID");
						System.out.println(JSON.toJSONString(List));
					}
					
					Map<String, Map<String, Object>> result = new HashMap<String, Map<String,Object>>();
			 
			        for(Map<String, Object> key : List){
			            String categoryName = key.get("categoryName").toString();
//			            System.out.println(categoryName);
//			            String cID = key.get("categoryID").toString();
//			            System.out.println(cID);
//			            String newId  = categoryName+cID;
			            Long newnum = Long.parseLong(key.get("newnum").toString());
//			            System.out.println(newnum);
			            if(result.containsKey(categoryName)){
			                Long temp = Long.parseLong(result.get(categoryName).get("newnum").toString());
//			                System.out.println(temp);
			                newnum += temp;
//			                System.out.println(newnum);
			                result.get(categoryName).put("newnum", newnum);
//			                System.out.println(JSON.toJSONString(result));
			                continue;
			            }
			            result.put(categoryName, key);
			            FinaList.add(key);
//			            System.out.println(JSON.toJSONString(FinaList));
			        }
			        AllList.addAll(FinaList);
//				System.out.println(JSON.toJSONString(AllList));
				return AllList;
			}else{

				return null;
			}

	
		}catch(Exception e){
//			System.out.println(e);
//			flag = 0;
//			code = 0;
//			message = "Ê§°Ü";
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
