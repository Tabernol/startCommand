package command.post;

import controllers.servlet.RequestHandler;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUser implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        UserService userService = new UserService();
        Long userId = Long.valueOf(req.getParameter("user_id"));
        userService.deleteUser(Long.valueOf(userId));
        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("/WEB-INF/view/admin/admin_users.jsp").forward(req,resp);
    }
}
