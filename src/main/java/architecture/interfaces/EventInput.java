package architecture.interfaces;

import architecture.EventObject;
import architecture.ReturnableObject;

public interface EventInput {
    void onCreate();
    void onDestroy();
    void onStart();
    void onStop();
    void onResume();
    void onRestart();
    void onPause();
    ReturnableObject onEvent(EventObject eventObject);
}
