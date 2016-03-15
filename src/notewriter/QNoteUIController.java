package notewriter;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

//This class controls responsive features of the UI and event handling.  
//It is closely coupled with the UI implementation.
public class QNoteUIController implements ActionListener {
	
	protected JFrame guiFrame;
	protected JTextArea txtNoteText;
	protected JButton btnSubmitNote, btnEditPathMap, btnSubmitPathMap;
	protected JTable table;
	
	protected ConnectionMap cMap;
	protected NoteConnector noteConnect;
	
	private String note;
	private String connection_key;
	
	private static final String SUBMIT_NOTE = "submit";
	private static final String GOTO_CONNECTION_UI = "maps";
	private static final String UPDATE_MAPS = "update";

	public QNoteUIController(JFrame form_guiFrame, JButton form_btnSubmitNote, JButton form_btnEditPathMap, JButton form_btnSubmitPathMap, JTextArea form_txtNoteText, JTable form_table){
		//reference all responsive elements from GUI to be used by controller
		table = form_table;
		guiFrame = form_guiFrame;
		btnSubmitNote = form_btnSubmitNote;
		btnEditPathMap = form_btnEditPathMap;
		btnSubmitPathMap = form_btnSubmitPathMap;
		txtNoteText = form_txtNoteText;
	}
	
	public void activateEventHandlers(){
		//TODO read and load GUI connection table from config file
		
		//load initial map (will be reloaded if necessary)
		loadCMap();
		
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
				//use swing worker?
				//parse key only
				//lookup path from map
				//if successful active submit button and update target path
			}
		});
	}

	private void loadCMap(){
		for (int count = 0; count < table.getRowCount(); count++){
			String key;
			String path;
			
			key = table.getValueAt(count, 0).toString();
			path = table.getValueAt(count, 0).toString();
			
			if (key != "" && path != "" && key != null && path != null){
				cMap.load(key, path);				
			}
		}
	}
	
	private void swapVisibleGUI(){
		//there are only two panels.  This would need substantial revisions if this changes.
		CardLayout cl = (CardLayout)(guiFrame.getLayout());
		cl.next(guiFrame);
	}
	
	
	private void parseInputText(){
		//find key
		//set connection key
		//set note to input text less starting whitespace/newlines and key
	}

	

    public void actionPerformed(ActionEvent e) {
	    if(UPDATE_MAPS.equals(e.getActionCommand())){
	    	loadCMap();
	    	//go back to main UI
	    	swapVisibleGUI();
	    }
	    else if (SUBMIT_NOTE.equals(e.getActionCommand())){
	    	parseInputText();
	    	noteConnect.write(note, connection_key, cMap.isDirectory(connection_key));
        }
    	else if (GOTO_CONNECTION_UI.equals(e.getActionCommand())){
        	//go to map UI
    		swapVisibleGUI();
        }
    }
}
