package codegenerator.skeleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import lombok.Data;

@Data
public abstract class Skeleton {

	protected String className;
	protected String finalClassName;
	protected String rootPackage;
	protected ProjectFileType fileType;
	protected String finalPackageName;
	protected String fileName ;
	protected List<MethodSpec> methodSpecs = new ArrayList<>();

	protected Builder classBuilder;

	public Skeleton(String rootPackage, String className, ProjectFileType fileType) {
		this.rootPackage = rootPackage;
		this.className = className;
		this.fileType= fileType;
		this.finalClassName = className + fileType.getClassSuffix();
		if ( fileType.getType().equals("interface")){
			this.classBuilder = TypeSpec.interfaceBuilder(finalClassName);
		}
		else
		{
			this.classBuilder = TypeSpec.classBuilder(finalClassName);
		}

		this.finalPackageName = rootPackage + "." + fileType.getPackageName();
		this.fileName = getFileName(fileType);
	}

	public void addMethod(com.squareup.javapoet.MethodSpec.Builder ...methodSpecs ) {
		if ( methodSpecs != null) {
			for (com.squareup.javapoet.MethodSpec.Builder methodSpec : methodSpecs) {
				MethodSpec build = methodSpec.build();
				this.methodSpecs.add(build);

			}
		}
	}

	public static String rootDir = "src/main/java/";

	public static String testDir = "src/test/java/";

	protected abstract void createClass();

	public abstract void createMethod();

	void generateClass() {

	}

	protected abstract void addClassAnnotation();

	protected void addMethodAnnotation() {
	}

	public void generateFile() {

		System.out.println("Going to create class :" + rootPackage + className);
		String content = createClassFileWithContent();
		Path path = Paths.get(fileName);

		try {
			Files.createDirectories(path.getParent());

			Files.write(path, content.getBytes());
			System.out.println("Completed-> " + path);
		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("Unable to create file :" + path);
		}
	}

	public String createClassFileWithContent() {

		System.out.println("Invoking ...addClassAnnotation and method annotation");
		addClassAnnotation();
		addMethodAnnotation();

		System.out.println("Creating ..class and methods");
		createClassAndMethods();

		System.out.println("Fetching final Class Content");
		JavaFile javaFile = getContent();
		return javaFile.toString();
	}

	public Builder createClassAndMethods() {

		classBuilder.addModifiers(Modifier.PUBLIC);
		if (methodSpecs != null) {
			System.out.println("Going to add methods:" + methodSpecs.size());


			for (MethodSpec method : methodSpecs) {

				System.out.println("FileType:"+ fileType);


				if ( fileType.getType().equals("interface")){

				}
				else{
					classBuilder.addMethod(method);
				}
			}
		}

		return classBuilder;

	}

	private JavaFile getContent() {
		TypeSpec classSpec = classBuilder.build();

		JavaFile javaFile = JavaFile.builder(finalPackageName, classSpec).build();
		return javaFile;
	}

	private String getFileName(ProjectFileType fileType) {
		String finalPackageName = rootPackage + "." + fileType.getPackageName();
		String finalClassName = className + fileType.getClassSuffix();
		System.out.println(finalPackageName);
		String packageDir = finalPackageName.replace(".", "/");
		System.out.println(packageDir);
		String fileName = rootDir + packageDir + "/" + finalClassName + ".java";
		return fileName;
	}

}
