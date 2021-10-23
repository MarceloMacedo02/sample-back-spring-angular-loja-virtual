package br.com.digital.demoproduct.dtos;

import br.com.digital.demoproduct.entitys.BaseEntity;

public interface BaseInterfaceDto {
	public Long getId();

	/*	public String getNome();

	public String getDescricao();

	public String getImagem();

	public String getExtension();

	public String getImagemView();*/
	
	BaseInterfaceDto setnew(BaseEntity obj);
}
