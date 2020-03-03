package FurnitureAdm.service.order;

import java.util.List;
import java.util.Map;

import FurnitureAdm.bean.order;
import FurnitureAdm.bean.orderItem;

public interface OrderService {
	
	//��ʾ�б�
	List<Map<String, Object>> OrderList(order Order, Integer page, Integer pageSize);

	List<order> AllOrder(order Order);
	
	//�û����������Ų�ѯ
	List<Map<String, Object>> AllWithOrderList(order Order, Integer page, Integer pageSize, String query);
	
	List<order> AllOrderList(order Order, String query);
	
//	//�û�����ѯ
//	List<Map<String,Object>> UserNameOrderList(order Order,Integer page,Integer pageSize,String query);
//	
//	List<order> UserNameAllOrder(order Order,String query);
//	
//	//�����Ų�ѯ
//	List<Map<String, Object>> OrderCodeOrderList(order Order, Integer page, Integer pageSize, String query);
//
//	List<order> OrderCodeAllOrder(order Order, String query);
	
	//��������
	Map<String, Object> OrderDetail(order Order, Integer id);
	
	//���������б�
	List<Map<String, Object>> OrderItemList(orderItem OrderItem, Integer orderFormID);
	
	

}
