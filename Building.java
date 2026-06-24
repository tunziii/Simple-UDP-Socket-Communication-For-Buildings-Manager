import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class Building extends UnicastRemoteObject implements BuildingImp{
    private String name;
    private Room[] rooms;

    public Building(String name) throws RemoteException {
        super();
        this.name = name;
        rooms = new Room[20];
    };

    public Room searchRoom(String roomName) throws RemoteException{
        boolean found = false;
        for(Room r : rooms){
            if(r == null){
                break;
            }

            if(Objects.equals(r.getName(), roomName)){
                found = true;
                return r;
            }
        }
        return null;
    }

    public void addRoom(Room r) throws RemoteException{
        boolean added = false;
        for(int i = 0; i < rooms.length; i++){
            if(rooms[i] == null){
                rooms[i] = r;
                System.out.println("Room " + rooms[i].getName() + " added!");
                added = true;
                break;
            }
        }
        if(!added){System.out.println("Building is full");}
    }

    public int size() throws RemoteException{
        int i = 0;
        for(Room r : rooms){
            if(r != null){i += r.getSize();}
        }
        return i;
    }

    public String getName() throws RemoteException{return name;}
    public Room[] getRooms() throws RemoteException{return rooms;}
}

