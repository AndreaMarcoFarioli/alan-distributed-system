package bombe.core.definitions;

import bombe.core.AbstractService;

/**
 * @author Andrea Marco Farioli
 * @version 1
 * Descrive ogni genere di azione che un Manager puo' inoltrare ad un servizio
 */
public interface ActionInput {
    void create() throws Exception;
    void destroy() throws Exception;
    void start() throws Exception;
    void stop() throws Exception;
    void restart() throws Exception;
    void pause() throws Exception;
    void resume() throws Exception;
    void startService(AbstractService abstractService) throws Exception;
    void stopService(AbstractService abstractService) throws Exception;
    void destroyService(AbstractService abstractService) throws Exception;
    void createService(AbstractService abstractService) throws Exception;
    void restartService(AbstractService abstractService) throws Exception;
    void pauseService(AbstractService abstractService) throws Exception;
    void resumeService(AbstractService abstractService) throws Exception;
}
