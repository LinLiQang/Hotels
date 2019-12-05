package good.service;

import good.domain.Comments;

import java.util.List;

public interface ICommentsService {

    public List<Comments> findAll(int page, int size);

    void deleteComments(String cid);

    Comments findById(String cid);

    List<Comments> findByUid(int uid);

    List<Comments> findByRid(String rid);
}
