package by.belotskiy.movie_star.service.impl;

import by.belotskiy.movie_star.dao.UserDao;
import by.belotskiy.movie_star.dao.impl.UserDaoImpl;
import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.creator.UserCreator;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.validator.UserValidator;
import by.belotskiy.movie_star.service.UserService;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.util.PasswordEncryptor;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userServiceInstance;

    private UserServiceImpl(){

    }

    public static UserServiceImpl getInstance(){
        if(userServiceInstance == null){
            userServiceInstance = new UserServiceImpl();
        }
        return userServiceInstance;
    }

    private final UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        String passwordHash = PasswordEncryptor.encrypt(password);
        Optional<User> optionalUser = Optional.empty();
        try{
            if(userDao.isUserExists(login, passwordHash)){
                optionalUser = userDao.findByLogin(login);
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }

        return optionalUser;
    }

    @Override
    public boolean register(String login, String password, String passwordConfirmed) throws ServiceException {
        boolean result;
        try {
            Optional<User> optionalUser = userDao.findByLogin(login);
            if(optionalUser.isPresent()){
                result = false;
            }else {
                String passwordHash = PasswordEncryptor.encrypt(password);
                User user = UserCreator.createUser(login, passwordHash);
                result = userDao.save(user);
            }
        }
        catch (DaoException e){
            throw new ServiceException(e);
        }
        return result;
    }
}
