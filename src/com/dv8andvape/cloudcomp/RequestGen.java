package com.dv8andvape.cloudcomp;

public class RequestGen {
	
	public String requestGen(String type, String api_key){
		String temp;
		//used to create json for tourny control
		if(type.equals("createcomp")){

			temp = "tournament%5Bname%5D=test1"
				+ "&tournament%5Bsubdomain%5D=DV8"
				+ "&tournament%5Burl%5D=test1"
				+ "&api_key="+api_key
				+ "&tournament%5Btournament_type%5D=single+elimination";
			return temp;
		}
		if (type.equals("startcomp")){
			
		}
		if (type.equals("stopcomp")){

		}
		if (type.equals("destroycomp")){

		}
		
		
		//used to control participants
		if (type.equals("randomize")){

		}
		
		
		//used to update the match details
		if (type.equals("update")){

		}

		return null;
	}
	
	public String requestGen(String type, String api_key, String[] names){
		String temp;
		if (type.equals("addpeople")){
			temp = "&api_key="+api_key;
			for(int x = 0;x< names.length;x++){
				temp+="b";
			}
		return temp;

		}
		
		
		return api_key;
		
	}
	

}
