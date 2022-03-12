package com.gvendas.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Se os pacotes criados não estivessem abaixo do pacote principal teríamos que criar todas as anotações abaixo.
//@EntityScan(basePackages = {"com.gvendas.gestaovendas.entidades"} )// aqui estamos informando onde ficam as nossas entidades para que o spring capte e use elas
//@EnableJpaRepositories(basePackages = {"com.gvendas.gestaovendas.repositorios"})// aqui estamos informando o caminho dos repositórios
//@ComponentScan(basePackages = {"com.gvendas.gestaovendas.servico","com.gvendas.gestaovendas.controlador"})// aqui estamos informando o nosso controller e serviço
@SpringBootApplication
public class GestaoVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVendasApplication.class, args);
	}

}
