package good.service;

import good.domain.Orders;

import java.util.List;

public interface IOrdersService {

    public List<Orders> findAll(int page, int size);

    void add(Orders orders);

    Orders findById(String oid);

    void updateOrders(Orders orders);

    void deleteOrders(String id);

    List<Orders> findAllToOrders();

    List<Orders> findByUid(int uid);

    List<Orders> findByRid(String rid);

    List<Orders> findAllForOrders();

    List<Orders> findToUser(int uid);

    void cancelOrders(String oid);

    void updateStatus(String oid);

    void updateStatusTime(String oid);
}
