package good.dao;

import good.domain.Comments;
import good.domain.Orders;
import good.domain.Room;
import good.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ICommentsDao {

    /**
     * 查询所有评论
     * @return
     */
    @Select("select * from comments")
    @Results({
            @Result(id = true, property = "cid", column = "cid"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "commentTime", column = "commentTime"),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "good.dao.IUserDao.findById")),
            @Result(property = "room", column = "rid", javaType = Room.class, one = @One(select = "good.dao.IRoomDao.findById")),
            @Result(property = "orders", column = "oid", javaType = Orders.class, one = @One(select = "good.dao.IOrdersDao.findById"))
    })
    public List<Comments> findAll();

    /**
     * 根据cid删除评论
     * @param cid
     */
    @Delete("delete from comments where cid = #{cid}")
    void deleteComments(String cid);

    /**
     * 根据cid查询评论所有信息
     * @param cid
     * @return
     */
    @Select("select * from comments where cid = #{cid}")
    @Results({
            @Result(id = true, property = "cid", column = "cid"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "commentTime", column = "commentTime"),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "good.dao.IUserDao.findById")),
            @Result(property = "room", column = "rid", javaType = Room.class, one = @One(select = "good.dao.IRoomDao.findById")),
            @Result(property = "orders", column = "oid", javaType = Orders.class, one = @One(select = "good.dao.IOrdersDao.findById"))
    })
    Comments findById(String cid);

    /**
     * 根据uid查询评论及其他信息
     * @param uid
     * @return
     */
    @Select("select * from comments where uid = #{uid}")
    @Results({
            @Result(id = true, property = "cid", column = "cid"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "commentTime", column = "commentTime"),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "good.dao.IUserDao.findById")),
            @Result(property = "room", column = "rid", javaType = Room.class, one = @One(select = "good.dao.IRoomDao.findById")),
            @Result(property = "orders", column = "oid", javaType = Orders.class, one = @One(select = "good.dao.IOrdersDao.findById"))
    })
    List<Comments> findByUid(int uid);

    /**
     * 根据rid查询评论及其他信息
     * @param rid
     * @return
     */
    @Select("select * from comments where rid = #{rid}")
    @Results({
            @Result(id = true, property = "cid", column = "cid"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "commentTime", column = "commentTime"),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "good.dao.IUserDao.findById")),
            @Result(property = "room", column = "rid", javaType = Room.class, one = @One(select = "good.dao.IRoomDao.findById")),
            @Result(property = "orders", column = "oid", javaType = Orders.class, one = @One(select = "good.dao.IOrdersDao.findById"))
    })
    List<Comments> findByRid(String rid);

    /**
     * 根据rid查询评论信息和用户信息
     * @param rid
     * @return
     */
    @Select("select * from comments where rid = #{rid}")
    @Results({
            @Result(id = true, property = "cid", column = "cid"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "commentTime", column = "commentTime"),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "good.dao.IUserDao.findById"))
    })
    List<Comments> findByRidToRoom(String rid);

    /**
     * 添加评论
     * @param comments
     */
    @Insert("insert into comments(comment,commentTime,uid,rid,oid) values(#{comment},#{commentTime},#{uid},#{rid},#{oid})")
    void addComments(Comments comments);
}
