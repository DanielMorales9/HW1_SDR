package graphic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class GraphicEngine {

	private JFrame frame;
	private JTextField txtInserisciQuiUn_1;
	private JTextField txtInserisciQuiUn;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton btnAvviaSimulazione;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicEngine window = new GraphicEngine();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicEngine() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Simulazione");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[94px,grow][86px,grow]", "[20px][20px][][][grow]"));
		
		JLabel lblValoreDiDetection = new JLabel("Valore di Detection:");
		frame.getContentPane().add(lblValoreDiDetection, "cell 0 0,alignx right,aligny center");
		
		txtInserisciQuiUn_1 = new JTextField();
		txtInserisciQuiUn_1.setToolTipText("Inserisci qui un valore di Detection");
		frame.getContentPane().add(txtInserisciQuiUn_1, "cell 1 0,alignx right,aligny center");
		txtInserisciQuiUn_1.setColumns(10);
		
		JLabel lblValoreDiSnr = new JLabel("Valore dell' SNR:");
		frame.getContentPane().add(lblValoreDiSnr, "cell 0 1,alignx right,aligny center");
		
		txtInserisciQuiUn = new JTextField();
		txtInserisciQuiUn.setToolTipText("Inserisci qui un valore per l'SNR");
		frame.getContentPane().add(txtInserisciQuiUn, "cell 1 1,alignx right,aligny center");
		txtInserisciQuiUn.setColumns(10);
		
		lblNewLabel = new JLabel("Numero di Prove:");
		frame.getContentPane().add(lblNewLabel, "cell 0 2,alignx trailing,aligny center");
		
		textField = new JTextField();
		textField.setToolTipText("numero di prove per le quali viene generato un nuovo rumore con stesso snr e varianza  ");
		textField.setColumns(10);
		frame.getContentPane().add(textField, "cell 1 2,alignx right,aligny center");
		
		btnAvviaSimulazione = new JButton("Avvia Simulazione");
		btnAvviaSimulazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frame.getContentPane().add(btnAvviaSimulazione, "cell 1 3,alignx right");
	}

}
