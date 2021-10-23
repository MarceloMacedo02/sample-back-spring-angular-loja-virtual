package br.com.digital.demoproduct.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.digital.demoproduct.dtos.BaseInterfaceDto;
import br.com.digital.demoproduct.entitys.BaseEntity;
import br.com.digital.demoproduct.services.GenericService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Operações CLUD insert delete update Input Obj id id, obj Output path: edit/id
 * -- dto
 * 
 * Lista All Page Input --- nome, pageable Output List<dto> Page<dto>
 * 
 * objeto /{id} /{nome Input id nome Output dto dto
 * 
 * 
 * Image ** função loadimage void saveimagem * Input nome imagem + extensao
 * grava imagem nuvem Output url imagem url imagem
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = {@Autowired})
public abstract class GenericController<T extends BaseEntity, D extends BaseInterfaceDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	 
	GenericService<T, D> service;

	/*** OPERAÇÃO COM CRUD ****/
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody T obj) {
		D dto = getService().insert(obj);
		java.net.URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto.getId());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<D> update(@PathVariable Long id, @RequestBody T obj) {
		D dto = getService().update(id, obj);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		getService().delete(id);
		return ResponseEntity.noContent().build();
	}

	/**** FIM OPERACAO CRUD ***/

	/*** Lista */
	/**
	 * @param name
	 * @param pageable
	 * @return lista de dto paginado default com imagem
	 */
	@GetMapping
	public ResponseEntity<Page<D>> findAllPaged(@RequestParam(value = "name", defaultValue = "") String name,
			Pageable pageable) {
		Page<D> list = getService().findAllPaged(name.trim(), pageable, true);
		return ResponseEntity.ok().body(list);
	}

	/**
	 * @param name
	 * @param pageable
	 * @return lista de dto paginado default com imagem
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<D>> findAll() {
		List<D> list = getService().findAll(true);
		return ResponseEntity.ok().body(list);
	}

	/*** FIM Lista */

	/*** SIMPLES OBJETO **/
	/**
	 * @param id
	 * @return default com imagem
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<D> findById(@PathVariable Long id) {
		D dto = getService().findById(id, true);
		return ResponseEntity.ok().body(dto);
	}

	/**
	 * @param nome localiza entidade por field: nome
	 * @return DTO 
	 */
 
	/*** FIM SIMPLES OBJETO **/
	
	/** IMAGEM*/
	
	/**
	 * Grava imagem
	 * @param file 
	 * @param id
	 * @return Endereço da url da imagem salva
	 */
	@PostMapping(value = "/{id}/uploadfile")
	public ResponseEntity<String> uplaodImage(@RequestBody MultipartFile file, @PathVariable Long id) {
		// TODO Auto- return ;
		try {
			String s = null;
			 s = getService().uploadFile(file, id);

			return ResponseEntity.ok(s);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
 
}
