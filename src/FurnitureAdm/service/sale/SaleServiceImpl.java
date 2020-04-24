package FurnitureAdm.service.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus.Series;
import org.springframework.stereotype.Service;

import FurnitureAdm.bean.orderItem;
import FurnitureAdm.mapper.SaleMapper;


@Service("SaleService")
public class SaleServiceImpl implements SaleService  {

	@Autowired
    private SaleMapper SaleMapper;
	
	
	@Override
	public List<Map<String, Object>> SaleList(orderItem OrderItem) {
		// TODO Auto-generated method stub
		return SaleMapper.getsaleList(OrderItem);
	}


	@Override
	public List<Map<String, Object>> GetcategoryName(orderItem OrderItem,
			Integer FinalcategoryID) {
		// TODO Auto-generated method stub
		return SaleMapper.getcategoryName(OrderItem, FinalcategoryID);
	}


	@Override
	public List<Map<String, Object>> GetcategoryID(Integer categoryID) {
		// TODO Auto-generated method stub
		return SaleMapper.getCategoryID(categoryID);
	}





}
