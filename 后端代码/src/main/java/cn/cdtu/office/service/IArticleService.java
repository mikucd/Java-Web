package cn.cdtu.office.service;

import cn.cdtu.office.common.PageResult;
import cn.cdtu.office.common.Result;
import cn.cdtu.office.entity.Article;

import java.util.List;

public interface IArticleService {

    /**
     * 列表查询
     *
     * @param article
     * @return
     */
    List<Article> getList(Article article);

    /**
     * 分页查询
     *
     * @param article
     * @return
     */
    PageResult<Article> getPage(Article article);

    /**
     * 新增或修改物品
     *
     * @param article
     * @return
     */
    Result<?> saveOrUpdate(Article article);

    /**
     * 根据主键删除
     *
     * @param articleId
     */
    void deleteById(Integer articleId);
}
