package codegenerator.layers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import codegenerator.util.FileUtil;

public class DAOBuilder extends CommonFileGenerator {


	public DAOBuilder(String rootPackage, String className) {
		super(rootPackage,className);

	}

	@Override
	public Builder createClass(ProjectFileType fileType, String packageName, String className,
			MethodSpec... methodSpecs) {

		String finalClassName = className + fileType.getClassSuffix();
		com.squareup.javapoet.TypeSpec.Builder classBuilder = TypeSpec.classBuilder(finalClassName);

		classBuilder.addModifiers(Modifier.PUBLIC);
		FieldSpec fieldSpec = FieldSpec.builder(JdbcTemplate.class, "jdbcTemplate", Modifier.PRIVATE)
				/*.addModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.FINAL)*/
				.addAnnotation(Autowired.class)
				.build();
		classBuilder.addField(fieldSpec);


		return classBuilder;


	}

	public void createFile( String className, ProjectFileType fileType) throws IOException{

		MethodSpec method1 = FileUtil.createMethod("save");
		MethodSpec method2 = FileUtil.createMethod("update");

		Map<Class,String> params = new HashMap<Class,String>();
		params.put(Long.class, "id");

		MethodSpec method3 = FileUtil.createMethod("delete", params);
		MethodSpec method4 = FileUtil.createMethod("findAll", List.class);



		MethodSpec method5 = FileUtil.createMethod("findById", Object.class, params);
		createJavaFile(fileType,rootPackage, className,method1,method2,method3,method4,method5);
		System.out.println("File Creation Done");
	}

	@Override
	void addMethodAnnotation(Builder builder) {


	}

	@Override
	void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(Repository.class);
		classBuilder.addAnnotation(Transactional.class);


	}


}
