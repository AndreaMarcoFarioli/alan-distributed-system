package bombe2.distributed.std_servicies;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.core.definitions.ServiceModel;

public class RedirectModel implements ServiceModel {
    private static RedirectModel redirectMethod = null;

    public ReturnableObject<?> exchange(EventObject eventObject) throws Exception {
        System.out.println(eventObject);
        //return MainManager.getInstance().getNodeProvider().getNode().call(eventObject);
        return null;
    }

    public static RedirectModel getInstance() {
        if (redirectMethod == null)
            redirectMethod = new RedirectModel();
        return redirectMethod;
    }
}
