package FurnitureAdm.mapper;

import org.apache.ibatis.annotations.Select;
import FurnitureAdm.bean.order;
import java.util.List;
import java.util.Map;

public interface OrderEchartsMapper {
	@Select("select sum(totalNum) as totalNum,orderDate from orderform group by orderDate")
	List<Map<String,Object>> getOrderList(order Order);
}
