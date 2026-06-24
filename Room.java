import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class Room {
    private String name;
    private String floor;
    private int size;

    Room(String name, String floor, int size){
        super();
        this.name = name;
        this.floor = floor;
        this.size = size;
    }

    public String getName() throws RemoteException {return name;}
    public String getFloor() throws RemoteException{return floor;}
    public int getSize() throws RemoteException{return size;}
    public void setSize(int s) throws RemoteException{size=s;}
}