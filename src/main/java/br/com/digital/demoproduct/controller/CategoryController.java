package br.com.digital.demoproduct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.demoproduct.dtos.CategoryDTO;
import br.com.digital.demoproduct.entitys.Category;
import br.com.digital.demoproduct.services.CategoryService;
import br.com.digital.demoproduct.services.GenericService;
@RestController
@RequestMapping(value = "/categorys")
public class CategoryController extends GenericController<Category, CategoryDTO> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryService service;

	@Override
	public GenericService<Category, CategoryDTO> getService() {
		return service;
	}
}
