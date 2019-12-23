package good.service;

import good.domain.RoomImg;

import java.util.List;

public interface IRoomImgService {

    RoomImg findByRid(String rid);

    void updateRoomImg(RoomImg roomImg);

    void add(RoomImg roomImg);

    void deleteRoomImg(String rid);

    String findFirstImg(String rid);
}
