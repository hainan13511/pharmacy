package pharmacy.web.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.common.model.UserInfo;
import pharmacy.web.management.dao.ChangePasswordMapper;
import pharmacy.web.management.service.ChangePasswordSeverice;

@Service
public class ChangePasswordSevericeImpl implements ChangePasswordSeverice {

	@Autowired
	private ChangePasswordMapper changePasswordMapper;
	//修改密码
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String changePwd(String oldpwd,String newpwd,String id) {
		UserInfo user = changePasswordMapper.user(id);
		if(!user.getUpwd().equals(oldpwd)) {
			return "密码不一致";
		}
		int changepwd = changePasswordMapper.changepwd(newpwd, id);
		return changepwd+"";
	}

}
