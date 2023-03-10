package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.ResultRepo;
import servises.ResultService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * FinishTest.class is allowed only for student.
 * The meaning of the class is to calculate the result of the completed test, and show it
 * @author makskrasnopolskyi@gmail.com
 */
public class FinishTest implements RequestHandler {
    private static Logger logger = LogManager.getLogger(FinishTest.class);
    ResultService resultService = new ResultService(new ResultRepo());

    /**
     * This method is read parameter from request.
     * It calls the service layer to calculate the result of the completed test
     * if DataBaseException is caught, redirects to error page.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        List<Boolean> result = (List<Boolean>) req.getSession().getAttribute("result_test");
        Integer size = (Integer) req.getSession().getAttribute("size");
        Long testId = (Long) req.getSession().getAttribute("test_id");
        Long userId = (Long) req.getSession().getAttribute("user_id");


        try {
            Integer percentResult = resultService.getGrade(result, size);
            resultService.addResult(userId, testId, percentResult);
            logger.info("User with id " + userId + " finished test with id " + testId + " with grade " + percentResult);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/finish" +
                    "&percent_result=" + percentResult);
        } catch (DataBaseException e) {
            logger.warn("User with id " + userId + " finish test with id " + testId + " has trouble finish");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }


    }
}
