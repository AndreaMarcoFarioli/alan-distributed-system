package core.definitions;

import core.Service;

/**
 * @author Andrea Marco Farioli
 * @version 1
 * Descrive ogni genere di azione che un Manager puo' inoltrare ad un servizio
 */
public interface ActionInput {
    void create();
    void destroy();
    void start();
    void stop();
    void restart();
    void pause();
    void resume();
    void startService(Service service);
    void stopService(Service service);
    void destroyService(Service service);
    void createService(Service service);
    void restartService(Service service);
    void pauseService(Service service);
    void resumeService(Service service);
}
