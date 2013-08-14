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

public class AboutDialog extends JDialog{
	
	JLabel MLabel = new JLabel();
	JLabel NLabel = new JLabel();
	JLabel LLabel = new JLabel();
	JButton okButton = new JButton("Ok");
	
	AboutDialog(JFrame frame){
		
		super(frame,"About");
		
		//create panel and add the labels
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(MLabel);
		panel.add(NLabel);
		panel.add(LLabel);
		MLabel.setText("Bookmark Manager");
		NLabel.setText("Copyright © 2012 Naveen");
		LLabel.setText("nullpntr.blogspot.com");
		
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

        //add panel to pane
        getContentPane().add(panel);
        setResizable(false);
		
	}
	
	
	
}
