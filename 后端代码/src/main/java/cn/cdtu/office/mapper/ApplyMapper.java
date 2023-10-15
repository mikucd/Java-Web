package cn.cdtu.office.mapper;

import cn.cdtu.office.entity.Apply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ApplyMapper {

    /**
     * 分页查询
     *
     * @param apply
     * @return
     */
    List<Apply> selectPage(Apply apply);

    /**
     * 总记录数查询
     *
     * @param apply
     * @return
     */
    Integer selectCount(Apply apply);

    /**
     * 根据主键修改
     *
     * @param apply
     */
    void updateById(Apply apply);

}
