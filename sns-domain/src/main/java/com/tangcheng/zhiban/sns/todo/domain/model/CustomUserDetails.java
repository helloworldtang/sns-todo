package com.tangcheng.zhiban.sns.todo.domain.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by tangcheng on 8/31/2017.
 */
public class CustomUserDetails extends SnsUserDO {
    private List<SnsRoleDO> roles;

    public List<SnsRoleDO> getRoles() {
        return roles;
    }

    public void setRoles(List<SnsRoleDO> roles) {
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> generateAuthorities() {
        //定义权限集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        //当前用户的角色信息集合
        List<SnsRoleDO> roles = this.getRoles();
        //添加角色信息到权限集合
        for (SnsRoleDO role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public boolean generateAccountNonExpired() {
        return new Date().before(this.getAccountExpired());
    }

    public boolean generateCredentialsNonExpired() {
        return new Date().before(this.getCredentialsExpired());
    }
}
