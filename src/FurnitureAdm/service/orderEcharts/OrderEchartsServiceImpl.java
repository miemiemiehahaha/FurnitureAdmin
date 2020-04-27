package FurnitureAdm.service.orderEcharts;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus.Series;
import org.springframework.stereotype.Service;
import FurnitureAdm.bean.order;
import FurnitureAdm.mapper.OrderEchartsMapper;

@Service("OrderEchartsService")
public class OrderEchartsServiceImpl implements OrderEchartsService {

	@Autowired
	private OrderEchartsMapper orderEchartsMapper;
	
	@Override
	public List<Map<String, Object>> getorderList(order Order) {
		// TODO Auto-generated method stub
		return orderEchartsMapper.getOrderList(Order);
	}

}
