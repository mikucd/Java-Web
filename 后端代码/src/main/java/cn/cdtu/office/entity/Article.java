package cn.cdtu.office.entity;

import cn.cdtu.office.common.PageQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class Article extends PageQuery {

    private Integer articleId;//物品ID
    private String articleName;//物品名称
    private Integer articleType;//物品分类
    private Integer stockNum;//库存数量
    private String stockUnit;//物品单位
    private String articleDescribe;//描述
    private String createBy;//创建人
    private String updateBy;//修改人
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
