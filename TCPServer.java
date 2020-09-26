//Ajay Dangol
//12046287
//File:TCPServer.java

import java.net.*;

import java.io.*;

import java.util.Timer;

import java.util.TimerTask;



public class TCPServer {

    public static void main(String args[]) {

        try {

            int serverPort = 1187;

            ServerSocket listenSocket = new ServerSocket(serverPort);


            while (true) {

                Socket clientSocket = listenSocket.accept();

                Connection c = new Connection(clientSocket);

                System.out.println("Client:"+ clientSocket.getPort());

                System.out.println("Receiving data from client");

                TimerTask repeatedTask = new TimerTask() {

                    public void run() {

                        String str;

                        FileOutputStream f;

                        ObjectOutputStream o;

                        try

                        {

                            f = new FileOutputStream("memberlistObject");

                            o = new ObjectOutputStream(f);

                            String dataToObject = "";

                            BufferedReader br =  new BufferedReader( new FileReader("memberlist.txt"));

                            while((str = br.readLine()) != null) {

                                dataToObject += (str +">");

                            }

                            o.writeObject(dataToObject);

                            o.close();

                        }

                        catch(IOException ex)

                        {
                            ex.printStackTrace();
                        }
                    }
                };

                Timer timer = new Timer("Timer");

                long delay  = 2000L, period = 2000L;

                timer.scheduleAtFixedRate(repeatedTask, delay, period);
            }

        } catch (IOException e) { }
    }
}

class Connection extends Thread {

    DataInputStream in;

    DataOutputStream out;

    Socket clientSocket;

    PrintWriter printWriter=null;


    public Connection(Socket aClientSocket) {

        try {

            clientSocket = aClientSocket;

            in = new DataInputStream(clientSocket.getInputStream());

            out = new DataOutputStream(clientSocket.getOutputStream());

            printWriter = new PrintWriter(new BufferedWriter(new FileWriter("memberlist.txt", true)));

            this.start();

        } catch (IOException e) {

            System.out.println("Connection: " + e.getMessage());

        }
    }



    public void run() {

        String data = null;

        try {

            data = in.readUTF();

        } catch (IOException e) {

            e.printStackTrace();

        }

        try {

            out.writeUTF(data);

        } catch (IOException e) {

            e.printStackTrace();

        }


        printWriter.println(data);

            printWriter.close();

    }
}


