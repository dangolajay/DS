//Ajay Dangol
//12046287
//File:TCPServer.java

import java.net.*;

import java.io.*;

import java.util.Scanner;



public class TCPClient {

    public static void main(String args[]) {

        Socket s = null;

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Enter Detail for Member Number:");

            String number = scanner.nextLine();

            System.out.println("Enter your first name:");

            String firstName = scanner.nextLine();

            System.out.println("Enter your last name:");

            String lastName = scanner.nextLine();

            System.out.println("Enter your Address:");

            String address = scanner.nextLine();

            System.out.println("Enter your Phone Number:");

            String phoneNumber = scanner.nextLine();

            System.out.println("Sending Data to server........");

            String memberDetailString = firstName + ":" + lastName + ":" + address + ":" + phoneNumber;


            try {

                int serverPort = 1187;

                s = new Socket("localhost", serverPort);

                DataInputStream in = new

                        DataInputStream(s.getInputStream());

                DataOutputStream out = new

                        DataOutputStream(s.getOutputStream());

                out.writeUTF(memberDetailString);

                String dataFromServer = in.readUTF();


                System.out.println(dataFromServer);

                System.out.println("Server Response: Save data of the member number: " + number);

                System.out.println("-------------------------------------------------");



            } catch (UnknownHostException e) {

                System.out.println("Sock:" + e.getMessage());

            } catch (EOFException e) {

                System.out.println("EOF:" + e.getMessage());

            } catch (IOException e) {

                System.out.println("IO:" + e.getMessage());

            } finally {

                if (s != null)

                    try {

                        s.close();

                    } catch (IOException e) {

                        System.out.println("close:" + e.getMessage());

                    }
            }

        }

    }
}







