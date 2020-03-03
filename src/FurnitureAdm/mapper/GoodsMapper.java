package FurnitureAdm.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import FurnitureAdm.bean.goods;

public interface GoodsMapper {
	
	//��ʾȫ��
	@Select("select * from goods limit #{page},#{pageSize}")
	List<goods> getGoodsList(goods Goods, @Param("page")Integer page, @Param("pageSize")Integer pageSize);
	
	@Select("select * from goods")
	List<goods> getAllGoodsList(goods Goods);
	
	//���ݲ�Ʒ����color��ѯ
	@Select("select * from goods where goodsName like #{query} or color like #{query} limit #{page},#{pageSize}")
	List<goods> getSearchGoodsList(goods Goods, @Param("page")Integer page, @Param("pageSize")Integer pageSize,@Param("query")String query);

	@Select("select * from goods where goodsName like #{query} or color like #{query}")
	List<goods> getSearchAllGoodsList(goods Goods,@Param("query")String query);
	
	//��Ʒ����
	@Select("select * from goods where id=#{id}")
	Map<String, Object> getGoodsDetail(goods Goods, @Param("id")Integer id);
	
	//��Ʒ�޸�
	@Update("update goods set goodsName = #{goodsName},categoryID=#{categoryID},picInfo=#{picInfo},inPrice=#{inPrice},quantity=#{quantity},detail=#{detail},size=#{size},color=#{color} ,remark = #{remark}where id=#{id}")
	void updategoods(@Param("id")Integer id, @Param("goodsName")String goodsName,  @Param("categoryID")String categoryID,@Param("picInfo")String picInfo, @Param("inPrice")String inPrice, @Param("quantity")String quantity, @Param("detail")String detail,@Param("size")String size,@Param("color")String color, @Param("remark")String remark);

	//��Ʒ���
	@Insert("insert into goods(goodsName,categoryID,picInfo,inPrice,quantity,detail,size,color,remark)")
	void addGoods( @Param("goodsName")String goodsName,  @Param("categoryID")String categoryID,@Param("picInfo")String picInfo, @Param("inPrice")String inPrice, @Param("quantity")String quantity, @Param("detail")String detail,@Param("size")String size,@Param("color")String color, @Param("remark")String remark);

	@Select("select * from goods where goodsName=#{goodsName}")
	Map<String, Object> sreachGoodsName(@Param("goodsName")String goodsName);
	
	//��Ʒɾ��
	@Select("select * from goods where id=#{id}")
	Map<String, Object> sreachgoods(@Param("id")Integer id);

	@Delete("delete from goods where id=#{id}")
	void deleteGoods(@Param("id")Integer id);
	
	//��ȡ����
	@Select("select goodsName from goods where id=#{goodsID}")
	Map<String, Object> getgoodskName(@Param("goodsID")Integer goodsID);


}