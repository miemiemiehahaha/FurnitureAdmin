package FurnitureAdm.service.user;

import java.util.List;
import java.util.Map;



import FurnitureAdm.bean.user;

public interface UserService {
	//显示全部
	List<user> userList(user User,Integer page,Integer pageSize);
	
	List<user> Alluser(user User);
	
	//模糊查询
	List<user> searchUserList(user User, Integer page, Integer pageSize, String query);
	
	List<user> searchAlluser(user User,String query);
	
//	//用户名查询
//	List<user> userNameUserList(user User, Integer page, Integer pageSize, String query);
//	
//	List<user> userNameAlluser(user User,String query);
//	
//	//电话查询
//	List<user> phoneUserList(user User, Integer page, Integer pageSize, String query);
//	
//	List<user> phoneAlluser(user User,String query);
	
	//获取用户 详情
	Map<String,Object> userDetail(user User,Integer id);
	
	//修改用户详情
	void updateuser(Integer id, String userName,String pwd,  String addr, String phone);
	
	//用户添加
	void Insertuser(String userName, String pwd, String addr, String phone,String creatDate);
	Map<String,Object> SearchuserName(String userName); 
	
	//用户删除
	Map<String,Object> Searchuser(Integer id);
	void Deleteuser(Integer id);
	
	//获取用户名
	Map<String,Object> GetUserName(Integer userID);
	
	
	

}
