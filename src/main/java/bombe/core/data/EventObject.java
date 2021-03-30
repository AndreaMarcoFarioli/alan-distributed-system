package bombe.core.data;

import bombe.exceptions.MalformedEventException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EventObject implements Serializable {
    private String coordinate;
    private final String method;
    private final Object[] params;
    private final Class<?>[] types;
    private boolean hasNext = true;
    public static final Pattern EVENT_PATTERN =
            Pattern.compile("^(?<whole>(?<first>[a-z0-9]+)(?:(?:\\.[a-z0-9]+)+)?\\:)(?<method>[a-z](?:[a-z0-9]+)?)$", Pattern.CASE_INSENSITIVE);
    public final static String WHOLE = "whole", FIRST = "first", METHOD = "method";


    public EventObject(String coordinate, Object... params) throws MalformedEventException {
        Matcher matcher = EVENT_PATTERN.matcher(coordinate);

        if (!matcher.matches())
            throw new MalformedEventException();

        this.coordinate = coordinate;
        this.method = matcher.group(METHOD);
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

    /**
     * Restituisce il nome del servizio successivo
     * @return nome del servizio successivo
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String getNext(){
        Matcher matcher = EVENT_PATTERN.matcher(coordinate);
        matcher.matches();
        String result = matcher.group(FIRST);
        hasNext = !matcher.group(WHOLE).equals(result + ":");
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
                ", method='" + method + '\'' +
                ", params=" + Arrays.toString(params) +
                ", types=" + Arrays.toString(types) +
                ", hasNext=" + hasNext +
                '}';
    }
}
