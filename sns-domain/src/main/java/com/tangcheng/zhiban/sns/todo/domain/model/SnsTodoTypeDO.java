package com.tangcheng.zhiban.sns.todo.domain.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sns-todo-type")
public class SnsTodoTypeDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String detail;

    private String icon;

    @Column(name = "create_ip")
    private String createIp;

    @Column(name = "update_ip")
    private String updateIp;

    private Byte status;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
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
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
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