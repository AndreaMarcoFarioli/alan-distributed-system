package bombe.distributedArchitecture.defaultServices.master;

import bombe.core.Service;

public class Redirect extends Service {
    public Redirect() {
        super("redirect", RedirectModel.getInstance());
    }
}

