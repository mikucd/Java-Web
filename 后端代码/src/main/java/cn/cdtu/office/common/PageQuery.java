package cn.cdtu.office.common;


import lombok.Data;

@Data
public class PageQuery {

    private Integer currentPage = 1;//第一页

    private Integer pageSize = 5;//每页显示记录条数

    /**
     * 返回每页第一条数据对应的索引
     *
     * @return
     */
    private Integer getStart() {
        // limit 0,5 第一个参数为开始索引（从0开始），第二个参数为每页显示几条
        // 显而易见，第一页第一条数据的索引为0，即索引为0,1,2,3,4的五条
        // 第二页第一条的数据索引为5，即5,6,7,8,9这五条
        return (currentPage - 1) * pageSize;
    }

}
