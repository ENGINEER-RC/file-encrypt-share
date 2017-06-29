import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpEntity;

public class LoginFile {
	
		public static String Stringmake(String Username,String Password){
			String Packing="123456789abcdefg";
			Password+=Packing;
			Password=Password.substring(0,16);
			
			Username+=Packing;
			Username=Username.substring(0,16);
			Username = new StringBuffer(Username).reverse().toString();
			
			char passe1[]=Username.toCharArray();
			char passe2[]=Password.toCharArray();
			Password="";
			for(int i=0;i<16;i++)
			{
				Password+=(char)(((int)passe1[i]+(int)passe2[i])/2);
			}
			return Password;
		}
         public static String Post(String PostLink,String Username,String Password,String Servar) throws Exception  {
				String Cookie="";
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(PostLink);
				String UPc=Stringmake(Username,Password);
						String CryptU=FileSecurity.encrypt(Username,Servar);
						String CryptP=FileSecurity.encrypt(UPc,Servar);
						System.out.println("Username: "+Username);
						System.out.println("Crypted-UserName: "+CryptU);
						System.out.println("Password: "+Password);
						System.out.println("Merged-Password: "+UPc);
						System.out.println("Crypted-Merged-Password: "+CryptP);
                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                        nameValuePairs.add(new BasicNameValuePair("user",CryptU));
						nameValuePairs.add(new BasicNameValuePair("pass",CryptP));
                        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                        HttpResponse response = client.execute(post);
                        HttpEntity resEntity =response.getEntity();
                        if(!(response.getStatusLine().toString()).equals("HTTP/1.1 200 OK")){
							Cookie=null;
						}
						else{
							if (resEntity != null) {
							Cookie=EntityUtils.toString(resEntity);
							}
						}
						System.out.println(response.getStatusLine());
						
						if (resEntity != null) {
							resEntity.consumeContent();
						}
						client.getConnectionManager().shutdown();
						return Cookie;
        }

}