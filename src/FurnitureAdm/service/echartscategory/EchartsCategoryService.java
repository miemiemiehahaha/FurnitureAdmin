package FurnitureAdm.service.echartscategory;

import java.util.List;
import java.util.Map;

import FurnitureAdm.bean.orderItem;

public interface EchartsCategoryService {
	//�ں�goodsID�Լ�num��list�б�
	List<Map<String,Object>> getSaleList(orderItem OrderItem);
	
	//��ȡgoods��categoryName
	List<Map<String, Object>> getcategoryName(Integer FinalcategoryID);
	
	//��ȡcategoryID
	List<Map<String, Object>> getcategoryID( Integer goodsid);
	
	//��ȡcategoryID
	List<Map<String, Object>> getnum( Integer goodsid);
	


}
