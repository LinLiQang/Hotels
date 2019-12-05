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
     * 查询所有
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
     * 根据id查询
     * @param oid
     * @return
     */
    @Override
    public Orders findById(String oid) {
        return ordersDao.findById(oid);
    }

    /**
     * 修改订单
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

    @Override
    public List<Orders> findAllToOrders() {
        return ordersDao.findAll();
    }
}
