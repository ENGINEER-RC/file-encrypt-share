import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
public class FileSelect {
	public static String GetFilePath() {
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName()
					);
		} catch (Exception e) { }

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			return selectedFile.getAbsolutePath();
		}
		else{
			return null;
		}
	}
}