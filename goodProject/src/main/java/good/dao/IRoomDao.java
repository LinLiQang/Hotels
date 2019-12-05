package good.dao;

import good.domain.Room;
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
    @Insert("insert into room(rid,roomPrice,type,roomStatus) values (#{rid},#{roomPrice},#{type},#{roomStatus})")
    public void add(Room room);


    /**
     * 根据id查询客房信息
     * @param id
     * @return
     */
    @Select("select * from room where rid = #{id}")
    Room findById(String id);

    /**
     * 更新客房信息
     * @param room
     */
    @Update("update room set rid = #{rid}, roomPrice = #{roomPrice}, roomStatus = #{roomStatus}, type = #{type} where rid = #{rid}")
    void updateRoom(Room room);

    /**
     * 根据id删除客房
     * @param id
     */
    @Delete("delete from room where rid = #{id}")
    void deleteRoom(String id);
}
