package good.dao;

import good.domain.Room;
import good.domain.RoomImg;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface IRoomDao {

    /**
     * 查询所有客房
     * @return
     */
    @Select("select * from room")
    public List<Room> findAll();

    /**
     * 添加客房
     * @param room
     */
    @Insert("insert into room(rid,introduction,detail,roomPrice,type,roomStatus) values (#{rid},#{introduction},#{detail},#{roomPrice},#{type},#{roomStatus})")
    public void add(Room room);


    /**
     * 根据rid查询客房信息
     * @param id
     * @return
     */
    @Select("select * from room where rid = #{id}")
    Room findById(String id);

    /**
     * 修改客房信息
     * @param room
     */
    @Update("update room set rid = #{rid}, introduction = #{introduction}, detail = #{detail}, roomPrice = #{roomPrice}, roomStatus = #{roomStatus}, type = #{type} where rid = #{rid}")
    void updateRoom(Room room);

    /**
     * 根据rid删除客房
     * @param id
     */
    @Delete("delete from room where rid = #{id}")
    void deleteRoom(String id);

    /**
     * 根据房间类型和房间状态查找房间
     * @param type
     * @return
     */
    @Select("select * from room where type = #{type} and roomStatus != 0")
    List<Room> findByType(int type);

    /**
     * 查找房间状态为正在运营的房间
     * @return
     */
    @Select("select * from room where roomStatus != 0")
    List<Room> findStatus();

    /**
     * 根据rid确认type
     * @param rid
     * @return
     */
    @Select("select type from room where rid = #{rid}")
    int findToType(String rid);
}
