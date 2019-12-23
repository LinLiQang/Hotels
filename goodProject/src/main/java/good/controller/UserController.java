package good.controller;

import com.github.pagehelper.PageInfo;
import good.domain.Orders;
import good.domain.User;
import good.service.IOrdersService;
import good.service.IRoomImgService;
import good.service.IUserService;
import good.utils.JavaToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrdersService ordersService;

    /**管理员操作**/

    /**
     * 后台登录时验证码的生成
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/checkCode")
    public void CheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //创建图片
        int width = 85;
        int height = 35;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.PINK);
        //填充图片
        graphics.fillRect(0, 0, width, height);

        //产生随机码
        String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int size = base.length();
        Random r = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 1; i <= 4; i++){
            int index = r.nextInt(size);
            char c = base.charAt(index);
            stringBuffer.append(c);
        }
        String checkCode = stringBuffer.toString();

        //将验证码放入HttPSession
        request.getSession().setAttribute("CHECKCODE_SERVER",checkCode);

        //设置画笔为黄色
        graphics.setColor(Color.YELLOW);
        //设置字体大小
        graphics.setFont(new Font("楷体",Font.BOLD,24));
        //向图片上写入验证码
        graphics.drawString(checkCode, 15, 25);

        //将内存中图片输入到浏览器
        ImageIO.write(image,"PNG",response.getOutputStream());
    }


    /**
     * 后台查询所有用户
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true, defaultValue = "1")int page,
                                @RequestParam(name = "size",required = true, defaultValue = "5")int size){
        ModelAndView mv = new ModelAndView();
        List<User> users = userService.findAll(page,size);
        //PageInfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(users);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("userList");
        return mv;
    }

    /**
     * 后台编辑用户之前查询用户信息
     * @param id
     * @return
     */
    @RequestMapping("/beforeUpdateUser")
    public ModelAndView beforeUpdateUser(int id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findById(id);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("updateUser");
        return modelAndView;
    }

    /**
     * 后台编辑用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUser(@RequestParam(name = "photo",required = false) MultipartFile photo,
                                          User user,HttpServletRequest request) throws IOException{
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(user);
        if(user.getUsername() != null && user.getUsername() != "" &&
                user.getTel() != null && user.getTel() != ""){
            //从数据库中找出当前用户信息
            User u = userService.findById(user.getUid());
            //获取当前项目下的头像保存路径
            String uploadTargetPath = request.getSession().getServletContext().getRealPath("/") + "Avatar\\";

            //判断是否修改头像
            //是--保存图片，修改数据库存储信息
            //否--保存原来图片信息
            if(photo != null) {
                if (!photo.isEmpty()) {
                    String photoName = photo.getOriginalFilename();
                    File photoFile = new File(uploadTargetPath, photoName);
                    if (!photoFile.exists()) {
                        new File(uploadTargetPath).mkdirs();
                    }
                    photo.transferTo(photoFile);
                    user.setAvatar("Avatar/" + photoName);
                }else{
                    user.setAvatar(u.getAvatar());
                }
            }else {
                user.setAvatar(u.getAvatar());
            }
            userService.editUser(user);
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
     * 后台登录验证
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(User user, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        String checkCode_serv = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        //判断验证码是否正确
        if (checkCode_serv == null || !checkCode_serv.equalsIgnoreCase(checkCode)) {
            map.put("flag", false);
            map.put("msg", "验证码错误！");
            return map;
        }
        if (user.getUsername() != null && user.getUsername() != "" &&
                user.getPassword() != null && user.getPassword() != "") {
            //根据用户名查找用户
            User u = userService.findByUsername(user.getUsername());
            //判断用户是否存在
            if (u != null) {
                //判断用户是否被封禁
                if (u.getUserStatus() != 0) {
                    if(u.getUserStatus() == 3) {
                        //判断密码是否正确
                        if (u.getPassword().equals(user.getPassword())) {
                            //存入新的姓名
                            request.getSession().setAttribute("loginName", u.getName());
                            map.put("flag", true);
                            map.put("msg", "登陆成功！正在跳转至首页...");
                            return map;
                        } else {
                            map.put("flag", false);
                            map.put("msg", "密码错误！");
                            return map;
                        }
                    }else{
                        map.put("flag", false);
                        map.put("msg", "您无权访问此页面！");
                        return map;
                    }
                } else {
                    map.put("flag", false);
                    map.put("msg", "用户名已被禁止访问！请联系管理员解封！");
                    return map;
                }
            }else {
                map.put("flag", false);
                map.put("msg", "用户不存在！");
                return map;
            }
        }else {
            map.put("flag", false);
            map.put("msg", "用户名或密码不可为空！");
            return map;
        }
    }

    /**
     * 后台根据uid查询用户信息及订单等具体信息
     * @param uid
     * @return
     */
    @RequestMapping("/findByUid")
    public ModelAndView findByUid(@RequestParam(name="uid",required = true) int uid){
        ModelAndView mv = new ModelAndView();
        User user = userService.findById(uid);
        List<Orders> ordersList = ordersService.findByUid(uid);
        mv.addObject("user",user);
        mv.addObject("ordersList",ordersList);
        mv.setViewName("userShow");
        return mv;
    }

    /**
     * 后台删除用户
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(int id){
        userService.deleteUser(id);
        return new ModelAndView("redirect:findAll");
    }

    /**
     * 后台删除选中用户
     * @param ids
     * @return
     */
    @RequestMapping("/deleteSelect")
    @ResponseBody
    public Map<String, Object> deleteSelect(@RequestParam(value = "ids[]")String[] ids){
        Map<String, Object> map = new HashMap<>();
        for(String id : ids){
            int uid = Integer.parseInt(id);
            userService.deleteUser(uid);
        }
        map.put("flag", true);
        map.put("msg","删除成功！");
        return map;
    }

    /**
     * 后台退出登录
     * @param request
     * @return
     */
    @RequestMapping("/quit")
    public String quit(HttpServletRequest request){
        request.getSession().removeAttribute("loginName");
        return "login";
    }

    /**管理员操作**/


    /**用户操作**/

    /**
     * 前台注册用户
     * @param user
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(user.getUsername() != null && user.getUsername() != "" &&
                user.getPassword() != null && user.getPassword() != "" &&
                user.getTel() != null && user.getTel() != "" &&
                user.getName() != null && user.getName() != ""&&
                user.getIDcard() != null && user.getIDcard() != "") {
            User u = userService.findByUsername(user.getUsername());
            if(u != null){
                map.put("flag",false);
                map.put("msg","用户名已存在！");
                return map;
            }else if(!userService.findByName(user.getName())){
                map.put("flag",false);
                map.put("msg","此人已有账户！");
                return map;
            }else if(!userService.findByIDCard(user.getIDcard())){
                map.put("flag",false);
                map.put("msg","此身份证已被注册！");
                return map;
            }else{
                //设置用户默认状态为1/普通用户
                user.setUserStatus(1);
                user.setAvatar("Avatar/IronMan.jpg");
                userService.add(user);
                map.put("flag", true);
                map.put("msg", "注册成功！");
                return map;
            }
        }else{
            map.put("flag",false);
            map.put("msg","数据异常！");
            return map;
        }
    }


    /**
     * 前台用户找回密码
     * @param user
     * @return
     */
    @RequestMapping("/findPassword")
    @ResponseBody
    public Map<String, Object> findPassword(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        User u = userService.findByUsername(user.getUsername());
        if(u != null) {
            if ((u.getTel()).equals(user.getTel())) {
                u.setPassword(user.getPassword());
                userService.updateUser(u);
                map.put("flag", true);
                map.put("msg", "修改密码成功！");
                return map;
            } else {
                map.put("flag", false);
                map.put("msg", "该账号绑定手机号错误！请重新输入！");
                return map;
            }
        }else{
            map.put("flag", false);
            map.put("msg", "该账号不存在！请重新输入！");
            return map;
        }
    }


    /**
     * 前台用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public Map<String,Object> userLogin(@RequestParam(value = "username", required = true)String username,
                                        @RequestParam(value = "password", required = true)String password) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (username != null && username != "" && password != null && password != "") {
            //根据用户名查找用户
            User u = userService.findByUsername(username);
            //判断用户是否存在
            if (u != null) {
                //判断用户是否被封禁
                if (u.getUserStatus() != 0) {
                    if (u.getPassword().equals(password)) {
                        //调用函数生成token
                        String token = JavaToken.createToken(u.getUsername(),u.getPassword());
                        map.put("token",token);
                        map.put("user",u);
                        map.put("flag", true);
                        map.put("msg", "登陆成功！正在跳转至首页...");
                        return map;
                    } else {
                        map.put("flag", false);
                        map.put("msg", "密码错误！");
                        return map;
                    }
                } else {
                    map.put("flag", false);
                    map.put("msg", "用户名已被禁止访问！请联系管理员解封！");
                    return map;
                }
            }else {
                map.put("flag", false);
                map.put("msg", "用户不存在！请先注册！");
                return map;
            }
        }else {
            map.put("flag", false);
            map.put("msg", "用户名或密码不可为空！");
            return map;
        }
    }

    /**
     * 前台展示用户信息
     * @param username
     * @return
     */
    @RequestMapping("/userDetail")
    @ResponseBody
    public Map<String,Object> userDetail(String username){
        Map<String, Object> map = new HashMap<>();
        User user = userService.findByUsername(username);
        map.put("user",user);
        return map;
    }

    /**
     * 前台用户修改密码
     * @param uid
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
    public Map<String,Object> updatePassword(@RequestParam(value = "uid")int uid,
                                             @RequestParam(value = "oldPassword")String oldPassword,
                                             @RequestParam(value = "newPassword")String newPassword){
        Map<String,Object> map = new HashMap<>();
        User user = userService.findById(uid);
        if(oldPassword.equals(user.getPassword())){
            user.setPassword(newPassword);
            userService.updateUser(user);
            map.put("flag",true);
            map.put("msg","修改密码成功！");
            return map;
        }else{
            map.put("flag",false);
            map.put("msg","原密码不正确！请尝试找回密码！");
            return map;
        }
    }

    /**
     * 前台我的订单
     * @param uid
     * @return
     */
    @RequestMapping("/myOrders")
    @ResponseBody
    public Map<String,Object> myOrders(@RequestParam(value = "uid")int uid,
                                       @RequestParam(value = "status")int status){
        Map<String,Object> map = new HashMap<>();

        //查询所有订单前遍历数据库修改订单状态
        List<Orders> updateTimeOrders = ordersService.findAllToOrders();
        Date date = new Date();
        for(Orders order : updateTimeOrders){
            if(order.getEndTime().compareTo(date) <= 0){
                order.setOrdersStatus(2);
                ordersService.updateOrders(order);
            }
        }

        List<Orders> ordersList1 = ordersService.findToUser(uid);
        if(status == 4){
            map.put("ordersList",ordersList1);
        }else if(status == 0){
            List<Orders> ordersList = new ArrayList<>();
            for(Orders orders : ordersList1){
                if(orders.getOrdersStatus() == 0){
                    ordersList.add(orders);
                }
            }
            map.put("ordersList",ordersList);
        }else if(status == 1){
            List<Orders> ordersList = new ArrayList<>();
            for(Orders orders : ordersList1){
                if(orders.getOrdersStatus() == 1){
                    ordersList.add(orders);
                }
            }
            map.put("ordersList",ordersList);
        }else if(status == 2){
            List<Orders> ordersList = new ArrayList<>();
            for(Orders orders : ordersList1){
                if(orders.getOrdersStatus() == 2){
                    ordersList.add(orders);
                }
            }
            map.put("ordersList",ordersList);
        }else if(status == 3){
            List<Orders> ordersList = new ArrayList<>();
            for(Orders orders : ordersList1){
                if(orders.getOrdersStatus() == 3){
                    ordersList.add(orders);
                }
            }
            map.put("ordersList",ordersList);
        }
        return map;
    }

    /**用户操作**/

}
