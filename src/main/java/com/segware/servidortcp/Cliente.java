package com.segware.servidortcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.segware.servidortcp.model.Mensagem;
import com.segware.servidortcp.util.Conexao;

public class Cliente {
	
	private Socket clienteSocket;
	private static PrintWriter saida;
	private BufferedReader entrada;
	
	static final Logger logger = Logger.getLogger(Cliente.class);
	
	EntityManager em = new Conexao().getEntityManager();
	
	public void startConexao(String ip, int port) throws UnknownHostException, IOException {
		clienteSocket = new Socket(ip, port);
		saida = new PrintWriter(clienteSocket.getOutputStream(), true);
		entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
		logger.info("HOST: " + InetAddress.getLocalHost().getHostAddress());
		System.out.println("O cliente se conectou ao servidor");
	}
	
	public void stopConexao() throws IOException {
		entrada.close();
		saida.close();
		clienteSocket.close();
	}
	
	public String enviaMensagem(String msg) throws IOException {
		saida.println(msg);
		String resposta = entrada.readLine();
		return resposta;
	}
	
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		BasicConfigurator.configure();
		
		String texto = "";
		
		EntityManager em = new Conexao().getEntityManager();
			
		Cliente cliente = new Cliente();
		cliente.startConexao("127.0.0.1", 12345);
		
		Scanner entrada = new Scanner(System.in);
		
		while(entrada.hasNextLine()) {
			
			texto = entrada.nextLine();
			
			Mensagem mensagem = new Mensagem();
			mensagem.setTexto(texto);
			mensagem.setHost(InetAddress.getLocalHost().getHostAddress());
			
			em.getTransaction().begin();
			em.persist(mensagem);
			em.getTransaction().commit();

			saida.println(mensagem.getTexto());
			logger.debug("MENSAGEM: " + mensagem);
		}
		cliente.stopConexao();
	}
	
}
