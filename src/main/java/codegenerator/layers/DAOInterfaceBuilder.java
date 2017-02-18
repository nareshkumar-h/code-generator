package codegenerator.layers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import codegenerator.util.InterfaceFileUtil;

public class DAOInterfaceBuilder extends CommonFileGenerator {

	public DAOInterfaceBuilder(String rootPackage, String className) {
		super(rootPackage,className);

	}




	public void createFile( ProjectFileType fileType) throws IOException{

		MethodSpec method1 = InterfaceFileUtil.declareMethod("save");
		MethodSpec method2 = InterfaceFileUtil.declareMethod("update");

		Map<Class,String> params = new HashMap<Class,String>();
		params.put(Long.class, "id");

		MethodSpec method3 = InterfaceFileUtil.declareMethod("delete", params);
		MethodSpec method4 = InterfaceFileUtil.declareMethod("findAll", List.class);



		MethodSpec method5 = InterfaceFileUtil.declareMethod("findById", params,  Object.class);
		createInterfaceJavaFile(fileType,rootPackage, className,method1,method2,method3,method4,method5);
		System.out.println("File Creation Done");
	}

	@Override
	public  void addMethodAnnotation(Builder builder) {


	}

	@Override
	public  void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(Repository.class);
	}


}
