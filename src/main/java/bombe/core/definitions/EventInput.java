package bombe.core.definitions;

public interface EventInput {
    void onCreate();

     void onDestroy() ;
     void onStart() ;
     void onStop();
     void onPause();

     void onResume();

     void onRestart();
}