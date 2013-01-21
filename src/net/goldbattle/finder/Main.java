package net.goldbattle.finder;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {
	
	public static void main(String[] args) {

		//custom colors
		UIManager.put("nimbusBase", Color.BLACK);      // Base color
		UIManager.put("nimbusBlueGrey", Color.GRAY);  // Secondary Color
		UIManager.put("control", Color.DARK_GRAY);         // Control
		UIManager.put("OptionPane.messageForeground", Color.WHITE); //popups have white text
		//load new skin
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				e.printStackTrace();
			}
		}

		
		//Init the program
		PNG_Finder program = new PNG_Finder();
		
		//load frame
		program.loadFrame();
		
		//loads all internal parts
		program.initializeComponents();
		
		//display frame
		program.displayFrame();
		
		//done
	}

}
