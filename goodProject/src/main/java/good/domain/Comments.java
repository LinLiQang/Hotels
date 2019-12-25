package good.domain;

import good.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论实体类
 */
public class Comments implements Serializable {

    private int cid;                //评论id
    private String comment;         //评论内容
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date commentTime;       //评论时间
    private String commentTimeStr;  //评论时间的格式转换
    private Double star;            //用户给出星数
    private int uid;                //评论用户id
    private String rid;             //评论房间id
    private String oid;             //评论订单id
    private User user;              //当前评论对应用户
    private Room room;              //当前评论对应房间
    private Orders orders;          //当前评论对应订单

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentTimeStr() {
        if(commentTime != null){
            commentTimeStr = DateUtils.dateToString(commentTime,"yyyy-MM-dd");
        }
        return commentTimeStr;
    }

    public void setCommentTimeStr(String commentTimeStr) {
        this.commentTimeStr = commentTimeStr;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
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

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
