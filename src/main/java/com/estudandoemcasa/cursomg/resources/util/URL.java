package com.estudandoemcasa.cursomg.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	public static String decodeParam(String decodeString) {
		try {
			return URLDecoder.decode(decodeString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static List<Integer> decodeIntList(String tecoUrl){
		
//		String[] vet = tecoUrl.split(",");
//		List<Integer> listaInteiros = new ArrayList<>();
//		
//		for(int i = 0; i < vet.length; i++) {
//			listaInteiros.add(Integer.parseInt(vet[i]));
//		}
		
		/*
		 * Lambda
		 */
		return Arrays.asList(tecoUrl.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
}
