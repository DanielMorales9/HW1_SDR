package graphic;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RadioPanelResult extends JPanel {
	private JTextField textValoreSNR;
	private JTextField textValoreSoglia;
	private JTextField textPercDetection;
	public RadioPanelResult() {
		setLayout(null);
		
		JLabel lblValoreSnr = new JLabel("Valore SNR:");
		lblValoreSnr.setBounds(63, 74, 63, 14);
		add(lblValoreSnr);
		
		textValoreSNR = new JTextField();
		textValoreSNR.setToolTipText("Valore SNR");
		textValoreSNR.setEditable(false);
		textValoreSNR.setBounds(229, 71, 86, 20);
		add(textValoreSNR);
		textValoreSNR.setColumns(10);
		
		JLabel lblValoreDellaSoglia = new JLabel("Valore della Soglia:");
		lblValoreDellaSoglia.setBounds(63, 121, 90, 14);
		add(lblValoreDellaSoglia);
		
		textValoreSoglia = new JTextField();
		textValoreSoglia.setEditable(false);
		textValoreSoglia.setBounds(229, 118, 86, 20);
		add(textValoreSoglia);
		textValoreSoglia.setColumns(10);
		
		JLabel lblPercentualeDiDetection = new JLabel("Percentuale di Detection ");
		lblPercentualeDiDetection.setBounds(63, 172, 129, 14);
		add(lblPercentualeDiDetection);
		
		textPercDetection = new JTextField();
		textPercDetection.setEditable(false);
		textPercDetection.setToolTipText("Percentuale di detection calcolata");
		textPercDetection.setBounds(229, 169, 86, 20);
		add(textPercDetection);
		textPercDetection.setColumns(10);
	}

}
