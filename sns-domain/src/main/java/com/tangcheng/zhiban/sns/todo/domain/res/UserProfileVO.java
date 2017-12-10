package com.tangcheng.zhiban.sns.todo.domain.res;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileVO {
    private Long id;
    private String thirdPartId = "";
    private Byte type;
    private String nickName;
    private String bio = "";
    private String icon;
    private Boolean sex;
    private String email = "";
    private String mobile = "";
    private String username;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

}
