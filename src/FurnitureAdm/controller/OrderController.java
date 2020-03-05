package FurnitureAdm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import FurnitureAdm.bean.GetList;
import FurnitureAdm.bean.GetRandomid;
import FurnitureAdm.bean.order;
import FurnitureAdm.bean.orderItem;
import FurnitureAdm.service.goods.GoodsService;
import FurnitureAdm.service.goods.GoodsServiceImpl;
import FurnitureAdm.service.order.OrderService;
import FurnitureAdm.service.user.UserService;

@Controller
public class OrderController {

	@Autowired
	@Qualifier("OrderService")
	private OrderService orderService;	
	@Autowired
	@Qualifier("UserService")
	private UserService userService;
	@Autowired
	@Qualifier("GoodsService")
	private GoodsService goodsService;
	
	/**
     * ����/order���б�
     */	
	
	 @RequestMapping("/getOrderList")
	 @ResponseBody
	 public Map<String, Object> getOrderList(order Order,  Integer page, Integer pageSize, String query, Integer userId){
//		 System.out.println(query+","+page+","+pageSize+","+query+","+userId);
			List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
			List<order> AllorderList = new ArrayList<order>();
			Map<String, Object> userName;
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			int code,total;
			String state,message;	
			try{
				if(page != 0 & pageSize != 0){
					if(query == ""){
						orderList = orderService.OrderList(Order, page, pageSize);
						AllorderList = orderService.AllOrder(Order);
						
						
					}else{
						orderList = orderService.AllWithOrderList(Order, page, pageSize, query);
						AllorderList = orderService.AllOrderList(Order, query);
					}
					for(int i = 0;i < orderList.size(); i++){
//						String str = "userID";
						Integer userID = 0;
						userID = Integer.parseInt(orderList.get(i).get("userID").toString());
						userName = userService.GetUserName(userID);
						orderList.get(i).putAll(userName);
						}
					total = AllorderList.size();
					result.put("total", total);
	            	result.put("list", orderList);
	            	code=200;  
	    			state="success";
	    			message="�ɹ�";
	    			map.put("code", code);
	    			map.put("state", state);	
	    			map.put("message", message);
	    			map.put("result", result);
//	    			System.out.println(JSON.toJSONString(map));
	        		return map;
					
				}else{
					total=0;
	        		result.put("total", total);
	            	result.put("list", orderList);
	            	code=0;
	    			state="fail";
	    			message="ʧ��";
	    			map.put("code", code);
	    			map.put("state", state);	
	    			map.put("message", message);
	    			map.put("result", result);
//	    			System.out.println(map);
	        		return map;
				}
				
			}catch(Exception e){
				System.out.println(e);
	        	total=0;
	    		result.put("total", total);
	        	result.put("list", orderList);
	        	code=0;
				state="fail";
				message="ʧ��";
				map.put("code", code);
				map.put("state", state);	
				map.put("message", message);
				map.put("result", result);
//				System.out.println(map);
	    		return map;
			}
	 }
	 
	    /**
	     * ����/order����
	     */	
		@RequestMapping("/getOrderDetail")
		@ResponseBody  
		public Map<String, Object> getOrderDetail(order Order, orderItem OrderItem, Integer id) {
//			System.out.println(id);
			Map<String, Object> orderDetail = new HashMap<String, Object>();
			Map<String, Object> userName;
			Map<String, Object> orderDetailList = new HashMap<String, Object>();				
			Map<String, Object> goodsName;
			List<Map<String, Object>> orderItemList = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			int code;
			String state,message;		
			try{
				if(id != 0){
					orderDetail = orderService.OrderDetail(Order, id);
					Integer userID = 0;
					userID = Integer.parseInt(orderDetail.get("userID").toString());
//					System.out.println(userID);
					
					//��ȡ�û���
					userName = userService.GetUserName(userID);
					
					Integer orderFormID = id;
					orderItemList = orderService.OrderItemList(OrderItem, orderFormID);
					//��ȡ��Ʒ���ֲ���ӵ�OrderItemList��
					for(int i = 0 ; i<orderItemList.size(); i++){
						Integer goodsID = 0;
						goodsID = Integer.parseInt(orderItemList.get(i).get("goodsID").toString());
						goodsName = goodsService.GetGoodsName(goodsID);
						orderItemList.get(i).putAll(goodsName);
				
					}
					//���Ӷ��������ж�����ӵ�orderDetailList 
					orderDetailList.putAll(orderDetail); 
					orderDetailList.putAll(userName);
					orderDetailList.put("orderItemList",orderItemList);
					code=200;
	    			state="success";
	    			message="�ɹ�";
	    			map.put("code", code);
	    			map.put("state", state);	
	    			map.put("message", message);
	    			map.put("result", orderDetailList);
//	    			System.out.println(JSON.toJSONString(map));
	        		return map;
				}else{
					code=0;
	    			state="fail";
	    			message="ʧ��";
	    			map.put("code", code);
	    			map.put("state", state);	
	    			map.put("message", message);
	    			map.put("result", orderDetailList);
//	    			System.out.println(map);
	        		return map;
				}
			}catch(Exception e){
				System.out.println(e);
	        	code=0;
				state="fail";
				message="ʧ��";
				map.put("code", code);
				map.put("state", state);	
				map.put("message", message);
				map.put("result", orderDetailList);
//				System.out.println(map);
	    		return map;
			}
		}
}
