package good.service.impl;

import com.github.pagehelper.PageHelper;
import good.dao.IOrdersDao;
import good.domain.Orders;
import good.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    /**
     * 后台查询所有订单
     * @return
     */
    @Override
    public List<Orders> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    /**
     * 添加订单
     * @param orders
     */
    @Override
    public void add(Orders orders) {
        ordersDao.add(orders);
    }

    /**
     * 根据oid查询订单
     * @param oid
     * @return
     */
    @Override
    public Orders findById(String oid) {
        return ordersDao.findById(oid);
    }

    /**
     * 后台修改订单
     * @param orders
     */
    @Override
    public void updateOrders(Orders orders) {
        ordersDao.updateOrders(orders);
    }

    /**
     * 根据id删除订单
     * @param id
     */
    @Override
    public void deleteOrders(String id) {
        ordersDao.deleteOrders(id);
    }

    /**
     * 查询所有房间时检测不可预定房间
     * @return
     */
    @Override
    public List<Orders> findAllToOrders() {
        return ordersDao.findAllToOrders();
    }

    /**
     * 根据uid查询所有订单
     * @param uid
     * @return
     */
    @Override
    public List<Orders> findByUid(int uid) {
        return ordersDao.findByUid(uid);
    }

    /**
     * 根据rid查询所有订单
     * @param rid
     * @return
     */
    @Override
    public List<Orders> findByRid(String rid) {
        return ordersDao.findByRid(rid);
    }

    /**
     * 生成订单时检测是否冲突
     * @return
     */
    @Override
    public List<Orders> findAllForOrders() {
        return ordersDao.findAll();
    }

    /**
     * 为前台用户查看订单返回订单
     * @param uid
     * @return
     */
    @Override
    public List<Orders> findToUser(int uid) {
        return ordersDao.findToUser(uid);
    }

    /**
     * 用户取消订单
     * @param oid
     */
    @Override
    public void cancelOrders(String oid) {
        ordersDao.cancelOrders(oid);
    }

    /**
     * 用户评论完修改订单状态
     * @param oid
     */
    @Override
    public void updateStatus(String oid) {
        ordersDao.updateStatus(oid);
    }

    /**
     * 根据时间修改订单状态为已完成
     * @param oid
     */
    @Override
    public void updateStatusTime(String oid) {
        ordersDao.updateStatusTime(oid);
    }
}
