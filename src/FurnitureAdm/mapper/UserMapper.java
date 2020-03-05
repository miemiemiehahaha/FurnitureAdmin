package FurnitureAdm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import FurnitureAdm.bean.user;


public interface UserMapper {
	
	//��ʾ�б�
	@Select("select * from users limit #{page},#{pageSize}")
	List<user> getuserList(user user, @Param("page")Integer page, @Param("pageSize")Integer pageSize);
	
	@Select("select * from users")
	List<user> getAlluser(user User);
	
	//ȫ��ģ����ѯ
	@Select("select * from users where id like #{query} or userName like #{query} or phone like #{query} or addr like #{query} limit #{page},#{pageSize}")
	List<user> getsearchUserList(user User, @Param("page")Integer page, @Param("pageSize")Integer pageSize, @Param("query")String query);

	@Select("select * from users where id like #{query} or userName like #{query} or phone like #{query} or addr like #{query}")
	List<user> getsearchAlluser(user User, @Param("query")String query);
//
//	//�û�����ѯ
//	@Select("select * from users where userName like #{query} limit #{page},#{pageSize}")
//	List<user> getuserNameUserList(user User,@Param("page")Integer page, @Param("pageSize")Integer pageSize, @Param("query")String query);
//
//	@Select("select * from users where userName like #{query}")
//	List<user> getuserNameAllUser(user User, @Param("query")String query);
//	
//	//�ֻ������ѯ
//	@Select("select * from users where phone like #{query} limit #{page},#{pageSize}")
//	List<user> getPhoneUserList(user User, @Param("page")Integer page, @Param("pageSize")Integer pageSize, @Param("query")String query);
//
//	@Select("select * from users where phone like #{query}")
//	List<user> getPhoneAlluser(user User, @Param("query")String query);
	
	//�õ��û�����
	@Select("select * from users where id=#{id}")
	Map<String, Object> getUserDetail(user User, @Param("id")Integer id);
	
	//�û��޸�
	@Update("update users set userName = #{userName},pwd = #{pwd},addr = #{addr} ,phone = #{phone} where id = #{id}")
	void updateUser(@Param("id")Integer id, @Param("userName")String userName, @Param("pwd")String pwd, @Param("addr")String addr, @Param("phone")String phone);
	
	//�û����
	@Insert("insert into users(userName,pwd,addr,phone,creatDate) values (#{userName},#{pwd},#{addr},#{phone},#{creatDate})")
	void addUser(@Param("userName")String userName, @Param("pwd")String pwd, @Param("addr")String addr, @Param("phone")String phone,@Param("creatDate")String creatDate);

	@Select("select * from users where userName=#{userName}")
	Map<String, Object> sreachUserName(@Param("userName")String userName);

	
	//�û�ɾ��
	@Select("select * from users where id=#{id}")
	Map<String, Object> sreachUser(@Param("id")Integer id);
	
	@Delete("delete from users where id=#{id}")
	void deleteUser(@Param("id")Integer id);
	
	//��ȡ�û���
	@Select("select userName from users where id=#{userID}")
	Map<String, Object> getUserName(@Param("userID")Integer userID);
	
}
