package cn.itlzq.model;


import java.sql.Date;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/3 22:03
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class User {

    //id
    private int id;
    //手机号码
    private String userphone;
    //邮箱
    private String email;
    //密码
    private String password;
    private String nickName;
    private String userPhoto;
    private String lastIp;
    private Date lastTime;
    private Date createTime;

    public User() {
    }

    public User(int id, String userphone, String email, String password, String nickName, String userPhoto, String lastIp, Date lastTime, Date createTime) {
        this.id = id;
        this.userphone = userphone;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.userPhoto = userPhoto;
        this.lastIp = lastIp;
        this.lastTime = lastTime;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userphone='" + userphone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", lastIp='" + lastIp + '\'' +
                ", lastTime=" + lastTime +
                ", createTime=" + createTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
