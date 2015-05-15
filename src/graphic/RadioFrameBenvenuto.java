package graphic;

import javax.swing.JFrame;

import radio.Radio;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RadioFrameBenvenuto extends JFrame{

	private JPanel currentPannel;
	
	public RadioFrameBenvenuto(){
		setTitle("Benvenuto!");
		this.currentPannel = new JPanel();
		currentPannel.setLayout(null);
		
		JButton btnSimulazione = new JButton("Simulazione");
		
		btnSimulazione.setBounds(76, 113, 115, 23);
		currentPannel.add(btnSimulazione);
		
		JButton btnAscoltoSegnali = new JButton("Ascolto Segnali");
		btnAscoltoSegnali.setBounds(241, 113, 115, 23);
		currentPannel.add(btnAscoltoSegnali);
		setupFrame();
	}
	
	private void setupFrame(){
		this.setContentPane(this.currentPannel);
	}
}
