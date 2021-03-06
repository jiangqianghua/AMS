package com.ams.util;
/**
 * 对象和json转化
 * @author jiangqianghua
 *
 */

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.annotate.JsonSerialize;;
/**
 * 对象和json转化
 * @author jiangqianghua
 *
 */
public class JsonMapper {

	//private static Logger logger = LoggerFactory.getLogger(JsonMapper.getClass());
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	static{
		objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
		objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
	}
	
	public static <T> String	 obj2String(T src){
		if(src == null){
			return null;
		}
		try{
			return src instanceof String ?(String)src:objectMapper.writeValueAsString(src);
		}catch(Exception e){
		//	logger.warn("parse object to string exeption,error:{}",e);
			return null;
		}
	}
	
	public static <T> T String2Obj(String src,TypeReference<T> typeReference){
		if(src == null || typeReference == null)
			return null ;
		try{
			return (T) (typeReference.getType().equals(String.class)?src:objectMapper.readValue(src, typeReference));
		}catch(Exception e){
			//logger.warn("parse string to object exeption,error:{}",e);
			return null;
		}
	}
	
}
