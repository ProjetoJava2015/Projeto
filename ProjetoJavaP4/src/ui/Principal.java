package ui;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.unipe.mlp.banco.conta.fachada.Banco;
import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.conta.modelo.ContaCorrente;
import br.unipe.mlp.banco.modelo.exception.ContaNaoEncontradaException;


public class Principal {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException, ParseException {
		Banco banco = new Banco();
		
		try {
			System.out.println(banco.buscarNumero("1020-30"));
		} catch (ContaNaoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Conta c1 = new ContaCorrente("Jo","100-7",300.0);
//		DateFormat dataUsuario = new SimpleDateFormat("dd/MM/yyyy");
//		Date dataConvertida = new Date();
//	
//		dataConvertida =  dataUsuario.parse("02/10/2015") ;
//	
//		c1.setData(dataConvertida);
//		String data = dataUsuario.format(dataConvertida);
		
//		System.out.println(c1.getData());
		//banco.remover("158-7");
		//banco.cadastraConta(c1);
		//banco.alterar(c1);
		//sql.inserir("INSERT INTO Contas (Nome,Numero,Saldo) VALUES('"+c1.getNome()+"','"+c1.getNumero()+"','"+c1.getSaldo()+"')");
		//sql.inserir("DELETE FROM Contas");
		
		//System.out.println(sql.inserir("INSERT INTO Contas(Nome,Numero,Saldo) VALUES ('Jezreel','666-x',200.0)"));
//		ResultSet resul = sql.executar("SELECT nome,numero,saldo FROM Contas");
//		while(resul.next()){
//			System.out.println(resul.getString("Nome"));
//		}
		//@SuppressWarnings({ "unused", "static-access" })
		//@SuppressWarnings("static-access")
		//CaminhoRelatorio caminho = new CaminhoRelatorio(banco.listTable());
		
		
	
		
		  


		//System.out.println(banco.listar());
	}	

}
