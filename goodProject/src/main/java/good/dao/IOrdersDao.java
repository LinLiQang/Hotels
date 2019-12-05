package good.dao;


import good.domain.Orders;
import good.domain.Room;
import good.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {

    /**
     * 查询所有
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
}
