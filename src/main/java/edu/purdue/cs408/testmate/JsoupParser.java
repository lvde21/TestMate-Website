package edu.purdue.cs408.testmate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



public class JsoupParser {
	String filePath;  //the name of html to open and modify
	Document doc; // // get the Document for the fileName, which is easy to access in any methods in this class.
    private static String moduleName;
    private static List<String> programFiles;
    private static List<String> testFiles;
   	private static List<String> configureFiles;
	private static ClientSocket clientSocket = new ClientSocket();

	public JsoupParser() {
		// test if client socket is working

	}

    /* not needed */
	/*public JsoupParser(String filePath) throws IOException {
		 this.filePath = filePath;
		 // get the Document for the fileName
		 File input = new File(filePath);
		 Document doc = Jsoup.parse(input, "UTF-8");
		 this.doc = doc;
	}*/

	/* not needed */
	/*public void displayResult(String result) throws IOException {
		// Specify the Element form the document
		 Element res = doc.getElementById("result");
		 System.out.println(res.text()); // for debug purpose
		 // modify the text content of the Element
		 res.text(result);
		 System.out.println(res.text()); // for debug purpose
		 // write back to the same file

		 SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
			    	 FileWriter fw = null;
					try {
						fw = new FileWriter(filePath);
						fw.write(doc.toString());
						 fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			    }
			});
	} */

	public static void setModuleName(String name) {
		moduleName = name;
	}

	public static void setProgramFiles(List<String> fileList) {
		programFiles = fileList;
	}

	public static void setTestFiles(List<String> fileList) {
		testFiles = fileList;
	}

	public static void setConfigFiles(List<String> fileList) {
		configureFiles = fileList;
	}


	/*
	 * send test module information when 
	 * creating a new test module to server 
	 */
	public static void sendTestModuleToServer() {  // port:8001
		String module = "CREATE;{{ModuleName:" + moduleName + "},{Files:"; //File1,...(Executable file); Test File; Script file...>}}

		System.err.println("dsadasndasda\n");

		for (int i = 0; i < programFiles.size(); i++) {
			module += programFiles.get(i);

			if (i != programFiles.size() - 1) {
				module += ",";
			}
		}

		module += "};{Files:";

		for (int i = 0; i < testFiles.size(); i++) {
			module += testFiles.get(i);

			if (i != testFiles.size() - 1) {
				module += ",";
			}
		}
		module += "};{Files:";

		for (int i = 0; i < configureFiles.size(); i++) {
			module += configureFiles.get(i);

			if (i != configureFiles.size() - 1) {
				module += ",";
			}
		}
		module += "}}";

		try {
			System.err.println("djasdasdasdahd\n");
			System.err.println(module);
			String message = clientSocket.run(module, "Input Form");

        } catch (Exception e) {
        	 System.err.println("Client Error: " + e.getMessage());
        }

	}



	/* Retrieve test module information 
	 * from the server for a single test module
	 */
	public static String sendGetRequestToServer(String moduleName) {
		String getRequest = "SEND:" + moduleName;

		try {
			String message = clientSocket.run("Hi Server! I am the Display form", "");
			if (message.contains("Hi DisplayForm. What do you need")) {
				message = clientSocket.run(getRequest, "Display Form");

				// TO DO : parsing 

				return message;  //TODO: Change this if necessary
			}
		} catch (Exception e) {
			System.err.println("Client Error: " + e.getMessage());
		}

		return "ERROR: Could not get response from server. Please try again or check the server";
	}



	/* Retrive information for all test modules
	 * suitable for represting on the landing page
	 */
	public static List<TestModuleForForm> sendGetAllRequestToServer() {
		String getAllRequest = "GET ALL";
		List<TestModuleForForm> testModuleList = new ArrayList();

		getAllRequest += "\r\n";
		try {
			String message = clientSocket.run("Hi Server! I am the Display form\r\n", "");
			System.err.println(message);
			if (message.contains("Hi DisplayForm. What do you need")) {
				System.err.println("Sending");
				message = clientSocket.run(getAllRequest, "Display Form");
				System.err.println("Hi");


				// message parsing
				if(message.equals("")) throw new Exception();
				else {
					StringTokenizer st = new StringTokenizer(message, ",}");
					while(st.hasMoreTokens()) {
						/*System.err.println(getValue(st.nextToken()));
						System.err.println(getValue(st.nextToken()));	
						System.err.println(getValue(st.nextToken()));
						System.err.println(getValue(st.nextToken()));
						System.err.println(getValue(st.nextToken()));
						System.err.println(getValue(st.nextToken()));*/

						TestModuleForForm testModule = new TestModuleForForm(getValue(st.nextToken()), 
														getValue(st.nextToken()), getValue(st.nextToken()), 
														Integer.parseInt(getValue(st.nextToken())), 
														Integer.parseInt(getValue(st.nextToken())),
														Integer.parseInt(getValue(st.nextToken())));
						testModuleList.add(testModule);
					}
				}	

				
				return testModuleList;
			
			}
		} catch (Exception e) {
			//return "Client Error: " + e.getMessage();
			e.printStackTrace();
			return null;
		}
		return null;
		//return "ERROR: Could not get response from server. Please try again or check the server";
	}



	public static void runModule(String moduleName) {
		String runRequest = "RUN:" + moduleName;
		try {
			String message = clientSocket.run("Hi Server! I am the Display form", "");
			if (message.equalsIgnoreCase("Hi DisplayForm. What do you need")) {
				message = clientSocket.run(runRequest, "Display Form");

				//return message;  TODO: Change this if necessary
			}
		} catch (Exception e) {
			System.err.println("Client Error: " + e.getMessage());
		}
	}



	/* 
	 * Utility method to extract value from 
	 * key:value pairs in a string seperated 
	 * by :
	 */
	public static String getValue(String keyValuePair) {
        StringTokenizer st = new StringTokenizer(keyValuePair, ":");
        st.nextToken();
        if(st.hasMoreTokens()) return st.nextToken();
        else return "";
     }
    

	/*
	public static void main(String[] args) throws IOException {

        JsoupParser jp = new JsoupParser();
        jp.setModuleName("abcd");
        //jp.sendDataToServer();
        //jp.displayResult("test4 passed");

	     //JsoupParser jp2 = new JsoupParser("C:\\Users\\User\\eclipse\\JsoupParser\\src\\test.html");
	     //jp2.displayResult("test5 passed");


	}
	*/

}