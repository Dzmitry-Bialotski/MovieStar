package by.belotskiy.movie_star.service.impl;

import by.belotskiy.movie_star.dao.UserDao;
import by.belotskiy.movie_star.dao.impl.UserDaoMySql;
import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.creator.UserCreator;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.service.UserService;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.util.PasswordEncryptor;

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

    private final UserDao userDao = UserDaoMySql.getInstance();

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> optionalUser;
        try{
            optionalUser = userDao.findByLogin(login);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                boolean isPasswordRight =  PasswordEncryptor.check(password, user.getPasswordHash());
                if(isPasswordRight){
                    return optionalUser;
                }else {
                    return Optional.empty();
                }
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
                User user = UserCreator.createUserAfterRegistration(login, passwordHash);
                result = userDao.save(user);
            }
        }
        catch (DaoException e){
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Optional<User> findUser(int id) throws ServiceException {
        Optional<User> optionalUser;
        try {
             optionalUser = userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findUserWithCookies(String login, String userHash) throws ServiceException {
        Optional<User> optionalUser;
        try {
            optionalUser = userDao.findByLoginAndUserHash(login, userHash);
        } catch(DaoException e){
            throw new ServiceException(e);
        }
        return optionalUser;
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        boolean result;
        try{
            result = userDao.update(user);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return result;
    }
}
