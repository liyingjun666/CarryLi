package com.liyingjun.course.server;

import com.liyingjun.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/")
@RequestMapping(value = "/v1")
@Resource
public class MyPostMethod {

    //声明cookie变量接收cookie信息
    @Autowired
     private static Cookie cookie;

     //用户登录成功 获取cookies，携带cookies访问其他接口获取到列表
    @RequestMapping(value = "/postCookies",method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口，登录成功获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String username,
                        @RequestParam (value = "password",required = true) String password){
        if (username.equals("liyingjun")&& password.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "登陆成功!";
        }
        return "用户名或密码错误";
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){

        User user;
        //获取cookies
        Cookie[] cookies= request.getCookies();
        // 验证cookies是否合法
        for (Cookie c:cookies){
            if (c.getName().equals("login")
                    && c.getValue().equals("true")
                    && u.getUserName().equals("liyingjun")
                    && u.getPassword().equals("123456")
            ){
                user =new User();
                user.setUserName("lisi");
                user.setAge("18");
                user.setSex("man");
                return user.toString();
            }
        }
        return "参数不合法";
    }

}
