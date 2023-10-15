package cn.cdtu.office.service.impl;



import cn.cdtu.office.common.PageResult;
import cn.cdtu.office.common.Result;
import cn.cdtu.office.entity.Article;
import cn.cdtu.office.mapper.ArticleMapper;
import cn.cdtu.office.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 列表查询
     *
     * @param article
     * @return
     */
    @Override
    public List<Article> getList(Article article){
        return articleMapper.selectList(article);
    }


    /**
     * 分页查询
     *
     * @param article
     * @return
     */
    @Override
    public PageResult<Article> getPage(Article article) {
        // 1.查询总记录数
        Integer count = articleMapper.selectCount(article);
        if (count == 0) {
            // 如果总记录数为0，表示没有数据，那么调用缺省的构造器赋默认值
            return new PageResult<>();
        }
        // 2.记录数不为0，表示有数据，查询出列表数据并赋值
        List<Article> articleList = articleMapper.selectPage(article);
        return new PageResult<>(articleList, count);
    }


    /**
     * 新增或修改物品
     *
     * @param article
     */
    @Override
    public Result<?> saveOrUpdate(Article article) {
        if (null == article.getArticleId()) {
            // ID为空表示新增
            // 设置默认值
            article.setCreateTime(new Date());
            article.setCreateBy("admin");
            articleMapper.insertArticle(article);
            return Result.OK("新增成功");
        } else {
            // 修改
            article.setUpdateBy("admin");
            article.setUpdateTime(new Date());
            articleMapper.updateById(article);
            return Result.OK("修改成功");
        }
    }
    /**
     * 根据主键删除
     *
     * @param articleId
     */
    @Override
    public void deleteById(Integer articleId) {
        articleMapper.deleteById(articleId);
    }
}
