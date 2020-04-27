package FurnitureAdm.service.sale;

import java.util.List;
import java.util.Map;

import FurnitureAdm.bean.orderItem;
import FurnitureAdm.bean.goods;

public interface SaleService {
	//内含goodsID以及num的list列表
	List<Map<String, Object>> SaleList(orderItem OrderItem);
	
	//获取goods的categoryName
	List<Map<String, Object>> GetcategoryName(Integer FinalcategoryID);
	
	//获取categoryID
	List<Map<String, Object>> GetcategoryID( Integer goodsid);
	//获取goodsName
	Map<String, Object> GetgoodsName( Integer goodsid);
}
