package com.tangcheng.zhiban.sns.todo.domain.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sns-todo-detail")
public class SnsTodoDetailDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long type;

    private String digest;

    private String content;

    private Boolean finished;

    @Column(name = "create_ip")
    private String createIp;

    @Column(name = "update_ip")
    private String updateIp;

    private Byte status;

    @Column(name = "create_date")
    private Date createDate;

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
     * @return type
     */
    public Long getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * @return digest
     */
    public String getDigest() {
        return digest;
    }

    /**
     * @param digest
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return finished
     */
    public Boolean getFinished() {
        return finished;
    }

    /**
     * @param finished
     */
    public void setFinished(Boolean finished) {
        this.finished = finished;
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
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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