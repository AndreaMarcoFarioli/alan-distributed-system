package bombe2.annotations;

public enum VisibilityType {
    /**
     * Il metodo sarà visibile da tutti
     */
    PUBLIC,
    /**
     * Il metodo sarà visibile da tutto il nodo locale
     */
    PROTECTED,
    /**
     * Il metodo risulta invisibile a tutti
     */
    PRIVATE,
    /**
     * Il metodo risulta visibile solo ed esclusivamente dalla famiglia del servizio e sotto servizi
     */
    INSIDE
}
