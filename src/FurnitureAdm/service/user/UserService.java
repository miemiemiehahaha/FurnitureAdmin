package FurnitureAdm.service.user;

import java.util.List;
import java.util.Map;



import FurnitureAdm.bean.user;

public interface UserService {
	//��ʾȫ��
	List<user> userList(user User,Integer page,Integer pageSize);
	
	List<user> Alluser(user User);
	
	//ģ����ѯ
	List<user> searchUserList(user User, Integer page, Integer pageSize, String query);
	
	List<user> searchAlluser(user User,String query);
	
//	//�û�����ѯ
//	List<user> userNameUserList(user User, Integer page, Integer pageSize, String query);
//	
//	List<user> userNameAlluser(user User,String query);
//	
//	//�绰��ѯ
//	List<user> phoneUserList(user User, Integer page, Integer pageSize, String query);
//	
//	List<user> phoneAlluser(user User,String query);
	
	//��ȡ�û� ����
	Map<String,Object> userDetail(user User,Integer id);
	
	//�޸��û�����
	void updateuser(Integer id, String userName,String pwd,  String addr, String phone);
	
	//�û����
	void Insertuser(String userName, String pwd, String addr, String phone,String creatDate);
	Map<String,Object> SearchuserName(String userName); 
	
	//�û�ɾ��
	Map<String,Object> Searchuser(Integer id);
	void Deleteuser(Integer id);
	
	//��ȡ�û���
	Map<String,Object> GetUserName(Integer userID);
	
	
	

}
