package codegenerator.layers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;

public abstract class CommonFileGenerator {

	public static String rootDir = "src/main/java/";

	public static String testDir = "src/test/java/";

	protected String rootPackage;

	protected String className;

	public CommonFileGenerator (String rootPackage, String className) {
		this.rootPackage= rootPackage;
		this.className = className;
	}


	public Builder createClass(ProjectFileType fileType, String packageName, String className,
			MethodSpec... methodSpecs) {

		String finalClassName = className + fileType.getClassSuffix();
		com.squareup.javapoet.TypeSpec.Builder classBuilder = TypeSpec.classBuilder(finalClassName);

		classBuilder.addModifiers(Modifier.PUBLIC);
		for (MethodSpec method : methodSpecs) {

			classBuilder.addMethod(method);
		}

		return classBuilder;


	}

	public String createClassFileWithContent(ProjectFileType fileType, String packageName, String className,
			MethodSpec... methodSpecs){

		Builder classBuilder = createClass(fileType, packageName, className, methodSpecs);
		addClassAnnotation(classBuilder);

		JavaFile javaFile = getContent(fileType, packageName, classBuilder);
		return javaFile.toString();
	}

	private JavaFile getContent(ProjectFileType fileType, String packageName,
			com.squareup.javapoet.TypeSpec.Builder classBuilder) {
		TypeSpec classSpec = classBuilder.build();
		String finalPackageName = packageName + "." + fileType.getPackageName();
		JavaFile javaFile = JavaFile.builder(finalPackageName, classSpec).build();
		return javaFile;
	}

	public static com.squareup.javapoet.MethodSpec.Builder createMethodBuilder(String methodName, boolean isStatic, Class clazz, Map<Class,String> params) {

		com.squareup.javapoet.MethodSpec.Builder builder = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC);

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

		return builder;
	}

	public MethodSpec createMethod(String methodName, boolean isStatic, Class clazz, Map<Class,String> params) {

		com.squareup.javapoet.MethodSpec.Builder createMethod = createMethodBuilder(methodName, isStatic, clazz, params);

		MethodSpec method = createMethod.build();

		return method;

	}

	protected abstract void addMethodAnnotation(Builder builder);

	protected abstract void addClassAnnotation(Builder builder);




	public void createJavaFile(ProjectFileType fileType, String packageName, String className, MethodSpec... methodSpecs)
			throws IOException {

		String content = createClassFileWithContent(fileType,packageName, className, methodSpecs);
		String fileName = getFileName(fileType, packageName, className);
		Path path = Paths.get(fileName);

		Files.createDirectories(path.getParent());

		Files.write(path, content.getBytes());
	}

	private String getFileName(ProjectFileType fileType, String packageName, String className) {
		String finalPackageName = packageName + "." +fileType.getPackageName();
		String finalClassName = className + fileType.getClassSuffix();
		System.out.println(finalPackageName);
		String packageDir = finalPackageName.replace(".", "/");
		System.out.println(packageDir);
		String fileName = rootDir + packageDir + "/" + finalClassName + ".java";
		return fileName;
	}


}
