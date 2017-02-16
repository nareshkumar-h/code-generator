package codegenerator.layers;

import java.io.IOException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;

public class RESTControllerBuilder extends CommonFileGenerator {

	public RESTControllerBuilder(String rootPackage, String className) {
		super(rootPackage, className);
	}

	public void createFile(ProjectFileType fileType) throws IOException {

		MethodSpec method1 = RESTBuilder.createMethod("save", PostMapping.class).build();
		MethodSpec method2 = RESTBuilder.createMethodWithId("delete", DeleteMapping.class).build();
		MethodSpec method3 = RESTBuilder.createMethod("index", GetMapping.class).build();
		MethodSpec method4 = RESTBuilder.createMethodWithId("edit", GetMapping.class).build();
		MethodSpec method5 = RESTBuilder.createMethodWithId("update", PutMapping.class).build();
		createJavaFile(fileType, rootPackage, className, method1, method2, method3, method4, method5);
		System.out.println("File Creation Done");
	}


	@Override
	public void addMethodAnnotation(Builder builder) {

		builder.addAnnotation(RequestMapping.class);

	}

	@Override
	public void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(RestController.class);
		classBuilder.addAnnotation(AnnotationSpec.builder(RequestMapping.class)
				.addMember("value", "\"/api/" + className.toLowerCase() + "s\"").build());
	}

}
