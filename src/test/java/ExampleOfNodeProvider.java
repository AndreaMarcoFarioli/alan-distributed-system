import bombe2.core.data.EventObject;
import bombe2.distributed.NodeProvider;
import bombe2.distributed.RemoteNode;
import bombe2.exceptions.MalformedEventException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ExampleOfNodeProvider extends NodeProvider {

    private List<RemoteNodeScoreBundled> nodeList = new ArrayList<>();

    @Override
    public RemoteNode getNode() throws RemoteException {
        return nodeList.get(0).getRemoteNode();
    }

    @Override
    public EventObject middleware(EventObject eventObject) throws MalformedEventException {
        return null;
    }
}

class RemoteNodeScoreBundled{
    private long score;
    private final RemoteNode remoteNode;

    public RemoteNodeScoreBundled(RemoteNode nodeProvider){
        this(nodeProvider, 0);
    }

    public RemoteNodeScoreBundled(RemoteNode nodeProvider, long score){
        this.score = score;
        this.remoteNode = nodeProvider;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getScore() {
        return score;
    }

    public RemoteNode getRemoteNode() {
        return remoteNode;
    }
}
