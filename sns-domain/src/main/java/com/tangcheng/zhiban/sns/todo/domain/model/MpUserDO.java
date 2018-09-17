package com.tangcheng.zhiban.sns.todo.domain.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "mp_user")
public class MpUserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appid;

    private String unionid;

    private String openid;

    /**
     * 1:关注；0：取消关注
     */
    private Boolean subscribe;

    /**
     * 返回用户关注的渠道来源：ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    @Column(name = "subscribe_scene")
    private String subscribeScene;

    /**
     * qr_scene 二维码扫码场景（开发者自定义）.
     */
    @Column(name = "qr_scene")
    private String qrScene;

    @Column(name = "qr_scene_str")
    private String qrSceneStr;

    /**
     * 用户关注或取关操作的次数
     */
    private Integer count;

    /**
     * 呢称
     */
    private String nickname;

    /**
     * 用户头像
     */
    @Column(name = "head_img_url")
    private String headImgUrl;

    private String country;

    private String province;

    private String city;

    private String language;

    /**
     * 性别描述信息：男、女、未知等.
     */
    @Column(name = "sex_desc")
    private String sexDesc;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
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
     * @return appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * @param appid
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * @return unionid
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * @param unionid
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * @return openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取1:关注；0：取消关注
     *
     * @return subscribe - 1:关注；0：取消关注
     */
    public Boolean getSubscribe() {
        return subscribe;
    }

    /**
     * 设置1:关注；0：取消关注
     *
     * @param subscribe 1:关注；0：取消关注
     */
    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * 获取返回用户关注的渠道来源：ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     *
     * @return subscribe_scene - 返回用户关注的渠道来源：ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    public String getSubscribeScene() {
        return subscribeScene;
    }

    /**
     * 设置返回用户关注的渠道来源：ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     *
     * @param subscribeScene 返回用户关注的渠道来源：ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
    }

    /**
     * 获取qr_scene 二维码扫码场景（开发者自定义）.
     *
     * @return qr_scene - qr_scene 二维码扫码场景（开发者自定义）.
     */
    public String getQrScene() {
        return qrScene;
    }

    /**
     * 设置qr_scene 二维码扫码场景（开发者自定义）.
     *
     * @param qrScene qr_scene 二维码扫码场景（开发者自定义）.
     */
    public void setQrScene(String qrScene) {
        this.qrScene = qrScene;
    }

    /**
     * @return qr_scene_str
     */
    public String getQrSceneStr() {
        return qrSceneStr;
    }

    /**
     * @param qrSceneStr
     */
    public void setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr;
    }

    /**
     * 获取用户关注或取关操作的次数
     *
     * @return count - 用户关注或取关操作的次数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置用户关注或取关操作的次数
     *
     * @param count 用户关注或取关操作的次数
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取呢称
     *
     * @return nickname - 呢称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置呢称
     *
     * @param nickname 呢称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户头像
     *
     * @return head_img_url - 用户头像
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    /**
     * 设置用户头像
     *
     * @param headImgUrl 用户头像
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取性别描述信息：男、女、未知等.
     *
     * @return sex_desc - 性别描述信息：男、女、未知等.
     */
    public String getSexDesc() {
        return sexDesc;
    }

    /**
     * 设置性别描述信息：男、女、未知等.
     *
     * @param sexDesc 性别描述信息：男、女、未知等.
     */
    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}