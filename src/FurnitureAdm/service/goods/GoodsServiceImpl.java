package FurnitureAdm.service.goods;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FurnitureAdm.bean.goods;
import FurnitureAdm.mapper.GoodsMapper;

@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
    private GoodsMapper goodsMapper;
	
	@Override
	public List<goods> GoodsList(goods Goods, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		page=(page-1)*pageSize;
		return goodsMapper.getGoodsList(Goods, page, pageSize);
	}

	@Override
	public List<goods> AllGoodsList(goods Goods) {
		// TODO Auto-generated method stub
		return goodsMapper.getAllGoodsList(Goods);
	}

	@Override
	public List<goods> SearchGoodsList(goods Goods, Integer page,
			Integer pageSize, String query) {
		// TODO Auto-generated method stub
		page=(page-1)*pageSize;
		query="%"+query+"%";
		return goodsMapper.getSearchGoodsList(Goods, page, pageSize, query);
	}

	@Override
	public List<goods> SearchAllGoodsList(goods Goods, String query) {
		// TODO Auto-generated method stub
		query="%"+query+"%";
		return goodsMapper.getAllGoodsList(Goods);
	}

	@Override
	public Map<String, Object> GoodsDetail(goods Goods, Integer id) {
		// TODO Auto-generated method stub
		return goodsMapper.getGoodsDetail(Goods, id);
	}



	@Override
	public void AddGoods(String goodsName, String categoryID, String picInfo,
			String inPrice, String quantity, String detail, String size,
			String color, String remark) {
		// TODO Auto-generated method stub
		goodsMapper.addGoods(goodsName, categoryID, picInfo, inPrice, quantity, detail, size, color, remark);
	}

	@Override
	public Map<String, Object> SearchGoodwithname(String goodsName) {
		// TODO Auto-generated method stub
		return goodsMapper.sreachGoodsName(goodsName);
	}

	@Override
	public Map<String, Object> SearchGoods(Integer id) {
		// TODO Auto-generated method stub
		return goodsMapper.sreachgoods(id);
	}

	@Override
	public void DeleteGoods(Integer id) {
		// TODO Auto-generated method stub
		goodsMapper.deleteGoods(id);
	}

	@Override
	public void UpdateGoods(Integer id, String goodsName, String categoryID,
			String picInfo, String inPrice, String quantity, String detail,
			String size, String color, String remark) {
		// TODO Auto-generated method stub
		goodsMapper.updategoods(id, goodsName, categoryID, picInfo, inPrice, quantity, detail, size, color, remark);
	}

}
