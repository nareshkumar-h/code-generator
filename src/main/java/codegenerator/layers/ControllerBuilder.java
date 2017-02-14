package codegenerator.layers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import codegenerator.util.FileUtil;

public class ControllerBuilder extends CommonFileGenerator{


	public ControllerBuilder(String rootPackage, String className) {
		super(rootPackage,className);
	}

	public void createFile( String className, ProjectFileType fileType) throws IOException{

		 com.squareup.javapoet.MethodSpec.Builder mb = FileUtil.createMethodBuilder("save", String.class , null);
		 mb.addAnnotation(PostMapping.class);
		 MethodSpec method1 = mb.build();

		 com.squareup.javapoet.MethodSpec.Builder mb2 = FileUtil.createMethodBuilder("update", String.class , null);
		 mb2.addAnnotation(PostMapping.class);
		 MethodSpec method2 = mb2.build();

		 Map<Class,String> params = new HashMap<Class,String>();
			params.put(Long.class, "id");

		 com.squareup.javapoet.MethodSpec.Builder mb3 = FileUtil.createMethodBuilder("delete", String.class , params);
		 mb3.addAnnotation(GetMapping.class);
		 MethodSpec method3 = mb3.build();


		 com.squareup.javapoet.MethodSpec.Builder mb4 = FileUtil.createMethodBuilder("findAll", List.class , null );
		 mb4.addAnnotation(GetMapping.class);
		 MethodSpec method4 = mb4.build();

		 com.squareup.javapoet.MethodSpec.Builder mb5 = FileUtil.createMethodBuilder("findById", Object.class, params);
		 mb5.addAnnotation(GetMapping.class);
		 MethodSpec method5 = mb5.build();

		createJavaFile(fileType,rootPackage, className,method1,method2,method3,method4,method5);
		System.out.println("File Creation Done");
	}


	@Override
	void addMethodAnnotation(Builder builder) {

		builder.addAnnotation(RequestMapping.class);

	}



	@Override
	void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(Controller.class);
		classBuilder.addAnnotation(
				AnnotationSpec.builder(RequestMapping.class)
			      .addMember("value", "\"/" + className.toLowerCase()+"s\"").build());
	}

}
