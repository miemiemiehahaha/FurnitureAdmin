package FurnitureAdm.service.admin;

import java.util.Map;
import FurnitureAdm.bean.Admin;

public interface AdminService {
	//����Ա��¼
	Map<String,Object> Login(String userName , String pwd);
	
	//����Ա�˳�
	Map<String,Object> Exit(Integer id);
}
