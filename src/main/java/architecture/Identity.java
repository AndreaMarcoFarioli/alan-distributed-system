package architecture;

public class Identity {
    private final String name;
    private final int uniqueId;
    private static int counter;
    public Identity(String name){
        this.name = name;
        uniqueId = counter++;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public static int getCounter() {
        return counter;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Identity{" +
                "name='" + name + '\'' +
                ", uniqueId=" + uniqueId +
                '}';
    }
}
