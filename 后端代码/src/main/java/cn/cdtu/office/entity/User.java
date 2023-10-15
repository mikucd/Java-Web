package cn.cdtu.office.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Data注解的作用：
 * 1.@Data可以为类提供读写功能，从而不用写get、set方法。
 * 2.会为类提供 equals()、hashCode()、toString() 方法。
 * 3.使用时IDEA需要安装Lombok插件
 */
@Data
public class User {

    private Integer userId;//主键
    private String loginName;//账号
    private String password;//密码
    private String userName;//姓名
    private String role;//角色
    private String avatar;//头像
    private Date createTime;//创建时间
    private String createBy;//创建人
    private Date updateTime;//修改时间
    private String updateBy;//修改人
}
