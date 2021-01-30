package by.belotskiy.movie_star.service.impl;

import by.belotskiy.movie_star.model.entity.Role;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.service.UserService;
import by.belotskiy.movie_star.exception.ServiceException;

import java.util.Map;
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

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> optionalUser = Optional.of(new User(1));

        return optionalUser;
    }

    @Override
    public boolean register(Map<String, String> fields) throws ServiceException {
        return false;
    }
}
