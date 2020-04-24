package FurnitureAdm.service.sale;

import java.util.List;
import java.util.Map;

import FurnitureAdm.bean.orderItem;
import FurnitureAdm.bean.goods;

public interface SaleService {
	//内含goodsID以及num的list列表
	List<Map<String, Object>> SaleList(orderItem OrderItem);
	
	//获取goods的categoryName
	List<Map<String, Object>> GetcategoryName(orderItem OrderItem, Integer FinalcategoryID);
	
	//获取categoryID
	List<Map<String, Object>> GetcategoryID( Integer categoryID);
}
