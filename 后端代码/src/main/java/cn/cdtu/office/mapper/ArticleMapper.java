package cn.cdtu.office.mapper;

import cn.cdtu.office.common.Result;
import cn.cdtu.office.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 列表查询
     *
     * @param article
     * @return
     */
    List<Article> selectList(Article article);

    /**
     * 分页查询
     *
     * @param article
     * @return
     */
    List<Article> selectPage(Article article);

    /**
     * 总记录数查询
     *
     * @param article
     * @return
     */
    Integer selectCount(Article article);

    /**
     * 新增物品
     *
     * @param article
     */
    void insertArticle(Article article);

    /**
     * 根据主键修改
     *
     * @param article
     */
    void updateById(Article article);

    /**
     * 根据主键删除
     *
     * @param articleId
     */
    void deleteById(Integer articleId);

}
