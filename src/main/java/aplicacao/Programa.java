package aplicacao;

import javax.swing.JOptionPane;

import visual.TelaCadastro;

public class Programa {

	public static void main(String[] args) {
		try {
			new TelaCadastro().setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ligue o XAMPP e tente novamente!", "Erro de Conexão", 0, null);
		}
		
	}
}
