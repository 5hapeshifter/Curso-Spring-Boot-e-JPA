package com.gvendas.gestaovendas.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gvendas.gestaovendas.dto.categoria.CategoriaRequestDTO;
import com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.servico.CategoriaServico;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

	@Autowired // aqui precisamos instanciar um CategoriaServico para o controller acessar o serviço e o serviço acessar o repositório.
	private CategoriaServico categoriaServico; 
	
	@ApiOperation(value = "Listar", nickname = "listarTodas")
	@GetMapping
	public List<CategoriaResponseDTO> listarTodas(){
		return categoriaServico.listarTodas().stream()
				.map(categoria -> CategoriaResponseDTO.converterParaCategoriaDto(categoria))// para cada categoria dentro da lista, sera feita a conversao pela expressao lambda.
				.collect(Collectors.toList());
	}
	
	@ApiOperation(value = "Listar por codigo", nickname = "buscarPorId")
	@GetMapping("/{codigo}")//teríamos que usar o "@PathVariable(name ="codigo")" se o nome do codigo da entidade e de serviço fossem diferentes.
	public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long codigo){// com a classe ResponseEntity conseguimos retornar o status correto da requisição, 200 se ela for encontrada e 404 se não for encontrada.
		Optional<Categoria> categoria = categoriaServico.buscarPorCodigo(codigo);
		return categoria.isPresent() 
				? ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDto(categoria.get())) 
				: ResponseEntity.notFound().build();
	}
		
	@ApiOperation(value = "Salvar", nickname = "salvarCategoria")
	@PostMapping // @Valid valida todos atributos com as anotações do bean validation, como "NotBlank", "Length" e etc
	public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriaDto){// aqui temos que usar o @RequestBody pq os dados para salvar o objeto vem no corpo da requisição
		Categoria categoriaSalva = categoriaServico.salvar(categoriaDto.converterParaEntidade());
		return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.converterParaCategoriaDto(categoriaSalva));
	}
	
	@ApiOperation(value = "Atualizar", nickname = "atualizarCategoria")
	@PutMapping("/{codigo}")
	public ResponseEntity<CategoriaResponseDTO>atualizar(@PathVariable Long codigo,@Valid @RequestBody CategoriaRequestDTO categoriaDto){
		Categoria categoriaAtualizada = categoriaServico.atualizar(codigo, categoriaDto.converterParaEntidade(codigo));
		return ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDto(categoriaAtualizada));
	}
	
	@ApiOperation(value = "Deletar", nickname = "deletarCategoria")
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo) {
		categoriaServico.deletar(codigo);
	}
	
}
