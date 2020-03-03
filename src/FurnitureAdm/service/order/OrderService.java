package FurnitureAdm.service.order;

import java.util.List;
import java.util.Map;

import FurnitureAdm.bean.order;
import FurnitureAdm.bean.orderItem;

public interface OrderService {
	
	//显示列表
	List<Map<String, Object>> OrderList(order Order, Integer page, Integer pageSize);

	List<order> AllOrder(order Order);
	
	//用户名及订单号查询
	List<Map<String, Object>> AllWithOrderList(order Order, Integer page, Integer pageSize, String query);
	
	List<order> AllOrderList(order Order, String query);
	
//	//用户名查询
//	List<Map<String,Object>> UserNameOrderList(order Order,Integer page,Integer pageSize,String query);
//	
//	List<order> UserNameAllOrder(order Order,String query);
//	
//	//订单号查询
//	List<Map<String, Object>> OrderCodeOrderList(order Order, Integer page, Integer pageSize, String query);
//
//	List<order> OrderCodeAllOrder(order Order, String query);
	
	//订单详情
	Map<String, Object> OrderDetail(order Order, Integer id);
	
	//订单子项列表
	List<Map<String, Object>> OrderItemList(orderItem OrderItem, Integer orderFormID);
	
	

}
