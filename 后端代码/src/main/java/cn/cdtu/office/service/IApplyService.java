package cn.cdtu.office.service;

import cn.cdtu.office.common.PageResult;
import cn.cdtu.office.common.Result;
import cn.cdtu.office.entity.Apply;

public interface IApplyService {

    /**
     * 分页查询
     *
     * @param apply
     * @return
     */
    PageResult<Apply> getPage(Apply apply);


    /**
     * 申请审核
     *
     * @param apply
     * @return
     */
    Result<?> applyAudit(Apply apply);
}
