package FurnitureAdm.service.sale;

import java.util.List;
import java.util.Map;

import FurnitureAdm.bean.orderItem;
import FurnitureAdm.bean.goods;

public interface SaleService {
	//�ں�goodsID�Լ�num��list�б�
	List<Map<String, Object>> SaleList(orderItem OrderItem);
	
	//��ȡgoods��categoryName
	List<Map<String, Object>> GetcategoryName(orderItem OrderItem, Integer FinalcategoryID);
	
	//��ȡcategoryID
	List<Map<String, Object>> GetcategoryID( Integer categoryID);
}
