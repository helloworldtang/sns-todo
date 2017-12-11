package com.tangcheng.zhiban.sns.todo.manager.biz;

import com.tangcheng.zhiban.sns.todo.core.constant.Flag;
import com.tangcheng.zhiban.sns.todo.core.util.NetworkUtil;
import com.tangcheng.zhiban.sns.todo.dao.biz.UserRepository;
import com.tangcheng.zhiban.sns.todo.dao.biz.UserRoleRepository;
import com.tangcheng.zhiban.sns.todo.domain.model.CustomUserDetails;
import com.tangcheng.zhiban.sns.todo.domain.model.SnsUserDO;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.tangcheng.zhiban.sns.todo.core.constant.RoleEnum.ADMIN;
import static com.tangcheng.zhiban.sns.todo.core.constant.RoleEnum.USER;


/**
 * Created by tangcheng on 8/31/2017.
 */
@Slf4j
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
        log.info("begin to init account");
        String username = "todoList";
        CustomUserDetails userDetails = userRepository.getUser(username);
        if (userDetails == null) {
            SnsUserDO snsUserDO = new SnsUserDO();
            snsUserDO.setThirdPartId("");
            snsUserDO.setUsername(username);
            snsUserDO.setNickName(username);
            snsUserDO.setType(Flag.UserTypeFlag.SIGN_UP);
            snsUserDO.setPassword(passwordEncoder.encode("admin123456"));
            snsUserDO.setEmail("793059909@qq.com");
            snsUserDO.setSex(true);
            snsUserDO.setCreateIp(NetworkUtil.getRemoteIp());
            snsUserDO.setAccountEnabled(true);
            Date month3 = LocalDate.now().plusDays(90).toDate();
            snsUserDO.setAccountExpired(month3);
            snsUserDO.setCredentialsExpired(month3);
            snsUserDO.setAccountLocked(false);
            Date now = new Date();
            snsUserDO.setCreateTime(now);
            snsUserDO.setUpdateTime(now);
            snsUserDO.setTodoCount(0);
            Long userId = userRepository.save(snsUserDO);
            userRoleRepository.save(userId, ADMIN);
        }
    }


    @Override
    public Long save(String thirdPartId, byte type, String nickname, String icon) {
        return save(thirdPartId, type, nickname, icon, null, "", "");
    }

    @Override
    public Long save(String thirdPartId, byte type, String nickname, String icon, Boolean gender) {
        return save(thirdPartId, type, nickname, icon, gender, "", "");
    }


    @Override
    public Long save(String thirdPartId, byte type, String nickname, String icon, String bio, String email) {
        return save(thirdPartId, type, nickname, icon, null, "", "");
    }

    @Override
    public Long save(String thirdPartId, byte type, String nickname, String icon, Boolean gender, String bio, String email) {
        SnsUserDO snsUserDO = userRepository.getUser(thirdPartId, type);
        if (snsUserDO != null) {
            userRepository.update(snsUserDO.getId(), nickname, icon, gender, bio, email);
            return snsUserDO.getId();
        }

        snsUserDO = new SnsUserDO();
        snsUserDO.setThirdPartId(thirdPartId);
        snsUserDO.setUsername(nickname);
        snsUserDO.setNickName(nickname);
        snsUserDO.setIcon(icon);
        snsUserDO.setType(type);
        snsUserDO.setPassword(passwordEncoder.encode(thirdPartId));
        snsUserDO.setSex(gender);
        snsUserDO.setBio(bio);
        snsUserDO.setEmail(email);
        snsUserDO.setCreateIp(NetworkUtil.getRemoteIp());
        snsUserDO.setAccountEnabled(true);
        Date month3 = LocalDate.now().plusDays(90).toDate();
        snsUserDO.setAccountExpired(month3);
        snsUserDO.setCredentialsExpired(month3);
        snsUserDO.setAccountLocked(false);
        Date now = new Date();
        snsUserDO.setCreateTime(now);
        snsUserDO.setUpdateTime(now);
        snsUserDO.setTodoCount(0);
        Long userId = userRepository.save(snsUserDO);
        userRoleRepository.save(userId, USER);
        return userId;
    }


}
