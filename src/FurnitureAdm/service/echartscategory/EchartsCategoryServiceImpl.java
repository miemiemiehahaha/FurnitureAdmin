package FurnitureAdm.service.echartscategory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus.Series;
import org.springframework.stereotype.Service;

import FurnitureAdm.bean.orderItem;
import FurnitureAdm.mapper.EchartsCategoryMapper;

@Service("EchartsCategoryService")
public class EchartsCategoryServiceImpl implements EchartsCategoryService {

	@Autowired
	private EchartsCategoryMapper echartsCategoryMapper;
	
	@Override
	public List<Map<String, Object>> getSaleList(orderItem OrderItem) {
		// TODO Auto-generated method stub
		return echartsCategoryMapper.GetsaleList(OrderItem);
	}

	@Override
	public List<Map<String, Object>> getcategoryName(Integer FinalcategoryID) {
		// TODO Auto-generated method stub
		return echartsCategoryMapper.GetcategoryName(FinalcategoryID);
	}

	@Override
	public List<Map<String, Object>> getcategoryID(Integer goodsid) {
		// TODO Auto-generated method stub
		return echartsCategoryMapper.GetCategoryID(goodsid);
	}

	@Override
	public List<Map<String, Object>> getnum(Integer goodsid) {
		// TODO Auto-generated method stub
		return echartsCategoryMapper.GetNum(goodsid);
	}


}
