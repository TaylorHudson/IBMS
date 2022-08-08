package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import entidade.Pessoa;
import repositorio.PessoaRepositorio;

public class TelaCadastro extends JFrame implements ActionListener{
		private static final long serialVersionUID = 1L;
		private JLabel lblTitulo;
		private JLabel lblNome;
		private JLabel lblNascimento;
		private JLabel lblPesquisar;
		private JTextField txtNome;
		private JButton btnCadastrar;
		private JButton btnClique;
		private JFormattedTextField jfText;
		private boolean textoValido = false;
		private boolean dataValida = false;
		private PessoaRepositorio rep = new PessoaRepositorio();
		private SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		
		public TelaCadastro() {
			try {
	            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (Exception e) {
	        	configurarTela();
				desenharLabel();
				desenharBotao();
				desenharTexto();
				desenharTextoFormatado();
	        }
			configurarTela();
			desenharLabel();
			desenharBotao();
			desenharTexto();
			desenharTextoFormatado();
		}
		
		private void configurarTela() { 
			this.setTitle("Cadastro de Membros");
			this.setResizable(false);
			this.setLayout(null);
			this.setSize(550,400);
			this.getContentPane().setBackground(new Color(25,25,112));
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		private void desenharLabel() {
			lblTitulo = new JLabel("Cadastro de Aniversariantes");
			lblNome = new JLabel("Nome");
			lblNascimento = new JLabel("Nascimento");
			lblPesquisar = new JLabel("Pesquisar? ");
			
			lblTitulo.setBounds(25,5,475,35);
			lblTitulo.setFont(new Font("Arial", 1, 35));
			lblTitulo.setForeground(Color.WHITE);
			
			lblNome.setBounds(2,100,80,30);
			lblNome.setFont(new Font("Arial", 1, 20));
			lblNome.setForeground(Color.WHITE);
			
			lblNascimento.setBounds(2,150,120,30);
			lblNascimento.setFont(new Font("Arial", 1, 20));
			lblNascimento.setForeground(Color.WHITE);
			
			lblPesquisar.setBounds(2, 335, 80, 20);
			lblPesquisar.setFont(new Font("Arial", 0, 13));
			lblPesquisar.setForeground(Color.WHITE);
			
			this.add(lblNascimento);
			this.add(lblPesquisar);
			this.add(lblNome);
			this.add(lblTitulo);
		}
		
		private void desenharBotao() {
			btnCadastrar = new JButton("Cadastrar");
			btnCadastrar.setFont(new Font("Arial",0, 20));
			btnCadastrar.setBounds(200,255,150,40);
			btnCadastrar.addActionListener(this);
			
			btnClique = new JButton("Clique Aqui");
			btnClique.setFont(new Font("Arial",0, 13));
			btnClique.setBounds(70,330,120,25);
			btnClique.addActionListener(this);
			
			this.add(btnClique);
			this.add(btnCadastrar);
		}
		
		private void desenharTexto() {
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", 0, 15));
			txtNome.setBounds(60,100, 470, 30);
			txtNome.addActionListener(this);

			this.add(txtNome);
		}
		
		private void desenharTextoFormatado() {
			jfText = new JFormattedTextField();
			jfText.setFont(new Font("Arial", 0, 20));
			try {
				MaskFormatter mascara = new MaskFormatter("##/##/####");
				jfText.setFormatterFactory(new DefaultFormatterFactory(mascara));
				jfText.setBounds(115, 150, 115, 30);
			} catch (Exception e) {
			
			}
			this.add(jfText);
		}

		private boolean dataValida(Date data) {
			Date dataAtual = new Date();
			
			try {
				if(data.getDay() > 0 & data.getMonth() > 0 & data.getYear() > 0 ) {
					if(data.getYear() <= dataAtual.getYear() & data.getMonth() <= 12 & data.getDay() <= 31) {
						return true;
				}
			}
			return false;
			} catch (Exception e) {
				return false;
			}
		}
		
		public void limparTexto() {
			txtNome.setText("");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnCadastrar) {
				Date data;
				try {
					data = dataFormatada.parse(jfText.getText());
					boolean valido = dataValida(data);
					if(valido) {
						rep.cadastrarAniversariante(new Pessoa(null,txtNome.getText(), dataFormatada.format(data)));
					}
					else {
						JOptionPane.showMessageDialog(null, "Dados inválidos");
					}
				} catch (ParseException erro) {
					JOptionPane.showMessageDialog(null, "Dados inválidos");
				}
				
			}
			if(e.getSource() == btnClique) {
				limparTexto();
				this.dispose();
				new TelaPesquisa().setVisible(true);
			}
		}
	}