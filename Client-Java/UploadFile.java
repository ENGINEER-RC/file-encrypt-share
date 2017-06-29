import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
 
 
public class UploadFile {
  public static String upload(String PostLink,String FilePath,String CryptU,String jsonU) throws Exception {
		String Cookie="";
		System.out.println("File-Name: "+FilePath);
		System.out.println("Identify: "+CryptU);
		System.out.println("Share-User-Json: "+jsonU);
        String userHome=System.getProperty("user.home");
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpPost httppost = new HttpPost(PostLink);
        File file = new File("data.encrypted");
		File file2 = new File(FilePath);
		file.renameTo(file2);
        MultipartEntity mpEntity = new MultipartEntity();
        ContentBody contentFile = new FileBody(file2);
        mpEntity.addPart("userfile", contentFile);
		mpEntity.addPart("CryptU", new StringBody(CryptU));
		mpEntity.addPart("json_user", new StringBody(jsonU));
        httppost.setEntity(mpEntity);
        System.out.println("executing request " + httppost.getRequestLine());
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity resEntity = response.getEntity(); 
		file2.delete();
 
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
	
 

}