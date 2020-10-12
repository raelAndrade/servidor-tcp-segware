package com.segware.servidortcp;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServidorApplicationTest {
	
	private ClienteApplication cliente;
	
	@Before
	public void setup() throws UnknownHostException, IOException {
		cliente = new ClienteApplication();
		cliente.startConexao("127.0.0.1", 12345);
	}
	
	@After
	public void destruirConexao() throws IOException {
		cliente = new ClienteApplication();
	    cliente.stopConexao();
	}
	
	@Test
	public void saudacaoAoClienteQuandoOServidorRespondeQuandoIniciado() throws UnknownHostException, IOException {
		String resposta = cliente.enviaMensagem("Olá servidor");
		assertEquals("Olá cliente", resposta);
	}

}
