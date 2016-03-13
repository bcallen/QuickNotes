package notewriter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class ConnectionMapUI {

	private JFrame frmQuicknoteWriter;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionMapUI window = new ConnectionMapUI();
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
	public ConnectionMapUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQuicknoteWriter = new JFrame();
		frmQuicknoteWriter.setTitle("QuickNote Writer - Edit Path Shortcuts");
		frmQuicknoteWriter.setBounds(100, 100, 1506, 890);
		frmQuicknoteWriter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQuicknoteWriter.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(128dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(472dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(330dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		
		JLabel lblEnterNotesShortcuts = new JLabel("Enter Shortcut Paths for Saving Notes");
		lblEnterNotesShortcuts.setFont(new Font("Sitka Heading", Font.BOLD, 16));
		frmQuicknoteWriter.getContentPane().add(lblEnterNotesShortcuts, "2, 2");
		
		table = new JTable();
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"SMPL", "C:\\MyPath"},
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
				"Shortcut", "Full Path"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(74);
		table.getColumnModel().getColumn(1).setPreferredWidth(782);
		
		JLabel lblShortcut = new JLabel("Shortcut");
		lblShortcut.setFont(new Font("Sitka Subheading", Font.ITALIC, 16));
		frmQuicknoteWriter.getContentPane().add(lblShortcut, "2, 4, left, default");
		
		JLabel lblFullPath = new JLabel("Full Path");
		lblFullPath.setFont(new Font("Sitka Subheading", Font.ITALIC, 16));
		frmQuicknoteWriter.getContentPane().add(lblFullPath, "3, 4, 2, 1, center, default");
		frmQuicknoteWriter.getContentPane().add(table, "2, 6, 3, 1, fill, fill");
		
		JButton btnUpdateMappings = new JButton("Update Paths <Alt + Enter>");
		btnUpdateMappings.setFont(new Font("Sitka Display", Font.BOLD, 16));
		frmQuicknoteWriter.getContentPane().add(btnUpdateMappings, "2, 8, fill, top");
	}

	protected JTable getTable() {
		return table;
	}
}
