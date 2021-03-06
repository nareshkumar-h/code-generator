package codegenerator.layers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import codegenerator.util.FileUtil;

public class RepositoryInterfaceBuilder extends CommonFileGenerator {

	public RepositoryInterfaceBuilder(String rootPackage, String className) {
		super(rootPackage,className);

	}


	@Override
	public Builder createClass(ProjectFileType fileType, String packageName, String className,
			MethodSpec... methodSpecs) {

		String finalClassName = className + fileType.getClassSuffix();
		com.squareup.javapoet.TypeSpec.Builder classBuilder = TypeSpec.interfaceBuilder(finalClassName);

		classBuilder.addModifiers(Modifier.PUBLIC);
		classBuilder.addSuperinterface(JpaRepository.class);
		// classBuilder.addField(type, name, modifiers)
		for (MethodSpec method : methodSpecs) {

			//classBuilder.addMethod(method);
		}

		return classBuilder;


	}


	public void createFile( ProjectFileType fileType) throws IOException{

		MethodSpec method1 = FileUtil.createMethod("save");
		MethodSpec method2 = FileUtil.createMethod("update");

		Map<Class,String> params = new HashMap<Class,String>();
		params.put(Long.class, "id");

		MethodSpec method3 = FileUtil.createMethod("delete", params);
		MethodSpec method4 = FileUtil.createMethod("findAll", List.class);



		MethodSpec method5 = FileUtil.createMethod("findById", Object.class, params);
		createJavaFile(fileType,rootPackage, className);
		System.out.println("File Creation Done");
	}

	@Override
	public void addMethodAnnotation(Builder builder) {


	}

	@Override
	public void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(Repository.class);
	}


}
