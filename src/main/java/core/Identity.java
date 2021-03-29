package core;

public class Identity {
    private final String name;
    private final int id;
    private static int staticId;

    public Identity(String name){
        id = staticId++;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
