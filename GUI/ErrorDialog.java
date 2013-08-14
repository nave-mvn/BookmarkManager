package GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class ErrorDialog extends JDialog {

	public JLabel error = new JLabel("Error in Url entry");
	public JButton okButton  = new JButton("Ok"); 
	
	public ErrorDialog(JFrame jframe){
		
		super(jframe,"Error");
	
		error.setAlignmentX(Component.CENTER_ALIGNMENT);

		//create panel and add label, customize
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(error);
		getContentPane().add(panel);
		setResizable(false);
		panel.add(new JSeparator());
		panel.add(Box.createRigidArea(new Dimension(0, 10)));

		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		//add button to panel
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(okButton);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		
	}
	
}
