package net.goldbattle.finder;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.goldbattle.finder.listeners.BrowseListener;
import net.goldbattle.finder.listeners.CompareListener;

public class PNG_Finder {

	// TODO: Main Components
	public static JFrame frame;
	public static JTextArea text;
	public static JTextField file1;
	public static JTextField file2;	

	private JButton pack2Button;
	private JButton pack1Button;
	private JButton compareButton;

	//load main frame compnets
	public void loadFrame() {
		frame = new JFrame();
		frame.setSize(600,700);

		//layout
		GridBagLayout gd = new GridBagLayout();
		frame.setLayout(gd);
		frame.setFocusableWindowState(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("PNG Finder");

		// TODO: Icon loading
		try {
			URL url = getClass().getClassLoader().getResource("");
			frame.setIconImage(Toolkit.getDefaultToolkit().createImage(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//creates and adds to
	public void initializeComponents() {

		final Insets insets = new Insets(3, 3, 3, 3);
		GridBagConstraints gbc = new GridBagConstraints();

		//directory 1
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 3;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = insets;
		file1 = new JTextField();
		file1.setText("File 1");
		frame.add(file1, gbc);

		//button 1
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		pack1Button = new JButton("Browse 1");
		pack1Button.addActionListener(new BrowseListener.Browse1Listener());
		frame.add(pack1Button, gbc);

		//directory 2
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.weightx = 3;
		gbc.weighty = 0;
		file2 = new JTextField();
		file2.setText("File 2");
		frame.add(file2, gbc);

		//button 2
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		pack2Button = new JButton("Browse 2");
		pack2Button.addActionListener(new BrowseListener.Browse2Listener());
		frame.add(pack2Button, gbc);


		//text panel
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.weightx = 4;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		text = new JTextArea();
		JScrollPane scrollpane = new JScrollPane(text);
		frame.add(scrollpane, gbc);

		//compare button
		gbc = new GridBagConstraints();
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		gbc.weightx = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 3, 3, 3);
		compareButton = new JButton("Compare File 1 to File 2");
		compareButton.addActionListener(new CompareListener.Compare1Listener());
		frame.add(compareButton, gbc);

		//compare 2 button
		gbc.gridy = 5;
		compareButton = new JButton("Compare File 2 to File 1");
		//compareButton.addActionListener(new Listeners.WizardListener(this));
		frame.add(compareButton, gbc);
	}

	//displays the frame
	public void displayFrame() {
		frame.setVisible(true);
		frame.setFocusableWindowState(true);
	}

}
