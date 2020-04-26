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
	public List<Map<String, Object>> GetcategoryName(
			Integer FinalcategoryID) {
		// TODO Auto-generated method stub
		return SaleMapper.getcategoryName( FinalcategoryID);
	}


	@Override
	public List<Map<String, Object>> GetcategoryID(Integer goodsid) {
		// TODO Auto-generated method stub
		return SaleMapper.getCategoryID(goodsid);
	}


	@Override
	public Map<String, Object> GetgoodsName(Integer goodsid) {
		// TODO Auto-generated method stub
		return SaleMapper.getgoodsName(goodsid);
	}





}
