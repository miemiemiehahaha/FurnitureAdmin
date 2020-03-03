package FurnitureAdm.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FurnitureAdm.bean.user;
import FurnitureAdm.mapper.UserMapper;
import FurnitureAdm.service.user.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
    private UserMapper userMapper;
	
	//显示全部数据
	@Override
	public List<user> userList(user User, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		page=(page-1)*pageSize;
		return userMapper.getuserList(User, page, pageSize);
	}

	@Override
	public List<user> Alluser(user User) {
		// TODO Auto-generated method stub
		return userMapper.getAlluser(User);
	}
	
	//全部模糊查询
	@Override
	public List<user> searchUserList(user User, Integer page, Integer pageSize,
			String query) {
		// TODO Auto-generated method stub
		page=(page-1)*pageSize;
		query = "%" + query + "%";
		return userMapper.getsearchUserList(User, page, pageSize, query);
	}

	@Override
	public List<user> searchAlluser(user User, String query) {
		// TODO Auto-generated method stub
		query = "%" + query + "%";
		return userMapper.getsearchAlluser(User, query);
	}

//	//用户名模糊查询
//	@Override
//	public List<user> userNameUserList(user User, Integer page,
//			Integer pageSize, String query) {
//		// TODO Auto-generated method stub
//		page=(page-1)*pageSize;
//		query = "%" + query + "%";
//		return userMapper.getuserNameUserList(User, page, pageSize, query);
//	}

//	@Override
//	public List<user> userNameAlluser(user User, String query) {
//		// TODO Auto-generated method stub
//		query = "%" + query + "%";
//		return userMapper.getsearchAlluser(User, query);
//	}

	//电话模糊查询
//	@Override
//	public List<user> phoneUserList(user User, Integer page, Integer pageSize,
//			String query) {
//		// TODO Auto-generated method stub
//		page=(page-1)*pageSize;
//		query = "%" + query + "%";
//		return userMapper.getPhoneUserList(User, page, pageSize, query);
//	}
//
//	@Override
//	public List<user> phoneAlluser(user User, String query) {
//		// TODO Auto-generated method stub
//		query = "%" + query + "%";
//		return userMapper.getPhoneAlluser(User, query);
//	}
	
	//拿到某个用户详情
	@Override
	public Map<String, Object> userDetail(user User, Integer id) {
		// TODO Auto-generated method stub
		return userMapper.getUserDetail(User, id);
	}

	//修改用户详情
	@Override
	public void updateuser(Integer id, String userName, String pwd,
			String addr, String phone) {
		// TODO Auto-generated method stub
		userMapper.updateUser(id, userName, pwd, addr, phone);
	}

	//增加用户
	@Override
	public void Insertuser(String userName, String pwd, String addr,
			String phone , String creatDate) {
		// TODO Auto-generated method stub
		userMapper.addUser(userName, pwd, addr, phone,creatDate);
	}

	@Override
	public Map<String, Object> SearchuserName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.sreachUserName(userName);
	}
	
	//用户删除
	@Override
	public Map<String, Object> Searchuser(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.sreachUser(id);
	}

	@Override
	public void Deleteuser(Integer id) {
		// TODO Auto-generated method stub
		userMapper.deleteUser(id);
	}

	//获取用户名
	@Override
	public Map<String, Object> GetUserName(Integer userID) {
		// TODO Auto-generated method stub
		return userMapper.getUserName(userID);
	}

}
