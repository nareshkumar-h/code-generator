package codegenerator.layers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import codegenerator.util.FileUtil;

public class ServiceBuilder extends CommonFileGenerator{

	public ServiceBuilder(String rootPackage, String className) {
		super(rootPackage,className);

	}


	public void createFile( ProjectFileType fileType) throws IOException{

		MethodSpec method1 = FileUtil.createMethod("save");
		MethodSpec method2 = FileUtil.createMethod("update");

		Map<Class,String> params = new HashMap<Class,String>();
		params.put(Long.class, "id");

		MethodSpec method3 = FileUtil.createMethod("delete", params);
		MethodSpec method4 = FileUtil.createMethod("findAll", List.class);

		MethodSpec method5 = FileUtil.createMethod("findById", Object.class, params);

		//createClassFileWithContent(fileType, rootPackage, className, methodSpecs);
		createInterfaceJavaFile(fileType,rootPackage, className,method1,method2,method3,method4,method5);
		System.out.println("File Creation Done");
	}


	@Override
	public void addMethodAnnotation(Builder builder) {


	}

	@Override
	public void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(Service.class);
	}


}
