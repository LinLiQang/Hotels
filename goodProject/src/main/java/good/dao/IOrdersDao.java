package good.dao;


import good.domain.Orders;
import good.domain.Room;
import good.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    /**
     * 查询订单所有信息包括相关具体信息
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "oid", column = "oid"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "endTime", column = "endTime"),
            @Result(property = "ordersPrice", column = "ordersPrice"),
            @Result(property = "ordersStatus", column = "ordersStatus"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "room", column = "rid", javaType = Room.class, one = @One(select = "good.dao.IRoomDao.findById")),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "good.dao.IUserDao.findById"))
    })
    List<Orders> findAll();

    /**
     * 订单详情
     * 涉及多表操作
     * @param oid
     * @return
     */
    @Select("select * from orders where oid = #{oid}")
    @Results({
            @Result(id = true, property = "oid", column = "oid"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "endTime", column = "endTime"),
            @Result(property = "ordersPrice", column = "ordersPrice"),
            @Result(property = "ordersStatus", column = "ordersStatus"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "room", column = "rid", javaType = Room.class, one = @One(select = "good.dao.IRoomDao.findById")),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "good.dao.IUserDao.findById"))
    })
    Orders findById(String oid);

    /**
     * 添加订单
     * @param orders
     */
    @Insert("insert into orders(oid,startTime,endTime,ordersPrice,ordersStatus,rid,uid) values(#{oid},#{startTime},#{endTime},#{ordersPrice},#{ordersStatus},#{rid},#{uid})")
    void add(Orders orders);

    /**
     * 修改订单
     * @param orders
     */
    @Update("update orders set rid = #{rid} , startTime = #{startTime} , endTime = #{endTime} , ordersStatus = #{ordersStatus} , ordersPrice = #{ordersPrice} WHERE oid = #{oid}")
    void updateOrders(Orders orders);

    /**
     * 根据oid删除订单
     * @param id
     */
    @Delete("delete from orders where oid = #{id}")
    void deleteOrders(String id);

    /**
     * 根据uid查询所有订单
     * @param uid
     * @return
     */
    @Select("select * from orders where uid = #{uid}")
    @Results({
            @Result(id = true, property = "oid", column = "oid"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "endTime", column = "endTime"),
            @Result(property = "ordersPrice", column = "ordersPrice"),
            @Result(property = "ordersStatus", column = "ordersStatus"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "room", column = "rid", javaType = Room.class, one = @One(select = "good.dao.IRoomDao.findById")),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "good.dao.IUserDao.findById"))
    })
    List<Orders> findByUid(int uid);

    /**
     * 根据rid查询所有订单
     * @param rid
     * @return
     */
    @Select("select * from orders where rid = #{rid}")
    @Results({
            @Result(id = true, property = "oid", column = "oid"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "endTime", column = "endTime"),
            @Result(property = "ordersPrice", column = "ordersPrice"),
            @Result(property = "ordersStatus", column = "ordersStatus"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "room", column = "rid", javaType = Room.class, one = @One(select = "good.dao.IRoomDao.findById")),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "good.dao.IUserDao.findById"))
    })
    List<Orders> findByRid(String rid);

    /**
     * 根据uid查询所有订单
     * @param uid
     * @return
     */
    @Select("select * from orders where uid = #{uid}")
    @Results({
            @Result(id = true, property = "oid", column = "oid"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "endTime", column = "endTime"),
            @Result(property = "ordersPrice", column = "ordersPrice"),
            @Result(property = "ordersStatus", column = "ordersStatus"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "rid", column = "rid"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "type", column = "rid",javaType = int.class, one = @One(select = "good.dao.IRoomDao.findToType")),
            @Result(property = "firstImg", column = "rid", javaType = String.class, one = @One(select = "good.dao.IRoomImgDao.findFirstImg")),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "good.dao.IUserDao.findById"))
    })
    List<Orders> findToUser(int uid);

    /**
     * 为了查询可用房间查询所有订单
     * @return
     */
    @Select("select * from orders")
    List<Orders> findAllToOrders();

    /**
     * 取消订单，修改订单状态
     * @param oid
     */
    @Update("update orders set ordersStatus = 0 where oid = #{oid}")
    void cancelOrders(String oid);

    /**
     * 修改订单状态为已评论
     * @param oid
     */
    @Update("update orders set ordersStatus = 3 where oid = #{oid}")
    void updateStatus(String oid);

    /**
     * 修改订单状态为已完成
     * @param oid
     */
    @Update("update orders set ordersStatus = 2 where oid = #{oid}")
    void updateStatusTime(String oid);
}
