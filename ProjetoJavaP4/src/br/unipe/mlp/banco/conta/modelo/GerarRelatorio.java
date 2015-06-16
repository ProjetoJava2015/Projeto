package br.unipe.mlp.banco.conta.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GerarRelatorio{

   private JFileChooser fc = null;
   private File diretorio = null;
   private PrintWriter pw = null;
   private ArrayList<Conta> contas = null;
   private boolean isLingua=false;
   
    public GerarRelatorio(JFrame parent,ArrayList<Conta> contas,boolean lingua) {
    	   this.contas=contas; 
    	   isLingua=lingua;
    	   fc = new JFileChooser();
		   // restringe a amostra a diretorios apenas
		   fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		   int res = 0;
		   if(!isLingua){
			   res = fc.showDialog(null,"Salvar");
		   } else {
			   res = fc.showDialog(null,"Save");
		   }
		    
		   
		   if(res == JFileChooser.APPROVE_OPTION){
		       diretorio = fc.getSelectedFile();
		       if(!isLingua){
		    	   JOptionPane.showMessageDialog(parent, "Relatório Salvo em: " + diretorio.getPath() + "\\relatorio.txt");
		       } else {
		    	   JOptionPane.showMessageDialog(parent, "report except in: " + diretorio.getPath() + "\\relatorio.txt");
		       }
		        		       
		       gravarArquivo();
		   }
		   else
			   if(!isLingua){
				   JOptionPane.showMessageDialog(null, "Voce não selecionou nenhum diretorio."); 
			   } else {
				   JOptionPane.showMessageDialog(null, "You did not select any directory."); 
			   }
       
           
       }
  
	 private void gravarArquivo(){
		 
		 try {
				pw = new PrintWriter(new FileWriter(diretorio.getPath().concat("\\relatorio.txt")));
				@SuppressWarnings("unused")
				String list[] ;
				pw.println("----------------------------------------");
				if(!isLingua){
					pw.println("Relatório de Contas Cadastradas");
				} else {
					pw.println("Members Accounts Report");
				}
				
				pw.println();
				for(Conta conta : contas){
					if(!isLingua){
						pw.println("Nome: "+conta.getNome());
						pw.println("Numero: "+conta.getNumero());
						pw.println("Saldo: "+conta.getSaldo());
					} else {
						pw.println("Name: "+conta.getNome());
						pw.println("Number: "+conta.getNumero());
						pw.println("Balance: "+conta.getSaldo());
					}					
					pw.println();
				}
				pw.println("----------------------------------------");
				pw.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}  
	 }
}
