package GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import Database.Bookmark;

public class NewBookmarkDialog extends JDialog {
	
	public JTextField nameTextField= new JTextField();
	public JTextField urlTextField = new JTextField();

	private boolean okClicked = false;
	
	public NewBookmarkDialog(final JFrame frame,String title ){
	    
		super(frame, title);
		
		Container container = getContentPane();
		
        // Create a pane with an empty border for spacing
        Border emptyBorder = BorderFactory.createEmptyBorder(2, 5, 5, 5);
        JPanel emptyBorderPanel = new JPanel();
        emptyBorderPanel.setLayout(new BoxLayout(emptyBorderPanel, BoxLayout.Y_AXIS));
        emptyBorderPanel.setBorder(emptyBorder);
        container.add(emptyBorderPanel);

        // Create a pane with an title etched border
        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border etchedTitleBorder = BorderFactory.createTitledBorder(title);
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
        

        // The Remote URL input field row
        urlTextField = new JTextField(20);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 5, 5, 5);
        c.weightx = 1;
        c.weighty = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(urlTextField, c);
		
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

        // The name inpur field
        nameTextField = new JTextField(10);
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
        
        //upon create, check url input and call actions.creatBookamrk
        JButton okButton = new JButton("Create");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	okClicked = true;
            	
            	String u = urlTextField.getText();
    			Pattern p = Pattern.compile("\\.com$|\\.net$|\\.edu$|\\.html|\\.org");
    			Matcher m = p.matcher(u);
    			
    			if(!m.find()){
    				Actions.errorDialog();
    			}
    			
    			else{
            	
    			Actions.createBookmark(nameTextField.getText(),urlTextField.getText());
                setVisible(false);
                dispose();
            	String search = MainWindow.searchField.getText();
              	search = "^" + search + "\\w*";
              	MainWindow.vBk = Actions.getList(search);
              	MainWindow.searchList.setListData(MainWindow.vBk);
              	
    			}
            }
        });
        
        //add button to Buttonpanel and Buttonpanel to JPanel
        buttonPanel.add(okButton);
        emptyBorderPanel.add(buttonPanel);

}

	public boolean getOkClicked() {
		 return okClicked;
	}


}
