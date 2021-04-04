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

