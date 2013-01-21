package net.goldbattle.finder.helpers;

import java.io.File;
import java.util.ArrayList;

import net.goldbattle.finder.PNG_Finder;

public class DirFileHandler {

	public static ArrayList<String> fileSearchPrint(String path) {
		ArrayList<String> files = fileSearch(path);

		PNG_Finder.text.append("\n");
		for (String file : files) {
			PNG_Finder.text.append(file.toString()+"\n");
		}
		return files;
	}

	public static ArrayList<String> fileSearch(String path) {
		File parent = new File(path);		
		return listFilesRec(parent, parent);
	}

	protected static ArrayList<String> listFilesRec(File parent, File folder) {
		ArrayList<String> lstFiles = new ArrayList<String>(25);
		if (folder.isDirectory()) {

			File[] files = folder.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						lstFiles.addAll(listFilesRec(parent, file));
					} else {
						String path = file.getPath();
						String offset = parent.getPath();

						path = path.substring(offset.length());
						lstFiles.add(path);
					}
				}
			}
		}

		return lstFiles;
	}
}
