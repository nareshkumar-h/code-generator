package codegenerator.layers;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ParameterSpec;

public class AnnotationUtil {

	public static AnnotationSpec getMethodAnnotation(Class clazz, String annotationValue) {
		return AnnotationSpec.builder(clazz).addMember("value", "\"/"+ annotationValue+"\"").build();
	}

	public static ParameterSpec getParameterAnnotation(Class annotationClazz, Class parameterType, String paramName){
		return ParameterSpec.builder(parameterType, paramName).addAnnotation(annotationClazz).build();
	}

}
