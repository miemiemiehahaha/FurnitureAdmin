package FurnitureAdm.service.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FurnitureAdm.mapper.OrderMapper;

import FurnitureAdm.bean.order;
import FurnitureAdm.bean.orderItem;
import FurnitureAdm.service.order.OrderService;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
    private OrderMapper orderMapper;
	
	@Override
	public List<Map<String, Object>> OrderList(order Order, Integer page,
			Integer pageSize) {
		// TODO Auto-generated method stub
		page=(page-1)*pageSize;
		return orderMapper.getOrderList(Order, page, pageSize);
	}

	@Override
	public List<order> AllOrder(order Order) {
		// TODO Auto-generated method stub
		return orderMapper.getAllOrder(Order);
	}

	@Override
	public List<Map<String, Object>> AllWithOrderList(order Order,
			Integer page, Integer pageSize, String query) {
		// TODO Auto-generated method stub
		page=(page-1)*pageSize;
		query="%"+query+"%";
		return orderMapper.getSearchOrderList(Order, page, pageSize, query);
	}

	@Override
	public List<order> AllOrderList(order Order, String query) {
		// TODO Auto-generated method stub
		query="%"+query+"%";
		return orderMapper.getSearchAllOrder(Order, query);
	}

	@Override
	public Map<String, Object> OrderDetail(order Order, Integer id) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderDetail(Order, id);
	}

	@Override
	public List<Map<String, Object>> OrderItemList(orderItem OrderItem,
			Integer orderFormID) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderItemList(OrderItem, orderFormID);
	}

}
