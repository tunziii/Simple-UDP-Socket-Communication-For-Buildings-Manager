import java.net.*;
import java.io.*;
import java.rmi.RemoteException;

public class UDPServer {

  Building a = new Building("Main Building");
  Building b = new Building("Not Main Building");
  Building[] buildings = {a,b};

    public UDPServer() throws RemoteException {
    }

    public void main(String args[]) throws RemoteException {

    a.addRoom(new Room("a100", "EG", 10));
    b.addRoom(new Room("a101", "EG", 5));

    System.out.println("The Server is running");
    try{
      DatagramSocket aSocket = new DatagramSocket (6789);
      byte[] buffer = new byte[1000];

      while(true){
        //recieve msg
        DatagramPacket request = new DatagramPacket (buffer, buffer.length);
        aSocket.receive (request);

        // msg = function.objectName.parameter1.parameter2.parameter3
        String message = new String(request.getData(), 0, request.getLength());

        System.out.println(" Request: " + message);
        String send = function(message);
        byte [] newBuffer = send.getBytes();

        //send msg
        DatagramPacket reply = new DatagramPacket (newBuffer, newBuffer.length, request.getAddress(), request.getPort());
        aSocket.send (reply);
      }
    }catch (SocketException e){ System.out.println(" Socket: " + e.getMessage());
    }catch (IOException e) {System.out.println(" IO: " + e.getMessage());}
  } // main

  public String function(String message) throws RemoteException {
    String[] parts = message.split("\\.");
    int bID = 0;

    for (int i = 0; i < buildings.length; i++){
      if(buildings[i].getName().equals(parts[1])){
        bID = i;
      }
    }

    //msg = searchRoom.MainBuilding.a100
    if(parts[0].equals("searchRoom")){
        for(Room r : buildings[bID].getRooms()){
          if(r != null && r.getName().equals(parts[2])){ return "Room " + parts[2] + " found in building " + "'" + buildings[bID].getName() + "'";}
        }
      return "Room " + parts[2] + " not found";
    }

    //msg = addRoom.MainBuilding.a102.EG.5
    else if(parts[0].equals("addRoom")){
      buildings[bID].addRoom(new Room(parts[2], parts[3], Integer.parseInt(parts[4])));
      return "Room " + parts[2] + " has been added to " + "'" + buildings[bID].getName() + "'";
    }

    //msg = getName.MainBuilding
    else if(parts[0].equals("getName")){
      return buildings[bID].getName();
    }

    //msg = getRooms.MainBuilding
    else if(parts[0].equals("getRooms")){
      String g = "";
      Room[] rooms = buildings[bID].getRooms();

      for(Room r : rooms){
        if(r != null){
          g += "\n" + r.getName() +  " " + r.getFloor() + " " + r.getSize();
        }
      }
      return g;
    }

    return "error, no function found";
  }

} //class UDPServer