package good.service.impl;

import com.github.pagehelper.PageHelper;
import good.dao.ICommentsDao;
import good.domain.Comments;
import good.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentsServiceImpl implements ICommentsService {

    @Autowired
    private ICommentsDao commentsDao;

    /**
     * 查询所有评论
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Comments> findAll(int page, int size) {
        //分页管理pageHelper
        PageHelper.startPage(page,size);
        return commentsDao.findAll();
    }

    /**
     * 根据cid删除评论
     * @param cid
     */
    @Override
    public void deleteComments(String cid) {
        commentsDao.deleteComments(cid);
    }

    /**
     * 根据ID查询评论
     * @param cid
     * @return
     */
    @Override
    public Comments findById(String cid) {
        return commentsDao.findById(cid);
    }

    /**
     * 根据uid查询评论及其对应用户等具体信息
     * @param uid
     * @return
     */
    @Override
    public List<Comments> findByUid(int uid) {
        return commentsDao.findByUid(uid);
    }

    /**
     * 根据rid查询评论及其对应用户等具体信息
     * @param rid
     * @return
     */
    @Override
    public List<Comments> findByRid(String rid) {
        return commentsDao.findByRid(rid);
    }
}
