package bombe.core;

import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import bombe.core.definitions.*;
import bombe.exceptions.PropagationException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * @author Andrea Marco Farioli
 * @version 1
 */

/*
    TODO gestione degli eventi
 */

public final class Manager implements IManager, EventPropagator, ActionInput {
    private final Map<String, AbstractService> serviceMap = new HashMap<>();

    //region IManager and EventPropagator
    /**
     * Aggiunge un servizio all'interno della tabella servizi del manager
     * @param service Servizio che si intende aggiungere
     * @throws KeyAlreadyExistsException Il nome logico assegnato al servizio e' gia' esistente all'interno del manager
     */
    @Override
    public void addService(AbstractService service) throws KeyAlreadyExistsException {
        if (serviceMap.containsKey(service.getEntity().getName()))
            throw new KeyAlreadyExistsException("Service Name Already Exists");
        serviceMap.put(service.getEntity().getName(), service);
    }
    /**
     * Elimina un servizio all'interno della tabella servizi del manager
     * @param service Servizio che si intende eliminare
     * @throws NoSuchElementException L'elemento che si intende eliminare non esiste all'interno del manager
     */
    @Override
    public void deleteService(AbstractService service) throws NoSuchElementException {
        if (!serviceMap.containsKey(service.getEntity().getName()))
            throw new NoSuchElementException();
        serviceMap.remove(service.getEntity().getName());
    }

    /**
     * Ritorna un servizio dalla tabella dei servizi del manager
     * @param name nome logico assegnato al servizio
     * @return Servizio corrispondente al nome logico
     * @throws NoSuchElementException Non e' stato trovato alcuno servizio all'interno della tabella dei servizi del manager
     */
    @Override
    public AbstractService getService(String name) throws NoSuchElementException {
        if (!serviceMap.containsKey(name))
            throw new NoSuchElementException();
        return serviceMap.get(name);
    }

    /**
     * Metodo utilizzato per propagare un evento dal Manager ad un servizio
     * @param eventObject oggetto utile per recuperare le informazioni del servizio
     * @return restituisce un oggetto contenitore che puo' passare attraverso la rete mediante RMI.
     */
    @Override
    public ReturnableObject<?> propagate(EventObject eventObject) throws Exception {
        if (!eventObject.hasNext())
            throw new PropagationException();
        return serviceMap.get(eventObject.getNext()).propagate(eventObject);
    }
    //endregion

    //region massiveEvents
    /**
     * Metodo invocato alla creazione di tutti i servizi
     */
    @Override
    public void create() {
        Consumer<AbstractService> eventInputConsumer = this::createService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato alla distruzione di tutti i servizi
     */
    @Override
    public void destroy() {
        Consumer<AbstractService> eventInputConsumer = this::destroyService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocate all'inizio di tutti i servizi
     */
    @Override
    public void start() {
        Consumer<AbstractService> eventInputConsumer = this::startService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato al blocco di tutti i servizi
     */
    @Override
    public void stop() {
        Consumer<AbstractService> eventInputConsumer = this::stopService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato al riavvio di tutti i servizi
     */
    @Override
    public void restart() {
        Consumer<AbstractService> eventInputConsumer = this::restartService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato alla messa in pausa di tutti i servizi
     */
    @Override
    public void pause() {
        Consumer<AbstractService> eventInputConsumer = this::pauseService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato alla rimessa in esecuzione, dopo la pausa, di tutti i servizi
     */
    @Override
    public void resume() {
        Consumer<AbstractService> eventInputConsumer = this::resumeService;
        iterateAll(eventInputConsumer);
    }
    //endregion

    //region singleEvent
    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void startService(AbstractService abstractService)  {
        try {
            abstractService.onStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void stopService(AbstractService abstractService) {
        try {
            abstractService.onStop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void destroyService(AbstractService abstractService) {
        try {
            abstractService.onDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void createService(AbstractService abstractService) {
        try {
            abstractService.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void restartService(AbstractService abstractService) {
        try {
            abstractService.onRestart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void pauseService(AbstractService abstractService) {
        try {
            abstractService.onPause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void resumeService(AbstractService abstractService) {
        try {
            abstractService.onResume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

    @SuppressWarnings("unchecked")
    private <T>void iterateAll(Consumer<T> consumer){
        serviceMap.values().forEach(service -> consumer.accept((T)service));
    }
}