package FurnitureAdm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;
import java.util.List;
import java.util.Map;
import FurnitureAdm.bean.orderItem;


public interface SaleMapper {

	@Select("select goodsID as goodsid,num from orderitem")
	List<Map<String,Object>> getsaleList(orderItem OrderItem);
	
	@Select("select categoryID from goods where id = #{categoryID}")
	List<Map<String,Object>> getCategoryID(@Param("categoryID")Integer categoryID);
	
	@Select("select categoryName from goods join category on goods.categoryID = category.id where id = #{FinalcategoryID} ")
	List<Map<String,Object>> getcategoryName(orderItem OrderItem,@Param("FinalcategoryID")Integer FinalcategoryID);
}
