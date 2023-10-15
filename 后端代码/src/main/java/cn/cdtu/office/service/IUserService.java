package cn.cdtu.office.service;

import cn.cdtu.office.common.Result;
import cn.cdtu.office.entity.Article;
import cn.cdtu.office.entity.User;

import java.util.List;

public interface IUserService {

    /**
     * 用户登录
     *
     * @param user
     * @return
     */

    Result<?> login(User user);

    /**
     * 列表查询
     *
     * @param user
     * @return
     */
    List<User> getList(User user);
}
