package com.tangcheng.zhiban.sns.todo.manager.biz;

import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.dao.biz.UserRepository;
import com.tangcheng.zhiban.sns.todo.dao.biz.UserRoleRepository;
import com.tangcheng.zhiban.sns.todo.domain.constant.Flag;
import com.tangcheng.zhiban.sns.todo.domain.model.CustomUserDetails;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsUserDO;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Created by tangcheng on 8/31/2017.
 */
@Service
public class UserManagerImpl implements UserManager, CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        String username = "todoList";
        CustomUserDetails userDetails = userRepository.getUser(username);
        if (userDetails == null) {
            SnsUserDO snsUserDO = new SnsUserDO();
            snsUserDO.setUsername(username);
            snsUserDO.setPassword(passwordEncoder.encode("admin123456"));
            snsUserDO.setEmail("793059909@qq.com");
            snsUserDO.setCreateIp(NetworkUtil.getRemoteIp());
            snsUserDO.setAccountEnabled(true);
            snsUserDO.setType(Flag.UserTypeFlag.SIGN_UP);
            Date month3 = LocalDate.now().plusDays(90).toDate();
            snsUserDO.setAccountExpired(month3);
            snsUserDO.setCredentialsExpired(month3);
            snsUserDO.setAccountLocked(false);
            Date now = new Date();
            snsUserDO.setCreateTime(now);
            snsUserDO.setUpdateTime(now);
            snsUserDO.setTodoCount(0);
            Long userId = userRepository.save(snsUserDO);
            userRoleRepository.save(userId, Flag.UserRoleFlag.ADMIN);
        }

    }
}
