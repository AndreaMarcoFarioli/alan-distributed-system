package bombe2.distributedArchitecture.defaultServices;

import bombe2.core.Service;

public class Redirect extends Service {
    public Redirect() {
        super("redirect", RedirectModel.getInstance());
    }
}
