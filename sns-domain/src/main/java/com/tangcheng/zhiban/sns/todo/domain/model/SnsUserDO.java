package com.tangcheng.zhiban.sns.todo.domain.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sns_user")
public class SnsUserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "third_part_id")
    private String thirdPartId;

    private Byte type;

    @Column(name = "nick_name")
    private String nickName;

    private String bio;

    private String username;

    private String password;

    private String email;

    private String icon;

    private String salt;

    @Column(name = "account_enabled")
    private Boolean accountEnabled;

    @Column(name = "account_expired")
    private Date accountExpired;

    @Column(name = "credentials_expired")
    private Date credentialsExpired;

    @Column(name = "account_locked")
    private Boolean accountLocked;

    @Column(name = "create_ip")
    private String createIp;

    @Column(name = "update_ip")
    private String updateIp;

    private Boolean sex;

    private String mobile;

    private String location;

    @Column(name = "todo_count")
    private Integer todoCount;

    @Column(name = "last_login_ip")
    private String lastLoginIp;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return third_part_id
     */
    public String getThirdPartId() {
        return thirdPartId;
    }

    /**
     * @param thirdPartId
     */
    public void setThirdPartId(String thirdPartId) {
        this.thirdPartId = thirdPartId;
    }

    /**
     * @return type
     */
    public Byte getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return account_enabled
     */
    public Boolean getAccountEnabled() {
        return accountEnabled;
    }

    /**
     * @param accountEnabled
     */
    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    /**
     * @return account_expired
     */
    public Date getAccountExpired() {
        return accountExpired;
    }

    /**
     * @param accountExpired
     */
    public void setAccountExpired(Date accountExpired) {
        this.accountExpired = accountExpired;
    }

    /**
     * @return credentials_expired
     */
    public Date getCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * @param credentialsExpired
     */
    public void setCredentialsExpired(Date credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    /**
     * @return account_locked
     */
    public Boolean getAccountLocked() {
        return accountLocked;
    }

    /**
     * @param accountLocked
     */
    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    /**
     * @return create_ip
     */
    public String getCreateIp() {
        return createIp;
    }

    /**
     * @param createIp
     */
    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    /**
     * @return update_ip
     */
    public String getUpdateIp() {
        return updateIp;
    }

    /**
     * @param updateIp
     */
    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }

    /**
     * @return sex
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return todo_count
     */
    public Integer getTodoCount() {
        return todoCount;
    }

    /**
     * @param todoCount
     */
    public void setTodoCount(Integer todoCount) {
        this.todoCount = todoCount;
    }

    /**
     * @return last_login_ip
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * @param lastLoginIp
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}