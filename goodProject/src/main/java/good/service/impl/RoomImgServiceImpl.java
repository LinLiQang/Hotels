package good.service.impl;

import good.dao.IRoomImgDao;
import good.domain.RoomImg;
import good.service.IRoomImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomImgServiceImpl implements IRoomImgService {

    @Autowired
    private IRoomImgDao roomImgDao;

    /**
     * 根据rid查询房间图片
     * @param rid
     * @return
     */
    @Override
    public RoomImg findByRid(String rid) {
        return roomImgDao.findByRid(rid);
    }

    @Override
    public void updateRoomImg(RoomImg roomImg) {
        roomImgDao.updateRoomImg(roomImg);
    }

    @Override
    public void add(RoomImg roomImg) {
        roomImgDao.add(roomImg);
    }

    @Override
    public void deleteRoomImg(String rid) {
        roomImgDao.deleteRoomImg(rid);
    }
}
