package by.belotskiy.movie_star.controller;

import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.service.impl.UserServiceImpl;
import by.belotskiy.movie_star.util.ImageValidator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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

    private final String AVATAR_UPLOAD_DIRECTORY = new StringBuilder("D:")
            .append(File.separator).append("java_web_projects").append(File.separator).append("MovieStar")
            .append(File.separator).append("src").append(File.separator).append("main").append(File.separator)
            .append("webapp").append(File.separator).append("img").append(File.separator).append("avatar").toString();

    private final String RELATIVE_AVATAR_PATH = File.separator + "img" + File.separator + "avatar";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(request)){
            Part part = request.getPart("file");
            String filename = part.getSubmittedFileName();
            boolean isValid = ImageValidator.validateExtension(filename);
            if(isValid){
                String realFilename = UUID.randomUUID()+ filename;
                String upload_path = AVATAR_UPLOAD_DIRECTORY + File.separator + realFilename;
                String relative_path = RELATIVE_AVATAR_PATH + File.separator + realFilename;
                InputStream inputStream = part.getInputStream();
                boolean isSuccess = uploadFile(inputStream, upload_path);
                if(isSuccess){
                    HttpSession session = request.getSession();
                    User user = (User)session.getAttribute(SessionAttributeName.USER);
                    user.setAvatar_path(relative_path);
                    try{
                        UserServiceImpl.getInstance().updateUser(user);
                    }catch (Exception e){
                        throw new ServletException(e);
                    }

                }
            }
        }
        HttpSession session = request.getSession();
        String returnUrl = (String)session.getAttribute(SessionAttributeName.RETURN_URL);
        response.sendRedirect(returnUrl);
        return;
    }

    private boolean uploadFile(InputStream inputStream, String path) throws ServletException {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(bytes);
            fops.flush();
            fops.close();
        }catch (IOException e) {
            throw new ServletException(e);
        }
        return true;
    }
}
