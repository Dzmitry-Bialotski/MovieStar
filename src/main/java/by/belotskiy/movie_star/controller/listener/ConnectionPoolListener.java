package by.belotskiy.movie_star.controller.listener;

import by.belotskiy.movie_star.model.pool.DynamicConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener correctly close connection pull on context destruction
 *
 * @author Dmitriy Belotskiy
 */
public class ConnectionPoolListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DynamicConnectionPool.getInstance().destroyPool();
    }
}
