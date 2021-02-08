package by.belotskiy.movie_star.service.impl;

import by.belotskiy.movie_star.service.MailService;

public class MailServiceImpl implements MailService {
    private static MailServiceImpl instance;

    private MailServiceImpl() { }

    public static MailServiceImpl getInstance() {
        if (instance == null) {
            instance = new MailServiceImpl();
        }
        return instance;
    }
}
