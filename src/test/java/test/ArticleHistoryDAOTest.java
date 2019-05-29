package test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import weonforall.capstone.news_recommendation.domain.ArticleHistoryVO;
import weonforall.capstone.news_recommendation.domain.ArticleVO;
import weonforall.capstone.news_recommendation.domain.UserVO;
import weonforall.capstone.news_recommendation.persistence.IArticleDAO;
import weonforall.capstone.news_recommendation.persistence.IArticleHistoryDAO;
import weonforall.capstone.news_recommendation.persistence.IUserDAO;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleHistoryDAOTest {
    @Autowired
    private IArticleHistoryDAO iArticleHistoryDAO;

    @Autowired
    private IArticleDAO iArticleDAO;

    @Autowired
    private IUserDAO iUserDAO;

    @Test
    public void Order1_InsertArticleHistory() throws Exception {

        try {
            UserVO userVO = iUserDAO.getUser("usertest00");
            for (int i = 3; i < 100; i++) {

                ArticleVO articleVO = iArticleDAO.getArticle((long) i);
                if (articleVO == null) continue;

                ArticleHistoryVO articleHistoryVO = new ArticleHistoryVO();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                articleHistoryVO.setAid(articleVO.getAid());
                articleHistoryVO.setUid(userVO.getUid());
                articleHistoryVO.setFeedback(true);
                articleHistoryVO.setTimestamp(timestamp);
                articleHistoryVO.setRead(false);

                iArticleHistoryDAO.insertArticleHistory(articleHistoryVO);
            }
            assertTrue(true);
        } catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void Order2_GetArticleHistory() throws Exception {
        try {
            System.out.println(iArticleHistoryDAO.getArticleHistory((long) 1, "usertest00"));
            assertTrue(true);
        } catch(Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void Order3_GetRecommendArticles() throws Exception {
        try {
            List<ArticleVO> articleVOList = iArticleHistoryDAO.getRecommendedArticles("usertest00");
            if (articleVOList == null) {
                System.out.println("null");
                return;
            }

            for (int i = 0; i < articleVOList.size(); i++) {
                System.out.println(articleVOList.get(i));
            }
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

}
