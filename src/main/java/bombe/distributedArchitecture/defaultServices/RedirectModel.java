package bombe.distributedArchitecture.defaultServices;

import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import bombe.core.definitions.ServiceModel;
import bombe.distributedArchitecture.MainManager;

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
