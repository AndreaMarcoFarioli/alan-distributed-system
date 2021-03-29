package core;

import exceptions.MalformedEventException;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventObject implements Serializable {
    private String coordinate, method;
    private final Object[] params;
    private final Class<?>[] types;
    private boolean hasNext = true;
    //private int flag = 0;
    public static final Pattern EVENT_PATTERN = Pattern.compile("^(([a-z0-9]+)(?:(?:\\.[a-z0-9]+)+)?\\:)([a-z](?:[a-z0-9]+)?)$", Pattern.CASE_INSENSITIVE);



    public EventObject(String coordinate, Object... params) throws MalformedEventException {
        Matcher matcher = EVENT_PATTERN.matcher(coordinate);

        if (!matcher.matches())
            throw new MalformedEventException();

        this.coordinate = coordinate;
        this.method = matcher.group(3);
        this.params = params;
        this.types = calcTypes(params);
    }

    public static Class<?>[] calcTypes(Object... params){
        Class<?>[] types = new Class[params.length];
        for (int i = 0; i < types.length; i++)
            types[i] = params[i].getClass();
        return types;
    }

    public Object[] getParams() {
        return params;
    }

    public Class<?>[] getTypes() {
        return types;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String getNext(){
        Matcher matcher = EVENT_PATTERN.matcher(coordinate);
        matcher.matches();
        String result = matcher.group(2);
        hasNext = !matcher.group(1).equals(matcher.group(2) + ":");
        coordinate = coordinate.substring(result.length() + 1);
        return result;
    }

    public boolean hasNext(){
        return this.hasNext;
    }

    public String getMethod(){
        return this.method;
    }

    public String getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "EventObject{" +
                "coordinate='" + coordinate + '\'' +
                //", flag=" + flag +
                '}';
    }
}
