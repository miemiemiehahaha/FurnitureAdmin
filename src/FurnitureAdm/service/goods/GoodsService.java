package FurnitureAdm.service.goods;

import java.util.List; 
import java.util.Map;

import FurnitureAdm.bean.goods;

public interface GoodsService {
	
	//��ʾȫ���б�
	List<goods> GoodsList(goods Goods,Integer page,Integer pageSize);
	
	List<goods> AllGoodsList(goods Goods);
	
	//��Ʒ����colorģ����ѯ
	List<goods> SearchGoodsList(goods Goods,Integer page,Integer pageSize,String query);
	
	List<goods> SearchAllGoodsList(goods Goods , String query);
	
	//��Ʒ����
	Map<String,Object> GoodsDetail(goods Goods,Integer id);
	
	//��Ʒ�޸�
	void UpdateGoods(Integer id,String goodsName,String categoryID,String picInfo,String inPrice,String quantity,String detail ,String size,String color,String remark);
		
	//��Ʒ���
	void AddGoods(String goodsName,String categoryID,String picInfo,String inPrice,String quantity,String detail ,String size,String color,String remark);
	
	Map<String, Object> SearchGoodwithname(String goodsName);
	
	//��Ʒɾ��
	Map<String,Object> SearchGoods(Integer id);
	
	void DeleteGoods(Integer id);

}
