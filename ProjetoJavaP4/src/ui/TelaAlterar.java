package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.MaskFormatter;

import br.unipe.mlp.banco.conta.fachada.Banco;
import br.unipe.mlp.banco.conta.modelo.ContaCorrente;
import br.unipe.mlp.banco.modelo.exception.ContaNaoEncontradaException;

public class TelaAlterar {
	private JDialog telaCadDialog = null;
	private JTextField txtNome = null;
	private JFormattedTextField txtNumero = null;
	private JFormattedTextField txtSaldo = null;
	private SpringLayout sl = null;
	private JPanel t_Cad = null;
	private static Banco banco;
	private static String str = "";
	private JLabel nome = null;
	private JLabel conta = null;
	private JLabel saldo = null;
	private JButton botao = null;
	private boolean isLingua = false;
	
	public TelaAlterar(JFrame pai,Banco banco,boolean lingua){
		String titulo;
		if(!lingua){
			titulo="ALTERAR CONTA";
		} else {
			titulo="CHANGE ACCOUNT";
		}
		TelaAlterar.banco = banco;
		telaCadDialog = new JDialog(pai,titulo,true);
		txtNome = new JTextField(15);
		nome = new JLabel();
		conta = new JLabel();
		saldo = new JLabel();
		botao = new JButton();
		this.isLingua = lingua;
		TelaAlterar.banco=banco;
		if(!isLingua){
			montarTradutorPortugues();
		} else {
			montarTradutorEnglish();
		}
		try {
			txtNumero = new JFormattedTextField(new MaskFormatter("####-##"));
			str = txtNumero.getText();
			DecimalFormat parametro = new DecimalFormat("##.##");
			parametro.applyPattern("# # # 00.00");
			txtSaldo = new JFormattedTextField(parametro);
			txtNumero.setColumns(7);
			txtSaldo.setColumns(8);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		sl = new SpringLayout();
		t_Cad = new JPanel();
		mostrarTela();		
	}
	
	private void mostrarTela() {		
		cadastroNome();
		cadastroConta();
		cadastroSaldo();
		botaoConcluido();
		montarTela();		
	}
	private void montarTradutorPortugues(){
		nome.setText("NOME COMPLETO: ");
		conta.setText("CONTA:");
		saldo.setText("SALDO: ");
		botao.setText("CONFIRMAR");
	}
	private void montarTradutorEnglish(){
		nome.setText("NAME: ");
		conta.setText("ACCOUNT:");
		saldo.setText("BALANCE: ");
		botao.setText("CONFIRM");
	}
	
	private void cadastroNome() {		
		sl.putConstraint(SpringLayout.NORTH, nome, 40, SpringLayout.NORTH, t_Cad);
		sl.putConstraint(SpringLayout.WEST, nome, 10, SpringLayout.WEST, t_Cad);
		sl.putConstraint(SpringLayout.NORTH, txtNome,-4, SpringLayout.NORTH, nome);
		sl.putConstraint(SpringLayout.WEST, txtNome, 13, SpringLayout.EAST, nome);
		t_Cad.add(nome);
		t_Cad.add(txtNome);
	}
	
	private void cadastroConta() {				
		sl.putConstraint(SpringLayout.NORTH, conta, 75, SpringLayout.NORTH, t_Cad);
		sl.putConstraint(SpringLayout.WEST, conta, 36, SpringLayout.WEST, t_Cad);
		
		sl.putConstraint(SpringLayout.NORTH, txtNumero,-2, SpringLayout.NORTH, conta);
		sl.putConstraint(SpringLayout.WEST, txtNumero, 52, SpringLayout.EAST, conta);
		t_Cad.add(conta);
		t_Cad.add(txtNumero);
	}		
	

	
	private void cadastroSaldo() {				
		sl.putConstraint(SpringLayout.NORTH, saldo, 110, SpringLayout.NORTH, t_Cad);
		sl.putConstraint(SpringLayout.WEST, saldo, 37, SpringLayout.WEST, t_Cad);
		t_Cad.add(saldo);		
		
		sl.putConstraint(SpringLayout.NORTH, txtSaldo, 1, SpringLayout.NORTH, saldo);
		sl.putConstraint(SpringLayout.WEST, txtSaldo, 47, SpringLayout.EAST, saldo);		
		
		t_Cad.add(txtSaldo);		
	}
	
	private void botaoConcluido() {		
		
		t_Cad.add(botao);
		sl.putConstraint(SpringLayout.SOUTH, botao, -20, SpringLayout.SOUTH, t_Cad);
		sl.putConstraint(SpringLayout.WEST, botao, 120, SpringLayout.WEST, t_Cad);
		t_Cad.add(botao);
	
		botao.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent e) {
			if(!txtNumero.getText().equals(str)){
				
				String nome = txtNome.getText().toLowerCase();
				String numero = txtNumero.getText();
				double saldo = Double.parseDouble(txtSaldo.getText().replace(',','.'));
				ContaCorrente c = new ContaCorrente(nome, numero, saldo);
				try{
					banco.alterar(c);
					telaCadDialog.dispose();
				} catch (ContaNaoEncontradaException e1){
			
					banco.mudarLingua(isLingua);
					JOptionPane.showMessageDialog(telaCadDialog, e1.getMessage());
				}					
							
			} else {
				if(!isLingua){
					JOptionPane.showMessageDialog(null, "INSIRA O NUMERO DA CONTA A SER ALTERADA");
				} else {
					JOptionPane.showMessageDialog(null, "INSERT NUMBER OF ACCOUNT TO BE AMENDED");
				}
				
			}
			
			
			
			
		}
		
	});
	}
	
	private void montarTela() {
	
		telaCadDialog.setSize(350, 300);
		telaCadDialog.setLayout(new FlowLayout());
		t_Cad.setLayout(sl);
		telaCadDialog.setContentPane(t_Cad);
		t_Cad.setSize(500, 500);
		telaCadDialog.setResizable(false);
		telaCadDialog.setLocationRelativeTo(null);
		telaCadDialog.setVisible(true);	
	}
	
}
