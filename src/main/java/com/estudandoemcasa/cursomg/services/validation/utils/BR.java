package com.estudandoemcasa.cursomg.services.validation.utils;

public class BR {

	/*
	 * Válidador de CPF && PESSOA JURIDICA
	 * 
	 * WEIGHT_SSN = CPF
	 * WEIGHT_TFN = CNPJ
	 */
	
	private static final int[] 
			WEIGHT_SSN = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 }, 
			WEIGHT_TFN = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static int sum(int[] weight, char[] numbers, int length) {
		return length <= 0 ? 0 : (sum(weight, numbers, (length - 1)) + 
				Character.getNumericValue(numbers[(length - 1)]) * 
				weight[(weight.length > numbers.length ? length : (length - 1))]);
	}

	private static int calculate(final String document, final int[] weight) {
		final char[] numbers = document.toCharArray();
		int sum = 11 - ((sum(weight, numbers, numbers.length)) % 11);
		return sum > 9 ? 0 : sum;
	}

	private static boolean check(String tfn, int length, int[] weight) {
		final String number = tfn.substring(0, length);
		return tfn.equals(number + calculate(number, weight) + calculate(number + calculate(number, weight), weight));
	}

	public static boolean isValidCodigoPessoa(String identificadorPessoa) {
		/*
		 * identificadorPessoa = Fisica || Juridica
		 */
		if(identificadorPessoa.length() == 11) {
			return (identificadorPessoa.isEmpty() || !identificadorPessoa.matches("\\d{11}") 
			|| identificadorPessoa.matches(identificadorPessoa.charAt(0) + "{11}")) ? 
					false : check(identificadorPessoa, 9, WEIGHT_SSN);
		}
		
		return (identificadorPessoa.isEmpty() ||
				!identificadorPessoa.matches("\\d{14}")) ? false : check(identificadorPessoa, 12, WEIGHT_TFN);
	}

}
