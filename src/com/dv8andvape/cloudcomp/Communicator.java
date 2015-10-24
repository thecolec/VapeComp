package com.dv8andvape.cloudcomp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;


public class Communicator {
	
	String api_key = com.dv8andvape.cloudcomp.Main.api_key;
	String subdomain = com.dv8andvape.cloudcomp.Main.subdomain;
	String username = com.dv8andvape.cloudcomp.Main.username;
	
	String baseurl = "https://"+username+":"+api_key+"@"+"api.challonge.com/v1/";

	
	public void post(String part, String message) {
		
		try {
			
			//String url = baseurl+part+".json";
			
		

			URL url = new URL(baseurl+part);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "/");
			conn.addRequestProperty("User-agent", "Cassidy/1.0");
			conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.addRequestProperty("Authorization", "Basic aWdlZWs1OkMzZkIwaEFDaUJyWnc1ZWdpem1mMkQwZWJMczRpNURzN1VuQUE1Q3Y=");
			conn.setDoOutput(true);
			System.out.println(url.toString());
			
			conn.connect();
			
			
			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			osw.write(message);
			osw.flush();
			osw.close();
			System.out.println(os.toString());
			
			
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				
				InputStream in = conn.getErrorStream();
				String encoding = conn.getContentEncoding();
				encoding = encoding == null ? "UTF-8" : encoding;
				String body = IOUtils.toString(in, encoding);
				System.out.println(body);
				
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}


			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

	}
	public String get(String part, String message) {
		
		try {
			
			//String url = baseurl+part+".json";
			
		

			URL url = new URL(baseurl+part);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.addRequestProperty("User-agent", "Cassidy/1.0");
			conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.addRequestProperty("Authorization", "Basic aWdlZWs1OkMzZkIwaEFDaUJyWnc1ZWdpem1mMkQwZWJMczRpNURzN1VuQUE1Q3Y=");
			System.out.println(url.toString());
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				conn.disconnect();
				return output;
				
			}
			
			conn.disconnect();
			
			return output;

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		
		return null;

	}

	
	
	


}
