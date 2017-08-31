package com.tangcheng.zhiban.sns.todo.service.biz;

import com.tangcheng.zhiban.sns.todo.dao.biz.UserRepository;
import com.tangcheng.zhiban.sns.todo.domain.model.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by tangcheng on 8/31/2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails userDetails = userRepository.getUser(username);
        if (userDetails == null) {
            LOGGER.warn("{} not exist.", username);
            throw new UsernameNotFoundException(username + " not exists");
        }

        return new User(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAccountEnabled(),
                userDetails.generateAccountNonExpired(),
                userDetails.generateCredentialsNonExpired(),
                !userDetails.getAccountLocked(),
                userDetails.generateAuthorities());

    }
}