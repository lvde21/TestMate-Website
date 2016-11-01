package edu.purdue.cs408.testmate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;

/**
 * Created by Dhairya on 10/18/2016.
 */
public class ClientSocket {
    Socket clientSocket = null;
    PrintWriter out;
    BufferedReader in;
    private String hostName = "localhost";
    private int portNumber = 8001;


    public ClientSocket() {
        try {
            clientSocket = new Socket(hostName, portNumber);

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String run(String message, String formType) {
        try {
            clientSocket.setReuseAddress(true);

            String fromServer = "";
            String fromUser;

            message += "\r\n";

            clientSocket.getOutputStream().write(message.getBytes());
            InputStream ins = clientSocket.getInputStream();

            byte[] contents = new byte[1024];

            int bytesRead = 0;
            int read = 0;

            String inMessage = "";

            while((read = ins.read(contents)) != -1) {
                inMessage += new String(contents, bytesRead, read);
                bytesRead += read;
                if (inMessage.contains("\r\n")) {
                    inMessage = inMessage.substring(0, inMessage.indexOf('\r'));
                    break;
                }
            }
            if (inMessage != null && !inMessage.equals(""))   {
                System.out.println(inMessage);
                return inMessage;
            }
            else {
                return run(message, formType);
            }
        } catch(UnknownHostException e){
                System.err.println("Don't know about host " + hostName);
                System.exit(1);
        } catch(IOException e){
                System.err.println("Could not connect to host on port:" + portNumber);
                System.exit(1);
        }

        return "";
    }

    public void close() {
        out.println("Bye");
        try {
            clientSocket.close();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}