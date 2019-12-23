package good.service;


import good.domain.Room;

import java.util.List;

public interface IRoomService {

    public List<Room> findAll(int page, int size);

    public void add(Room room);

    //public boolean findByPName(String productName);

    Room findById(String id);

    void updateRoom(Room room);

    void deleteRoom(String id);

    List<Room> findAllToOrders();

    List<Room> findByType(int page, int size, int type);

    List<Room> findByType(int type);

    List<Room> findAllToAdminOrders();

    List<Room> findAllToAdmin(int page, int size);
}
