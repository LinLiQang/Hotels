package good.service.impl;

import com.github.pagehelper.PageHelper;
import good.dao.IRoomImgDao;
import good.domain.RoomImg;
import good.service.IRoomImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 修改图片路径
     * @param roomImg
     */
    @Override
    public void updateRoomImg(RoomImg roomImg) {
        roomImgDao.updateRoomImg(roomImg);
    }

    /**
     * 添加图片路径
     * @param roomImg
     */
    @Override
    public void add(RoomImg roomImg) {
        roomImgDao.add(roomImg);
    }

    /**
     * 删除图片路径
     * @param rid
     */
    @Override
    public void deleteRoomImg(String rid) {
        roomImgDao.deleteRoomImg(rid);
    }

    @Override
    public String findFirstImg(String rid) {
        return roomImgDao.findFirstImg(rid);
    }

}
