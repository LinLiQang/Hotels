package good.domain;

import java.io.Serializable;

/**
 * 客房图片实体类
 */
public class RoomImg implements Serializable {

    private int  imgid;             //房间照片id
    private String rid;             //房间id
    private String firstImg;        //第一张照片
    private String secondImg;       //第二张照片
    private String thirdImg;        //第三张照片
    private String forthImg;        //第四张照片
    private String fifthImg;        //第五张照片

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public String getSecondImg() {
        return secondImg;
    }

    public void setSecondImg(String secondImg) {
        this.secondImg = secondImg;
    }

    public String getThirdImg() {
        return thirdImg;
    }

    public void setThirdImg(String thirdImg) {
        this.thirdImg = thirdImg;
    }

    public String getForthImg() {
        return forthImg;
    }

    public void setForthImg(String forthImg) {
        this.forthImg = forthImg;
    }

    public String getFifthImg() {
        return fifthImg;
    }

    public void setFifthImg(String fifthImg) {
        this.fifthImg = fifthImg;
    }
}
