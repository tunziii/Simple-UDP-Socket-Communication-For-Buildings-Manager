import com.sun.tools.javac.Main;

import java.net.*;
import java.io.*;

public class UDPClient{

  public void main(String args[]) throws IOException {
  // args[0]: Message
  // args[1]: Server
    //searchRoom.a100
    //addRoom.MainBuilding.a102.EG.5
    //getRooms.MainBuilding
    //getName.MainBuilding

    BuildingProxy MainBuilding = new BuildingProxy("Main Building","localhost");
    BuildingProxy NotMainBuilding = new BuildingProxy("Not Main Building","localhost");

    MainBuilding.ProxyGetName();
    MainBuilding.ProxyAddRoom("a102", "EG", 5);
    MainBuilding.ProxyAddRoom("a103", "EG", 10);
    MainBuilding.ProxySearchRoom("a103");
    MainBuilding.ProxyGetRooms();

    NotMainBuilding.ProxyGetName();
    NotMainBuilding.ProxyAddRoom("a105", "EG", 20);
    NotMainBuilding.ProxyAddRoom("a106", "EG", 30);
    NotMainBuilding.ProxySearchRoom("a106");
    NotMainBuilding.ProxyGetRooms();

  }

  static String SendAndRecieveProxy(String Msg) throws IOException {

    DatagramSocket aSocket = new DatagramSocket();
    String port = "localhost";
    byte [] m = Msg.getBytes();
    InetAddress aHost = InetAddress.getByName(port);
    int serverPort = 6789;
    DatagramPacket request = new DatagramPacket (m, m.length, aHost, serverPort);
    aSocket.send(request);

    byte[] buffer = new byte[1000];
    DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(reply);

    String response = new String(reply.getData(), 0, reply.getLength());

    System.out.println(" Reply: " + response + "\n");
    aSocket.close();

    return response;
  }
}
