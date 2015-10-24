package com.dv8andvape.cloudcomp;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.apache.commons.codec.binary.StringUtils;


public class Main {
	//temp values
	public static int numVap = 0;
	public static String[] names;
	public static String temprequest;
	public static String[] matches;
	
	public static String[] matchesID;
	public static String[] namesID;
	
	//API KEY
	//MUST BE RECOMPILED EACH TIME
	public static String api_key = "C3fB0hACiBrZw5egizmf2D0ebLs4i5Ds7UnAA5Cv";
	public static String subdomain = "DV8";
	public static String username = "igeek5";
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		System.out.println("!!!DV8 Cloud Comp!!!");
		names = setup();
		namesID = new String[names.length];
		run();

	}
	
	public static String[] setup() {
		System.out.println("Competitors: ");
		Scanner sc = new Scanner(System.in);
		String input = "null";
		String[] tempA = new String [50];
		
		//read names
		int x = 0;
		while((!input.equals(""))&& x<50)
		{
			input = sc.nextLine();
			tempA[x] = input;
			x++;
		}
		sc.close();
		
		//create fitted name Array
		String[] temp = new String [x];
		for(int y = 0; y<x; y++){
			temp[y] = tempA[y];
		}
		return temp;
	}
	public static void run(){
		numVap = names.length;
		System.out.println("!!!Awesome Let's Start this Competition!!!");
		
		//createGame();
		//for(int x =0;x<names.length;x++){
		//	addPlayer(names[x]);
		//}
		//randomize();
		//startGame();
		index();
		for(int x=0;x<matches.length;x++){
			
		}

	
	}
	public static void createGame(){
		RequestGen generator = new RequestGen();
		Communicator connection = new Communicator();
		
		temprequest = generator.requestGen("createcomp", api_key);

		try {
			connection.post("tournaments.json", temprequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void addPlayer(String Name){
		RequestGen generator = new RequestGen();
		Communicator connection = new Communicator();
		
		temprequest = generator.requestGen("createcomp", api_key);

		try {
			connection.post("tournaments/"+subdomain+"-test1/participants.json", "participant%5Bname%5D="+Name+"&api_key="+api_key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void startGame(){
		RequestGen generator = new RequestGen();
		Communicator connection = new Communicator();
		
		temprequest = generator.requestGen("createcomp", api_key);

		try {
			connection.post("tournaments/"+subdomain+"-test1/start.json", "api_key="+api_key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void randomize(){
		RequestGen generator = new RequestGen();
		Communicator connection = new Communicator();
		
		temprequest = generator.requestGen("createcomp", api_key);

		try {
			connection.post("tournaments/"+subdomain+"-test1/participants/randomize.json", "api_key="+api_key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void index(){
		RequestGen generator = new RequestGen();
		Communicator connection = new Communicator();
		
		temprequest = generator.requestGen("createcomp", api_key);

		String toparse = connection.get("tournaments/"+subdomain+"-test1/matches.json", "api_key="+api_key);
		String findStr = "\"match\"";
		int lastIndex = 0;
		int count = 0;
		while(lastIndex != -1){
		    lastIndex = toparse.indexOf(findStr,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += findStr.length();
		    }
		}
		System.out.println(count);
		matches = new String[count];
		matchesID = new String[count];
		
		
		for(int x=0; x<count; x++)
        {   
            int quotient, remainder;
            String result="";
            quotient=x;

            while (quotient >= 0)
            {
                remainder = quotient % 26;
                result = (char)(remainder + 65) + result;
                quotient = (int)Math.floor(quotient/26) - 1;
            }
            matches[x]=result;
        }
		
		for(int x=0;x<matches.length;x++){
			System.out.print(matches[x]+" ");
		}
		matchRead(toparse);

		// TODO Auto-generated catch block

	}
	public static void update(int matchnum, String scores, int winner){
		
		
		
		RequestGen generator = new RequestGen();
		Communicator connection = new Communicator();
		
		temprequest = generator.requestGen("createcomp", api_key);

		try {
			connection.post("tournaments/"+subdomain+"-test1/matches/"+matches[matchnum]+".json", "api_key="+api_key+"&match%5Bscores_csv%5D="+scores+"&match%5Bwinner_id%5D="+Integer.toString(winner));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void matchRead(String data){
		String input = data;
		int index = input.indexOf("\"id\":");
		int count = 0;
		while (index != -1) {
			System.out.println(input.substring(index+5, index+13));
		    count++;
		    input = input.substring(index + 1);
		    index = input.indexOf("\"id\":");
		}
	}
}
