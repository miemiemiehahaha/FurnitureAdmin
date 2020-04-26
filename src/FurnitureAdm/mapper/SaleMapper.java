package FurnitureAdm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import java.util.List;
import java.util.Map;
import FurnitureAdm.bean.orderItem;


public interface SaleMapper {

	@Select("select goodsID as goodsid ,sum(num) as num from orderitem group by goodsID order by num desc")
	List<Map<String,Object>> getsaleList(orderItem OrderItem);
	
	@Select("select categoryID from goods where id = #{goodsid}")
	List<Map<String,Object>> getCategoryID(@Param("goodsid")Integer goodsid);
	
	@Select("select categoryName from goods join category on goods.categoryID = category.id where category.id = #{FinalcategoryID}  ")
	List<Map<String,Object>> getcategoryName(@Param("FinalcategoryID")Integer FinalcategoryID);
	
	@Select("select goodsName from goods where id = #{goodsid}")
	Map<String,Object> getgoodsName(@Param("goodsid")Integer goodsid);
}
