package good.service.impl;

import com.github.pagehelper.PageHelper;
import good.dao.IRoomDao;
import good.domain.Room;
import good.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements IRoomService {

    @Autowired
    private IRoomDao roomDao;

    /**
     * 查询所有房间（分页）
     * @return
     */
    @Override
    public List<Room> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        return roomDao.findStatus();
    }

    /**
     * 添加
     * @param room
     */
    @Override
    public void add(Room room) {
        roomDao.add(room);
    }

    /**
     * 根据名称查询产品是否存在
     * @param productName
     * @return
     */
    /*@Override
    public boolean findByPName(String productName) {
        Product product = productDao.findByPName(productName);
        if(product == null){
            return true;
        }else {
            return false;
        }
    }*/

    /**
     * 根据id查询客房
     * @param id
     * @return
     */
    @Override
    public Room findById(String id) {
        return roomDao.findById(id);
    }

    /**
     * 更新客房信息
     * @param room
     */
    @Override
    public void updateRoom(Room room) {
        roomDao.updateRoom(room);
    }

    /**
     * 根据id删除客房
     * @param id
     */
    @Override
    public void deleteRoom(String id) {
        roomDao.deleteRoom(id);
    }

    /**
     * 为订单模块查询所有客房信息
     * @return
     */
    @Override
    public List<Room> findAllToOrders() {
        return roomDao.findStatus();
    }

    /**
     * 根据房间类型查找房间（分页）
     * @param page
     * @param size
     * @param type
     * @return
     */
    @Override
    public List<Room> findByType(int page, int size, int type) {
        PageHelper.startPage(page,size);
        return roomDao.findByType(type);
    }

    /**
     * 根据房间类型查找房间（未分页）
     * @param type
     * @return
     */
    @Override
    public List<Room> findByType(int type) {
        return roomDao.findByType(type);
    }

    /**
     * 查询所有房间
     * @return
     */
    @Override
    public List<Room> findAllToAdminOrders() {
        return roomDao.findAll();
    }

    /**
     * 向后台返回所有房间信息
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Room> findAllToAdmin(int page, int size) {
        PageHelper.startPage(page,size);
        return roomDao.findAll();
    }
}
