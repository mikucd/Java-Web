package cn.cdtu.office.controller;

import cn.cdtu.office.common.PageResult;
import cn.cdtu.office.common.Result;
import cn.cdtu.office.entity.Article;
import cn.cdtu.office.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    /**
     * 列表查询
     *
     * @param article
     * @return
     */
    @GetMapping("/queryList")
    public Result<?> queryList(Article article){
        List<Article> list =articleService.getList(article);
        return  Result.OK(list);

    }
    /**
     * 分页查询0
     *
     * @param article
     * @return
     */
    @GetMapping("/queryPage")
    public Result<?> queryPage(Article article) {
        PageResult<Article> page = articleService.getPage(article);
        return Result.OK(page);
    }
    /**
     * 根据主键删除
     *
     * @param articleId
     * @return
     */
    @GetMapping("/deleteById")
    public Result<?> deleteById(@RequestParam("articleId") Integer articleId) {
        articleService.deleteById(articleId);
        return Result.OK("删除成功");
    }
    /**
     * 新增或修改物品
     *
     * @param article
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody Article article) {
        return articleService.saveOrUpdate(article);
    }
}
