package FurnitureAdm.service.echartscategory;

import java.util.List;
import java.util.Map;

import FurnitureAdm.bean.orderItem;

public interface EchartsCategoryService {
	//内含goodsID以及num的list列表
	List<Map<String,Object>> getSaleList(orderItem OrderItem);
	
	//获取goods的categoryName
	List<Map<String, Object>> getcategoryName(Integer FinalcategoryID);
	
	//获取categoryID
	List<Map<String, Object>> getcategoryID( Integer goodsid);
	
	//获取categoryID
	List<Map<String, Object>> getnum( Integer goodsid);
	


}
