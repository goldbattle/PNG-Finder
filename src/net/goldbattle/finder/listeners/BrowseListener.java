package net.goldbattle.finder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import net.goldbattle.finder.PNG_Finder;

public class BrowseListener {

	//first button
	public static class Browse1Listener implements ActionListener {
		@Override public void actionPerformed (final ActionEvent e) {

			final JFileChooser fileChooser = new JFileChooser();			
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setFileFilter(new ZipFileFilter());
			fileChooser.setSize(600, 700);
			if (fileChooser.showOpenDialog(PNG_Finder.frame) != JFileChooser.APPROVE_OPTION) return;

			final File file = fileChooser.getSelectedFile();
			PNG_Finder.file1.setText(file.getAbsolutePath());
			PNG_Finder.text.append("File Loaded!"+"\n");
			PNG_Finder.text.append(file.getAbsolutePath()+"\n");
		}

		private static class ZipFileFilter extends FileFilter {

			@Override public boolean accept (final File f) {

				return f.isDirectory() || f.getName().endsWith(".zip") || f.getName().endsWith(".jar");

			}

			@Override public String getDescription() {

				return "Minecraft Mod (*.zip/*.jar)";

			}

		}
	}

	//second button
	public static class Browse2Listener implements ActionListener {
		@Override public void actionPerformed (final ActionEvent e) {

			final JFileChooser fileChooser = new JFileChooser();			
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setFileFilter(new ZipFileFilter());
			fileChooser.setSize(600, 700);
			if (fileChooser.showOpenDialog(PNG_Finder.frame) != JFileChooser.APPROVE_OPTION) return;

			final File file = fileChooser.getSelectedFile();
			PNG_Finder.file2.setText(file.getAbsolutePath());
			PNG_Finder.text.append("File Loaded!"+"\n");
			PNG_Finder.text.append(file.getAbsolutePath()+"\n");
			
		}

		private static class ZipFileFilter extends FileFilter {

			@Override public boolean accept (final File f) {

				return f.isDirectory() || f.getName().endsWith(".zip") || f.getName().endsWith(".jar");

			}

			@Override public String getDescription() {

				return "Minecraft Mod (*.zip/*.jar)";

			}

		}
	}
}
