package alands.distributed.remote_data;

public interface RemoteItemManipulator<T>
        extends RemoteDeleter<T>, RemoteSetter<T>, RemoteGetter<T>{
}
