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
    private String thirdPartId;
    private Byte type;
    private String nickName;
    private String bio;
    private String icon;
    private String gender;
    private String email;
    private String mobile;

    public UserBO(Long id, String thirdPartId, Byte type, String nickName, String bio, String icon, String gender, String email, String mobile,
                  String username, String password, boolean enabled, boolean accountNonExpired,
                  boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        setId(id);
        setThirdPartId(thirdPartId);
        setType(type);
        setNickName(nickName);
        setEmail(email);
        setBio(bio);
        setIcon(icon);
        setGender(gender);
        setMobile(mobile);
    }

    public UserBO(Long id, String thirdPartId, Byte type, String nickName, String bio, String icon, String email, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(id, thirdPartId, type, nickName, bio, icon, null,
                email, null,
                username, password, true, true, true, true,
                authorities);
    }

    public UserBO(Long id, String thirdPartId, Byte type, String nickName, String bio, String icon, String gender, String email, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(id, thirdPartId, type, nickName, bio, icon, gender,
                email, null,
                username, password, true, true, true, true,
                authorities);
    }
}
