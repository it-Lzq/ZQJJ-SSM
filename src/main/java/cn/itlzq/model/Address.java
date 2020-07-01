package cn.itlzq.model;


import java.sql.Date;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/3 22:08
 * @email 邮箱:905866484@qq.com
 * @description 描述：地址
 */
public class Address {
    //id
    private int id;
    //用户id
    private int userId;
    //收件人姓名
    private String userName;
    //收件人手机号
    private String userPhone;
    //省份id
    private int provinceId;
    //城市id
    private int cityId;
    //区的id
    private int areaId;
    //街道地址
    private int streeId;
    //用户地址
    private String userAddress;
    //
    private int isDefault;
    private Date createTime;

    public Address() {
    }

    public Address(int id, int userId, String userName, String userPhone, int provinceId, int cityId, int areaId, int streeId, String userAddress, int isDefault, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.areaId = areaId;
        this.streeId = streeId;
        this.userAddress = userAddress;
        this.isDefault = isDefault;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", provinceId=" + provinceId +
                ", cityId=" + cityId +
                ", areaId=" + areaId +
                ", streeId=" + streeId +
                ", userAddress='" + userAddress + '\'' +
                ", isDefault=" + isDefault +
                ", createTime=" + createTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getStreeId() {
        return streeId;
    }

    public void setStreeId(int streeId) {
        this.streeId = streeId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
