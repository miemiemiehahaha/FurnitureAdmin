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
import FurnitureAdm.bean.order;
import FurnitureAdm.bean.orderItem;
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
	
	@RequestMapping("/SaleView")
	@ResponseBody
	
	public List<Map<String,Object>> SaleView(orderItem OrderItem,order Order) {

		List<Map<String, Object>> SaleList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> CategoryIDList = new ArrayList<Map<String, Object>>();
//		List<Map<String,Object>> GoodsNameList = new ArrayList<Map<String,Object>>(); 
//		Map<String, Object> CategoryNameList = new HashMap<String, Object>();
		List<Map<String, Object>> OrderList = new ArrayList<Map<String, Object>>();
		List<Map<String,Object>> CategoryNameList = new ArrayList<Map<String,Object>>(); 
		List<Map<String, Object>> FinaList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> AllList = new ArrayList<Map<String, Object>>();
		Map<String, Object> GoodsNameList = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();

		Integer goodsid ;
		Integer FinalcategoryID = null ;
		Integer finall = null;

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
				System.out.println(JSON.toJSONString(AllList));
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
