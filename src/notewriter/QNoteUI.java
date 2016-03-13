package notewriter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class QNoteUI implements ActionListener{

	private JFrame frmQuicknoteWriter;
	protected JButton btnSubmit, btnEditPathShortCuts;
	private JTextArea txtrSampleText;
	
	private static final String SUBMIT = "submit";
	private static final String EDIT_NOTE_TEXT = "text";
	private static final String UPDATE_MAPS = "update";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QNoteUI window = new QNoteUI();
					window.frmQuicknoteWriter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected String getNoteText(){
		String text;
		try {
			text = txtrSampleText.getText();
		} catch (NullPointerException e) {
			text = "";
		}
		return text;
	}

	/**
	 * Create the application.
	 */
	public QNoteUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQuicknoteWriter = new JFrame();
		frmQuicknoteWriter.setTitle("QuickNote Writer");
		frmQuicknoteWriter.setBounds(100, 100, 960, 775);
		frmQuicknoteWriter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQuicknoteWriter.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("469px"),
				ColumnSpec.decode("469px"),},
			new RowSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("45px"),
				RowSpec.decode("169px"),
				RowSpec.decode("410px"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("54px"),}));
		
		JLabel lblEnterNote = new JLabel("  Enter note text below.  Start note with >CODE to specify save location shortcut.  Select edit path shortcuts to configure.");
		lblEnterNote.setFont(new Font("Sitka Heading", Font.BOLD, 15));
		lblEnterNote.setBackground(new Color(255, 239, 213));
		frmQuicknoteWriter.getContentPane().add(lblEnterNote, "1, 2, 2, 1, fill, fill");
		
		JTextArea txtrSampleText = new JTextArea();
		txtrSampleText.setAlignmentY(Component.TOP_ALIGNMENT);
		txtrSampleText.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtrSampleText.setFont(new Font("Sitka Text", Font.PLAIN, 12));
		txtrSampleText.setBackground(Color.WHITE);
		txtrSampleText.setText("Sample text");
		txtrSampleText.setTabSize(4);
		txtrSampleText.setRows(1);
		txtrSampleText.setColumns(2);
		frmQuicknoteWriter.getContentPane().add(txtrSampleText, "1, 3, 2, 2, fill, fill");
		txtrSampleText.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				
			}
			public void removeUpdate(DocumentEvent e){
				
			}
			public void insertUpdate(DocumentEvent e){
				
			}
			
			public void checkMaps(DocumentEvent e){
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("    Will save to: <PATH>");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 15));
		frmQuicknoteWriter.getContentPane().add(lblNewLabel, "1, 5, 2, 2");
		
		JButton btnSubmit = new JButton("Submit <Ctrl + Enter>");
		btnSubmit.setFont(new Font("Sitka Display", Font.BOLD, 16));
		frmQuicknoteWriter.getContentPane().add(btnSubmit, "1, 7, fill, fill");
		btnSubmit.setActionCommand(SUBMIT);
		
		JButton btnEditPathShortcuts = new JButton("Edit Path Shortcuts <Alt + Enter>");
		btnEditPathShortcuts.setFont(new Font("Sitka Display", Font.BOLD, 16));
		frmQuicknoteWriter.getContentPane().add(btnEditPathShortcuts, "2, 7, fill, fill");
		btnSubmit.setActionCommand(UPDATE_MAPS);

		//Listen for action commands
		btnEditPathShortcuts.addActionListener(this);
		btnSubmit.addActionListener(this);
		
	}

    public void actionPerformed(ActionEvent e) {
	    if(EDIT_NOTE_TEXT.equals(e.getActionCommand())){
	    	
	    }
	    else if (SUBMIT.equals(e.getActionCommand())){ 
	    

        }
    	else if (UPDATE_MAPS.equals(e.getActionCommand())){
    	
        	
        }
    }

	
}
