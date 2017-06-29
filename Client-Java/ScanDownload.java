import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
 
 
public class ScanDownload {
	  public static String getFiles(String PostLink,String CryptU) throws Exception {
		String Cookie="";
		System.out.println("Identify: "+CryptU);
        String userHome=System.getProperty("user.home");
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpPost httppost = new HttpPost(PostLink);
		                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                        nameValuePairs.add(new BasicNameValuePair("CryptU",CryptU));
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        System.out.println("executing request " + httppost.getRequestLine());
 
 
       HttpResponse response = httpclient.execute(httppost);
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
						httpclient.getConnectionManager().shutdown();
						return Cookie;
    }
	  public static String DownloadFile(String PostLink,String CryptU,String FileName) throws Exception {
		String Cookie="";
		System.out.println("Identify: "+CryptU);
		System.out.println("File-Name: "+FileName);
        String userHome=System.getProperty("user.home");
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpPost httppost = new HttpPost(PostLink);
        MultipartEntity mpEntity = new MultipartEntity();
		mpEntity.addPart("CryptU", new StringBody(CryptU));
		mpEntity.addPart("file_name", new StringBody(FileName));
        httppost.setEntity(mpEntity);
        System.out.println("executing request " + httppost.getRequestLine());
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity resEntity = response.getEntity(); 

        if(!(response.getStatusLine().toString()).equals("HTTP/1.1 200 OK")){
            // Successfully Uploaded
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
        httpclient.getConnectionManager().shutdown();
		return Cookie;
    }
	public static void DownloadCrypt(String Downloaddata,String password)throws Exception{
		Scanner sc=new Scanner(System.in);
		String fileName=Downloaddata.substring(Downloaddata.lastIndexOf("\\")+1,Downloaddata.length());
		String Packing="",filedata="";
		String Lol=Downloaddata.substring(0,Downloaddata.lastIndexOf("\\"));
		if(fileName.substring(0,3).equals("EN-"))
		{
		
			System.out.println("File May Be Encrypted, Do you want to Decrypt? (Yes=1/No=0): ");
			int file=sc.nextInt();
			if(file==1){
				
				
				fileName=fileName.substring(3);
				System.out.println("Enter Password: ");
				filedata=sc.next();
				Packing="123456789abcdefg";
				filedata+=Packing;
				filedata=filedata.substring(0,16);
				Lol=FileSecurity.decrypt(Lol,password);
				
				FileSecurity.DecryptStorFile(Lol,filedata,fileName);
			}
			else{
				FileSecurity.DecryptStorFile(Lol,password,fileName);
			}
		}
	    else{ FileSecurity.DecryptStorFile(Lol,password,fileName);}
		
		
	}
	
 

}