package com.gvendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.excecao.RegraNegocioException;
import com.gvendas.gestaovendas.repositorio.CategoriaRepositorio;

@Service// como essa classe é um serviço, precisamos colocar a anotação
public class CategoriaServico { 

	@Autowired
	private  CategoriaRepositorio categoriaRepositorio; // como essa classe é um serviço, temos que instanciar um objeto CategoriaRepositorio, para esse serviço conseguir acessar a entidade a partir do objeto
	
	public List<Categoria> listarTodas(){
		return categoriaRepositorio.findAll();// esse método é um dos que já vem pelo JpaRepository
	}
	
	public Optional<Categoria> buscarPorCodigo (Long codigo){
		return categoriaRepositorio.findById(codigo);
	}
	
	public Categoria salvar(Categoria categoria) {
		validarCategoriaDuplicada(categoria);
		return categoriaRepositorio.save(categoria);
	}
	
	public Categoria atualizar (Long codigo, Categoria categoria) {
		Categoria categoriaSalvar = validarCategoriaExiste(codigo);// aqui estamos validando se essa categoria existe para não ocorrer um erro se tentarmos atualizar uma categoria que não existe
		validarCategoriaDuplicada(categoria);
		BeanUtils.copyProperties(categoria, categoriaSalvar, "codigo");// aqui estamos usando a classe BeanUtils que possui o método copyProperties que permite a gente substituir os dados existentes pelos recebidos,
		// dessa forma temos que informar o objeto recebido "categoria", o objeto que será atualizado "categoriaSalvar" e o que não será atualizado, nessa caso "codigo".
		return categoriaRepositorio.save(categoriaSalvar);
	}
	
	public void deletar(Long codigo) {
		categoriaRepositorio.deleteById(codigo);
	}

	private Categoria validarCategoriaExiste(Long codigo) {
		Optional<Categoria> categoria = buscarPorCodigo(codigo);
		if(categoria.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoria.get();
	}
	
	private void validarCategoriaDuplicada(Categoria categoria) {
		Categoria categoriaEncontrada = categoriaRepositorio.findByNome(categoria.getNome());
		if(categoriaEncontrada != null && categoriaEncontrada.getCodigo() != categoria.getCodigo()) {
			throw new RegraNegocioException(String.format("A categoria %s ja esta cadastrada.", categoria.getNome().toUpperCase()));
		}
	}
}
