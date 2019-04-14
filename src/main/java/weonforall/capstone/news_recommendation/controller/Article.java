package weonforall.capstone.news_recommendation.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import weonforall.capstone.news_recommendation.domain.ArticleHistoryVO;
import weonforall.capstone.news_recommendation.domain.ArticleVO;
import weonforall.capstone.news_recommendation.domain.UserVO;
import weonforall.capstone.news_recommendation.enums.Status;
import weonforall.capstone.news_recommendation.model.Result;
import weonforall.capstone.news_recommendation.persistence.IArticleDAO;
import weonforall.capstone.news_recommendation.persistence.IArticleHistoryDAO;
import weonforall.capstone.news_recommendation.persistence.IUserDAO;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@RestController
public class Article {

    @Inject
    IArticleHistoryDAO iArticleHistoryDAO;

    @Inject
    IArticleDAO iArticleDAO;

    @Inject
    IUserDAO iUserDAO;

    @RequestMapping(value = "/article/read", method = RequestMethod.POST)
    public @ResponseBody Result read(@RequestBody Map<String, Object> param) {
        String uid = (String)param.getOrDefault("uid", null);
        Long aid = Integer.toUnsignedLong((int)param.getOrDefault("aid", -1));

        Result result = null;

        try {
            ArticleVO articleVO = iArticleDAO.getArticle(aid);
            if (articleVO == null) {
                result = new Result(Status.Key.NOT_EXIST, Status.Obj.ARTICLE);
                return result;
            }

            UserVO userVO = iUserDAO.getUser(uid);
            if (userVO == null) {
                result = new Result(Status.Key.NOT_EXIST, Status.Obj.USER);
                return result;
            }

            ArticleHistoryVO articleHistoryVO = iArticleHistoryDAO.getArticleHistory(aid, uid);
            articleHistoryVO.setRead(true);
            iArticleHistoryDAO.updateArticleHistory(articleHistoryVO);

            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST, "readArticle", articleHistoryVO);
        } catch (Exception e) {
            // need to Logging
            result = new Result(Status.Key.UNKNOWN, Status.Obj.ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/article/get", method = RequestMethod.GET)
    public @ResponseBody Result get(@RequestParam(value = "uid") String _uid) {
        String uid = _uid;

        Result result = null;

        System.out.println("sss");
        if (uid == null) {
            result = new Result(Status.Key.INVALID, Status.Obj.PARAM);
            return result;
        }

        try {
            UserVO userVO = iUserDAO.getUser(uid);
            if (userVO == null) {
                result = new Result(Status.Key.NOT_EXIST, Status.Obj.USER);
                return result;
            }

            List<ArticleVO> articleList = iArticleHistoryDAO.getRecommendedArticles(uid);
            if (articleList == null) {
                result = new Result(Status.Key.NOT_EXIST, Status.Obj.ARTICLE_HISTORY);
                return result;
            }

            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST,"articles", articleList);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(Status.Key.UNKNOWN, Status.Obj.ERROR);
        }

        return result;
    }


    @RequestMapping(value = "/article/feedback", method = RequestMethod.POST)
    public @ResponseBody Result feedback(@RequestBody Map<String, Object> param) {
        String uid = (String) param.getOrDefault("uid", null);
        Long aid = Integer.toUnsignedLong((int)param.getOrDefault("aid", -1));
        Boolean feedback = (Boolean)param.getOrDefault(("feedback"), null);

        Result result = null;

        try {
            ArticleVO articleVO = iArticleDAO.getArticle(aid);
            if (articleVO == null) {
                result = new Result(Status.Key.NOT_EXIST, Status.Obj.ARTICLE);
                return result;
            }

            UserVO userVO = iUserDAO.getUser(uid);
            if(userVO == null) {
                result = new Result(Status.Key.NOT_EXIST, Status.Obj.USER);
                return result;
            }

            ArticleHistoryVO articleHistoryVO = iArticleHistoryDAO.getArticleHistory(aid, uid);
            articleHistoryVO.setFeedback(feedback);
            iArticleHistoryDAO.updateArticleHistory(articleHistoryVO);

            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST, "article", articleHistoryVO);
        } catch (Exception e) {
            result = new Result(Status.Key.UNKNOWN, Status.Obj.ERROR);
        }
        return result;
    }

}
