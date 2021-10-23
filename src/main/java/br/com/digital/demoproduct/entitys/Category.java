package br.com.digital.demoproduct.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class Category
 */
@Entity
public class Category implements BaseEntity, Serializable {

	//
	// Fields
	//
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id 
	private Long id;
	private String name;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "category")
	private List<Product> products;

	//
	// Constructors
	//
	public Category() {
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
	public void setProducts(List<Product> newVar) {
		products = newVar;
	}

	/**
	 * Get the value of products
	 * 
	 * @return the value of products
	 */
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescricao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImageView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setImageView(String imageView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setImage(String image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getExtension() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExtension(String extension) {
		// TODO Auto-generated method stub
		
	}

	//
	// Other methods
	//

}
