package good.dao;


import good.domain.RoomImg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IRoomImgDao {

    /**
     * 根据rid查找图片的路径
     * @param rid
     * @return
     */
    @Select("select * from room_img where rid = #{rid}")
    RoomImg findByRid(String rid);

    @Update("update room_img set firstImg = #{firstImg}, secondImg = #{secondImg}, thirdImg = #{thirdImg} where rid = #{rid}")
    void updateRoomImg(RoomImg roomImg);

    @Insert("insert into room_img(rid,firstImg,secondImg,thirdImg) values (#{rid},#{firstImg},#{secondImg},#{thirdImg})")
    void add(RoomImg roomImg);

    @Delete("delete from room_img where rid = #{rid}")
    void deleteRoomImg(String rid);
}
