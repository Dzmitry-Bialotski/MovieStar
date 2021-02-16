package by.belotskiy.movie_star.model.service.impl;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.creator.UserCreator;
import by.belotskiy.movie_star.model.dao.UserDao;
import by.belotskiy.movie_star.model.dao.factory.DaoFactory;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.entity.enums.Role;
import by.belotskiy.movie_star.model.service.UserService;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.util.MailSender;
import by.belotskiy.movie_star.util.PasswordEncryptor;
import by.belotskiy.movie_star.util.impl.GmailSender;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = DaoFactory.getInstance().getUserDao();

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

    @Override
    public Optional<User> confirmEmail(int userId, String token) throws ServiceException {
        Optional<User> optionalUser;
        try {
            optionalUser = userDao.findById(userId);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user by id", e);
        }
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            String generatedToken;
            try {
                MailSender mailSender = GmailSender.getInstance();
                generatedToken = mailSender.calculateMdHash(user.getPasswordHash() + GmailSender.SALT);
            } catch (NoSuchAlgorithmException e) {
                throw new ServiceException("Error while calculating md hash", e);
            }
            if (!token.equals(generatedToken)) {
                return Optional.empty();
            }
            user.setEmailConfirmed(true);
            user.setRole(Role.REVIEWER);
            try {
                userDao.update(user);
            } catch (DaoException e) {
                throw new ServiceException("Error while updating user", e);
            }
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findALlUsers() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }
}
