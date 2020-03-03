package FurnitureAdm.service.category;

import java.util.List;
import java.util.Map;

import FurnitureAdm.bean.Category;

public interface CategoryService {
	//通过分类ID拿分类名
	Map<String,Object> GetCategoryName(int id);
		
	//显示列表
	List<Category> CategoryList(Category category, Integer page, Integer pageSize);
		
	List<Category> AllCategoryList(Category category);
		
	//模糊类别查询
	List<Category> CategoryNameCategoryList(Category category, Integer page, Integer pageSize, String query);

	List<Category> CategoryNameAllCategoryList(Category category, String query);
		
	//类别修改详情
	Map<String, Object> CategoryDetail(Category category, Integer id);
		
	//类别修改
	void UpdateCategory(Integer id, String categoryName, String remark);
		
	//类别添加
	void AddCategory(String categoryName, String remark);

	Map<String, Object> SearchCategoryName(String categoryName);
		
	//类别删除
	Map<String, Object> SearchCategory(Integer id);

	void DeleteCategory(Integer id);


}
