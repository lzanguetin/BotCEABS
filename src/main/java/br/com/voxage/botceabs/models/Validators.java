package br.com.voxage.botceabs.models;

public class Validators{
	public static boolean validaOs(String os){
		   os = os.replaceAll("[^a-zA-Z0-9]", "");
		   boolean valido = true;
		   
		   if((os.length() < 7) || (os == null)){
			   valido = false;
		   }
		   
		   if (!os.substring(0).matches("[0-9]*")) {
			   valido = false;
		   }
		   return valido;
		}	
}