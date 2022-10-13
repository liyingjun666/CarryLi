package com.liyingjun.controller;

import com.baomidou.mybatisplus.annotation.TableId;
import com.liyingjun.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value = "v1",produces = "第一个版本")
@RequestMapping("v1")
public class DemoConterller {

    //获取一个执行sql的对象
    @Autowired
    private SqlSessionTemplate template ;

    //统计查询接口
    @GetMapping("/getUserCount")
    @ApiOperation(value = "统计用户数量",httpMethod = "GET")
    public int getUserCount(){
        return template.selectOne("getUserCount");

    }

    //新增接口
    @PostMapping("/addUser")
    @ApiOperation(value = "插入数据",httpMethod = "POST")
    public int addUser(@RequestBody User user){
       return template.insert("addUser",user);

    }

    //更新接口
    @PostMapping("/updateUser")
    @ApiOperation(value = "更新User表数据",httpMethod = "POST")
    public int updateUser(@RequestBody User user){
        return  template.update("updateUser", user);
    }

     //删除接口
     @GetMapping(value = "/deleteUser")
     @ApiOperation(value = "删除表数据",httpMethod = "GET")
    public int delUser(@RequestParam int id){
        return template.delete("deleteUser",id);
    }
}
