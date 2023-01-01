package controllers.filters;

import constans.PathConst;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", value = "/*")
public class SecurityFilter extends AbstractFilter {
    @Override
    public void doCustomFilter(HttpServletRequest req,
                               HttpServletResponse resp,
                               FilterChain filterChain)
            throws IOException, ServletException {
        String role = (String) req.getSession().getAttribute("role");
        String servletPath = req.getServletPath();

        if (role == null) {
            if (servletPath.equals(PathConst.LOGIN_FORM) || servletPath.equals(PathConst.REGISTRATION)||
                    servletPath.equals(PathConst.LANGUAGE)){
                req.getRequestDispatcher(servletPath).forward(req, resp);
            } else {
                req.getRequestDispatcher("/").forward(req, resp);
            }

        } else if (role.equals("student")) {
            if (servletPath.equals(PathConst.REGISTRATION) || servletPath.equals(PathConst.PROFILE) ||
                    servletPath.equals(PathConst.LOGIN_FORM) || servletPath.equals(PathConst.LOGOUT) ||
                    servletPath.equals(PathConst.HOME) || servletPath.equals(PathConst.LANGUAGE) ||
                    servletPath.equals(PathConst.FILTER_TESTS) || servletPath.equals(PathConst.NEXT_PAGE) ||
                    servletPath.equals(PathConst.INFO_TEST) || servletPath.equals(PathConst.START_TEST) ||
                    servletPath.equals(PathConst.NEXT_QUESTION) || servletPath.equals(PathConst.RESULT_ANSWER)) {
                req.getRequestDispatcher(servletPath).forward(req, resp);
            } else req.getRequestDispatcher("/WEB-INF/view/student/student_menu.jsp").forward(req, resp);


        } else if (role.equals("admin")) {
            if (servletPath.equals(PathConst.FILTER_TESTS) || servletPath.equals(PathConst.PROFILE) ||
                    servletPath.equals(PathConst.LOGIN_FORM) || servletPath.equals(PathConst.LOGOUT) ||
                    servletPath.equals(PathConst.REGISTRATION) || servletPath.equals(PathConst.HOME) ||
                    servletPath.equals(PathConst.LANGUAGE) ||
                    servletPath.equals(PathConst.FILTER_TESTS) ||
                    servletPath.equals(PathConst.NEXT_PAGE) || servletPath.equals(PathConst.USERS) ||
                    servletPath.equals(PathConst.CREATE_TEST) || servletPath.equals(PathConst.ADD_QUESTION) ||
                    servletPath.equals(PathConst.ADD_ANSWER) ||
                    servletPath.equals(PathConst.DELETE_USER) || servletPath.equals(PathConst.DELETE_TEST) ||
                    servletPath.equals(PathConst.DELETE_QUESTION) || servletPath.equals(PathConst.DELETE_ANSWER) ||
                    servletPath.equals(PathConst.EDIT_TEST) || servletPath.equals(PathConst.EDIT_QUESTION) ||
                    servletPath.equals(PathConst.EDIT_USER)) {
                req.getRequestDispatcher(servletPath).forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/admin/admin_menu.jsp").forward(req, resp);
            }
        }


//        req.getRequestDispatcher("/WEB-INF/view/" + role + "/" + servletPath + ".jsp").forward(req, resp);


    }
}
