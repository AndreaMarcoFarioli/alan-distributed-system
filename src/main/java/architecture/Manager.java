package architecture;

import architecture.interfaces.ActionInput;
import architecture.interfaces.EventInput;
import architecture.interfaces.IManager;
import architecture.interfaces.Propagation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Manager extends Identity implements IManager, ActionInput, Propagation {
    private final Map<String, Service> serviceMap = new HashMap<>();

    public Manager(String name) {
        super(name);
    }

    @Override
    public void start() {
        Consumer<EventInput> consumer = EventInput::onStart;
        iterateAll(consumer);
    }

    @Override
    public void stop() {
        Consumer<EventInput> consumer = EventInput::onStop;
        iterateAll(consumer);
    }

    @Override
    public void create() {
        Consumer<EventInput> consumer = EventInput::onCreate;
        iterateAll(consumer);
    }

    @Override
    public void destroy() {
        Consumer<EventInput> consumer = EventInput::onDestroy;
        iterateAll(consumer);
    }

    @Override
    public void pause() {
        Consumer<EventInput> consumer = EventInput::onPause;
        iterateAll(consumer);
    }

    @Override
    public void resume() {
        Consumer<EventInput> consumer = EventInput::onResume;
        iterateAll(consumer);
    }

    @Override
    public void restart() {
        Consumer<EventInput> consumer = EventInput::onRestart;
        iterateAll(consumer);
    }

    @Override
    public ReturnableObject propagate(EventObject eventObject) {
        return null;
    }


    @Override
    public <T> void iterateAll(Consumer<T> consumer) {

    }

    @Override
    public Service getService(String key) {
        return null;
    }

    @Override
    public void addService(Service service) {

    }

    @Override
    public void destroyService(Service service) {

    }
}
