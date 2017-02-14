package codegenerator.layers;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import lombok.Data;

public class ModelBuilder extends CommonFileGenerator {

	public ModelBuilder(String rootPackage, String className) {
		super(rootPackage, className);

	}

	public void createFile(String className, ProjectFileType fileType) throws IOException {

		createJavaFile(fileType, rootPackage, className);
		System.out.println("File Creation Done");
	}

	@Override
	void addMethodAnnotation(Builder builder) {

	}

	@Override
	void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(Data.class);
		classBuilder.addAnnotation(Entity.class);
		classBuilder.addAnnotation(
				AnnotationSpec.builder(Table.class).addMember("name", "\"" + className.toLowerCase() + "s\"").build());
	}

}
