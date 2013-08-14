package GUI;


//pack into jar

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ListUI;

import Database.Bookmark;

public class MainWindow extends JFrame implements ActionListener {
	
	public static final String NEW_BOOKMARK = "newBookmark";
	public static final String IMPORT = "import";
	public static final String EXPORT = "export";
	public static final String EXIT = "exit";
	public static final String ABOUT = "about";
	
	//the vector of bookmarks to be passed to list for display
	static Vector<Bookmark> vBk = null;
	//a reference to an actions object in order to call the methods in actions;
	protected Actions actions = new Actions(this);
	
	private JMenuBar Jbar = new JMenuBar();
	
	private  JMenu fileMenu;
	private  JMenu helpMenu;
	
	private  JMenuItem newBk;
	private  JMenuItem importBk;
	private JMenuItem exportBk;
	private JMenuItem exit;
	private JMenuItem about;
	
	static JTextField searchField = new JTextField(15);
	static JList searchList ;
	
	JScrollPane accountsScrollList; 
	
	public MainWindow(String title){
		
		super(title);
        pack();
        setLocationRelativeTo(null);
        setSize(new Dimension(400,250));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //call to methods to add components
        addComponentsToPane();
		
	}

	//entry point for app
	public static void main(String[] args) {
		
		MainWindow Mw = new MainWindow("Bookmark Manager");
	
	}

	@Override //--responds to any gui event on the mainwindow
	public void actionPerformed(ActionEvent event) {
		
		try {
            if (event.getActionCommand() == MainWindow.NEW_BOOKMARK) {
              actions.newBookmark();
            } 
            else if (event.getActionCommand() == MainWindow.IMPORT) {
             //    Actions.import();
            } 
            else if (event.getActionCommand() == MainWindow.EXPORT) {
             //   Actions.export();
            } 
            else if (event.getActionCommand() == MainWindow.EXIT) {
                actions.exit();
            } 
            else if (event.getActionCommand() == MainWindow.ABOUT) {
               actions.getAboutDialog();
            } 
            
        	} 

		catch (Exception e) {
		
			e.printStackTrace();
        
			}
		
	}

	//method to organize the layout of the various components
	private void addComponentsToPane() {
		
        //Ensure the layout manager is a BorderLayout
        if (!(getContentPane().getLayout() instanceof GridBagLayout)) {
        
        	getContentPane().setLayout(new GridBagLayout());
        
        }
        
        //Create the menubar,searchfield and searchlist
        createMenuBar();
        createSearchField();
        createSearchList();
        
        GridBagConstraints c = new GridBagConstraints();
               
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        c.insets = new Insets(1, 1, 1, 1);
        c.weightx = 0;
        c.weighty = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        getContentPane().add(searchField, c);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 1, 1, 1);
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        getContentPane().add(accountsScrollList, c);
		
	}


	private void createSearchList() {
		
		//gets bookmarks from database upon initialization
		try{
		
			vBk = actions.getList("\\.*"); 
			searchList = new JList(vBk);
			
		}
		catch(NullPointerException n){
		
			searchList = new JList();
		
		}
		
		searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchList.setSelectedIndex(0);
        searchList.setVisibleRowCount(10);
        
        //create scroll
        accountsScrollList = new JScrollPane(searchList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        accountsScrollList.setVisible(true);
        
        searchList.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchList.getModel().getSize() > 0 && searchList.getSelectedIndex() == -1) {
                    searchList.setSelectionInterval(0, 0);
                }
            }
        });
        
        searchList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                //actions.editBookmark();
            }
        });
        
        //on double-click on a listitem, open it
        searchList.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                   int index = searchList.locationToIndex(e.getPoint());
                   actions.editBookmark(vBk.get(index).getName(), vBk.get(index).getUrl(),vBk.get(index).getDate());
               }
           }
        });
        
        //enter needs to be implemented
        searchList.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	//int index = searchList.locationToIndex(e.getPoint());
                    //actions.editBookmark(vBk.get(index).getName(), vBk.get(index).getUrl(),vBk.get(index).getDate());
                }
            }
        });
		
	}

	private void createSearchField() {
		
		searchField.setEnabled(true);
		searchField.setMinimumSize(searchField.getPreferredSize());
		searchField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				//This method never seems to be called
			}
			
			//gets called when an insert is made to the searchfield
			public void insertUpdate(DocumentEvent e) {
				
				try{
					String search = searchField.getText();
					search = "(?i)" + "^" + search + "\\w*"; //case-insensitive + at start + letter + anything after that
					vBk = actions.getList(search);
					searchList.setListData(vBk);
				}

				catch(NullPointerException n){
					
					System.out.print("in exception");
					
				}

			}
			
			//gets called when a removal is made from the searchfield
			public void removeUpdate(DocumentEvent e) {
				
				try{
				
					String search = searchField.getText();
					search = "(?i)" + "^" +  search + "\\w*";
					vBk = actions.getList(search);
					searchList.setListData(vBk);}
				
				catch(NullPointerException n){
					
					System.out.print("in exception");
				
				}
			}
		});
		
	}
	
	private void createMenuBar() {
		
		// link Jbar to main_window
		setJMenuBar(Jbar); 
		
		//create Jmenus
		fileMenu = new JMenu("File");
		Jbar.add(fileMenu);
		helpMenu = new JMenu("Help");
		Jbar.add(helpMenu);

		//create JmenuItem and associate with the respective Jmenu
		newBk = new JMenuItem("New");
		fileMenu.add(newBk);
		newBk.addActionListener(this);
		newBk.setActionCommand(NEW_BOOKMARK);
		fileMenu.addSeparator();
		importBk = new JMenuItem("Import");
		importBk.addActionListener(this);
		importBk.setActionCommand(IMPORT);
		fileMenu.add(importBk );
		fileMenu.addSeparator();
		exportBk = new JMenuItem("Export");
		exportBk.addActionListener(this);
		exportBk.setActionCommand(EXPORT);
		fileMenu.add(exportBk );
		fileMenu.addSeparator();
		exit = new JMenuItem("EXIT");
		fileMenu.add(exit);
		exit.addActionListener(this);
		exit.setActionCommand(EXIT);
		
		about = new JMenuItem("About");
		helpMenu.add(about);
		about.addActionListener(this);
		about.setActionCommand(ABOUT);
		
	}
}
