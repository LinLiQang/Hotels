package good.controller;

import com.github.pagehelper.PageInfo;
import good.domain.Comments;
import good.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private ICommentsService commentsService;

    /**
     * 查询所有评论
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true, defaultValue = "1")int page,//获取页面参数page，默认值为1
                                @RequestParam(name = "size",required = true, defaultValue = "5")int size){//获取页面参数size，默认值为5
        ModelAndView mv = new ModelAndView();
        //创建评论对象集合获取数据库返回的数据
        List<Comments> comments = commentsService.findAll(page,size);
        //创建PageHelper对象并将评论对象集合添加进去
        PageInfo pageInfo = new PageInfo(comments);
        //跳转至commentsList页面并传递pageInfo对象，命名为commentsList
        mv.addObject("commentsList",pageInfo);
        mv.setViewName("commentsList");
        return mv;
    }

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    @RequestMapping("/deleteComments")
    public ModelAndView deleteComments(String id){
        //调用service的方法删除评论
        commentsService.deleteComments(id);
        //跳转至findAll方法
        return new ModelAndView("redirect:findAll");
    }

    /**
     * 删除选中的评论
     * @param ids
     * @return
     */
    @RequestMapping("/deleteSelect")
    @ResponseBody
    public Map<String, Object> deleteSelect(@RequestParam(value = "ids[]")String[] ids){//获取页面参数ids数组
        Map<String, Object> map = new HashMap<>();
        //遍历数组，调用service的方法逐一删除
        for(String cid : ids){
            commentsService.deleteComments(cid);
        }
        //向页面返回数据
        map.put("flag", true);
        map.put("msg","删除成功！");
        return map;
    }

    /**
     * 根据cid查询评论详情
     * @param cid
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name="id",required = true) String cid){//获取页面参数id
        ModelAndView mv = new ModelAndView();
        //创建comments对象并从数据库获取该id的评论所有内容
        Comments comments = commentsService.findById(cid);
        //跳转至commentsShow页面，同时传递comments对象
        mv.addObject("comments",comments);
        mv.setViewName("commentsShow");
        return mv;
    }

    /**
     * 根据客房id查询该房间所有的评论
     * @param rid
     * @return
     */
    @RequestMapping(value = "/findCommentsByRid")
    @ResponseBody
    public Map<String,Object> findByRid(@RequestParam(name = "rid", required = true)String rid){
        Map<String,Object> map = new HashMap<>();
        List<Comments> commentsList = commentsService.findByRid(rid);
        map.put("commentsList",commentsList);
        return map;
    }

}
