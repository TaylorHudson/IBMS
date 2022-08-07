package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.time.LocalDate;
import javax.persistence.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import entidade.Pessoa;
import repositorio.PessoaRepositorio;

public class TelaCadastro extends JFrame implements ActionListener{
		private static final long serialVersionUID = 1L;
		private JLabel lblTitulo;
		private JLabel lblNome;
		private JLabel lblDia;
		private JLabel lblMes;
		private JLabel lblAno;
		private JLabel lblPesquisar;
		private JTextField txtNome;
		private JTextField txtDia;
		private JTextField txtMes;
		private JTextField txtAno;
		private JButton btnCadastrar;
		private JButton btnClique;
		private boolean textoValido = false;
		private boolean dataValida = false;
		private PessoaRepositorio rep = new PessoaRepositorio();
		
		public TelaCadastro() {
			try {
	            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			configurarTela();
			desenharLabel();
			desenharBotao();
			desenharTexto();
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
			lblDia = new JLabel("Dia");
			lblMes = new JLabel("Mês");
			lblAno = new JLabel("Ano");
			lblPesquisar = new JLabel("Pesquisar? ");
			
			lblTitulo.setBounds(25,5,475,35);
			lblTitulo.setFont(new Font("Arial", 1, 35));
			lblTitulo.setForeground(Color.WHITE);
			
			lblNome.setBounds(2,100,80,30);
			lblNome.setFont(new Font("Arial", 1, 20));
			lblNome.setForeground(Color.WHITE);
			
			lblDia.setBounds(2,150,50,30);
			lblDia.setFont(new Font("Arial", 1, 20));
			lblDia.setForeground(Color.WHITE);
			
			lblMes.setBounds(75,150,50,30);
			lblMes.setFont(new Font("Arial", 1, 20));
			lblMes.setForeground(Color.WHITE);
			
			lblAno.setBounds(155,150,50,30);
			lblAno.setFont(new Font("Arial", 1, 20));
			lblAno.setForeground(Color.WHITE);
			
			lblPesquisar.setBounds(2, 335, 80, 20);
			lblPesquisar.setFont(new Font("Arial", 0, 13));
			lblPesquisar.setForeground(Color.WHITE);
			
			this.add(lblPesquisar);
			this.add(lblDia);
			this.add(lblMes);
			this.add(lblAno);
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
			
			txtDia = new JTextField(2);
			txtDia.setFont(new Font("Arial", 0, 15));
			txtDia.setBounds(35,150, 35, 30);
			txtDia.addActionListener(this);
			
			txtMes = new JTextField(2);
			txtMes.setFont(new Font("Arial", 0, 15));
			txtMes.setBounds(115,150, 35, 30);
			txtMes.addActionListener(this);
			
			txtAno = new JTextField(2);
			txtAno.setFont(new Font("Arial", 0, 15));
			txtAno.setBounds(195,150, 48, 30);
			txtAno.addActionListener(this);
			
			this.add(txtAno);
			this.add(txtMes);
			this.add(txtDia);
			this.add(txtNome);
		}

		private boolean validarTexto(String nome,String dia, String mes, String ano) {
			boolean diaIsnumero = dia.matches("[+-]?\\d*(\\.\\d+)?");
	    	boolean mesIsNumero = mes.matches("[+-]?\\d*(\\.\\d+)?");
	    	boolean anoIsNumero = ano.matches("[+-]?\\d*(\\.\\d+)?");
	    	boolean nomeIsNumero = nome.matches("[+-]?\\d*(\\.\\d+)?");
	    	
			if(dia.length() == 2 & mes.length() == 2 & ano.length() == 4 & nome != "") {
				if(diaIsnumero & mesIsNumero & anoIsNumero & !nomeIsNumero) {
					return true;	
				}
			}
			return false;
		}

		private boolean validarDatas(String dia, String mes, String ano) {
			LocalDate data = LocalDate.now();
			try {
				if(dia != "" & mes != "" & ano != "") {
				int d = Integer.parseInt(dia);
				int m = Integer.parseInt(mes);
				int a = Integer.parseInt(ano);
				
				if(d>0 & m>0 & a>0) {
					if(a <= data.getYear() & m <= 12 & d <= 31) {
						return true;
					}
				}
			}
			return false;
			} catch (Exception e) {
				return false;
			}
		}
		public void limparTexto() {
			txtNome.setText("");
			txtDia.setText("");
			txtMes.setText("");
			txtAno.setText("");
		}

		public boolean validacao() {
			textoValido = validarTexto(txtNome.getText(), txtDia.getText(), txtMes.getText(), txtAno.getText());
			dataValida = validarDatas(txtDia.getText(), txtMes.getText(), txtAno.getText());
			if(textoValido & dataValida) {
				return true;
			}
			return false;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnCadastrar) {
				boolean valido = validacao();
				if(valido) {
					String dia = txtDia.getText();
					String mes = txtMes.getText();
					String ano = txtAno.getText();
					String data = dia + "/" + mes + "/" + ano;
					rep.cadastrarAniversariante(new Pessoa(null,txtNome.getText(), data));
				}
				else {
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