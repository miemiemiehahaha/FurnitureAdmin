package FurnitureAdm.service.category;

import java.util.List;
import java.util.Map;

import FurnitureAdm.bean.Category;

public interface CategoryService {
	//ͨ������ID�÷�����
	Map<String,Object> GetCategoryName(int id);
		
	//��ʾ�б�
	List<Category> CategoryList(Category category, Integer page, Integer pageSize);
		
	List<Category> AllCategoryList(Category category);
		
	//ģ������ѯ
	List<Category> CategoryNameCategoryList(Category category, Integer page, Integer pageSize, String query);

	List<Category> CategoryNameAllCategoryList(Category category, String query);
		
	//����޸�����
	Map<String, Object> CategoryDetail(Category category, Integer id);
		
	//����޸�
	void UpdateCategory(Integer id, String categoryName, String remark);
		
	//������
	void AddCategory(String categoryName, String remark);

	Map<String, Object> SearchCategoryName(String categoryName);
		
	//���ɾ��
	Map<String, Object> SearchCategory(Integer id);

	void DeleteCategory(Integer id);


}
