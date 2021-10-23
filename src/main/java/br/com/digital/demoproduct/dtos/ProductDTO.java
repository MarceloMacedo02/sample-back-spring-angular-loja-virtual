package br.com.digital.demoproduct.dtos;

import java.io.Serializable;

import br.com.digital.demoproduct.entitys.BaseEntity;
import lombok.Data;

/**
 * Class Produto
 */
@Data
public class ProductDTO implements  Serializable, BaseInterfaceDto {

	private static final long serialVersionUID = 1L;
 
	private Long id;
	protected String image;
	protected String extension;
 
	protected String imageView;
	private String name;
	private String description;
	private Float value; 
	private CategoryDTO category;

	//
	// Constructors
	//
	public ProductDTO() {
	};


	/**
	 * Set the value of id
	 * 
	 * @param newVar the new value of id
	 */
	public void setId(Long newVar) {
		id = newVar;
	}

	/**
	 * Get the value of id
	 * 
	 * @return the value of id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the value of name
	 * 
	 * @param newVar the new value of name
	 */
	public void setName(String newVar) {
		name = newVar;
	}

	/**
	 * Get the value of name
	 * 
	 * @return the value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value of description
	 * 
	 * @param newVar the new value of description
	 */
	public void setDescription(String newVar) {
		description = newVar;
	}

	/**
	 * Get the value of description
	 * 
	 * @return the value of description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the value of value
	 * 
	 * @param newVar the new value of value
	 */
	public void setValue(Float newVar) {
		value = newVar;
	}

	/**
	 * Get the value of value
	 * 
	 * @return the value of value
	 */
	public Float getValue() {
		return value;
	}

	/**
	 * Set the value of category
	 * 
	 * @param newVar the new value of category
	 */
	public void setCategory(CategoryDTO newVar) {
		category = newVar;
	}

	/**
	 * Get the value of category
	 * 
	 * @return the value of category
	 */
	public CategoryDTO getCategory() {
		return category;
	}

	@Override
	public BaseInterfaceDto setnew(BaseEntity obj) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
