package cn.cdtu.office.controller;

import cn.cdtu.office.common.PageResult;
import cn.cdtu.office.common.Result;
import cn.cdtu.office.entity.Apply;
import cn.cdtu.office.service.IApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/apply")
public class ApplyContrller {

    @Autowired
    private IApplyService applyService;

    /**
     * 分页查询
     *
     * @param apply
     * @return
     */

    @GetMapping("/queryPage")
    public Result<?> queryPage(Apply apply) {
        PageResult<Apply> page = applyService.getPage(apply);
        return Result.OK(page);
    }


    /**
     * 申请审核
     *
     * @param apply
     * @return
     */
    @PostMapping("/applyAudit")
    public Result<?> applyAudit(@RequestBody Apply apply) {
        return applyService.applyAudit(apply);
    }
}
