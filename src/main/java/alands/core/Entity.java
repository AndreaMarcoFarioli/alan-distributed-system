package alands.core;

public class Entity {
    private final String name;
    private final int id;
    private static int staticId;

    public Entity(String name){
        id = staticId++;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
