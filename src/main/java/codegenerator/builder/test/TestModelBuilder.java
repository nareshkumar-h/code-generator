package codegenerator.builder.test;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.Test;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import codegenerator.layers.CommonFileGenerator;
import lombok.Data;

public class TestModelBuilder extends CommonFileGenerator {

	public TestModelBuilder(String rootPackage, String className) {
		super(rootPackage, className);

	}

	public void createFile(String className, ProjectFileType fileType) throws IOException {

		createJavaFile(fileType, rootPackage, className);
		System.out.println("File Creation Done");
	}

	@Override
	public void addMethodAnnotation(Builder builder) {

		builder.addAnnotation(Test.class);
	}

	@Override
	public void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(Data.class);
		classBuilder.addAnnotation(Entity.class);
		classBuilder.addAnnotation(
				AnnotationSpec.builder(Table.class).addMember("name", "\"" + className.toLowerCase() + "s\"").build());
	}

}
