package command;

import constans.Sort;
import controllers.servlet.RequestHandler;
import models.Test;
import servises.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FilterTests implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String sub = req.getParameter("sub");
        String order = req.getParameter("order");
        Integer rows = Integer.valueOf(req.getParameter("rows"));

        TestService testService = new TestService();
        int countPages = testService.countPages(sub, rows);


        List<Test> filterTests = testService.getFilterTests(sub, order, rows);
        List<String> subjects = testService.getDistinctSubjects();
        List<String> sorts = Arrays.asList("difficult desc", "difficult asc", "name desc");

        req.getSession().setAttribute("subjects", subjects);
        req.getSession().setAttribute("orders", sorts);
        req.getSession().setAttribute("tests", filterTests);
        req.setAttribute("count_pages", countPages);

        req.setAttribute("sub", sub);
        req.setAttribute("order", order);
        req.setAttribute("rows", rows);



        req.getRequestDispatcher("/WEB-INF/view/admin/admin_tests.jsp").forward(req, resp);


    }
}
