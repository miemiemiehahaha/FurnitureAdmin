package FurnitureAdm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import FurnitureAdm.bean.orderItem;

public interface EchartsCategoryMapper {
	@Select("select goodsID as goodsid ,sum(num) as num from orderitem group by goodsID ")
	List<Map<String,Object>> GetsaleList(orderItem OrderItem);
	
	@Select("select categoryName from goods join category on goods.categoryID = category.id where category.id = #{FinalcategoryID}  ")
	List<Map<String, Object>> GetcategoryName(@Param("FinalcategoryID")Integer FinalcategoryID);
	
	@Select("select categoryID from goods where id = #{goodsid}")
	List<Map<String,Object>> GetCategoryID(@Param("goodsid")Integer goodsid);
	
	@Select("select num from orderitem where goodsID = #{goodsid}")
	List<Map<String,Object>> GetNum(@Param("goodsid")Integer goodsid);
	
}
