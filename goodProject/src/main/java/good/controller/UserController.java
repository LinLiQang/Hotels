package good.controller;

import com.github.pagehelper.PageInfo;
import good.domain.Comments;
import good.domain.User;
import good.service.ICommentsService;
import good.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICommentsService commentsService;

    /**
     * 验证码的生成
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
     * 未分页的查询所有
     * @return
     */
    /*@RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<User> users = userService.findAll();
        mv.addObject("userList",users);
        mv.setViewName("userList");
        return mv;
    }*/

    /**
     * 分页查询所有用户
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
     * 编辑用户之前查询用户信息
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
     * 编辑用户信息
     * @param user
     * @return
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public Map<String, Object> updateUser(User user){
        Map<String, Object> map = new HashMap<String, Object>();
        if(user.getUsername() != null && user.getUsername() != "" &&
                user.getPassword() != null && user.getPassword() != "" &&
                user.getTel() != null && user.getTel() != "" &&
                user.getName() != null && user.getName() != ""){
            userService.updateUser(user);
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
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> add(User user, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(user.getUsername() != null && user.getUsername() != "" &&
                user.getPassword() != null && user.getPassword() != "" &&
                user.getTel() != null && user.getTel() != "" &&
                user.getName() != null && user.getName() != "") {
            User u = userService.findByUsername(user.getUsername());
            if(u != null){
                map.put("flag",false);
                map.put("msg","用户名已存在！");
                return map;
            }else if(!userService.findByName(user.getName())){
                map.put("flag",false);
                map.put("msg","此人已有账户！");
                return map;
            }else{
                //设置用户默认状态为1/普通用户
                user.setUserStatus(1);
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
     * 登录验证
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
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(int id){
        userService.deleteUser(id);
        return new ModelAndView("redirect:findAll");
    }

    /**
     * 删除选中用户
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
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/quit")
    public String quit(HttpServletRequest request){
        request.getSession().removeAttribute("loginName");
        return "login";
    }

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
                map.put("msg", "该账号绑定邮箱错误！请重新输入！");
                return map;
            }
        }else{
            map.put("flag", false);
            map.put("msg", "该账号不存在！请重新输入！");
            return map;
        }
    }

    /**
     * 根据uid查询用户信息及订单等具体信息
     * @param uid
     * @return
     */
    @RequestMapping("/findByUid")
    public ModelAndView findByUid(@RequestParam(name="uid",required = true) int uid){
        ModelAndView mv = new ModelAndView();
        List<Comments> commentsList = commentsService.findByUid(uid);
        User user = userService.findById(uid);
        mv.addObject("user",user);
        mv.addObject("commentsList",commentsList);
        mv.setViewName("userShow");
        return mv;
    }


}