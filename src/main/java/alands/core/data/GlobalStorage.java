package alands.core.data;

public class GlobalStorage {
    private static GlobalStorage instance;
    private Storage storage;

    private GlobalStorage(){}

    public static GlobalStorage getInstance(){
        if(instance == null)
            instance = new GlobalStorage();
        return instance;
    }

    public void setStorage(Storage storage){
        if (this.storage != null)
            throw new IllegalStateException();
        this.storage = storage;
    }

    public Storage getStorage() {
        return storage;
    }
}
