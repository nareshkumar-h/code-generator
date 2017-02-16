package codegenerator.layers;

import java.io.IOException;

import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import lombok.Data;

public class DTOBuilder extends CommonFileGenerator {


	public DTOBuilder(String rootPackage, String className) {
		super(rootPackage,className);

	}

	public void createFile( ProjectFileType fileType) throws IOException{


		createJavaFile(fileType,rootPackage, className);
		System.out.println("File Creation Done");
	}

	@Override
	public void addMethodAnnotation(Builder builder) {


	}

	@Override
	public void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(Data.class);
	}


}
