package br.com.digital.demoproduct.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.digital.demoproduct.dtos.CategoryDTO;
import br.com.digital.demoproduct.entitys.Category;
import br.com.digital.demoproduct.mapper.IDigitalMapper;
import br.com.digital.demoproduct.repository.CategoryRepository; 
 

@Service
public class CategoryService extends GenericService<Category, CategoryDTO> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private CategoryRepository repo;
	@Autowired
	private IDigitalMapper digitalMapper;

	@Override
	public JpaRepository<Category, Long> getRepo() {
		return repo;
	}
	@Override
	public Class<CategoryDTO> getClasseDto() {		 
		return CategoryDTO.class;
	}
	@Override
	public CategoryDTO newDto(Category obj) {
		// TODO Auto-generated method stub
		return digitalMapper.toModelCategoryDTO(obj);
	}
@Override
public Page<CategoryDTO> findAllPaged(String name, Pageable pageable, Boolean isImg) {
	Category p=new Category();
	 p.setName(name);
	return super.findAllPaged(p, pageable, isImg,"name");
}
}
