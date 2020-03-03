package FurnitureAdm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import FurnitureAdm.bean.Category;

public interface CategoryMapper {
	//ͨ������ID�÷�����
	@Select("select categoryName from category where id=#{id}")
    Map<String, Object> getCategoryName(@Param("id")int id);
	
	//��ʾ�б�
	@Select("select * from category limit #{page},#{pageSize}")
	List<Category> getCategoryList(Category category, @Param("page")Integer page, @Param("pageSize")Integer pageSize);
	
	@Select("select * from category")
	List<Category> getCategoryAllCategoryList(Category category);
	
	//���ģ�����
	@Select("select * from category where categoryName like #{query} limit #{page},#{pageSize}")
	List<Category> getcategoryNameList(Category category, @Param("page")Integer page, @Param("pageSize")Integer pageSize, @Param("query")String query);

	@Select("select * from category where categoryName like #{query}")
	List<Category> getcategoryNameAllList(Category category, @Param("query")String query);
	
	//��ȡ����޸�����
	@Select("select * from category where id=#{id}")
	Map<String, Object> getCategoryDetail(Category category, @Param("id")Integer id);
	
	//����޸�
	@Update("update category set categoryName=#{categoryName},remark=#{remark} where id = #{id}")
	void updateCategory(@Param("id")Integer id, @Param("categoryName")String categoryName, @Param("remark")String remark);
	
	//������
	@Insert("insert into category(categoryName,parentID,remark) values (#{categoryName},0,#{remark})")
	void addCategory(@Param("categoryName")String categoryName, @Param("remark")String remark);

	@Select("select * from category where categoryName=#{categoryName}")
	Map<String, Object> sreachCategoryName(@Param("categoryName")String categoryName);
	
	//���ɾ�� ����id��ѯ��ɾ��
	@Select("select * from category where id=#{id}")
	Map<String, Object> sreachCategory(@Param("id")Integer id);

	@Delete("delete from category where id=#{id}")
	void deleteCategory(@Param("id")Integer id);
}
