package by.belotskiy.movie_star.util;

import by.belotskiy.movie_star.exception.ServiceException;

public interface FileService {
    byte[] readFile(String fileName) throws ServiceException;
}
