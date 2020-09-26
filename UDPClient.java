import java.io.IOException;

//Ajay Dangol
//12046287
//File:UDPClient.java

import java.net.*;


public class UDPClient {

    public static void main(String args[]) throws IOException {

            DatagramSocket aSocket;

            aSocket = new DatagramSocket();

            String fileName = "memberlistObject";

            byte [] m = fileName.getBytes();

            InetAddress aHost = InetAddress.getByName("localhost");

            int serverPort = 2287;

            DatagramPacket request =

                    new DatagramPacket(m,  fileName.length(), aHost, serverPort);

            aSocket.send(request);

            byte[] buffer = new byte[900];

            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            aSocket.receive(reply);

            System.out.println("Server Response: " + new String(reply.getData()));

    }

}
