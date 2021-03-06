package notewriter.java;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

//This class controls responsive features of the UI and event handling.  
//It is tightly coupled with the UI implementation.
public class QNoteUIController implements ActionListener {
	
	private JFrame guiFrame;
	private JTextArea txtNoteText;
	private JButton btnSubmitNote, btnEditPathMap, btnSubmitPathMap;
	private JTable table;
	private JLabel pathLabel;
	
	private ConnectionMap cMap;
	private NoteConnector noteConnect;
	private ConfigurationData config;
	
	private String note;
	private String connectionKey;
	
	private Pattern findKey;
	
	private static final String SUBMIT_NOTE = "submit";
	private static final String GOTO_CONNECTION_UI = "maps";
	private static final String UPDATE_MAPS = "update";
	
	private static final String LABEL_NO_TARGET = "  Add a file key to the first line to set note save location.";
	private static final String LABEL_WITH_TARGET = "  Path: ";

	
	private static final String regexFindKey = "^\\s*?>([A-Za-z_]*)([\\s|\\n]+?.*)";

	public QNoteUIController(JFrame form_guiFrame, JButton form_btnSubmitNote, JButton form_btnEditPathMap, 
			JButton form_btnSubmitPathMap, JTextArea form_txtNoteText, JTable form_table, JLabel form_pathLabel){
		
		config = new ConfigurationData(form_table.getRowCount());
		cMap = new ConnectionMap();
		noteConnect = new NoteConnector(config.getDefaultDocName());
		
		//reference all responsive elements from GUI to be used by controller
		table = form_table;
		guiFrame = form_guiFrame;
		btnSubmitNote = form_btnSubmitNote;
		btnEditPathMap = form_btnEditPathMap;
		btnSubmitPathMap = form_btnSubmitPathMap;
		txtNoteText = form_txtNoteText;
		pathLabel = form_pathLabel;

		btnSubmitNote.setEnabled(false);
		
		setGUITableFromStringMatrix(config.getPathMap());
	}
	
	public void activateEventHandlers(){
		//TODO read and load GUI connection table from config file
		//TODO enable pasting paths into table from clipboard
		
		//load initial map (will be reloaded if necessary)
		loadCMapFromGUITable();


		findKey = Pattern.compile(regexFindKey, Pattern.DOTALL);
		
		btnEditPathMap.setActionCommand(GOTO_CONNECTION_UI);
		btnSubmitNote.setActionCommand(SUBMIT_NOTE);
		btnSubmitPathMap.setActionCommand(UPDATE_MAPS);
		
		btnEditPathMap.addActionListener(this);
		btnSubmitNote.addActionListener(this);
		btnSubmitPathMap.addActionListener(this);
		
		txtNoteText.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				checkMaps(e);
			}
			public void removeUpdate(DocumentEvent e){
				checkMaps(e);
			}
			public void insertUpdate(DocumentEvent e){
				checkMaps(e);
			}
			
			public void checkMaps(DocumentEvent e){
				SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
					
					String[] wKeyNote = new String[2];
					
				    @Override
				    public String doInBackground() {
				    	String currentText = txtNoteText.getText();
				        wKeyNote = parseInputText(currentText);
				    	String sPath = cMap.getPath(wKeyNote[0]);
				        return sPath;
				    }

				    @Override
				    public void done() {
				    	//Update target labeling and activate/disable submit button
				    	try {
				    		String target = get();
				    		if (target == null){
				    			pathLabel.setText(LABEL_NO_TARGET);
				    			btnSubmitNote.setEnabled(false);
				    		} else {
				    			pathLabel.setText(LABEL_WITH_TARGET.concat(get()));//concat with target path
				    			btnSubmitNote.setEnabled(true);
				    		}
				    	} catch (Exception e) {
				    		//TODO handle exception
							e.printStackTrace();
				    	}
				
				    }
				};
				worker.execute();
			}
		});
	}

	private void loadCMapFromGUITable(){
		for (int count = 0; count < table.getRowCount(); count++){
			String key; Object okey;
			String path; Object opath;
			
			//TODO improve this
			okey = table.getValueAt(count, 0);
			opath = table.getValueAt(count, 1);
			
			if (okey != null && opath != null){
				key = okey.toString();
				path = opath.toString();
			
				if (key != "" && path != ""){ 
					cMap.load(key, path);		
				}
			}
		}
	}
	
	private void setGUITableFromStringMatrix(String[][] map){
		table.setModel(new DefaultTableModel(
				map,
				new String[] {
					"Shortcut", "Target"
				}
			));
	}
	
	private void swapVisibleGUI(){
		//there are only two panels.  This would need substantial revisions if the GUI structure changes (e.g. new panel added)
		CardLayout cl = (CardLayout)(guiFrame.getContentPane().getLayout());
		cl.next(guiFrame.getContentPane());
	}
	
	private String[] parseInputText(String text){
		String[] keyNotePair;
		keyNotePair = new String[2];
		Matcher parseMatch = findKey.matcher(text);
		
		if (parseMatch.find()){
			keyNotePair[0] = parseMatch.group(1);
			keyNotePair[1] = parseMatch.group(2);
		} else {
			keyNotePair[0] = "";
			keyNotePair[1] = text;
		}
		return keyNotePair;
	}

    public void actionPerformed(ActionEvent e) {
	    if(UPDATE_MAPS.equals(e.getActionCommand())){
	    	loadCMapFromGUITable();
	    	
	    	//update config file path mapping and save config file
	    	config.setPathMap(cMap.getPathMappingArray());
	    	config.saveConfigFile();
	    	
	    	//go back to main UI
	    	swapVisibleGUI();
	    }
	    else if (SUBMIT_NOTE.equals(e.getActionCommand())){
	    	String[] keynote = new String[2];
	    	String activePath;
	    	
	    	//split path key from note body
	    	keynote = parseInputText(txtNoteText.getText());
	    	connectionKey = keynote[0];
	    	activePath = cMap.getPath(connectionKey);
	    	note = keynote[1];
	    	noteConnect.write(note, activePath, cMap.isDirectory(connectionKey), cMap.isFile(connectionKey));
	    	System.exit(0);
        }
    	else if (GOTO_CONNECTION_UI.equals(e.getActionCommand())){
        	//go to map UI
    		swapVisibleGUI();
        }
    }
}
