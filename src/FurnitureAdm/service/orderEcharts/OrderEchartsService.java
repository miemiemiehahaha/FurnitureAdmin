package FurnitureAdm.service.orderEcharts;


import java.util.List;
import java.util.Map;
import FurnitureAdm.bean.order;

public interface OrderEchartsService {
	List<Map<String,Object>> getorderList(order Order);
}
