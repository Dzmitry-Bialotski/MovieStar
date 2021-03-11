package by.belotskiy.movie_star.controller;

import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import by.belotskiy.movie_star.util.ImageValidator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Controller used for uploading files
 *
 * @author Dmitriy Belotskiy
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = {UrlPath.UPLOAD_CONTROLLER})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadServlet extends HttpServlet {

    private final String AVATAR_UPLOAD_DIRECTORY = "D:" +
            File.separator + "java_web_projects" + File.separator + "MovieStar" +
            File.separator + "src" + File.separator + "main" + File.separator +
            "webapp" + File.separator + "img" + File.separator + "avatar";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(request)){
            Part part = request.getPart("file");
            String filename = part.getSubmittedFileName();
            boolean isValid = ImageValidator.validateExtension(filename);
            if(isValid){
                String upload_path = AVATAR_UPLOAD_DIRECTORY + File.separator + filename;
                if (Files.exists(Path.of(upload_path))) {
                    Files.delete(Path.of(upload_path));
                }
                boolean isSuccess;
                try(InputStream inputStream = part.getInputStream()){
                    isSuccess = uploadFile(inputStream, upload_path);
                }
                if(isSuccess){
                    HttpSession session = request.getSession();
                    User user = (User)session.getAttribute(SessionAttributeName.USER);
                    user.setAvatar_path(filename);
                    try{
                        ServiceFactory.getInstance().getUserService().updateUser(user);
                    }catch (Exception e){
                        throw new ServletException(e);
                    }
                }
            }
        }
        response.sendRedirect(request.getHeader("referer"));
    }

    private boolean uploadFile(InputStream inputStream, String path) throws ServletException {
        try {
            byte[] bytes = new byte[inputStream.available()];
            int result = inputStream.read(bytes);
            if(result != -1){
                try(FileOutputStream fops = new FileOutputStream(path)){
                    fops.write(bytes);
                }
            }
        }catch (IOException e) {
            throw new ServletException(e);
        }
        return true;
    }
}
