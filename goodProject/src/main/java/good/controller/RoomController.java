package good.controller;

import com.github.pagehelper.PageInfo;
import good.domain.Comments;
import good.domain.Orders;
import good.domain.Room;
import good.domain.RoomImg;
import good.service.ICommentsService;
import good.service.IOrdersService;
import good.service.IRoomImgService;
import good.service.IRoomService;
import good.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private ICommentsService commentsService;

    @Autowired
    private IRoomImgService roomImgService;

    /**管理员操作**/

    /**
     * 向后台返回所有客房信息
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true, defaultValue = "1")int page,
                                @RequestParam(name = "size",required = true, defaultValue = "5")int size){
        ModelAndView mv = new ModelAndView();
        List<Room> rooms = roomService.findAllToAdmin(page,size);
        PageInfo pageInfo = new PageInfo(rooms);
        mv.addObject("roomList",pageInfo);
        mv.setViewName("roomList");
        return mv;
    }

    /**
     * 后台添加客房
     * @param room
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestParam(name = "firstImg",required = true) MultipartFile firstImg,
                                   @RequestParam(name = "secondImg",required = true) MultipartFile secondImg,
                                   @RequestParam(name = "thirdImg",required = true) MultipartFile thirdImg,
                                   @RequestParam(name = "forthImg",required = true) MultipartFile forthImg,
                                   @RequestParam(name = "fifthImg",required = true) MultipartFile fifthImg,
                                   Room room, HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<>();
        if(!room.getRid().isEmpty() && room.getRoomPrice() != null && room.getRoomPrice() > 0 &&
                !room.getDetail().isEmpty() && !room.getIntroduction().isEmpty() &&
                !firstImg.isEmpty() && !secondImg.isEmpty() && !thirdImg.isEmpty() && !forthImg.isEmpty() && !fifthImg.isEmpty()){
            if(roomService.findById(room.getRid()) == null){
                RoomImg roomImg = new RoomImg();
                //获取存储图片路径
                String uploadTargetPath = request.getSession().getServletContext().getRealPath("/") + "RoomImage\\";
                //获取图片名称
                String firstImgName = firstImg.getOriginalFilename();
                String secondImgName = secondImg.getOriginalFilename();
                String thirdImgName = thirdImg.getOriginalFilename();
                String forthImgName = forthImg.getOriginalFilename();
                String fifthImgName = fifthImg.getOriginalFilename();
                //创建文件名
                File firstImgFile = new File( uploadTargetPath,firstImgName);
                File secondImgFile = new File( uploadTargetPath,secondImgName);
                File thirdImgFile = new File( uploadTargetPath,thirdImgName);
                File forthImgFile = new File(uploadTargetPath, forthImgName);
                File fifthImgFile = new File(uploadTargetPath, fifthImgName);
                //判断文件是否存在
                if(!firstImgFile.exists()) {
                    new File(uploadTargetPath).mkdirs();
                }
                if(!secondImgFile.exists()) {
                    new File(uploadTargetPath).mkdirs();
                }
                if(!thirdImgFile.exists()) {
                    new File(uploadTargetPath).mkdirs();
                }
                if(!forthImgFile.exists()){
                    new File(uploadTargetPath).mkdirs();
                }
                if(!fifthImgFile.exists()){
                    new File(uploadTargetPath).mkdirs();
                }
                //存储照片到服务器
                firstImg.transferTo(firstImgFile);
                secondImg.transferTo(secondImgFile);
                thirdImg.transferTo(thirdImgFile);
                forthImg.transferTo(forthImgFile);
                fifthImg.transferTo(fifthImgFile);
                //将图片名称存到roomImg类中
                roomImg.setRid(room.getRid());
                roomImg.setFirstImg("RoomImage/" + firstImgName);
                roomImg.setSecondImg("RoomImage/" + secondImgName);
                roomImg.setThirdImg("RoomImage/" + thirdImgName);
                roomImg.setForthImg("RoomImage/" + forthImgName);
                roomImg.setFifthImg("RoomImage/" + fifthImgName);
                //添加房间信息和照片信息
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
     * 后台修改客房信息之前查询原本的客房信息
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
     * 后台修改客房信息
     * @param room
     * @return
     */
    @RequestMapping(value = "/updateRoom", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateRoom(@RequestParam(name = "firstImg",required = true) MultipartFile firstImg,
                                          @RequestParam(name = "secondImg",required = true) MultipartFile secondImg,
                                          @RequestParam(name = "thirdImg",required = true) MultipartFile thirdImg,
                                          @RequestParam(name = "forthImg",required = true) MultipartFile forthImg,
                                          @RequestParam(name = "fifthImg",required = true) MultipartFile fifthImg,
                                          Room room, HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = 0;
        if(!room.getRid().isEmpty() && room.getRoomPrice() != null && room.getRoomPrice() > 0 && !room.getDetail().isEmpty() && !room.getIntroduction().isEmpty()){
            //从数据库中找出当前的图片数据
            RoomImg roomImg = roomImgService.findByRid(room.getRid());
            //获取存储房间图片路径
            String uploadTargetPath = request.getSession().getServletContext().getRealPath("/") + "RoomImage\\";

            if(!firstImg.isEmpty()){
                String firstImgName = firstImg.getOriginalFilename();
                File firstImgFile = new File( uploadTargetPath,firstImgName);
                if(!firstImgFile.exists()) {
                    new File(uploadTargetPath).mkdirs();
                }
                firstImg.transferTo(firstImgFile);
                roomImg.setFirstImg("RoomImage/" + firstImgName);
                n = n + 1;
            }
            if(!secondImg.isEmpty()){
                String secondImgName = secondImg.getOriginalFilename();
                File secondImgFile = new File( uploadTargetPath,secondImgName);
                if(!secondImgFile.exists()) {
                    new File(uploadTargetPath).mkdirs();
                }
                secondImg.transferTo(secondImgFile);
                roomImg.setSecondImg("RoomImage/" + secondImgName);
                n = n + 1;
            }
            if(!thirdImg.isEmpty()){
                String thirdImgName = thirdImg.getOriginalFilename();
                File thirdImgFile = new File( uploadTargetPath,thirdImgName);
                if(!thirdImgFile.exists()) {
                    new File(uploadTargetPath).mkdirs();
                }
                thirdImg.transferTo(thirdImgFile);
                roomImg.setThirdImg("RoomImage/" + thirdImgName);
                n = n + 1;
            }
            if(!forthImg.isEmpty()){
                String forthImgName = forthImg.getOriginalFilename();
                File forthImgFile = new File( uploadTargetPath,forthImgName);
                if(!forthImgFile.exists()) {
                    new File(uploadTargetPath).mkdirs();
                }
                forthImg.transferTo(forthImgFile);
                roomImg.setForthImg("RoomImage/" + forthImgName);
                n = n + 1;
            }
            if(!fifthImg.isEmpty()){
                String fifthImgName = fifthImg.getOriginalFilename();
                File fifthImgFile = new File( uploadTargetPath,fifthImgName);
                if(!fifthImgFile.exists()) {
                    new File(uploadTargetPath).mkdirs();
                }
                fifthImg.transferTo(fifthImgFile);
                roomImg.setFifthImg("RoomImage/" + fifthImgName);
                n = n + 1;
            }
            //修改图片信息和客房信息
            roomService.updateRoom(room);
            roomImgService.updateRoomImg(roomImg);
            if(n == 0){
                map.put("flag", true);
                map.put("msg","修改客房信息成功！图片信息并未修改");
                return map;
            }else if(n == 5){
                map.put("flag", true);
                map.put("msg","修改客房全部信息成功！");
                return map;
            }else {
                map.put("flag", true);
                map.put("msg","修改客房信息成功！未上传图片采用原照片！");
                return map;
            }
        }else{
            map.put("flag",false);
            map.put("msg","数据异常！");
            return map;
        }
    }

    /**
     * 后台根据rid删除客房
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
     * 后台删除选中的客房
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
     * 后台为订单模块根据客房的不同异步更新价格
     * @param rid
     * @return
     */
    @RequestMapping("/findPrice")
    @ResponseBody
    public Map<String, Object> findPrice(@RequestParam(value = "rid")String rid){
        Map<String, Object> map = new HashMap<>();
        List<Room> rooms = roomService.findAllToAdminOrders();
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
     * 后台根据rid查询客房信息及订单等详细信息
     * @param rid
     * @return
     */
    @RequestMapping("/findByRid")
    public ModelAndView findByRid(@RequestParam(name="rid",required = true) String rid){
        ModelAndView mv = new ModelAndView();
        Room room = roomService.findById(rid);
        RoomImg roomImg = roomImgService.findByRid(rid);
        System.out.println(roomImg);
        List<Orders> ordersList = ordersService.findByRid(rid);
        mv.addObject("room",room);
        mv.addObject("ordersList",ordersList);
        mv.addObject("roomImg",roomImg);
        mv.setViewName("roomShow");
        return mv;
    }

    /**管理员操作**/


    /**用户操作**/

    /**
     * 根据房间类型向前台返回房间
     * @param page
     * @param size
     * @param type
     * @return
     */
    @RequestMapping("/findAllRoomByType")
    @ResponseBody
    public Map<String,Object> findAllRoomByType(@RequestParam(name = "page",required = true, defaultValue = "1")int page,
                                                @RequestParam(name = "size",required = true, defaultValue = "6")int size,
                                                @RequestParam(name = "startTime",required = false)String startTime,
                                                @RequestParam(name = "endTime",required = false)String endTime,
                                                @RequestParam(name = "type",required = true)int type) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        //判断是否输入时间，未输入则是普通查找，输入时间则查找符合时间要求的房间
        if(startTime == null || endTime == null ) {
            //type=0为查询所有
            if(type == 0){
                List<Room> rooms = roomService.findAll(page, size);
                for (Room room:rooms) {
                    //为所有房间添加图片类
                    room.setRoomImg(roomImgService.findByRid(room.getRid()));
                }
                PageInfo pageInfo = new PageInfo(rooms);
                map.put("roomList",pageInfo);
                return map;
            }else {//按类别查找
                List<Room> rooms = roomService.findByType(page, size, type);
                for (Room room : rooms) {
                    room.setRoomImg(roomImgService.findByRid(room.getRid()));
                }
                PageInfo pageInfo = new PageInfo(rooms);
                map.put("roomList", pageInfo);
                return map;
            }
        }else{
            //根据时间查询
            //将时间字符串转为时间格式
            Date startTime1 = DateUtils.stringToDate(startTime,"yyyy-MM-dd");
            Date endTime1 = DateUtils.stringToDate(endTime,"yyyy-MM-dd");

            //从订单表中查出所有房间
            List<Orders> ordersList = ordersService.findAllForOrders();
            List<Room> noRoom = new ArrayList<Room>();
            for(Orders orders : ordersList){
                //判断是时间是否冲突并且订单是否正在进行
                if(startTime1.compareTo(orders.getStartTime())>=0 && startTime1.compareTo(orders.getEndTime())<=0 && orders.getOrdersStatus() == 1){
                    noRoom.add(orders.getRoom());
                }else if(endTime1.compareTo(orders.getStartTime())>=0 && endTime1.compareTo(orders.getEndTime())<=0 && orders.getOrdersStatus() == 1){
                    noRoom.add(orders.getRoom());
                }else if(startTime1.compareTo(orders.getStartTime())<=0 && endTime1.compareTo(orders.getEndTime())>=0 && orders.getOrdersStatus() == 1){
                    noRoom.add(orders.getRoom());
                }
            }
            //除去集合中重复的房间
            HashSet set = new HashSet(noRoom);
            noRoom.clear();
            noRoom.addAll(set);
            //查出所有房间并找出符合类型的房间
            List<Room> roomList = new ArrayList<>();
            if(type == 0) {//判断是否为查询所有
                roomList = roomService.findAllToOrders();
            }else {
                roomList = roomService.findByType(type);
            }
            //从符合类别的房间集合中去除时间冲突的房间
            roomList.removeAll(noRoom);
            List<Room> finalRoom = new ArrayList<>();
            if(page < 1) {
                page = 1;
            }
            if(page > (roomList.size()/size+1)) {
                page = roomList.size() / size + 1;
            }
            for(int i = (page*size-size); i <= (page*size-1); i++){
                finalRoom.add(roomList.get(i));
                if(i+1 == roomList.size())
                    break;
            }
            for(Room room : finalRoom){
                //向最终需要返回的房间集合插入图片对象
                room.setRoomImg(roomImgService.findByRid(room.getRid()));
                System.out.println(room);
            }
            if(finalRoom != null) {
                PageInfo pageInfo = new PageInfo(finalRoom);
                //设置最大页数
                if(roomList.size()%6 == 0){
                    pageInfo.setPages(roomList.size()/6);
                }else{
                    pageInfo.setPages(roomList.size()/6+1);
                }
                //设置当前页数
                pageInfo.setPageNum(page);
                //设置总数据条数
                pageInfo.setTotal(roomList.size());
                map.put("roomList", pageInfo);
                return map;
            }else{
                map.put("msg","当前时间段无您需要的房间!请您再考虑一下！");
                return map;
            }
        }
    }

    /**
     * 根据rid向前台返回客房详情和评论内容
     * @param rid
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Map<String,Object> findDetail(@RequestParam(name="rid",required = true)String rid){
        Map<String, Object> map = new HashMap<>();
        Room room = roomService.findById(rid);
        room.setRoomImg(roomImgService.findByRid(rid));
        List<Comments> comments = commentsService.findByRidToRoom(rid);
        map.put("room",room);
        map.put("comments", comments);
        return map;
    }

    /**用户操作**/

}
