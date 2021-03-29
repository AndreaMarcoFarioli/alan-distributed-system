package core;

import core.definitions.ActionInput;
import core.definitions.EventPropagator;
import core.definitions.IManager;
import exceptions.PropagationException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.lang.reflect.InvocationTargetException;
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

public class Manager implements IManager, EventPropagator, ActionInput {
    private final Map<String, Service> serviceMap = new HashMap<>();

    //region IManager and EventPropagator
    /**
     * Aggiunge un servizio all'interno della tabella servizi del manager
     * @param service Servizio che si intende aggiungere
     * @throws KeyAlreadyExistsException Il nome logico assegnato al servizio e' gia' esistente all'interno del manager
     */
    @Override
    public void addService(Service service) throws KeyAlreadyExistsException {
        if (serviceMap.containsKey(service.getIdentity().getName()))
            throw new KeyAlreadyExistsException("Service Name Already Exists");
        serviceMap.put(service.getIdentity().getName(), service);
    }
    /**
     * Elimina un servizio all'interno della tabella servizi del manager
     * @param service Servizio che si intende eliminare
     * @throws NoSuchElementException L'elemento che si intende eliminare non esiste all'interno del manager
     */
    @Override
    public void deleteService(Service service) throws NoSuchElementException {
        if (!serviceMap.containsKey(service.getIdentity().getName()))
            throw new NoSuchElementException();
        serviceMap.remove(service.getIdentity().getName());
    }

    /**
     * Ritorna un servizio dalla tabella dei servizi del manager
     * @param name nome logico assegnato al servizio
     * @return Servizio corrispondente al nome logico
     * @throws NoSuchElementException Non e' stato trovato alcuno servizio all'interno della tabella dei servizi del manager
     */
    @Override
    public Service getService(String name) throws NoSuchElementException {
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
        Consumer<Service> eventInputConsumer = this::createService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato alla distruzione di tutti i servizi
     */
    @Override
    public void destroy() {
        Consumer<Service> eventInputConsumer = this::destroyService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocate all'inizio di tutti i servizi
     */
    @Override
    public void start() {
        Consumer<Service> eventInputConsumer = this::startService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato al blocco di tutti i servizi
     */
    @Override
    public void stop() {
        Consumer<Service> eventInputConsumer = this::stopService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato al riavvio di tutti i servizi
     */
    @Override
    public void restart() {
        Consumer<Service> eventInputConsumer = this::restartService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato alla messa in pausa di tutti i servizi
     */
    @Override
    public void pause() {
        Consumer<Service> eventInputConsumer = this::pauseService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato alla rimessa in esecuzione, dopo la pausa, di tutti i servizi
     */
    @Override
    public void resume() {
        Consumer<Service> eventInputConsumer = this::resumeService;
        iterateAll(eventInputConsumer);
    }
    //endregion

    //region singleEvent
    /**
     * Invoca la creazione nel servizio
     * @param service servizio soggetto
     */
    @Override
    public void startService(Service service) {
        service.onStart();
    }

    /**
     * Invoca la creazione nel servizio
     * @param service servizio soggetto
     */
    @Override
    public void stopService(Service service) {
        service.onStop();
    }

    /**
     * Invoca la creazione nel servizio
     * @param service servizio soggetto
     */
    @Override
    public void destroyService(Service service) {
        service.onDestroy();
    }

    /**
     * Invoca la creazione nel servizio
     * @param service servizio soggetto
     */
    @Override
    public void createService(Service service) {
        service.onCreate();
    }

    /**
     * Invoca la creazione nel servizio
     * @param service servizio soggetto
     */
    @Override
    public void restartService(Service service) {
        service.onRestart();
    }

    /**
     * Invoca la creazione nel servizio
     * @param service servizio soggetto
     */
    @Override
    public void pauseService(Service service) {
        service.onPause();
    }

    /**
     * Invoca la creazione nel servizio
     * @param service servizio soggetto
     */
    @Override
    public void resumeService(Service service) {
        service.onResume();
    }
    //endregion

    private <T>void iterateAll(Consumer<T> consumer){
        serviceMap.values().forEach(service -> consumer.accept((T)service));
    }


}