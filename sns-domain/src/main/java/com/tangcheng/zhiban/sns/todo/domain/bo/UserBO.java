package com.tangcheng.zhiban.sns.todo.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author tangcheng
 * 2017/12/06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserBO extends User {
    private Long id;
    private String email;
    private String icon;
    private Byte type;
    private Boolean sex;
    private String mobile;

    public UserBO(Long id, String email, String icon, Byte type, Boolean sex, String mobile,
                  String username, String password, boolean enabled, boolean accountNonExpired,
                  boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        setId(id);
        setEmail(email);
        setIcon(icon);
        setType(type);
        setSex(sex);
        setMobile(mobile);
    }


}
