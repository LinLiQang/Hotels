package good.domain;

import java.io.Serializable;

/**
 * 用户实体类
 */
public class User implements Serializable {
    private int uid;                //用户id
    private String username;        //用户账号
    private String name;            //用户真实姓名
    private String IDcard;          //身份证号
    private String sex;             //用户性别
    private String password;        //用户密码
    private String avatar;          //用户头像
    private String tel;             //用户手机号
    private int userStatus;         //用户状态 0被封禁 1普通用户 2会员 3管理员
    private String userStatusStr;   //用户状态格式转换

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatusStr() {
        if(this.getUserStatus() == 0){
            userStatusStr = "封禁中";
        }else if(this.getUserStatus() == 1){
            userStatusStr = "普通用户";
        }else if(this.getUserStatus() == 2){
            userStatusStr = "会员";
        }else{
            userStatusStr = "管理员";
        }
        return userStatusStr;
    }

    public void setUserStatusStr(String userStatusStr) {
        this.userStatusStr = userStatusStr;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", tel='" + tel + '\'' +
                ", userStatus=" + userStatus +
                ", userStatusStr='" + userStatusStr + '\'' +
                '}';
    }
}
