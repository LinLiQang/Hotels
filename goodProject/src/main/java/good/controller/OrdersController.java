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

    /**管理员操作**/

    /**
     * 后台查询所有订单
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true, defaultValue = "1")int page,
                                @RequestParam(name = "size",required = true, defaultValue = "5")int size){
        ModelAndView mv = new ModelAndView();

        //查询所有订单前遍历数据库修改订单状态
        List<Orders> updateTimeOrders = ordersService.findAllToOrders();
        Date date = new Date();
        for(Orders order : updateTimeOrders){
            if(order.getOrdersStatus() == 1) {
                if (order.getEndTime().compareTo(date) <= 0) {
                    ordersService.updateStatusTime(order.getOid());
                }
            }
        }

        List<Orders> orders = ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("ordersList",pageInfo);
        mv.setViewName("ordersList");
        return mv;
    }

    /**
     * 后台根据ID查询订单
     * @param oid
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name="oid",required = true) String oid){
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(oid);
        mv.addObject("orders",orders);
        mv.setViewName("ordersShow");
        return mv;
    }

    /**
     * 后台修改订单前查询订单原来的信息
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
     * 后台修改订单信息
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
     * 后台根据oid删除订单
     * @param id
     * @return
     */
    @RequestMapping("/deleteOrders")
    public ModelAndView deleteOrders(String id){
        ordersService.deleteOrders(id);
        return new ModelAndView("redirect:findAll");
    }

    /**
     * 后台删除选中的订单
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
     * 后台添加订单
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

    /**管理员操作**/


    /**用户操作**/

    /**
     * 用户预定客房
     * @return
     */
    @RequestMapping("/toOrders")
    @ResponseBody
    public Map<String, Object> ToOrders(Orders orders){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Orders> ordersList = ordersService.findAllToOrders();
        Boolean boo = true;
        for(Orders order : ordersList){
            if(orders.getStartTime().compareTo(order.getStartTime())>=0 && orders.getStartTime().compareTo(order.getEndTime())<=0 &&
               orders.getRid().equals(order.getRid()) && order.getOrdersStatus() == 1){
                boo = false;
            }
            if(orders.getEndTime().compareTo(order.getStartTime())>=0 && orders.getEndTime().compareTo(order.getEndTime())<=0 &&
                    orders.getRid().equals(order.getRid()) && order.getOrdersStatus() == 1){
                boo = false;
            }
            if(orders.getStartTime().compareTo(order.getStartTime())<=0 && orders.getEndTime().compareTo(order.getEndTime())>=0 &&
                    orders.getRid().equals(order.getRid()) && order.getOrdersStatus() == 1){
                boo = false;
            }
        }
        if(boo){
            String id = RandomUtils.idRandom();
            orders.setOid(id);
            orders.setOrdersStatus(1);
            ordersService.add(orders);
            map.put("flag", true);
            map.put("msg", "添加成功！");
            return map;
        }else{
            map.put("flag",false);
            map.put("msg","当前时段该房间被预定！");
            return map;
        }
    }

    /**
     * 取消订单
     * @param oid
     * @return
     */
    @RequestMapping(value = "/cancelOrders")
    @ResponseBody
    public Map<String, Object> cancelOrders(String oid){
        Map<String, Object> map = new HashMap<>();
        ordersService.cancelOrders(oid);
        map.put("flag",true);
        map.put("msg","退订成功！");
        return map;
    }
    /**用户操作**/

}
