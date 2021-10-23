package br.com.digital.demoproduct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.digital.demoproduct.dtos.CategoryDTO;
import br.com.digital.demoproduct.dtos.ProductDTO;
import br.com.digital.demoproduct.entitys.Category;
import br.com.digital.demoproduct.entitys.Product;

@Mapper(componentModel = "spring")
public interface IDigitalMapper {

	IDigitalMapper INSTANCE = Mappers.getMapper(IDigitalMapper.class);
	 @Mapping(target = "products",ignore = true)
	CategoryDTO toModelCategoryDTO(Category CategoryDTO);
	 @Mapping(target = "category.products",ignore = true)
	ProductDTO toModelProductDTOo(Product product);

	 

 
}
