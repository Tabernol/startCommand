package command;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Answer;
import models.Question;
import servises.AnswerService;
import servises.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditQuestion implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();
        String testId = req.getParameter("test_id");
        String questionId = req.getParameter("question_id");
        req.setAttribute("test_id", req.getParameter("test_id"));

        Question question = null;
        List<Answer> answers = null;
        try {
            question = questionService.get(Long.valueOf(questionId));
            answers = answerService.getAnswers(Long.valueOf(questionId));
            req.setAttribute("answers", answers);
            req.setAttribute("page", req.getParameter("page"));
            req.setAttribute("question", question);

            req.getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }


    }
}
