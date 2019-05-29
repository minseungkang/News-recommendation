package test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import weonforall.capstone.news_recommendation.domain.ArticleVO;
import weonforall.capstone.news_recommendation.persistence.IArticleDAO;

import java.sql.Timestamp;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArticleDAOTest {

    @Autowired
    private IArticleDAO iArticleDAO;

    @Test
    public void Order1_InsertArticle() throws Exception {

        try {
            for (int i = 11; i < 20; i++) {
                ArticleVO articleVO = new ArticleVO();
                articleVO.setTitle("testTitle" + i);
                articleVO.setContentPath("testPath" + i);
                articleVO.setUrl("testUrl" + i);

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                articleVO.setTimestamp(timestamp);
                articleVO.setUploadedTimestamp(timestamp);
                articleVO.setScore(10.00);

                iArticleDAO.insertArticle(articleVO);
            }
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void Order2_GetArticle() throws Exception {
        try {
            System.out.println(iArticleDAO.getArticle((long) 3));
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

}
