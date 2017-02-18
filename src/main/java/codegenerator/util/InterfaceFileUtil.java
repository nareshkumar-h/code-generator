package codegenerator.util;

import java.util.Map;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeSpec;

import codegenerator.ProjectFileType;

public class InterfaceFileUtil {



	public static com.squareup.javapoet.TypeSpec.Builder createInterface(ProjectFileType fileType, String packageName, String className,
			MethodSpec... methodSpecs) {

		String finalClassName = className + fileType.getClassSuffix();
		com.squareup.javapoet.TypeSpec.Builder classBuilder = TypeSpec.interfaceBuilder(finalClassName);

		classBuilder.addModifiers(Modifier.PUBLIC);
		for (MethodSpec method : methodSpecs) {

			//classBuilder.addMethod(method);
		}

		return classBuilder;


	}

	public static MethodSpec declareMethod(String methodName, Class clazz)

	{
		return declareMethod(methodName, null, clazz);
	}

	public static MethodSpec declareMethod(String methodName, Map<Class, String> params, Class clazz) {

		Builder builder = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC).addModifiers(Modifier.ABSTRACT);

		if (clazz == null) {
			builder.returns(void.class);
		} else {
			builder.returns(clazz);
		}
		if (params != null) {
			for (Class paramclazz : params.keySet()) {
				String value = params.get(paramclazz);
				builder.addParameter(paramclazz, value);
			}
		}
		MethodSpec method = builder.build();
		return method;
	}

	public static MethodSpec declareMethod(String methodName, Map<Class, String> params) {

		return declareMethod(methodName, params, null);
	}

	public static MethodSpec declareMethod(String methodName) {

		return declareMethod(methodName, null, null);
	}
}
