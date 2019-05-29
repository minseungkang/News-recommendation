package test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import weonforall.capstone.news_recommendation.domain.UserVO;
import weonforall.capstone.news_recommendation.enums.Status;
import weonforall.capstone.news_recommendation.model.Result;
import weonforall.capstone.news_recommendation.persistence.IUserDAO;

import java.sql.Time;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
public class UserDAOTest {

    @Autowired
    private IUserDAO iUserDAO;

    @Test
    public void Order1_InsertUser() throws Exception {
        try {
            String uid = UUID.randomUUID().toString();

            Result result;
            int maximumLoopCnt = 100;
            int loopCnt = 0;
            while (loopCnt < maximumLoopCnt) {
                uid = UUID.randomUUID().toString();

                // check uid exists in db
                if (iUserDAO.checkUUid(uid) == 0) break;
                loopCnt++;
            }

            if (loopCnt >= maximumLoopCnt) {
                result = new Result(Status.Key.INVALID, Status.Obj.USER, "uid", uid);
            }

            UserVO userVO = new UserVO();
            userVO.setUid(uid);
            userVO.setPushTime(new Time(0));
            iUserDAO.insertUser(userVO);

            result = new Result(Status.Key.SUCCEED, Status.Obj.REQUEST, "uservo", userVO);
            System.out.println(result);
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void Order2_GetUser() throws Exception {
        try {
            System.out.println(iUserDAO.getUser("usertest00"));
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
