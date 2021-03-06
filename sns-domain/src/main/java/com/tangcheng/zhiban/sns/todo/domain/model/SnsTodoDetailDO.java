package com.tangcheng.zhiban.sns.todo.domain.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sns_todo_detail")
public class SnsTodoDetailDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String username;

    @Column(name = "category_id")
    private Long categoryId;

    private String digest;

    private Integer weight;

    @Column(name = "expect_finish_time")
    private Date expectFinishTime;

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

    @Column(name = "finish_time")
    private Date finishTime;

    @Column(name = "finish_ip")
    private String finishIp;

    private String content;

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
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * @return category_id
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
     * @return weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return expect_finish_time
     */
    public Date getExpectFinishTime() {
        return expectFinishTime;
    }

    /**
     * @param expectFinishTime
     */
    public void setExpectFinishTime(Date expectFinishTime) {
        this.expectFinishTime = expectFinishTime;
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

    /**
     * @return finish_time
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return finish_ip
     */
    public String getFinishIp() {
        return finishIp;
    }

    /**
     * @param finishIp
     */
    public void setFinishIp(String finishIp) {
        this.finishIp = finishIp;
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
}