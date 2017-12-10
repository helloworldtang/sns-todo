package com.tangcheng.zhiban.sns.todo.service.biz;

import com.tangcheng.zhiban.sns.todo.dao.biz.UserRepository;
import com.tangcheng.zhiban.sns.todo.domain.model.CustomUserDetails;
import com.tangcheng.zhiban.sns.todo.domain.req.ChangePwdReqVO;
import com.tangcheng.zhiban.sns.todo.manager.biz.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by tangcheng on 9/1/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserManager userManager;

    @Override
    public int changePwd(String username, ChangePwdReqVO changePwdReqVO) {
        CustomUserDetails userDetails = userRepository.getUser(username);
        if (passwordEncoder.matches(changePwdReqVO.getOldPwd(), userDetails.getPassword())) {
            return userRepository.updatePwd(username, passwordEncoder.encode(changePwdReqVO.getNewPwd()));
        }
        return 0;
    }

    @Override
    public Long save(String thirdPartId, byte type, String nickname, String icon) {
       return userManager.save(thirdPartId, type, nickname, icon);
    }

    @Override
    public Long save(String thirdPartId, byte type, String nickname, String icon, String bio, String email) {
        return userManager.save(thirdPartId, type, nickname, icon,bio,email);
    }


}
