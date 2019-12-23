package good.service.impl;

import com.github.pagehelper.PageHelper;
import good.dao.IUserDao;
import good.domain.User;
import good.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    /**
     * 根据用户真实姓名确认是否有账户
     * @param name
     * @return
     */
    @Override
    public boolean findByName(String name) {
        User user = userDao.findByName(name);
        if(user == null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询所有
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<User> findAll(int page, int size) {
        //参数pageNum是页码，pageSize是数据个数
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public User findById(int id) {
        User user = userDao.findById(id);
        return user;
    }

    /**
     * 修改用户
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    /**
     * 根据id删除用户
     * @param id
     */
    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 根据身份证号查找用户
     * @param IDcard
     * @return
     */
    @Override
    public boolean findByIDCard(String IDcard) {
        User user = userDao.findByIDCard(IDcard);
        if (user == null){
            return true;
        }else{
            return false;
        }
    }


}
