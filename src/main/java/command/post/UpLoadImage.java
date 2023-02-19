package command.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import repo.QuestionRepo;
import servises.QuestionService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@MultipartConfig(
//        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
//        maxFileSize = 1024 * 1024 * 10,      // 10 MB
//        maxRequestSize = 1024 * 1024 * 100   // 100 MB
//)
public class UpLoadImage implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String questionId = req.getParameter("question_id");
        String webInfPath = req.getServletContext().getRealPath("WEB-INF");

        QuestionService questionService = new QuestionService(new QuestionRepo(), new ValidatorService(new DataValidator()));

//        Part file = req.getPart("file");
//        String contentType = file.getContentType();
//        contentType = contentType.substring(contentType.indexOf("/")).replace("/", ".");

        String fullPath = webInfPath + "\\question" + questionId + ".jpeg";
        System.out.println(fullPath);

        for (Part part : req.getParts()) {
            part.write(fullPath);
        }

        Map config = new HashMap();

        config.put("cloud_name", "dluwxouux");

        config.put("api_key", "377146951856361");

        config.put("api_secret", "sNoheqeDLHGf2z8HgyNmAAQU1CI");
        Cloudinary cloudinary = new Cloudinary(config);

        File file = new File(fullPath);
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        int i = 0;
        for (Object item : uploadResult.keySet()) {

            System.out.print(++i + " = " + item.toString() + " , ");
        }
        System.out.println("=========================");
        int k = 0;
        for (Object item : uploadResult.values()) {
            System.out.print(++k + " = " + item.toString() + " , ");
        }

        String url = (String) uploadResult.get("url");
        try {
            questionService.updateImage(url, Long.valueOf(questionId));
        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        } catch (ValidateException e) {
            throw new RuntimeException(e);
        }


        resp.getWriter().print("The file uploaded sucessfully.");
    }
}