package br.com.darlan.portfoliomanager.util;

import java.util.List;

public interface BusinessMapper<E, D> {
	D entityToDTO(E e);
	
	E dtoToEntity(D d);
	
	List<D> listEntityToListDTO(List<E> listE);
	
	
	List<E> listDTOToListEntity(List<D> listD);
}
