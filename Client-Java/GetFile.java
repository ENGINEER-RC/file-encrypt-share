import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetFile {
			
			public static String Content(String path)throws Exception{
			URL url = new URL(path);
			String Lol="";
			URLConnection conn = url.openConnection();
			BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream()));
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				Lol+=inputLine;
			}
			br.close();
			return Lol;
			}
}
