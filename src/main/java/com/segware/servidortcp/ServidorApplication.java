package com.segware.servidortcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorApplication {
	
	private ServerSocket servidor;
	private static Socket cliente;
	private PrintWriter saida;
	private BufferedReader entrada;
	
	public void inicio(int port) throws IOException {
		servidor = new ServerSocket(port);
		cliente = servidor.accept();
		System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
	}
	
	public void stop() throws IOException {
		entrada.close();
		saida.close();
		cliente.close();
		servidor.close();
	}
	
	@SuppressWarnings("resource")
	public static void main( String[] args ) throws IOException {
		
		ServidorApplication server = new ServidorApplication();
		server.inicio(12345);
		
		Scanner entrada = new Scanner(cliente.getInputStream());
		
		while(entrada.hasNextLine()) {
			System.out.println(entrada.nextLine());
		}
		server.stop();
    }
}
