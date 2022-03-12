package com.gvendas.gestaovendas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvendas.gestaovendas.entidades.Venda;

public interface VendaRepositorio extends JpaRepository<Venda, Long> {

	List<Venda> findByClienteCodigo(Long codigoCliente); // o JPA entende a sintaxe sendo obrigatorio o "findBy" concatenado o objeto "Cliente" e o atributo da busca "Codigo".
	
}
