package br.com.digital.demoproduct.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.ServiceAccountSigner;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.SignUrlOption;

import br.com.digital.demoproduct.dtos.BaseInterfaceDto;
import br.com.digital.demoproduct.entitys.BaseEntity;
import br.com.digital.demoproduct.exceptions.DatabaseException;
import br.com.digital.demoproduct.exceptions.ResourceNotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Operações CLUD insert delete update Input Obj id id, obj Output path: edit/id
 * -- dto
 * 
 * Lista All Page Input --- name, pageable Output List<dto> Page<dto>
 * 
 * objeto /{id} /{name Input id name Output dto dto
 * 
 * 
 * Image ** função loadimage void saveimage * Input name image + extensao
 * grava image nuvem Output url image url image
 * 
 */
/**
 * Teste
 * pegar superCalsse
 * inserir elemento
 * erro excluir elemento inxistente
 *  excluir elemento
 *  salvar elemento
 *  editar elemento
 *  salvar image no firestor
 *  recuperar image no fire estore
 *  listar paginação 
 *  listar all
 *  localizar por id
 *  retorno dto
 * 
 */
/**
 * 
 * @author digital
 *
 * @param <T>
 * @param <D>
 */
@Data
@NoArgsConstructor
public abstract class GenericService<T extends BaseEntity, D extends BaseInterfaceDto> implements Serializable {

	T obj;

	private static final long serialVersionUID = 1L;

	@Autowired
	ResourceLoader resourceLoader;
 
	// firebase

	@Autowired
	public Storage storage;	
	@Autowired
	public Credentials credential;
 

 

	@Value("${spring.cloud.gcp.bucketName}")
	private String bucketName;
	 

	@Autowired
	JpaRepository<T, Long> repo;

	public Class<D> getClasseDto() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getClasse() {
		Class<T> classe = null;
		try {
			Class<T> class1 = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];

			classe = class1;
		} catch (Exception e) {
		}
		return classe;
	}

	/*** OPERAÇÃO COM CRUD ****/
	public void preNew(T obj) {

	}

	public void posNew(T obj) {

	}

	public void preSave(T obj) {

	}

	public void posSave(T obj) {

	}

	@Transactional
	public D insert(T obj) {
		// obj = getRepo().save(obj);
		preNew(obj);
		/*
		 * ExampleMatcher caseInsensitiveExampleMatcher =
		 * ExampleMatcher.matchingAll().withIgnoreCase();
		 * 
		 * ExampleMatcher matcher = ExampleMatcher.matchingAll() .withMatcher("name",
		 * match -> match.contains().ignoreCase()).withIgnoreNullValues() // ignore
		 * unset // properties when // finding .withIgnorePaths("id"); // ignore
		 * primitives as they default to 0
		 * 
		 * Example<T> example = Example.of(obj, matcher); List<T> p =
		 * getRepo().findAll(example); if (p.size() > 0) { throw new
		 * DataIntegrityViolationException("Existe um cadastro para o name:" +
		 * obj.getName()); }
		 */
		obj = getRepo().save(obj);
		posNew(obj);
		// System.out.println( (obj.getName()));
		return newDto(obj);
	}

	@Transactional
	public D update(Long id, T obj) {
		preSave(obj);
		obj.setId(id);
		try {
			T thisobj = getRepo().findById(id).get();
			BeanUtils.copyProperties(obj, thisobj, getNullPropertyNames(obj)); //
			// Perform update operation

			obj = getRepo().save(obj);
			posSave(thisobj);
			return newDto(obj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	@Transactional
	public T updateReturnT(Long id, T obj) {
		preSave(obj);
		try {
			T thisobj = getRepo().findById(id).get();
			BeanUtils.copyProperties(obj, thisobj, getNullPropertyNames(obj)); //
			// Perform update operation

			obj = getRepo().save(obj);
			posSave(thisobj);
			return (obj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			getRepo().deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	public D newDto(T obj) {
		D n_obj = null;
		/*
		 * try { obj.setImageView(downloadFile(obj.getImage() + "." +
		 * obj.getExtension())); } catch (Exception e) {
		 * 
		 * }
		 * 
		 * 
		 * try { n_obj = getClasseDto().getDeclaredConstructor().newInstance();
		 * 
		 * n_obj = (D) n_obj.setnew(obj); } catch (InstantiationException |
		 * IllegalAccessException | IllegalArgumentException | InvocationTargetException
		 * | NoSuchMethodException | SecurityException e1) {
		 * 
		 * }
		 */
		return n_obj;
	}

	// copiar reflexao
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/**** FIM OPERACAO CRUD ***/

	/*** Lista */
	public Page<D> findAllPaged(String name, Pageable pageable, Boolean isImg){
		return null;
		
	}
	
	/**
	 * 
	 * @param obj objeto tipo T com o paramneto de perquisa setado
	 * @param pageable 
	 * @param isImg true se tiver image
	 * @param field campo do filtro
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<D> findAllPaged(T obj, Pageable pageable, Boolean isImg,String field) {
		 
  	ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();

		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withMatcher(field, match -> match.contains().ignoreCase()).withIgnoreNullValues() // ignore unset
				.withIgnoreCase()																			// properties when
																									// finding
				.withIgnorePaths("id"); // ignore primitives as they default to 0

		Example<T> example = Example.of(obj, matcher);
		Page<T> p = getRepo().findAll(example, pageable);
		if (isImg) {
			try {
				p.stream().forEach(x -> x.setImageView(downloadFile(x.getImage() + "." + x.getExtension())));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		Page<D> baseDtos = p.map(x -> newDto(x));
	 
		try {
			baseDtos = p.map(x -> newDto(x));
		} catch (Exception e) {
			// TODO: handle exception
		}

		return baseDtos;
	}

	@Transactional(readOnly = true)
	public List<D> findAll(Boolean isImg) {
		List<T> lista = getRepo().findAll();
		return getRepo().findAll().stream().map(x -> {
			if (isImg) {
				try {
					x.setImageView(downloadFile(x.getImage() + "." + x.getExtension()));
				} catch (Exception e) {

				}
			}
			return newDto(x);
		}).collect(Collectors.toList());
	}

	/** FIM LISTAS */
	/*** SIMPLES OBJETO **/
	@Transactional(readOnly = true)
	public D findById(Long id, Boolean isImg) {
		Optional<T> obj = getRepo().findById(id);
		T entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		if (isImg) {
			try {
				entity.setImageView(downloadFile(entity.getImage() + "." + entity.getExtension()));
			} catch (Exception e) {
				// TODO: handle exception
			}
			;
		}
		return newDto(entity);
	}

	public D findbyname(String name, Boolean isImg) {

		try {
			obj = getClasse().getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {

		}
		obj.setName(name);
		ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withMatcher("name", match -> match.contains().ignoreCase()).withIgnorePaths("id"); // ignore primitives
																									// as they default
																									// to 0

		Example<T> example = Example.of(obj, matcher);
		obj = getRepo().findOne(example).get();
		if (isImg) {
			try {
				obj.setImageView(downloadFile(obj.getImage() + "." + obj.getExtension()));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return newDto(obj);
	}

	/*** FIM SIMPLES OBJETO **/

	/**** TRATAMENTO DE IMAGENS ***/
	 
	public String uploadFile(MultipartFile file, Long id) {

		obj = getRepo().findById(id).get();
		obj.setId(id);
		String s = obj.getImage();
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String fileName = s;

		String t = fileName;

		try {
			if (fileName.equals("") || fileName == null) {
				fileName = file.getOriginalFilename(); // to get original file name
				fileName = UUID.randomUUID().toString(); // to generated random
				obj.setImage(t);
				obj.setExtension(extension);
				fileName += "." + extension;
			} else {
				deleteimage(fileName + "." + obj.getExtension());
				obj.setExtension(extension);
				fileName += "." + extension;

			}
		} catch (Exception e) {
			fileName = file.getOriginalFilename(); // to get original file name
			fileName = UUID.randomUUID().toString(); // to generated random
			obj.setImage(fileName);
			obj.setExtension(extension);
			fileName += "." + extension;
		}

		try {

			BlobId blobId = BlobId.of(bucketName, fileName);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();

		} catch (Exception e) {
			obj.setImageView(fileName);
		}
		getRepo().save(obj);

		return upload(file, fileName);

	}

	private String uploadFile(File file, String fileName, String media) throws IOException {

		BlobId blobId = BlobId.of(bucketName, fileName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(media).build();

		storage.create(blobInfo, Files.readAllBytes(file.toPath()));
		System.out.println(storage.get(BlobId.of(bucketName, fileName)));
		return downloadFile(fileName);// String.format("https://firebasestorage.googleapis.com/v0/b/digital-servicos.appspot.como/%s?alt=media",
										// URLEncoder.encode(fileName, StandardCharsets.UTF_8));
	}

	private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
		File tempFile = new File(fileName);
		try (FileOutputStream fos = new FileOutputStream(tempFile)) {
			fos.write(multipartFile.getBytes());
			fos.close();
		}
		return tempFile;
	}

	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public String downloadFile(String fileName) {

		if (!fileName.equals("null.null")) {

			URL signedUrl = null;

			try {

				signedUrl = storage.signUrl(BlobInfo.newBuilder(bucketName, fileName).build(), 360, TimeUnit.DAYS,
						SignUrlOption.signWith((ServiceAccountSigner) credential));

			} catch (Exception e) {

				System.out.println(e.getMessage());
			}

			return signedUrl.toString();
		} else
			return null;
	}

	public void deleteimage(String filename) {

		try {

			storage.delete(bucketName, filename);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String upload(MultipartFile multipartFile, String fileName) {
		File file = null;
		String TEMP_URL = "";
		try {
			String media = multipartFile.getContentType(); // values for file name.

			file = this.convertToFile(multipartFile, fileName); // to convert multipartFile to File
			TEMP_URL = this.uploadFile(file, fileName, media); // to get uploaded file link
			file.delete(); // to delete the copy of uploaded file stored in the project folder
		} catch (Exception e) {
			try {
				file.delete();
			} catch (Exception e1) {
			}
			// return FilerException("Erro gravar arquivo: " + e.getMessage());
			// return sendResponse("500", e, "Unsuccessfully Uploaded!");
		}
		return TEMP_URL;
	}

	/**** FIM TRATAMENTO DE IMAGENS ***/
}
