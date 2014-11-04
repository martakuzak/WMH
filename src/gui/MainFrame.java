package gui;

import graph.GraphReader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
	/**
	 * 
	 * @param title
	 *            frame title
	 */
	public MainFrame(String s) {
		super(s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);

		setMenu();
		setVisible(true);
	}

	public void setMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("File");
		menuBar.add(menu);

		JMenuItem selectFile = new JMenuItem("Select input file");

		selectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Select input file");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				Path currentRelativePath = Paths.get("");
				String absolutePath = currentRelativePath.toAbsolutePath()
						.toString();

				chooser.setCurrentDirectory(new File(absolutePath));
				int returnVal = chooser.showOpenDialog(chooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					GraphReader.readFile(file);
				}

			}
		});
		menu.add(selectFile);

		JMenuItem close = new JMenuItem("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(1);
			}
		});
		menu.add(close);
		menuBar.add(menu);

		setJMenuBar(menuBar);
	}
	
	public static void main(String[] args) {
		new MainFrame("");
	}

}
