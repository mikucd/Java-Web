package cn.cdtu.office.controller;

import cn.cdtu.office.common.Result;
import cn.cdtu.office.entity.User;
import cn.cdtu.office.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户登录
     *
     * @param user
     * @return
     */

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        return userService.login(user);
    }



    /**
     * 列表查询
     *
     * @param user
     * @return
     */
    @GetMapping("/queryList")
    public Result<?> queryList(User user) {
        List<User> list = userService.getList(user);
        return Result.OK(list);
    }
}
