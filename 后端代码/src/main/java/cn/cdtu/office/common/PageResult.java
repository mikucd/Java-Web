package cn.cdtu.office.common;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class PageResult<T> {

    /**
     * 结果集
     */
    private List<T> data;
    /**
     * 总记录数
     */
    private Integer total;

    public PageResult(List<T> data, Integer total) {
        this.data = data;
        this.total = total;
    }

    public PageResult() {
        this(Collections.emptyList(), 0);
    }
}
