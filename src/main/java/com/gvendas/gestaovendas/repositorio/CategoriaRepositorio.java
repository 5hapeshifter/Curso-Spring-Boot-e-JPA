package com.gvendas.gestaovendas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvendas.gestaovendas.entidades.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {// aqui estamos definindo que será através dessa classe que operações no banco de dados serão efetivadas
//essa classe que extendemos JpaRepository, já possui uma série de métodos criados interagir com o banco de dados. depois informamos a entidade(aqui é a classe) que ela buscará informações, nesse caso, Categoria e Long que o tipo da nossa chave primária
//aqui estamos definindo a classe (Categoria) e o tipo da chave primária (Long) que será utilizada para criar os objetos
	
	// Metodo para buscar por nome
	Categoria findByNome(String nome);// so com isso, o Spring com o JPA ja faz um select com esses dados
	
}
