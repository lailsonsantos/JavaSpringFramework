package br.com.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.spring.data.orm.Funcionario;
import br.com.spring.data.repository.FuncionarioRepository;
import br.com.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {

	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYYY");

	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scan) {
		System.out.println("Digite o nome");
		String nome = scan.next();
		
		if (nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.println("Digite o cpf");
		String cpf = scan.next();
		
		if (cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Digite o salario");
		Double salario = scan.nextDouble();
		
		if (salario == 0) {
			salario = null;
		}
		
		System.out.println("Digite o data contratacao");
		String data = scan.next();
		
		LocalDate dataContratacao = null;
				
		if (data.equalsIgnoreCase("NULL")) {
			data = null;
		}else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll(
				Specification.where(
						SpecificationFuncionario.nome(nome))
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))
				);
		
		funcionarios.forEach(System.out::println);
	}
	
}
