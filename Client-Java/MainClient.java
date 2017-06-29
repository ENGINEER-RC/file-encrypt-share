import java.util.Scanner;
public class MainClient{
	public static void main(String args[])throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter Server Url: ");
		String ServerUrl=sc.next();
		String ServerCode=GetFile.Content(ServerUrl+"Server.txt");
		System.out.println("ReturnedString: "+ServerCode);
		System.out.print("Enter UserName: ");
		String Username=sc.next();
		System.out.print("Enter Password: ");
		String Password=sc.next();
		String Returndata=LoginFile.Post(ServerUrl+"login.php",Username,Password,ServerCode);
		if(Returndata.length()!=44){System.out.print("Login Failed");System.exit(0);}
		Returndata=FileSecurity.decrypt(Returndata,LoginFile.Stringmake(Username,Password));
		System.out.println("\nLogin Sucessfull: "+Returndata);
		int option,file;
		String ReadFiles="";
		String filedata="";
		String Packing="";
		String Lol="";
		while(true){
		System.out.println("\nChoose an option\n----------------\n1. Display-UserFiles");
		System.out.println("2. Upload-Files");
		System.out.println("3. Download-UserFiles");
		System.out.println("4. Exit");
		System.out.print("Enter Option:");
		option=sc.nextInt();
		switch(option) {
		   case 1 :
			ReadFiles=ScanDownload.getFiles(ServerUrl+"GetFiles.php",Returndata);
			if(ReadFiles.equals("##")){
			System.out.println("\nNo Files Found");}
			else if(ReadFiles.equals("#failed")){
			System.out.println("\nLogged Out");
			  System.exit(0);
			}
			else{
				System.out.println("\nFile List\n---------");
				System.out.println(ReadFiles);
			}
			ReadFiles="";
			  break; // optional
		   
		   case 2 :
			String path=FileSelect.GetFilePath();
			if(path!=null){
			System.out.println("Do You Want To Encrypt This File?(Yes=1/No=0): ");
			file=sc.nextInt();
			if(file>1){System.out.println("Invalid option");break;}
			if(file==1){
				
				System.out.println("Enter Password: ");
				filedata=sc.next();
				Packing="123456789abcdefg";
				filedata+=Packing;
				filedata=filedata.substring(0,16);
				Lol=FileSecurity.EncryptCurrFile(path,filedata,1);
				
				
				
				path="EN-"+path.substring(path.lastIndexOf("\\")+1);
				Lol=FileSecurity.EncryptCurrFile("data.encrypted",LoginFile.Stringmake(Username,Password),0);
				Lol=path;
			}
			else{
				Lol=FileSecurity.EncryptCurrFile(path,LoginFile.Stringmake(Username,Password),1);
			}
			ReadFiles=Jsonmaker.Userjson();
			System.out.println(UploadFile.upload(ServerUrl+"upload.php",Lol,Returndata,FileSecurity.encrypt(ReadFiles,ServerCode)));
			}
			  filedata="";
			  Lol="";
			  ReadFiles="";
			  break; // optional
		   
		   case 3 :
			  ReadFiles=ScanDownload.getFiles(ServerUrl+"GetFiles.php",Returndata);
			if(ReadFiles.equals("##")){
			System.out.println("\nNo Files Found");}
			else if(ReadFiles.equals("#failed")){
			System.out.println("\nLogged Out");
			  System.exit(0);
			}
			else{
				String[] parts = ReadFiles.split("\n");
				System.out.println("\nFile List\n---------");
				for(int i=0;i<parts.length;i++)
				{
					System.out.println("["+i+"] - "+parts[i]);
				}
				System.out.print("\nEnter Number Of The File: ");
				file=sc.nextInt();
				if(file>=parts.length){System.out.println("Invalid option");}
				else{
					
					filedata=ScanDownload.DownloadFile(ServerUrl+"DownloadFile.php",Returndata,parts[file]);
					if(filedata.equals("##")){
					System.out.println("\nNo Files Found");}
					else if(filedata.equals("#failed")){
					System.out.println("\nLogged Out");
					  System.exit(0);
					}
					else{
					ScanDownload.DownloadCrypt(filedata,LoginFile.Stringmake(Username,Password));}
				}
				
			}
			ReadFiles="";
			filedata="";
			  break; // opti
		   
		   case 4 :
			  System.out.println("Bye");
			  System.exit(0);
		   			  
		   
		   // You can have any number of case statements.
		   default : // Optional
			  System.out.println("Invalid Option");
		}
		
		
		
		
		
		
		}
		
	}
}