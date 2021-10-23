package br.com.digital.demoproduct.entitys;

public interface BaseEntity {
	Long getId();

	String getName();

	void setName(String name);

	String getImage();

	void setId(Long id);

	String getDescricao();

	String getImageView();

	void setImageView(String imageView);

	void setImage(String image);

	String getExtension();

	void setExtension(String extension);

}
