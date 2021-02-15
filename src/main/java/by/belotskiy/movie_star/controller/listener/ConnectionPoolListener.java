package by.belotskiy.movie_star.controller.listener;

import by.belotskiy.movie_star.model.dao.pool.DynamicConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConnectionPoolListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DynamicConnectionPool.getInstance().destroyPool();
    }
}
