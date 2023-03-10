package command.post;

import command.get.EditQuestion;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.AnswerRepo;
import repo.QuestionRepo;
import servises.AnswerService;
import servises.QuestionService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AddAnswer.class is allowed only for admin.
 * The meaning of the class is to add an Answer to an existing Question in database.
 * @author makskrasnopolskyi@gmail.com
 */
public class AddAnswer implements RequestHandler {
    private static Logger logger = LogManager.getLogger(AddAnswer.class);
    AnswerService answerService = new AnswerService(new AnswerRepo(), new ValidatorService(new DataValidator()));

    /**
     * This method is read parameter from request.
     * It calls the service layer to create Answer
     * if DataBaseException is caught, redirects to error page.
     * if ValidateException is caught, redirects to the page from which the request was made
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));
        String page = req.getParameter("page");
        String text = req.getParameter("text");
        Boolean result = Boolean.valueOf(req.getParameter("result"));

        try {
            answerService.createAnswer(questionId, text, result);
            logger.info("Answer for question id " + questionId + "has added");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question"+
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&message_answer=All Right");

        } catch (DataBaseException e) {
            logger.warn("Answer for question id " + questionId + "has not added", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        } catch (ValidateException e) {
            logger.info("Answer for question id " + questionId + "is invalid", e);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question"+
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&too_long_answer=" + text +
                    "&message_answer=" + "answer is too long");
        }
    }
}
