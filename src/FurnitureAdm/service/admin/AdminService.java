package FurnitureAdm.service.admin;

import java.util.Map;
import FurnitureAdm.bean.Admin;

public interface AdminService {
	//管理员登录
	Map<String,Object> Login(String userName , String pwd);
	
	//管理员退出
	Map<String,Object> Exit(Integer id);
}
