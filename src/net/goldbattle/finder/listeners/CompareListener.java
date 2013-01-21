package net.goldbattle.finder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import net.goldbattle.finder.PNG_Finder;
import net.goldbattle.finder.helpers.DirFileHandler;
import net.goldbattle.finder.helpers.UnZip;

public class CompareListener {
	//compare 1 to 2
	public static class Compare1Listener implements ActionListener {
		@Override public void actionPerformed (final ActionEvent e) {
			PNG_Finder.text.setText("");
			
			String tempLoc1 = getTMP() + "/.tempUnzip1";
			String tempLoc2 = getTMP() + "/.tempUnzip2";
			
			//unzip both of them
			File destinationFile = new File(tempLoc1).getAbsoluteFile();
			destinationFile.getParentFile().mkdirs();
			destinationFile.deleteOnExit();
			destinationFile.getParentFile().deleteOnExit();
			UnZip.unZipIt(PNG_Finder.file1.getText(), destinationFile.getAbsolutePath());
			
			File destinationFile2 = new File(tempLoc2).getAbsoluteFile();
			destinationFile2.getParentFile().mkdirs();
			destinationFile2.deleteOnExit();
			destinationFile2.getParentFile().deleteOnExit();
			UnZip.unZipIt(PNG_Finder.file2.getText(), destinationFile2.getAbsolutePath());
			
			//get both dir info				
			DirFileHandler.fileSearchPrint(tempLoc1);
			DirFileHandler.fileSearchPrint(tempLoc2);
			
			//compare directorys
			
			//remove everything
			delete(new File(tempLoc1));
			delete(new File(tempLoc2));
		}
	}

	//compare 2 to 1
	public static class Compare2Listener implements ActionListener {
		@Override public void actionPerformed (final ActionEvent e) {
			PNG_Finder.text.setText("");
			
			
		}
	}
	
	//helpers
	//delete everything
	public static void delete (final File f) {

		f.delete();

		if (f.isFile()) return;

		final File[] files = f.getAbsoluteFile().listFiles();

		if (files == null) return;

		for (final File file : files) {

			delete(file);

			f.delete();

		}

	}
	//get the temp location
	private static String getTMP() {
		String OS = System.getProperty("os.name").toUpperCase();
		
		if (OS.contains("WIN")) return System.getenv("TMP");

		else if (OS.contains("MAC") || OS.contains("DARWIN")) return System.getProperty("user.home") + "/Library/Caches/";
		else if (OS.contains("NUX")) return System.getProperty("user.home");

		return System.getProperty("user.dir");

	}
}
