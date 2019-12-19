package good.controller;

import com.github.pagehelper.PageInfo;
import good.domain.Orders;
import good.domain.Room;
import good.domain.RoomImg;
import good.service.IOrdersService;
import good.service.IRoomImgService;
import good.service.IRoomService;
import good.utils.DateUtils;
import good.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IRoomImgService roomImgService;

    /**
     * 查询所有订单
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true, defaultValue = "1")int page,
                                @RequestParam(name = "size",required = true, defaultValue = "5")int size){
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("ordersList",pageInfo);
        mv.setViewName("ordersList");
        return mv;
    }

    /**
     * 根据ID查询订单
     * @param oid
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name="id",required = true) String oid){
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(oid);
        mv.addObject("orders",orders);
        mv.setViewName("ordersShow");
        return mv;
    }

    /**
     * 修改订单前查询订单原来的信息
     * @param id
     * @return
     */
    @RequestMapping("/beforeUpdateOrders")
    public ModelAndView beforeUpdateOrders(String id){
        ModelAndView modelAndView = new ModelAndView();
        Orders orders = ordersService.findById(id);
        List<Room> rooms = roomService.findAllToOrders();
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("rooms",rooms);
        modelAndView.setViewName("updateOrders");
        return modelAndView;
    }

    /**
     * 修改订单信息
     * @param orders
     * @return
     */
    @RequestMapping("/updateOrders")
    @ResponseBody
    public Map<String, Object> updateOrders(Orders orders){
        Map<String, Object> map = new HashMap<String, Object>();
        if(orders.getStartTime().compareTo(orders.getEndTime()) > 0){
            map.put("flag", false);
            map.put("msg", "时间错误！");
            return map;
        }
        ordersService.updateOrders(orders);
        map.put("flag", true);
        map.put("msg", "修改成功！");
        return map;
    }

    /**
     * 根据oid删除订单
     * @param id
     * @return
     */
    @RequestMapping("/deleteOrders")
    public ModelAndView deleteOrders(String id){
        ordersService.deleteOrders(id);
        return new ModelAndView("redirect:findAll");
    }

    /**
     * 删除选中的订单
     * @param ids
     * @return
     */
    @RequestMapping("/deleteSelect")
    @ResponseBody
    public Map<String, Object> deleteSelect(@RequestParam(value = "ids[]")String[] ids){
        Map<String, Object> map = new HashMap<>();
        for(String id : ids){
            ordersService.deleteOrders(id);
        }
        map.put("flag", true);
        map.put("msg","删除成功！");
        return map;
    }


    /**
     * 添加订单
     * @param orders
     * @return
     */
    @RequestMapping("/add")
    public Map<String, Object> add(Orders orders) {
        Map<String, Object> map = new HashMap<String, Object>();
        String id = RandomUtils.idRandom();
        orders.setOid(id);
        ordersService.add(orders);
        map.put("flag", true);
        map.put("msg", "添加成功！");
        return map;
    }

    /**
     * 用户根据需求查询符合的客房
     * @param startTime
     * @param endTime
     * @param type
     * @throws ParseException
     */
    /*@RequestMapping("/toOrders")
    public Map<String,Object> findToOrders(@RequestParam(value = "startTime")String startTime,
                             @RequestParam(value = "endTime")String endTime,
                             @RequestParam(value = "type")int type) throws ParseException {
        Map<String,Object> map = new HashMap<String, Object>();
        Date startTime1 = DateUtils.stringToDate(startTime,"yyyy-MM-dd");
        Date endTime1 = DateUtils.stringToDate(endTime,"yyyy-MM-dd");

        //从订单表中查出时间重复的房间
        List<Orders> ordersList = ordersService.findAllToOrders();
        List<Room> noRoom = new ArrayList<Room>();
        for(Orders orders : ordersList){
            if(startTime1.compareTo(orders.getStartTime())>0 && startTime1.compareTo(orders.getEndTime())<0 && orders.getOrdersStatus() == 1){
                noRoom.add(orders.getRoom());
            }else if(endTime1.compareTo(orders.getStartTime())>0 && endTime1.compareTo(orders.getEndTime())<0 && orders.getOrdersStatus() == 1){
                noRoom.add(orders.getRoom());
            }else if(startTime1.compareTo(orders.getStartTime())<0 && endTime1.compareTo(orders.getEndTime())>0 && orders.getOrdersStatus() == 1){
                noRoom.add(orders.getRoom());
            }
        }
        //除去重复的房间
        HashSet set = new HashSet(noRoom);
        noRoom.clear();
        noRoom.addAll(set);
        //查出所有房间并找出符合类型的房间
        List<Room> roomList = roomService.findAllToOrders();
        List<Room> rooms = new ArrayList<Room>();
        for(Room room : roomList){
            if(room.getType() == type){
                rooms.add(room);
            }
        }
        *//*此处需要在room实体类中重写hashCode()和equals()方法
        * 实现去除符合类型房间集合中时间不符合的房间*//*
        rooms.removeAll(noRoom);
        //遍历所有符合要求的可用房间集合
        for(Room room : rooms){
            room.setRoomImg(roomImgService.findByRid(room.getRid()));
            System.out.println(room);
        }
        if(rooms != null) {
            map.put("roomList", rooms);
            return map;
        }else{
            map.put("msg","当前时间段无您需要的房间!请您再考虑一下！");
            return map;
        }
    }*/


}
