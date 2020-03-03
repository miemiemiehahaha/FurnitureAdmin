package FurnitureAdm.service.goods;

import java.util.List; 
import java.util.Map;

import FurnitureAdm.bean.goods;

public interface GoodsService {
	
	//显示全部列表
	List<goods> GoodsList(goods Goods,Integer page,Integer pageSize);
	
	List<goods> AllGoodsList(goods Goods);
	
	//物品名、color模糊查询
	List<goods> SearchGoodsList(goods Goods,Integer page,Integer pageSize,String query);
	
	List<goods> SearchAllGoodsList(goods Goods , String query);
	
	//产品详情
	Map<String,Object> GoodsDetail(goods Goods,Integer id);
	
	//产品修改
	void UpdateGoods(Integer id,String goodsName,String categoryID,String picInfo,String inPrice,String quantity,String detail ,String size,String color,String remark);
		
	//产品添加
	void AddGoods(String goodsName,String categoryID,String picInfo,String inPrice,String quantity,String detail ,String size,String color,String remark);
	
	Map<String, Object> SearchGoodwithname(String goodsName);
	
	//产品删除
	Map<String,Object> SearchGoods(Integer id);
	
	void DeleteGoods(Integer id);

}
