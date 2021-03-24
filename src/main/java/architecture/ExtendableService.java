package architecture;

import architecture.interfaces.HasManager;

public class ExtendableService extends Service implements HasManager {
    private final Manager manager = new Manager("");
    public ExtendableService(String name) {
        super(name);
    }

    @Override
    public Manager getManager() {
        return manager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager.destroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        manager.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        manager.pause();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        manager.resume();
    }

    @Override
    public void onStart() {
        super.onStart();
        manager.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        manager.stop();
    }
}
