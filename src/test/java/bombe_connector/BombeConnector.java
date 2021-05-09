package bombe_connector;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BombeConnector {
    private final int bombePort;
    private final String bombeHost;
    public BombeConnector(String bombeHost, int bombePort) throws IOException {
        this.bombeHost = bombeHost;
        this.bombePort = bombePort;
    }
}
