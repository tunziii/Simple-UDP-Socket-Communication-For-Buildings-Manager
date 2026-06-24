import java.io.IOException;
import java.net.DatagramSocket;

public class BuildingProxy {
    private String buildingName;
    private String Host;


    BuildingProxy(String name, String Host){
        this.buildingName = name;
        this.Host = Host;
    }

    String ProxyGetName() throws IOException {
        System.out.print("Function: GetName\n");

        return UDPClient.SendAndRecieveProxy("getName." + buildingName);
    }

    String ProxySearchRoom(String roomName) throws IOException {
        System.out.print("Function: SearchRoom\n");

        return UDPClient.SendAndRecieveProxy("searchRoom." + buildingName + "." + roomName);
    }

    String ProxyAddRoom(String roomName, String floor, int size) throws IOException {
        System.out.print("Function: AddRoom\n");

        return UDPClient.SendAndRecieveProxy("addRoom." + buildingName + "." + roomName + "." + floor + "." + size);
    }

    String ProxyGetRooms() throws  IOException {
        System.out.print("Function: GetRooms\n");

        return UDPClient.SendAndRecieveProxy("getRooms." + buildingName);
    }
}
