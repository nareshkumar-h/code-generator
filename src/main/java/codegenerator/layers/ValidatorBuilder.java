package codegenerator.layers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import codegenerator.util.FileUtil;

public class ValidatorBuilder extends CommonFileGenerator {


	public ValidatorBuilder(String rootPackage, String className) {
		super(rootPackage,className);

	}

	public void createFile( ProjectFileType fileType) throws IOException{

		MethodSpec method1 = FileUtil.createMethod("beforeSave");
		MethodSpec method2 = FileUtil.createMethod("beforeUpdate");

		Map<Class,String> params = new HashMap<Class,String>();
		params.put(Long.class, "id");

		MethodSpec method3 = FileUtil.createMethod("beforeDelete", params);
		//MethodSpec method4 = FileUtil.createMethod("befofindAll", List.class);



		MethodSpec method5 = FileUtil.createMethod("beforeFindById", Object.class, params);
		createJavaFile(fileType,rootPackage, className,method1,method2,method3,method5);
		System.out.println("File Creation Done");
	}

	@Override
	public void addMethodAnnotation(Builder builder) {


	}

	@Override
	public void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(Component.class);
	}


}
