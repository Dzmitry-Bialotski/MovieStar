package by.belotskiy.movie_star.controller;

import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.UUID;

@WebServlet(name = "FileUploadServlet", urlPatterns = {UrlPath.UPLOAD_CONTROLLER})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(FileUploadServlet.class);

    private final String AVATAR_UPLOAD_DIRECTORY = "D:" + File.separator + "java_web_projects" + File.separator
            + "MovieStar" + File.separator + "src" + File.separator + "main"  + File.separator + "webapp"
            + File.separator + "img" + File.separator + "avatar";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(request)){
            try{
                Part part = request.getPart("file");
                String filename = part.getSubmittedFileName();
                String path = AVATAR_UPLOAD_DIRECTORY +  File.separator + UUID.randomUUID()+ filename;
                InputStream inputStream = part.getInputStream();
                boolean isSuccess = uploadFile(inputStream, path);
                if(isSuccess){
                    HttpSession session = request.getSession();
                    User user = (User)session.getAttribute(SessionAttributeName.USER);
                    user.setAvatar_path(path);
                    UserServiceImpl.getInstance().updateUser(user);
                }
            }catch (Exception e){
                LOGGER.log(Level.ERROR, e);
            }
        }
        HttpSession session = request.getSession();
        String returnUrl = (String)session.getAttribute(SessionAttributeName.RETURN_URL);
        response.sendRedirect(returnUrl);
        return;
    }

    private boolean uploadFile(InputStream inputStream, String path){
        boolean result = false;
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(bytes);
            fops.flush();
            fops.close();
            result = true;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
