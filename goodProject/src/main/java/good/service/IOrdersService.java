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
}
