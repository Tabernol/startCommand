package command.get;

import connection.MyDataSource;
import controllers.servlet.RequestHandler;
import dto.ResultDto;
import exeptions.DataBaseException;
import models.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.ResultRepo;
import repo.TestRepo;
import repo.UserRepo;
import servises.ResultService;
import servises.TestService;
import servises.UserService;
import servises.ValidatorService;
import util.MyTable;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FilterResult implements RequestHandler {
    private static Logger logger = LogManager.getLogger(FilterResult.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String sub = req.getParameter("sub");
        String order = req.getParameter("order");
        String rows = req.getParameter("rows");
        String page = req.getParameter("page");

        if (sub == null || order == null || rows == null) {
            sub = (String) req.getSession().getAttribute("sub");
            order = (String) req.getSession().getAttribute("order");
            rows = (String) req.getSession().getAttribute("rows");
            if (sub == null || order == null || rows == null) {
                sub = "all";
                order = "name asc";
                rows = "5";
                HttpSession session = req.getSession();
                session.setAttribute("sub", sub);
                session.setAttribute("order", order);
                session.setAttribute("rows", rows);
                page = "1";
            }
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("sub", sub);
            session.setAttribute("order", order);
            session.setAttribute("rows", rows);
            page = "1";
        }
        if (page == null) {
            page = "1";
        }

        System.out.println("page = " + page);

        ResultService resultService = new ResultService(new ResultRepo());
        TestService testService = new TestService(new TestRepo(), new ValidatorService(new DataValidator()));
        UserService userService = new UserService(new UserRepo(), new ValidatorService(new DataValidator()));
        List<String> subjects;
        int countPages;

        Long userId;
        if (req.getParameter("user_id") != null) {
            userId = Long.valueOf(req.getParameter("user_id"));
        } else {
            userId = (Long) req.getSession().getAttribute("user_id");
        }

        System.out.println("user id filter_result   " + userId);


        try {
            subjects = resultService.getDistinctSubject(userId);
            req.getSession().setAttribute("subjects", subjects);
            req.setAttribute("user", userService.get(userId));


            countPages = resultService.getCountPagesResult(userId, Integer.valueOf(rows), sub);
            System.out.println("count page = " + countPages);

            List<ResultDto> pageResultList = resultService
                    .getPageResultList(userId, sub, order, Integer.valueOf(rows), Integer.valueOf(page));

            System.out.println(pageResultList);
            if (countPages != 0 || !pageResultList.isEmpty()) {
                req.setAttribute("user_result", pageResultList);
                req.setAttribute("count_pages", countPages);
            }
//            req.setAttribute("user_id", userId);

            //    req.setAttribute("page", page);

            logger.info("Filter result was used");
            req.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            logger.warn("Trouble with using filter result ", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
