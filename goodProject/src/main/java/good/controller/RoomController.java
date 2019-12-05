package good.controller;

import com.github.pagehelper.PageInfo;
import good.domain.Comments;
import good.domain.Room;
import good.domain.RoomImg;
import good.service.ICommentsService;
import good.service.IRoomImgService;
import good.service.IRoomService;
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
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private ICommentsService commentsService;

    @Autowired
    private IRoomImgService roomImgService;

    /**
     * 查询所有客房信息
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true, defaultValue = "1")int page,
                                @RequestParam(name = "size",required = true, defaultValue = "5")int size){
        ModelAndView mv = new ModelAndView();
        List<Room> rooms = roomService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(rooms);
        mv.addObject("roomList",pageInfo);
        mv.setViewName("roomList");
        return mv;
    }

    /**
     * 添加客房
     * @param room
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(Room room,RoomImg roomImg){
        Map<String, Object> map = new HashMap<>();
        if(room.getRid() != null && room.getRid() != "" && room.getRoomPrice() != null && room.getRoomPrice() > 0 &&
                roomImg.getFirstImg() != null && roomImg.getFirstImg() != "" &&
                roomImg.getSecondImg() != null && roomImg.getSecondImg() != "" &&
                roomImg.getThirdImg() != null && roomImg.getThirdImg() != ""){
            if(roomService.findById(room.getRid()) == null){
                roomService.add(room);
                roomImgService.add(roomImg);
                map.put("flag",true);
                map.put("msg","添加成功！");
                return map;
            }else{
                map.put("flag",false);
                map.put("msg","产品已存在！");
                return map;
            }
        }else{
            map.put("flag",false);
            map.put("msg","数据异常！");
            return map;
        }
    }

    /**
     * 修改客房信息之前查询原本的客房信息
     * @param id
     * @return
     */
    @RequestMapping("/beforeUpdateRoom")
    public ModelAndView beforeUpdateRoom(String id){
        ModelAndView modelAndView = new ModelAndView();
        Room room = roomService.findById(id);
        RoomImg roomImg = roomImgService.findByRid(id);
        modelAndView.addObject("room",room);
        modelAndView.addObject("roomImg",roomImg);
        modelAndView.setViewName("updateRoom");
        return modelAndView;
    }

    /**
     * 修改客房信息
     * @param room
     * @return
     */
    @RequestMapping("/updateRoom")
    @ResponseBody
    public Map<String, Object> updateRoom(Room room,RoomImg roomImg){
        Map<String, Object> map = new HashMap<String, Object>();
        if(room.getRid() != null && room.getRid() != "" && room.getRoomPrice() != null && room.getRoomPrice() > 0 &&
                roomImg.getFirstImg() != null && roomImg.getFirstImg() != "" &&
                roomImg.getSecondImg() != null && roomImg.getSecondImg() != "" &&
                roomImg.getThirdImg() != null && roomImg.getThirdImg() != ""){
            roomService.updateRoom(room);
            roomImgService.updateRoomImg(roomImg);
            map.put("flag", true);
            map.put("msg", "修改成功！");
            return map;
        }else{
            map.put("flag",false);
            map.put("msg","数据异常！");
            return map;
        }
    }

    /**
     * 根据客房号删除客房
     * @param id
     * @return
     */
    @RequestMapping("/deleteRoom")
    public ModelAndView deleteRoom(String id){
        roomImgService.deleteRoomImg(id);
        roomService.deleteRoom(id);
        return new ModelAndView("redirect:findAll");
    }

    /**
     * 删除选中的客房
     * @param ids
     * @return
     */
    @RequestMapping("/deleteSelect")
    @ResponseBody
    public Map<String, Object> deleteSelect(@RequestParam(value = "ids[]")String[] ids){
        Map<String, Object> map = new HashMap<>();
        for(String rid : ids){
            roomImgService.deleteRoomImg(rid);
            roomService.deleteRoom(rid);
        }
        map.put("flag", true);
        map.put("msg","删除成功！");
        return map;
    }

    /**
     * 为订单模块根据客房的不同异步更新价格
     * @param rid
     * @return
     */
    @RequestMapping("/findPrice")
    @ResponseBody
    public Map<String, Object> findPrice(@RequestParam(value = "rid")String rid){
        Map<String, Object> map = new HashMap<>();
        List<Room> rooms = roomService.findAllToOrders();
        for(Room room : rooms){
            if(room.getRid().equals(rid)){
                map.put("price",room.getRoomPrice());
                return map;
            }
        }
        map.put("msg","没找到");
        return map;
    }

    /**
     * 根据rid查询客房信息及订单等详细信息
     * @param rid
     * @return
     */
    @RequestMapping("/findByRid")
    public ModelAndView findByRid(@RequestParam(name="rid",required = true) String rid){
        ModelAndView mv = new ModelAndView();
        List<Comments> commentsList = commentsService.findByRid(rid);
        Room room = roomService.findById(rid);
        RoomImg roomImg = roomImgService.findByRid(rid);
        mv.addObject("room",room);
        mv.addObject("commentsList",commentsList);
        mv.addObject("roomImg",roomImg);
        mv.setViewName("roomShow");
        return mv;
    }

}
