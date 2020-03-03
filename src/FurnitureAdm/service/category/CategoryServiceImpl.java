package FurnitureAdm.service.category;

import java.util.List; 
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FurnitureAdm.bean.Category;
import FurnitureAdm.mapper.CategoryMapper;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryMapper categoryMapper;

	@Override
	public Map<String, Object> GetCategoryName(int id) {
		// TODO Auto-generated method stub
		return categoryMapper.getCategoryName(id);
	}

	@Override
	public List<Category> CategoryList(Category category, Integer page,
			Integer pageSize) {
		// TODO Auto-generated method stub
		page=(page-1)*pageSize;
		return categoryMapper.getCategoryList(category, page, pageSize);
	}

	@Override
	public List<Category> AllCategoryList(Category category) {
		// TODO Auto-generated method stub
		return categoryMapper.getCategoryAllCategoryList(category);
	}

	@Override
	public List<Category> CategoryNameCategoryList(Category category,
			Integer page, Integer pageSize, String query) {
		// TODO Auto-generated method stub
		page=(page-1)*pageSize;
		query="%"+query+"%";
		return categoryMapper.getcategoryNameList(category, page, pageSize, query);
	}

	@Override
	public List<Category> CategoryNameAllCategoryList(Category category,
			String query) {
		// TODO Auto-generated method stub
		query="%"+query+"%";
		return categoryMapper.getcategoryNameAllList(category, query);
	}

	@Override
	public Map<String, Object> CategoryDetail(Category category, Integer id) {
		// TODO Auto-generated method stub
		return categoryMapper.getCategoryDetail(category, id);
	}

	@Override
	public void UpdateCategory(Integer id, String categoryName, String remark) {
		// TODO Auto-generated method stub
		categoryMapper.updateCategory(id, categoryName, remark);
		
	}

	//类别添加
	@Override
	public void AddCategory(String categoryName, String remark) {
		// TODO Auto-generated method stub
		categoryMapper.addCategory(categoryName, remark);
	}

	@Override
	public Map<String, Object> SearchCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return categoryMapper.sreachCategoryName(categoryName);
	}

	//类别删除
	@Override
	public Map<String, Object> SearchCategory(Integer id) {
		// TODO Auto-generated method stub
		return categoryMapper.sreachCategory(id);
	}

	@Override
	public void DeleteCategory(Integer id) {
		// TODO Auto-generated method stub
		categoryMapper.deleteCategory(id);
	}

}
