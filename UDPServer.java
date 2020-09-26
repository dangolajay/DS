//Ajay Dangol
//12046287
//File:UDPServer.java


import java.io.FileInputStream;

import java.io.IOException;

import java.io.ObjectInputStream;

import java.net.DatagramPacket;

import java.net.DatagramSocket;



public class UDPServer {

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        DatagramSocket aSocket = null;

            aSocket = new DatagramSocket(2287);

            byte[] buffer = new byte[900];

            String members;


            while(true){

                DatagramPacket request = new DatagramPacket(buffer, buffer.length);

                aSocket.receive(request);

                FileInputStream fis = new FileInputStream((new String(request.getData())).trim());

                ObjectInputStream ois = new ObjectInputStream(fis);

                members = (String)ois.readObject();

                String message = "\nFirst Name:Last Name:Phone Number:Address";

                String[] membersArr = members.split(">");

                for(int i = 0; i< membersArr.length; i++){

                    message = message + ("\n"+ membersArr[i]);

                }


                ois.close();

                fis.close();

                DatagramPacket reply = new DatagramPacket(message.getBytes(), message.getBytes().length,

                        request.getAddress(), request.getPort());

                aSocket.send(reply);

                System.out.println("Client Request: "+(new String(request.getData())).trim());


          }

        }

}
