import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.File;
import java.nio.file.*;
import java.io.FileWriter;
import org.apache.commons.codec.binary.Hex;
import java.lang.*;

import org.apache.commons.codec.binary.Base64;

public class FileSecurity {
	public static String encrypt(String input, String key)throws Exception{
	  byte[] crypted = null;
	  try{
	    SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
	      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	      cipher.init(Cipher.ENCRYPT_MODE, skey);
	      crypted = cipher.doFinal(input.getBytes());
	    }catch(Exception e){
	    	System.out.println(e.toString());
	    }
	    return new String(Base64.encodeBase64(crypted));
	}
	
	public static String decrypt(String input, String key)throws Exception {
	    byte[] output = null;
	    try{
	      SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
	      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	      cipher.init(Cipher.DECRYPT_MODE, skey);
	      output = cipher.doFinal(Base64.decodeBase64(input));
	    }catch(Exception e){
	      System.out.println(e.toString());
	    }
	    return new String(output);
	}
	
	public static String bytesToHex(byte[] in) {
    final StringBuilder builder = new StringBuilder();
    for(byte b : in) {
        builder.append(String.format("%02x", b));
    }
    return builder.toString();
	}
	
	public static String EncryptCurrFile(String path,String key,int i) throws Exception {
		byte[] datab =Files.readAllBytes(Paths.get(path));
		String str =new String(datab);
		
				if(i!=0){str=bytesToHex(datab);}		
                        
		File file = new File("data.encrypted");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(FileSecurity.encrypt(str, key));
		fileWriter.flush();
		fileWriter.close();
		return path.substring(path.lastIndexOf("\\")+1,path.length());
	} 
	
		public static boolean DecryptStorFile(String data,String key,String FileName) throws Exception {
		dircheck("Downloads");
		File file = new File("Downloads\\"+FileName);
		int bet=file.getName().lastIndexOf(".");
		String filename=file.getName().substring(0, bet) + "_"+Long.toString(System.nanoTime()%100000) + file.getName().substring(bet, file.getName().length());
		
		if (file.exists()) {
				File file2 = new File("Downloads\\"+filename);
				System.out.println("File Saved:\nDownloads\\"+file2.getName());
				file=file2;
			}
		else{System.out.println("File Saved:\nDownloads\\"+file.getName());};
		
		String str=FileSecurity.decrypt(data, key);
		
								int len = str.length() / 2;
                                byte[] demBytes = new byte[len];
                                for (int i=0; i<len; i++) {
                                        demBytes[i] = (byte) Integer.parseInt(str.substring(i*2,i*2+2),16);
                                }
		

		Path path = Paths.get("Downloads\\"+file.getName());
		Files.write(path, demBytes);
		return true;
	} 
	
	public static void dircheck(String dirpath)throws Exception {
			File theDir = new File(dirpath);

			// if the directory does not exist, create it
			if (!theDir.isDirectory()) {
				System.out.println("Creating Directory: " + theDir.getName());
				boolean result = false;

				try{
					theDir.mkdir();
					result = true;
				} 
				catch(SecurityException se){
					//handle it
				}        
				if(result) {    
					System.out.println("Done.");  
				}
			}
	}
	
}
