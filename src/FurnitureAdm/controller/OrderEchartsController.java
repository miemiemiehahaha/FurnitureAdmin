package FurnitureAdm.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import FurnitureAdm.bean.order;

import FurnitureAdm.service.orderEcharts.OrderEchartsService;

import com.alibaba.fastjson.JSON;

@Controller
public class OrderEchartsController {

	@Autowired
	@Qualifier("OrderEchartsService")
	private OrderEchartsService orderEchartsService;
	
	@RequestMapping("/orderView")
	@ResponseBody
	
	public List<Map<String,Object>> orderView(order Order) {
		List<Map<String, Object>> OrderList = new ArrayList<Map<String, Object>>();
		try{
			OrderList = orderEchartsService.getorderList(Order);
//			System.out.println(JSON.toJSONString(OrderList));
			return OrderList;
		}catch(Exception e){
			return null;
		}

	}
	
}
