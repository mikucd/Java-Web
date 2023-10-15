package cn.cdtu.office.entity;

import cn.cdtu.office.common.PageQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Apply extends PageQuery {
    /**
     * 主键
     */
    private Integer applyId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 物品ID
     */
    private Integer articleId;
    /**
     * 申请数量
     */
    private Integer applyNum;
    /**
     * 物品单位
     */
    private String applyUnit;
    /**
     * 申请时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    /**
     * 申请原因
     */
    private String applyReason;
    /**
     * 申请状态（0-已撤回 1-待审核 2-审核通过 3-驳回）
     */
    private Integer applyStatus;
    /**
     * 审核人
     */
    private String auditBy;
    /**
     * 审核时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;
    /**
     * 审核描述
     */
    private String auditRemark;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    // 冗余字段
    /**
     * 申请人
     */
    private String userName;
    /**
     * 物品名称
     */
    private String articleName;
}
