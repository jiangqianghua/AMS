package com.ams.common;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.ams.exception.ParamsException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/**
 * 表单校验工具
 * @author jiangqianghua
 *
 */
public class BeanValidator {

	private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	
	public static <T> Map<String, String> validate(T t,Class... groups){
		
		Validator validator = validatorFactory.getValidator();
		Set validateResult = validator.validate(t, groups);
		if(validateResult.isEmpty()){
			return Collections.emptyMap();
		}else{
			LinkedHashMap errors = Maps.newLinkedHashMap();
			Iterator iterator = validateResult.iterator();
			while(iterator.hasNext()){
				ConstraintViolation violation = (ConstraintViolation)iterator.next();
				errors.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			return errors;
		}
	}
	
	public static Map<String, String> validateList(Collection<?> collection){
		Preconditions.checkNotNull(collection);
		Iterator iterator = collection.iterator();
		Map errors ;
		do{
			if(!iterator.hasNext())
				return Collections.emptyMap();
			Object object = iterator.next();
			errors = validate(object, new Class[0]);
		}while(errors.isEmpty());
		
		return errors ;
	}
	
	public static <T> Map<String, String> validateObject(Object first,Object... objects){
		if(objects != null && objects.length > 0){
			return validateList(Lists.asList(first,objects));
		}
		else{
			return validate(first, new Class[0]);
		}
	}
	/**
	 * 检测对象属性是否为空
	 * @param param
	 * @throws ParamsException
	 */
	public static void check(Object param) throws ParamsException
	{
		Map<String, String> map =BeanValidator.validateObject(param);
		if(map != null && map.entrySet().size() > 0){
			throw new ParamsException(map.toString());
		}
	}
}
