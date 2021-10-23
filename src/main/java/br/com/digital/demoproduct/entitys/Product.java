package br.com.digital.demoproduct.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Class Produto
 */
@Data
@Entity
public class Product implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;
	//
	// Fields
	//
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	protected String image;
	protected String extension;

	@Transient
	@Column(length = 100000000)
	@Lob
	protected String imageView;
	private String name;
	private String description;
	private Float value;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Category category;

	//
	// Constructors
	//
	public Product() {
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
	public void setCategory(Category newVar) {
		category = newVar;
	}

	/**
	 * Get the value of category
	 * 
	 * @return the value of category
	 */
	public Category getCategory() {
		return category;
	}

	@Override
	public String getDescricao() {
		// TODO Auto-generated method stub
		return null;
	}

	//
	// Other methods
	//

}
