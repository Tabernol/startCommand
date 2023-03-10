package servlets.get;

import command.get.Profile;
import dto.ResultDto;
import exeptions.DataBaseException;
import models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servises.ResultService;
import servises.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileTest {
    Profile profile = new Profile();

    @Test
    public void profileTest() throws DataBaseException, ServletException, IOException {
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        final HttpSession session = Mockito.mock(HttpSession.class);
        final UserService userService = Mockito.mock(UserService.class);

        final ResultService resultService = Mockito.mock(ResultService.class);

        User user = new User();
        List<ResultDto> resultDto = new ArrayList<>();
        user.setName("first");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getRequestDispatcher("/WEB-INF/view/profile.jsp")).thenReturn(dispatcher);
        Mockito.when(session.getAttribute("user_id")).thenReturn(12L);
        Mockito.when(userService.get(Mockito.anyLong())).thenReturn(user);


        profile.execute(request,response);

        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher("/WEB-INF/view/profile.jsp");
//        Mockito.verify(request, Mockito.times(1)).getSession();
        Mockito.verify(dispatcher).forward(request, response);

    }
}
