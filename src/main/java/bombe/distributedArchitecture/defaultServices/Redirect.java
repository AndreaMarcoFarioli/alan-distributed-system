package bombe.distributedArchitecture.defaultServices;

import bombe.core.Service;
import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import bombe.core.definitions.ServiceModel;
import bombe.distributedArchitecture.MainManager;

public class Redirect extends Service {
    public Redirect() {
        super("redirect", RedirectModel.getInstance());
    }
}

class RedirectModel implements ServiceModel{
    private static RedirectModel redirectMethod = null;

    ReturnableObject<?> exchange(EventObject eventObject) throws Exception{
        return MainManager.getInstance().getNodeProvider().getNode().call((EventObject) eventObject.getParams()[0]);
    }

    public static RedirectModel getInstance(){
        if (redirectMethod == null)
            redirectMethod = new RedirectModel();
        return redirectMethod;
    }
}