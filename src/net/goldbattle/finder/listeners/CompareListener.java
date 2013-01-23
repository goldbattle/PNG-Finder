package net.goldbattle.finder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import net.goldbattle.finder.PNG_Finder;
import net.goldbattle.finder.helpers.DirFileHandler;
import net.goldbattle.finder.helpers.UnZip;

public class CompareListener {
	//compare 1 to 2
	public static class Compare1Listener implements ActionListener {
		@Override public void actionPerformed (final ActionEvent e) {
			Thread thread = new Thread(){
				public void run(){
					PNG_Finder.text.setText("");

					String tempLoc1 = getTMP() + "/.tempUnzip1";
					String tempLoc2 = getTMP() + "/.tempUnzip2";

					try{
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
						ArrayList<String> files1 = DirFileHandler.fileSearch(tempLoc1);
						ArrayList<String> files2 = DirFileHandler.fileSearch(tempLoc2);

						//only have .png
						ArrayList<String> temp = new ArrayList<String>();
						for (String file : files1) {
							if(file.toLowerCase().endsWith(".png")){
								temp.add(file);
							}
						}
						files1=temp;
						temp = new ArrayList<String>();
						for (String file : files2) {
							if(file.toLowerCase().endsWith(".png")){
								temp.add(file);
							}
						}
						files2=temp;

						//compare directorys
						ArrayList<String> finalOut = new ArrayList<String>();
						for(String dir2 : files2){
							for(String dir1 : files1){
								if(dir2.equals(dir1)){
									finalOut.add(dir2);
								}					
							}				
						}

						for(String finallOUtTemp : finalOut){
							files2.remove(files2.indexOf(finallOUtTemp));
						}

						//print things out
						PNG_Finder.text.append("=====DIFFERENCES====="+"\n");
						for (String file : files2) {
							PNG_Finder.text.append(file.toString()+"\n");
						}
					}
					catch(Exception e1){
						e1.printStackTrace();
					}

					//remove everything
					delete(new File(tempLoc1));
					delete(new File(tempLoc2));
				}};
				thread.start();
			}

		}

		//compare 2 to 1
		public static class Compare2Listener implements ActionListener {
			@Override public void actionPerformed (final ActionEvent e) {
				Thread thread = new Thread(){
					public void run(){
						PNG_Finder.text.setText("");

						String tempLoc1 = getTMP() + "/.tempUnzip1";
						String tempLoc2 = getTMP() + "/.tempUnzip2";

						try{
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
							ArrayList<String> files1 = DirFileHandler.fileSearch(tempLoc1);
							ArrayList<String> files2 = DirFileHandler.fileSearch(tempLoc2);

							//only have .png
							ArrayList<String> temp = new ArrayList<String>();
							for (String file : files1) {
								if(file.toLowerCase().endsWith(".png")){
									temp.add(file);
								}
							}
							files1=temp;
							temp = new ArrayList<String>();
							for (String file : files2) {
								if(file.toLowerCase().endsWith(".png")){
									temp.add(file);
								}
							}
							files2=temp;

							//compare directorys
							ArrayList<String> finalOut = new ArrayList<String>();
							for(String dir1 : files1){
								for(String dir2 : files2){
									if(dir1.equals(dir2)){
										finalOut.add(dir2);
									}					
								}				
							}

							for(String finallOUtTemp : finalOut){
								files1.remove(files1.indexOf(finallOUtTemp));
							}
							//print things out
							PNG_Finder.text.append("=====DIFFERENCES====="+"\n");
							for (String file : files1) {
								PNG_Finder.text.append(file.toString()+"\n");
							}
						}
						catch(Exception e1){
							e1.printStackTrace();
						}

						//remove everything
						delete(new File(tempLoc1));
						delete(new File(tempLoc2));
					}};
					thread.start();
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
