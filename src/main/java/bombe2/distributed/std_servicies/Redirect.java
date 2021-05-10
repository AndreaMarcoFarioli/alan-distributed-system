package bombe2.distributed.std_servicies;

import bombe2.annotations.MethodVisibility;
import bombe2.annotations.VisibilityType;
import bombe2.core.Service;
import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.distributed.RootManager;

public class Redirect extends Service {
    public Redirect() {
        super("redirect");
    }

    @MethodVisibility(visibility = VisibilityType.PUBLIC)
    public ReturnableObject<?> commute(EventObject eventObject, EventObject target) throws Exception {
        RootManager.getInstance().sendOver(target);
        return null;
    }
}
