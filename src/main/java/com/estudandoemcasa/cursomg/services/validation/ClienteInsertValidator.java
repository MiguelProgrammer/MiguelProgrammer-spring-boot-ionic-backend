package com.estudandoemcasa.cursomg.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.estudandoemcasa.cursomg.domain.enums.TipoCliente;
import com.estudandoemcasa.cursomg.dto.ClienteNewDTO;
import com.estudandoemcasa.cursomg.resources.exception.FieldMessage;
import com.estudandoemcasa.cursomg.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
	
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if( (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) ||
				objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()))
				&& !BR.isValidCodigoPessoa(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Identificador de Tipo de Pessoa inválido!"));
		}
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFiledName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
