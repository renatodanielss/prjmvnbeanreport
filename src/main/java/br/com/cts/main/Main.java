package br.com.cts.main;

import java.util.ArrayList;
import java.util.List;

import br.com.cts.model.Cliente;
import br.com.cts.model.Teste;
import br.com.cts.util.Report;

public class Main {

	public static void main(String[] args) throws Exception {
		Cliente cliente1 = new Cliente();
		cliente1.setNomeCliente("John");
		
		Cliente cliente2 = new Cliente();
		cliente2.setNomeCliente("Mike");
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		
		Teste teste = new Teste();
		teste.setNome("Teste 1");
		
		List<Teste> testes = new ArrayList<Teste>();
		testes.add(teste);
		
		Report<Cliente> report = new Report<Cliente>();
		report.getReport(clientes, "reports/clientereport.jrxml");
	}
}