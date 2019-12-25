package good.dao;

import good.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IUserDao {

    /**
     * 添加用户
     * @param user
     */
    @Insert("insert into user(username,name,sex,password,avatar,tel,userStatus,idCard) values(#{username},#{name},#{sex},#{password},#{avatar},#{tel},#{userStatus},#{idCard})")
    void add(User user);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    public List<User> findAll();

    /**
     * 根据用户名查询
     * @param name
     * @return
     */
    @Select("select * from user where name = #{name}")
    public User findByName(String name);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from user where uid = #{id}")
    User findById(int id);

    /**
     * 修改用户密码
     * @param user
     */
    @Update("update user set name = #{name}, username = #{username}, password = #{password}, avatar = #{avatar}, sex = #{sex}, tel = #{tel}, userStatus = #{userStatus} WHERE uid = #{uid}")
    void updateUser(User user);

    /**
     * 修改用户信息
     * @param user
     */
    @Update("update user set name = #{name}, username = #{username}, avatar = #{avatar}, sex = #{sex}, tel = #{tel} WHERE uid = #{uid}")
    void editUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    @Delete("delete from user where uid = #{id}")
    void deleteUser(int id);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    /**
     * 根据身份证查找用户
     * @param idCard
     * @return
     */
    @Select("select * from user where idCard = #{idCard}")
    User findByIDCard(String idCard);
}
