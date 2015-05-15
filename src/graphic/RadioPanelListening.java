package graphic;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RadioPanelListening extends JPanel{
	private JTextField textField;
	public RadioPanelListening() {
		setLayout(null);
		
		JLabel lblPercorsoDelFile = new JLabel("Percorso del File:");
		lblPercorsoDelFile.setBounds(34, 87, 100, 14);
		add(lblPercorsoDelFile);
		
		textField = new JTextField();
		textField.setToolTipText("Inserisci il percorso del segnale");
		textField.setBounds(126, 84, 295, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Prosegui Simulazione");
		btnNewButton.setBounds(288, 266, 133, 23);
		add(btnNewButton);
	}

}
