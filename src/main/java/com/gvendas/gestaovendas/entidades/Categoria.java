package com.gvendas.gestaovendas.entidades;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //estamos dizendo que essa classe é uma entidade do banco de dados
@Table(name ="categoria")// nome exato da tabela do banco de dados que essa classe representa
public class Categoria {

	@Id // significa que aqui é uma chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // aqui estamos estabelecendo o auto-increment
	@Column(name="codigo")
	private long codigo; //atributos da tabela
	
	@Column(name="nome") // quando os nomes da tabela e do atributo forem iguais, não precisamos informar entre parenteses
	private String nome;

	public Categoria() { 
	}
	
	public Categoria(String nome) { 
		this.nome = nome; // os dados do atributo nome estao sendo validados direto na classe request dto
	}
	
	public Categoria(Long codigo) { 
		this.codigo = codigo; 
	}
	
	public Categoria(long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return codigo == other.codigo && Objects.equals(nome, other.nome);
	}
	
	
}
