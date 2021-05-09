package bombe2.core.data;

import bombe2.alpha.SessionManager;
import bombe2.alpha.SessionReference;
import bombe2.alpha.SystemSessionReference;
import bombe2.annotations.Origin;
import bombe2.annotations.VisibilityType;
import bombe2.exceptions.MalformedEventException;
import bombe2.exceptions.SessionException;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO definire una struttura più elegante

/**
 * @author Andrea Marco Farioli
 * @version 0.1.0
 * Classe che identifica la struttura standard per la propagazione degli eventi
 */
public final class EventObject implements Serializable {

    //region declarations
    private String coordinate;
    private final String fullPath, method, fork;
    private final StringBuilder actualPath = new StringBuilder();
    private final Object[] params;
    private final Class<?>[] types;
    private boolean hasNext, isBottomUp;
    private Origin origin = Origin.LOCAL;
    //endregion

    public static final String EVENT_PATTERN =
            "^(?:(?:(?<fork>[a-z0-9_\\-]+)#)?(?<whole>(?<first>(?:[a-z0-9]+|\\^))(\\.(?<second>(?:[a-z0-9]+|\\^)))?(?:(?:\\.(?:[a-z0-9]+|\\^))+)?:))?(?<method>[a-z](?:[a-z0-9]+)?)$";
    public static final Pattern COMPILED_PATTERN =
            Pattern.compile(EVENT_PATTERN, Pattern.CASE_INSENSITIVE);
    public final static String WHOLE = "whole", FIRST = "first", METHOD = "method", FORK = "fork", SECOND = "second";

    public static final SessionManager sessionManager = new SessionManager();

    private final SessionReference sessionReference;

    public EventObject(String coordinate, String sessionId, Object... params) throws MalformedEventException{

        this.sessionReference = sessionManager.getSession(sessionId);

        Matcher matcher = COMPILED_PATTERN.matcher(coordinate);
        if (!matcher.matches())
            throw new MalformedEventException();

        this.params = ArrayUtils.addAll(new Object[]{this}, params);
        this.types = calcTypes(this.params);
        this.fork = matcher.group(FORK);
        this.method = matcher.group(METHOD);
        this.hasNext = matcher.group(WHOLE) != null;
        if (this.hasNext)
            this.isBottomUp = matcher.group(FIRST).equals("^");
        this.coordinate = coordinate.substring(this.fork == null?0:this.fork.length()+1);
        this.fullPath = this.coordinate;
    }

    static {
        SystemSessionReference.setSessionManager(sessionManager);
    }

    public String getFork() {
        return fork;
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

    public Origin getOrigin() {
        return origin;
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
        Matcher matcher = COMPILED_PATTERN.matcher(coordinate);
        matcher.matches();
        String result = matcher.group(FIRST);
        hasNext = !matcher.group(WHOLE).equals(result + ":");
        if (isBottomUp = !(result+":").equals(matcher.group(WHOLE)))
            isBottomUp = matcher.group(SECOND).equals("^");
        coordinate = coordinate.substring(result.length() + 1);
        if (!"".equals(actualPath.toString()))
            actualPath.append(".");
        actualPath.append(result);
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

    public boolean isBottomUp() {
        return isBottomUp;
    }

    public static boolean visibilityTest(Origin origin, VisibilityType visibilityType){
        boolean
                L = (origin == Origin.LOCAL),
                R = (origin == Origin.REMOTE),
                r = (visibilityType == VisibilityType.PUBLIC),
                l = (visibilityType == VisibilityType.PROTECTED);
        return (L && l) || r && (R || L);
    }

    public String getActualPath() {
        return actualPath.toString();
    }

    public String getFullPath() {
        return fullPath;
    }

    public String getSessionId() {
        return getSession().getSessionId();
    }

    public SessionReference getSession(){
        if (!sessionReference.isOpened())
            throw new SessionException();
        return sessionReference;
    }

    public static SessionReference createSession(){
        return sessionManager.createSession();
    }

    public EventObject subEvent(String coordinate, Object... params) throws MalformedEventException{
        return new EventObject(coordinate, this.getSession().getSessionId(), params);
    }

    @Override
    public String toString() {
        return "EventObject{" +
                "coordinate='" + coordinate + '\'' +
                ", fork ='" + fork + '\'' +
                ", method='" + method + '\'' +
                ", types=" + Arrays.toString(types) +
                '}';
    }
}
