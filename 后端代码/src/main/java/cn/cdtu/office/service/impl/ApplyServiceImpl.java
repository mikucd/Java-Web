package cn.cdtu.office.service.impl;

import cn.cdtu.office.common.PageResult;
import cn.cdtu.office.common.Result;
import cn.cdtu.office.entity.Apply;
import cn.cdtu.office.mapper.ApplyMapper;
import cn.cdtu.office.service.IApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ApplyServiceImpl implements IApplyService {


    @Autowired
    private ApplyMapper applyMapper;

    /**
     * 分页查询
     *
     * @param apply
     * @return
     */
    @Override
    public PageResult<Apply> getPage(Apply apply) {
        Integer count = applyMapper.selectCount(apply);
        if (count == 0) {
            // 无记录
            return new PageResult<>();
        }
        // 有记录，查询列表
        List<Apply> applyList = applyMapper.selectPage(apply);
        return new PageResult<>(applyList, count);
    }

    /**
     * 申请审核
     *
     * @param apply
     * @return
     */
    @Override
    public Result<?> applyAudit(Apply apply) {
        apply.setAuditTime(new Date());
        apply.setUpdateTime(new Date());
        applyMapper.updateById(apply);
        return Result.OK("审核成功");
    }
}
