package good.dao;


import good.domain.Room;
import good.domain.RoomImg;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoomImgDao {


    /**
     * 根据rid查找图片的路径
     * @param rid
     * @return
     */
    @Select("select * from room_img where rid = #{rid}")
    RoomImg findByRid(String rid);

    /**
     * 修改客房图片信息
     * @param roomImg
     */
    @Update("update room_img set firstImg = #{firstImg}, secondImg = #{secondImg}, thirdImg = #{thirdImg}, forthImg = #{forthImg}, fifthImg = #{fifthImg} where rid = #{rid}")
    void updateRoomImg(RoomImg roomImg);

    /**
     * 添加客房图片信息
     * @param roomImg
     */
    @Insert("insert into room_img(rid,firstImg,secondImg,thirdImg,forthImg,fifthImg) values (#{rid},#{firstImg},#{secondImg},#{thirdImg},#{forthImg},#{fifthImg})")
    void add(RoomImg roomImg);

    /**
     * 根据id删除客房图片信息
     * @param rid
     */
    @Delete("delete from room_img where rid = #{rid}")
    void deleteRoomImg(String rid);

    /**
     * 根据rid返回第一张照片
     * @param rid
     * @return
     */
    @Select("select firstImg from room_img where rid = #{rid}")
    String findFirstImg(String rid);
}
