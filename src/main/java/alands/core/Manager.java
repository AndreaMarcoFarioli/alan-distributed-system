package alands.core;

import alands.core.data.EventObject;
import alands.core.data.ReturnableObject;
import alands.core.definitions.*;
import alands.exceptions.PropagationException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * @author Andrea Marco Farioli
 * @version 0.1.0
 */

/*
    TODO gestione degli eventi
 */

public final class Manager implements Propagator, ActionInput {
    private final Map<String, AbstractService> serviceMap = new HashMap<>();
    private final ExtendableService extendableService;
    public Manager(ExtendableService parentService){
        this.extendableService = parentService;
    }

    public Manager(){
        this(null);
    }

    //region IManager and EventPropagator
    /**
     * Aggiunge un servizio all'interno della tabella servizi del manager
     * @param abstractService Servizio che si intende aggiungere
     * @throws KeyAlreadyExistsException Il nome logico assegnato al servizio e' gia' esistente all'interno del manager
     */
    public void addService(AbstractService abstractService) {
        try {
            if (serviceMap.containsKey(abstractService.getEntity().getName()))
                throw new KeyAlreadyExistsException("Service Name Already Exists");
            setManagerReflection(abstractService);
            serviceMap.put(abstractService.getEntity().getName(), abstractService);
        }catch (NoSuchFieldException | IllegalAccessException ignored) {}
    }

    private void setManagerReflection(AbstractService abstractService) throws NoSuchFieldException, IllegalAccessException {
        Class<?> s = AbstractService.class;
        Field eventPropagator = s.getDeclaredField("propagator");
        eventPropagator.setAccessible(true);
        eventPropagator.set(abstractService, this);
        eventPropagator.setAccessible(false);
        Field f = AbstractService.class.getDeclaredField("path");
        f.setAccessible(true);
        f.set(abstractService, abstractService.calcPath());
        f.setAccessible(false);
        abstractService.onManagerAdded();
    }

    /**
     * Elimina un servizio all'interno della tabella servizi del manager
     * @param service Servizio che si intende eliminare
     * @throws NoSuchElementException L'elemento che si intende eliminare non esiste all'interno del manager
     */
    public void deleteService(AbstractService service){
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
    public AbstractService getService(String name) {
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
    public ReturnableObject<?> propagate(EventObject eventObject) throws ReflectiveOperationException {
        if (!eventObject.hasNext())
            throw new PropagationException();
        ReturnableObject<?> returnableObject;

        if (eventObject.isBottomUp()) {
            returnableObject = extendableService.propagate(eventObject);
        }
        else
            returnableObject = serviceMap.get(eventObject.getNext()).propagate(eventObject);
        return returnableObject;
    }
    //endregion

    //region massiveEvents
    /**
     * Metodo invocato alla creazione di tutti i servizi
     */
    @Override
    public void create(){
        Consumer<AbstractService> eventInputConsumer = this::createService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato alla distruzione di tutti i servizi
     */
    @Override
    public void destroy(){
        Consumer<AbstractService> eventInputConsumer= this::destroyService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocate all'inizio di tutti i servizi
     */
    @Override
    public void start(){
        Consumer<AbstractService> eventInputConsumer = this::startService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato al blocco di tutti i servizi
     */
    @Override
    public void stop(){
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
    public void pause(){
        Consumer<AbstractService> eventInputConsumer = this::pauseService;
        iterateAll(eventInputConsumer);
    }

    /**
     * Metodo invocato alla rimessa in esecuzione, dopo la pausa, di tutti i servizi
     */
    @Override
    public void resume(){
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
    public void startService(AbstractService abstractService){
        abstractService.onStart();
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void stopService(AbstractService abstractService){
        abstractService.onStop();
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void destroyService(AbstractService abstractService){
        abstractService.onDestroy();
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void createService(AbstractService abstractService){
        abstractService.onCreate();
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void restartService(AbstractService abstractService){
        abstractService.onRestart();
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void pauseService(AbstractService abstractService){
        abstractService.onPause();
    }

    /**
     * Invoca la creazione nel servizio
     * @param abstractService servizio soggetto
     */
    @Override
    public void resumeService(AbstractService abstractService){
            abstractService.onResume();
    }
    //endregion

    @SuppressWarnings("unchecked")
    private <T>void iterateAll(Consumer<T> consumer){
        for (AbstractService abstractService: serviceMap.values())
            consumer.accept((T)abstractService);
    }

    public String forwardPathRequest(){
        String path = "";
        if (extendableService != null)
            path = extendableService.calcPath();
        return path;
    }
}