package br.com.digital.demoproduct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.demoproduct.dtos.ProductDTO;
import br.com.digital.demoproduct.entitys.Product;
import br.com.digital.demoproduct.services.GenericService;
import br.com.digital.demoproduct.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController  extends GenericController<Product, ProductDTO> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductService service;
	
	@Override
	public GenericService<Product, ProductDTO> getService() { 
		return service;
	}

}
