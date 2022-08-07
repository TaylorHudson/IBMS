package visual;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import entidade.Pessoa;
import repositorio.PessoaRepositorio;

public class TelaPesquisa extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo;
	private JComboBox<String>cbMeses;
	private JLabel lblMes;
	private JLabel lblCadastrar;
    private JScrollPane scrol;
    private JTextArea areaTxt;
    private JButton btnClique;
    private JButton btnPesquisar;
    private PessoaRepositorio rep = new PessoaRepositorio();
    private String[] meses = {"janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro","dezembro"}; 
    
    public static void main(String[] args) {
		new TelaPesquisa().setVisible(true);
	}
    
    public TelaPesquisa() {
    	configurarTela();
    	criarAreaTexto();
    	criarBotao();
    	criarComboBox();
    	criarLabel();
    }
    
    private void configurarTela() {
    	this.setTitle("Pesquisar Membros");
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setSize(700,550);
    	this.setLayout(null);
    	this.setResizable(false);
    	
    }
    
    private void criarAreaTexto() {
    	areaTxt = new JTextArea();
    	scrol = new JScrollPane(areaTxt);
    	
    	areaTxt.setFont(new Font("Arial", 1, 15));
        areaTxt.setEditable(false);
        areaTxt.setRows(1);
        scrol.setViewportView(areaTxt);
        scrol.setBounds(5, 100,678, 340);
        
        this.add(scrol);
    }

    private void criarBotao() {
    	btnPesquisar = new JButton("Pesquisar");
    	btnClique = new JButton("Clique Aqui");
    	
    	btnClique.setFont(new Font("Arial", 1, 13));
    	btnClique.addActionListener(this);
    	btnClique.setBounds(73, 485, 110, 25);
    	
    	btnPesquisar.setFont(new Font("Arial", 1, 15));
    	btnPesquisar.addActionListener(this);
    	btnPesquisar.setBounds(280, 455, 120, 35);
    	
    	this.add(btnClique);
    	this.add(btnPesquisar);
    }

    private void criarLabel() {
    	lblMes = new JLabel();
    	lblTitulo = new JLabel();
    	lblCadastrar = new JLabel();
    	
    	lblCadastrar.setFont(new Font("Arial", 0, 13));
    	lblCadastrar.setText("Cadastrar?");
    	lblCadastrar.setBounds(5, 490, 90, 20);
    	
    	lblMes.setFont(new Font("Arial", 0, 18));
        lblMes.setText("Mês");
        lblMes.setBounds(5, 75, 50, 20);

        lblTitulo.setFont(new Font("Arial", 1, 34));
        lblTitulo.setText("Aniversariantes IBMS");
        lblTitulo.setBounds(150, 10, 400, 40);
        
        this.add(lblCadastrar);
        this.add(lblMes);
        this.add(lblTitulo);
    }

    private void criarComboBox() {
    	cbMeses = new JComboBox<>(meses);
    	cbMeses.setFont(new java.awt.Font("Arial", 0, 14));
    	cbMeses.setBounds(50, 72, 100, 25);
    	
    	this.add(cbMeses);
    }
    
    private void pesquisarPorMes(String mes) {
		
		String indiceEmStr = null;
		for(int i=0; i<meses.length; i++) {
    		if(meses[i].equals(mes)) {
    			int indice = i+1;
    			if(i <= 8) {
    				indiceEmStr = String.valueOf(0).concat(String.valueOf(indice));
    			}
    			else {
    				indiceEmStr = String.valueOf(indice);
    			}
    		}
    	}
		
		List<Pessoa> lista = rep.retornarPessoas();
		
		for(Pessoa p: lista) {
			String[] dataNascimento = p.getDataNascimento().split("/");
			if(dataNascimento[1].equals(indiceEmStr)) {
				areaTxt.append((p + "\n"));
    	}
     } 
 }
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == btnPesquisar) {
    		areaTxt.setText("");
           	String mes = cbMeses.getSelectedItem().toString();
           	pesquisarPorMes(mes);
    	}
    	else if(e.getSource() == btnClique) {
			this.dispose();
			new TelaCadastro().setVisible(true);
		}	
	}
}
