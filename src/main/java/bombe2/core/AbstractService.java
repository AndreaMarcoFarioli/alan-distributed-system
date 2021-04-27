package bombe2.core;

import bombe2.annotations.MethodVisibility;
import bombe2.annotations.Origin;
import bombe2.annotations.VisibilityType;
import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.core.definitions.Propagator;
import bombe2.distributed.MainManager;
import bombe2.exceptions.PropagationException;

import java.lang.reflect.Method;

public abstract class AbstractService implements Propagator {
    private final Entity entity;
    private Propagator propagator;

    public AbstractService(String name){
        entity = new Entity(name);
    }

    protected final ReturnableObject<?> propagateInside(EventObject eventObject) throws Exception {
        if (eventObject.hasNext())
            throw new PropagationException(
                    "end services can't route = "+
                            eventObject.getCoordinate()
            );

        ReturnableObject<?> returnableObject;

        Method method =
                 this.getClass()
                        .getMethod(eventObject.getMethod(), eventObject.getTypes());

        MethodVisibility methodVisibility;
        if ((methodVisibility = method.getAnnotation(MethodVisibility.class)) == null)
            throw new PropagationException("Method visibility not defined");
        if (!EventObject.visibilityTest(eventObject.getOrigin(),methodVisibility.visibility()))
            throw new PropagationException(
                    "You can't invoke a method, with visibility type = "+
                            methodVisibility.visibility()+
                            ", from "+
                            eventObject.getOrigin()
            );

        returnableObject = (ReturnableObject<?>) method.invoke(this, eventObject.getParams());
        return returnableObject;
    }

    public Propagator getPropagator() {
        return propagator;
    }

    public Entity getEntity() {
        return entity;
    }

    //region events
    protected void onCreate() throws Exception {

    }

    protected void onDestroy() throws Exception {

    }

    protected void onStart() throws Exception {

    }

    protected void onStop() throws Exception {

    }

    protected void onPause() throws Exception {

    }

    protected void onResume() throws Exception {

    }

    protected void onRestart() throws Exception {

    }
    //endregion

    @Deprecated
    public AbstractService searchServiceRoot(String name){
        return MainManager.
                getInstance().
                getManager().
                getService(name);
    }

    @Override
    public String toString() {
        return "AbstractService{" +
                "entity=" + entity +
                ", eventPropagator=" + propagator +
                '}';
    }
}
