package graphic;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RadioPanelSimulation extends JPanel {
	
	private JTextField textSNR;
	private JTextField txtValoreDiDetection;
	private JTextField textNumeroProve;
	private JLabel lblNumeroProve;
	private JButton btnAvviaSimulazione;
	
	public RadioPanelSimulation() {
		setLayout(null);
		
		JLabel lblValoreSnr = new JLabel("Valore SNR:");
		lblValoreSnr.setBounds(126, 94, 67, 14);
		add(lblValoreSnr);
		
		textSNR = new JTextField();
		textSNR.setBounds(235, 91, 86, 20);
		textSNR.setToolTipText("Inserisci il valore dell'SNR");
		add(textSNR);
		textSNR.setColumns(10);
		
		JLabel lblValoreDetection = new JLabel("Valore Detection:");
		lblValoreDetection.setBounds(107, 143, 86, 14);
		add(lblValoreDetection);
		
		txtValoreDiDetection = new JTextField();
		txtValoreDiDetection.setBounds(235, 140, 86, 20);
		txtValoreDiDetection.setToolTipText("Inserisci il valore di Detection ");
		add(txtValoreDiDetection);
		txtValoreDiDetection.setColumns(10);
		
		lblNumeroProve = new JLabel("Numero Prove:");
		lblNumeroProve.setBounds(107, 193, 86, 14);
		add(lblNumeroProve);
		
		textNumeroProve = new JTextField();
		textNumeroProve.setBounds(235, 190, 86, 20);
		textNumeroProve.setToolTipText("Numero di prove da effettuare con lo stesso valore di snr e rumore");
		add(textNumeroProve);
		textNumeroProve.setColumns(10);
		
		btnAvviaSimulazione = new JButton("Avvia Simulazione");
		btnAvviaSimulazione.setBounds(311, 266, 119, 23);
		add(btnAvviaSimulazione);
	}
}
