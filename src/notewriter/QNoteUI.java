package notewriter;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class QNoteUI{

	
	public static final String NOTE_GUI = "NotePanel";
	public static final String MAP_GUI = "MapPanel";
	
	private JFrame frmQuicknoteWriter;
	
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
		frmQuicknoteWriter.setBounds(100, 100, 960, 912);
		frmQuicknoteWriter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQuicknoteWriter.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel_note = new JPanel();
		frmQuicknoteWriter.getContentPane().add(panel_note, NOTE_GUI);
		panel_note.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("456px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(210dlu;default)"),},
			new RowSpec[] {
				RowSpec.decode("45px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(306dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(22dlu;default)"),}));
		
		JLabel lblEnterNote = new JLabel("  Enter note. Use >CODE to specify save location shortcut.  Select edit map to set up shortcuts. ");
		lblEnterNote.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		panel_note.add(lblEnterNote, "1, 1, 3, 1, fill, fill");
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		panel_note.add(textArea, "1, 3, 3, 1, fill, fill");
		
		JLabel lblSaveTo = new JLabel("  Save to: <PATH>");
		lblSaveTo.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		panel_note.add(lblSaveTo, "1, 5, 3, 1");
		
		JButton btnSubmit_1 = new JButton("Submit <Ctrl + Enter>");
		btnSubmit_1.setFont(new Font("Sitka Display", Font.BOLD, 16));
		panel_note.add(btnSubmit_1, "1, 7");
		
		JButton btnEditMappings = new JButton("Edit mappings <Alt + Enter>");
		btnEditMappings.setFont(new Font("Sitka Display", Font.BOLD, 16));
		panel_note.add(btnEditMappings, "3, 7");
		
		
		
		JPanel panel_connections = new JPanel();
		frmQuicknoteWriter.getContentPane().add(panel_connections, MAP_GUI);
		panel_connections.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("168px:grow"),
				ColumnSpec.decode("766px"),},
			new RowSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(358dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel label = new JLabel("  Set up shortcut codes");
		panel_connections.add(label, "1, 2");
		
		JLabel lblShortcut = new JLabel("   Shortcut");
		lblShortcut.setFont(new Font("Sitka Text", Font.ITALIC, 16));
		panel_connections.add(lblShortcut, "1, 4");
		
		JLabel lblTargetPath = new JLabel("  Target Path");
		lblTargetPath.setFont(new Font("Sitka Text", Font.ITALIC, 16));
		panel_connections.add(lblTargetPath, "2, 4");
		
		JTable table = new JTable();
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new String[][] {
				{"CODE", "O:\\staff\\ballen"},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Shortcut", "Target"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(153);
		table.getColumnModel().getColumn(1).setPreferredWidth(830);
		panel_connections.add(table, "1, 6, 2, 1, fill, fill");
		
		JButton btnUpdateMappings = new JButton("Update Mappings <Alt + Enter>");
		btnUpdateMappings.setFont(new Font("Sitka Display", Font.BOLD, 16));
		panel_connections.add(btnUpdateMappings, "1, 8, 2, 1, center, default");


		//Set and activate event listeners.  Pass responsive elements to controller.  End GUI setup.
		QNoteUIController listener = new QNoteUIController(frmQuicknoteWriter, btnSubmit_1, btnUpdateMappings, btnUpdateMappings, textArea, table);
		listener.activateEventHandlers();
	}
}
