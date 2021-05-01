package example;

import bombe2.core.AbstractService;
import bombe2.core.Manager;
import bombe2.core.data.EventObject;
import bombe2.distributed.MainManager;

public class Main {
    public static void main(String[] args) throws Exception {
        MainManager mainManager = MainManager.getInstance();
        Manager manager = mainManager.getManager();
        AbstractService exampleService = new ExampleService();
        manager.addService(exampleService);
        //System.out.println(((ExtendableService)exampleService).getManager().getService("ser"));
        mainManager.call(new EventObject("exampleService:method"));
    }
}
