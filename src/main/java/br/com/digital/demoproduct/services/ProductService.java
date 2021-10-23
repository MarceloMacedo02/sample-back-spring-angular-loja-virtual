package br.com.digital.demoproduct.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.digital.demoproduct.dtos.ProductDTO;
import br.com.digital.demoproduct.entitys.Product;
import br.com.digital.demoproduct.mapper.IDigitalMapper;
import br.com.digital.demoproduct.repository.ProductRepository;

@Service
public class ProductService extends GenericService<Product, ProductDTO> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductRepository repo;
	@Autowired
	private IDigitalMapper digitalMapper;

	@Override
	public JpaRepository<Product, Long> getRepo() {
		return repo;
	}
	@Override
	public Class<ProductDTO> getClasseDto() {		 
		return ProductDTO.class;
	}
	@Override
	public ProductDTO newDto(Product obj) {
		// TODO Auto-generated method stub
		return digitalMapper.toModelProductDTOo(obj);
	}
	@Override
	public Page<ProductDTO> findAllPaged(String name, Pageable pageable, Boolean isImg) {
		 Product p=new Product();
		 p.setName(name);
		return super.findAllPaged(p, pageable, isImg,"name");
	}

}
