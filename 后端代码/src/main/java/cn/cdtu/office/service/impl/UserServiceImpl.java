package cn.cdtu.office.service.impl;

import cn.cdtu.office.common.PageResult;
import cn.cdtu.office.common.Result;
import cn.cdtu.office.entity.Article;
import cn.cdtu.office.entity.User;
import cn.cdtu.office.mapper.UserMapper;
import cn.cdtu.office.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


    /**
     * @Autowired 表示通过类型自动注入，可以理解为new UserMapper()
     * @Resource 作用相当于 @Autowired，但是他默认是按bean的名称自动注入
     */
    @Resource
    private UserMapper userMapper;

    @Override
    public Result<?> login(User user) {
        String loginName =user.getLoginName();//用户键入的用户名
        String Password =user.getPassword();//用户键入的密码

        User loginUser =userMapper.selectByLoginName(loginName);//获取数据库存储的用户名
        //查询
        if(loginUser==null)
            return Result.error("没有该用户，请注册");
        //先判断用户名                                    再判断密码
        if(loginName.equals(loginUser.getLoginName()) &&Password.equals(loginUser.getPassword())){
            return Result.OK(loginUser);
        }else{
            return Result.error("用户名或密码错误");
        }
    }

    @Override
    public List<User> getList(User user ){
        return userMapper.selectList(user);
    }
}
