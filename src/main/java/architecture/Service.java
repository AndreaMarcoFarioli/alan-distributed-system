package architecture;

import architecture.interfaces.EventInput;

public class Service extends Identity implements EventInput {
    public Service(String name) {
        super(name);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public ReturnableObject onEvent(EventObject eventObject) {
        return null;
    }
}
