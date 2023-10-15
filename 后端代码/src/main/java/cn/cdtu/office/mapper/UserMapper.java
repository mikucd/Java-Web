package cn.cdtu.office.mapper;

import cn.cdtu.office.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 使用@Mapper标记该类是一个Mapper接口，可以被SpringBoot自动扫描
 * 如果在启动类上配置了 @MapperScan(basePackages = {"cn.cdtu.office.mapper"}) 就可以
 * 不在接口上配置@Mapper
 */
@Mapper
public interface UserMapper {
    /**
     * 根据账号获取用户信息
     *
     * @param loginName "用户名"
     * @return
     */

    User selectByLoginName(String loginName);


    /**
     * 列表查询
     *
     * @param user
     * @return
     */
    List<User> selectList(User user);
}
