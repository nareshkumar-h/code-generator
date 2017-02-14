package codegenerator.util;

import java.util.Map;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;

public class FileUtil {

	public static String rootDir = "src/main/java/";

	public static MethodSpec createMethod(String methodName) {

		return createMethod(methodName, false, null, null);
	}
	public static MethodSpec createMethod(String methodName, Class clazz) {
		return createMethod(methodName,false,clazz, null);
	}

	public static MethodSpec createMethod(String methodName, Map<Class,String> params) {
		return createMethod(methodName,false,null,params);
	}


	public static MethodSpec createMethod(String methodName, boolean isStatic) {

		return createMethod(methodName, isStatic, null, null);
	}

	public static MethodSpec createMethod(String methodName, boolean isStatic, Class clazz, Map<Class,String> params) {

		Builder builder = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC);
		if (isStatic) {
			builder.addModifiers(Modifier.STATIC);
		}
		if (clazz == null) {
			builder.returns(void.class);
		} else {
			builder.addStatement("return null");
			builder.returns(clazz);
		}
		if ( params != null) {
			for(Class paramclazz: params.keySet()){
				String value = params.get(paramclazz);
				builder.addParameter(paramclazz, value);
			}
		}


		// builder.addStatement("//todo");
		// builder.addComment("todo");
		// builder.addStatement("$T.out.println($S)",
		// System.class, "Hello, JavaPoet!");

		MethodSpec method = builder.build();
		return method;
	}

	public static Builder createMethodBuilder(String methodName, Class clazz, Map<Class,String> params) {

		Builder builder = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC);

		if (clazz == null) {
			builder.returns(void.class);
		} else {
			builder.addStatement("return null");
			builder.returns(clazz);
		}
		if ( params != null) {
			for(Class paramclazz: params.keySet()){
				String value = params.get(paramclazz);
				builder.addParameter(paramclazz, value);
			}
		}

		return builder;
	}



	public static MethodSpec createInterfaceMethod(String methodName, boolean isStatic, Class clazz, Map<Class,String> params) {

		Builder builder = MethodSpec.methodBuilder(methodName);

		if (clazz == null) {
			builder.returns(void.class);
		} else {
			builder.addStatement("return null");
			builder.returns(clazz);
		}
		if ( params != null) {
			for(Class paramclazz: params.keySet()){
				String value = params.get(paramclazz);
				builder.addParameter(paramclazz, value);
			}
		}

		MethodSpec method = builder.build();
		return method;
	}






/*
	public static void main(String[] args) throws IOException {

		MethodSpec main = MethodSpec.methodBuilder("main").addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.returns(void.class).addParameter(String[].class, "args")
				.addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!").build();

		String className = "HelloWorld";
		TypeSpec helloWorld = TypeSpec.classBuilder(className).addModifiers(Modifier.PUBLIC)// ,
																							// Modifier.FINAL)
				.addMethod(main).build();

		JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld).build();

		javaFile.writeTo(System.out);
		Path path = Paths.get(className + ".java");
		if (Files.isDirectory(path)) {
			System.out.println("Is Directory");
		} else {
			Files.createDirectories(path);
		}
		Files.write(path, javaFile.toString().getBytes());
	}*/
	public static MethodSpec createMethod(String methodName, Class<Object> clazz, Map<Class, String> params) {
		return createMethod(methodName, false, clazz, params);
	}

}
