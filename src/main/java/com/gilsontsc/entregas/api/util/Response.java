package com.gilsontsc.entregas.api.util;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Gilson
 *
 * @param <T> Tipo generico da classe
 * NoArgsConstructor Vai criar o construtor sem paramentros
 */
@Data
@NoArgsConstructor
public class Response<T> {

	private T data;
	private List<String> errors;
	
	public List<String> getErrors(){
		if(this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return this.errors;
	}
}