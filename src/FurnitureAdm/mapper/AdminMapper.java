package FurnitureAdm.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import FurnitureAdm.bean.Admin;

public interface AdminMapper {
	@Select("select * from admin where userName=#{userName} and pwd=#{pwd}")
	Map<String, Object> adminLogin(@Param("userName")String userName, @Param("pwd")String pwd);

	@Select("select * from admin where id=#{id}")
	Map<String, Object> adminExit(@Param("id")Integer id);

}
