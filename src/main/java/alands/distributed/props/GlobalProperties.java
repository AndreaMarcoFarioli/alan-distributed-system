package alands.distributed.props;

public class GlobalProperties {
    private static GlobalProperties instance;
    private Properties properties;
    private GlobalProperties(){}

    public static GlobalProperties getInstance() {
        if (instance == null)
            instance = new GlobalProperties();
        return instance;
    }

    public void setProperty(Properties properties){
        if (this.properties != null)
            throw new IllegalStateException("properties field already set");
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }
}