package codegenerator.skeleton.layers;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.squareup.javapoet.AnnotationSpec;

import codegenerator.ProjectFileType;
import codegenerator.skeleton.ClassSkeleton;
import lombok.Data;

public class ModelBuilder extends ClassSkeleton{

	public ModelBuilder(String rootPackage,String className) {
		super(rootPackage,className, ProjectFileType.MODEL);
	}


	@Override
	protected void addClassAnnotation() {

		classBuilder.addAnnotation(Data.class);
		classBuilder.addAnnotation(Entity.class);
		classBuilder.addAnnotation(
				AnnotationSpec.builder(Table.class).addMember("name", "\"" + className.toLowerCase() + "s\"").build());

	}

	@Override
	protected void addMethodAnnotation() {

	}


}
