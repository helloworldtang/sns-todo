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
    private String nickName;
    private String thirdPartId;

    public UserBO(Long id, String thirdPartId, Byte type, String nickName, String icon, Boolean sex, String email, String mobile,
                  String username, String password, boolean enabled, boolean accountNonExpired,
                  boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        setId(id);
        setThirdPartId(thirdPartId);
        setType(type);
        setNickName(nickName);
        setEmail(email);
        setIcon(icon);
        setSex(sex);
        setMobile(mobile);
    }

    public UserBO(Long id, String thirdPartId, Byte type, String nickName, String icon, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(id, thirdPartId, type, nickName, icon,
                null, null, null,
                username, password, true, true, true, true,
                authorities);
    }
}
