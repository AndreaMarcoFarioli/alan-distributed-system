package bombe.core.definitions;

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

    void startService(AbstractService abstractService);

    void stopService(AbstractService abstractService);

    void destroyService(AbstractService abstractService);

    void createService(AbstractService abstractService);

    void restartService(AbstractService abstractService);

    void pauseService(AbstractService abstractService);

    void resumeService(AbstractService abstractService);
}
