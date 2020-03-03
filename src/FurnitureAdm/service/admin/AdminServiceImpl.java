package FurnitureAdm.service.admin;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FurnitureAdm.bean.Admin;
import FurnitureAdm.mapper.AdminMapper;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
    private AdminMapper adminMapper;
	
	@Override
	public Map<String, Object> Login(String userName, String pwd) {
		return adminMapper.adminLogin(userName, pwd);
	}

	@Override
	public Map<String, Object> Exit(Integer id) {
		// TODO Auto-generated method stub
		return adminMapper.adminExit(id);
	}

}
