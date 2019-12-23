package good.domain;

import good.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体类
 */
public class Orders implements Serializable {

    private String oid;                 //订单id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;             //订单开始时间 设置时间格式
    private String startTimeStr;        //开始时间的格式转换
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;               //订单结束时间 设置时间格式
    private String endTimeStr;          //结束时间的格式转换
    private Double ordersPrice;         //订单价格
    private int ordersStatus;           //订单状态  0已取消 1正在进行 2已完成 3已评论
    private String ordersStatusStr;     //订单状态格式转换
    private int uid;                    //下单用户id
    private String rid;                 //下单房间
    private int type;                //下单房间类型
    private String typeStr;             //下单房间类型名称
    private User user;                  //当前订单对应用户
    private Room room;                  //当前订单对应房间
    private String firstImg;            //当前房间的第一张图片

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeStr() {
        if(startTime != null){
            startTimeStr = DateUtils.dateToString(startTime,"yyyy-MM-dd");
        }
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeStr() {
        if(endTime != null){
            endTimeStr = DateUtils.dateToString(endTime,"yyyy-MM-dd");
        }
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public Double getOrdersPrice() {
        return ordersPrice;
    }

    public void setOrdersPrice(Double ordersPrice) {
        this.ordersPrice = ordersPrice;
    }

    public int getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(int ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public String getOrdersStatusStr() {
        if(ordersStatus == 0){
            ordersStatusStr = "已取消";
        }
        if(ordersStatus == 1){
            ordersStatusStr = "正在进行";
        }
        if(ordersStatus == 2){
            ordersStatusStr = "已完成";
        }
        if(ordersStatus == 3){
            ordersStatusStr = "已评论";
        }
        return ordersStatusStr;
    }

    public void setOrdersStatusStr(String ordersStatusStr) {
        this.ordersStatusStr = ordersStatusStr;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeStr() {
        if(this.getType() == 1){
            typeStr = "单人房";
        }
        if(this.getType() == 2){
            typeStr = "双人房";
        }
        if(this.getType() == 3){
            typeStr = "豪华房";
        }
        if(this.getType() == 4){
            typeStr = "家庭房";
        }
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }
}
