package GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import Util.StartBrowser;
import Util.Util;

import Database.Bookmark;

public class EditDialog extends JDialog{
	
	public JLabel nameTextField;
	public JLabel urlTextField;
	JPanel buttonPanel;
	JButton delButton = new JButton("Delete");
	JButton launchButton = new JButton("Launch");
	
	public EditDialog(final JFrame frame,String title,String name, String url , String date){
		
		super(frame,title);
		
		Container container = getContentPane();
		
        // Create a pane with an empty border for spacing
        Border emptyBorder = BorderFactory.createEmptyBorder(2, 5, 5, 5);
        JPanel emptyBorderPanel = new JPanel();
        emptyBorderPanel.setLayout(new BoxLayout(emptyBorderPanel, BoxLayout.Y_AXIS));
        emptyBorderPanel.setBorder(emptyBorder);
        container.add(emptyBorderPanel);

        // Create a pane with an title etched border
        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border etchedTitleBorder = BorderFactory.createTitledBorder("Bookmark");
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(etchedTitleBorder);
        emptyBorderPanel.add(mainPanel);
	    
        GridBagConstraints c = new GridBagConstraints();

        // The URL Label row
        JLabel urlLabel = new JLabel("URL :");
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 5, 0, 0);
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(urlLabel, c);
        

        // The URL input field row
        urlTextField = new JLabel();
        urlTextField.setText(url);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 5, 5, 5);
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(urlTextField, c);
		
        //the name label row
        JLabel usernameLabel = new JLabel("Name :");
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 0);
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(usernameLabel, c);

        // The name input field
        nameTextField = new JLabel();
        nameTextField.setText(name);
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 5, 5, 5);
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        mainPanel.add(nameTextField, c);
		
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        //on click of delete button, do delete
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Actions.delBookmark(urlTextField.getText());
                setVisible(false);
                dispose();
            	String search = MainWindow.searchField.getText();
              	search = "^" + search + "\\w*";
              	MainWindow.vBk = Actions.getList(search);
              	MainWindow.searchList.setListData(MainWindow.vBk);
            }
        });
        
        //on click launch the website
        launchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String u = urlTextField.getText();
            	if (u.startsWith("http"))
            		StartBrowser.open(urlTextField.getText());
            	else
            		StartBrowser.open("http://"+urlTextField.getText());
                setVisible(false);
                dispose();
            	String search = MainWindow.searchField.getText();
              	search = "^" + search + "\\w*";
              	MainWindow.vBk = Actions.getList(search);
              	MainWindow.searchList.setListData(MainWindow.vBk);
            }
        });
        
        //add the buttons to the panel
        buttonPanel.add(launchButton);
        buttonPanel.add(delButton);
        emptyBorderPanel.add(buttonPanel);
	
		}

} 

