package br.com.digital.demoproduct.dtos;

import java.io.Serializable;
import java.util.List;

import br.com.digital.demoproduct.entitys.BaseEntity;
import lombok.Data;

/**
 * Class Category
 */
@Data
public class CategoryDTO implements Serializable, BaseInterfaceDto {

	private static final long serialVersionUID = 1L;
	 
	private Long id;
	private String name;	 
	private List<ProductDTO> products;
	//
	// Constructors
	//
	public CategoryDTO() {
	};

	//
	// Methods
	//

	//
	// Accessor methods
	//

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
	 * Set the value of products
	 * 
	 * @param newVar the new value of products
	 */
	public void setProducts(List<ProductDTO> newVar) {
		products = newVar;
	}

	/**
	 * Get the value of products
	 * 
	 * @return the value of products
	 */
	public List<ProductDTO> getProducts() {
		return products;
	}

 
	@Override
	public BaseInterfaceDto setnew(BaseEntity obj) {
		// TODO Auto-generated method stub
		return null;
	}

	//
	// Other methods
	//

}
