package good.domain;

import java.io.Serializable;

/**
 * 客房实体类
 */
public class Room implements Serializable {

    private String rid;             //客房id
    private String introduction;    //客房简介
    private String detail;          //客房详述
    private Double roomPrice;       //客房价格
    private int type;               //客房类型 1单人房 2双人房 3豪华房 4家庭房
    private String typeStr;         //客房类型格式转换
    private int roomStatus;         //客房状态 0正在维修 1正在运营
    private String roomStatusStr;   //客房状态格式转换
    private RoomImg roomImg;        //房间照片

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
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

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomStatusStr() {
        if(this.getRoomStatus() == 0){
            roomStatusStr = "正在维修";
        }
        if(this.getRoomStatus() == 1){
            roomStatusStr = "正在运营";
        }
        return roomStatusStr;
    }

    public void setRoomStatusStr(String roomStatusStr) {
        this.roomStatusStr = roomStatusStr;
    }

    public RoomImg getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(RoomImg roomImg) {
        this.roomImg = roomImg;
    }

    //重写方法
    @Override
    public int hashCode(){
        String result = rid;
        return result.hashCode();
    }
    //重写方法 用于确认两个集合中相同的room对象，重复条件为rid
    @Override
    public boolean equals(Object obj){
        Room room = (Room)obj;
        return this.getRid().equals(room.getRid());
    }

    @Override
    public String toString() {
        return "Room{" +
                "rid='" + rid + '\'' +
                ", introduction='" + introduction + '\'' +
                ", detail='" + detail + '\'' +
                ", roomPrice=" + roomPrice +
                ", type=" + type +
                ", typeStr='" + typeStr + '\'' +
                ", roomStatus=" + roomStatus +
                ", roomStatusStr='" + roomStatusStr + '\'' +
                '}';
    }
}
