package FurnitureAdm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;

import com.google.common.collect.Ordering;

import FurnitureAdm.bean.order;
import FurnitureAdm.bean.orderItem;

public interface OrderMapper {
	
	//显示列表	
	@Select("select * from orderform limit #{page},#{pageSize}")
	List<Map<String, Object>> getOrderList(order Order, @Param("page")Integer page, @Param("pageSize")Integer pageSize);
	
	@Select("select * from orderform")
	List<order> getAllOrder(order Order);
	
	//用户名及订单号查询
	@Select("select * from orderform o join users u on o.userID=u.id where  userName like #{query} or orderCode like #{query} limit #{page},#{pageSize}")
	List<Map<String,Object>> getSearchOrderList(order Order ,@Param("page") Integer page,@Param("pageSize")Integer pageSize, @Param("query")String query);
	
	@Select("select * from orderform o join users u on o.userID=u.id where userName like #{query} or orderCode like #{query}")
	List<order> getSearchAllOrder(order Order, @Param("query")String query);

	//订单详情
	@Select("select * from orderform where id=#{id}")
	Map<String, Object> getOrderDetail(order Order, @Param("id")Integer id);

	//订单子项列表
	@Select("select * from orderitem where orderFormID=#{orderFormID}")
	List<Map<String, Object>> getOrderItemList(orderItem OrderItem, @Param("orderFormID")Integer orderFormID);
}
