package codegenerator.layers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import codegenerator.util.FileUtil;

public class RESTControllerBuilder extends CommonFileGenerator {

	public RESTControllerBuilder(String rootPackage, String className) {
		super(rootPackage, className);
	}

	public void createFile(String className, ProjectFileType fileType) throws IOException {

		com.squareup.javapoet.MethodSpec.Builder mb = FileUtil.createMethodBuilder("save", String.class, null);
		mb.addAnnotation(PostMapping.class);
		MethodSpec method1 = mb.build();

		com.squareup.javapoet.MethodSpec.Builder mb2 = FileUtil.createMethodBuilder("update", String.class, null);
		mb2.addAnnotation(AnnotationSpec.builder(PutMapping.class).addMember("value", "\"/{id}\"").build());
		mb2.addParameter(ParameterSpec.builder(Long.class, "id").addAnnotation(PathVariable.class).build());
		MethodSpec method2 = mb2.build();

		Map<Class, String> params = new HashMap<Class, String>();
		params.put(Long.class, "id");

		com.squareup.javapoet.MethodSpec.Builder mb3 = FileUtil.createMethodBuilder("delete", String.class, null);
		mb3.addAnnotation(AnnotationSpec.builder(DeleteMapping.class).addMember("value", "\"/{id}\"").build());
		mb3.addParameter(ParameterSpec.builder(Long.class, "id").addAnnotation(PathVariable.class).build());
		MethodSpec method3 = mb3.build();

		com.squareup.javapoet.MethodSpec.Builder mb4 = FileUtil.createMethodBuilder("findAll", List.class, null);
		mb4.addAnnotation(GetMapping.class);
		MethodSpec method4 = mb4.build();

		com.squareup.javapoet.MethodSpec.Builder mb5 = FileUtil.createMethodBuilder("findById", Object.class, null);
		mb5.addAnnotation(AnnotationSpec.builder(GetMapping.class).addMember("value", "\"/{id}\"").build());
		mb5.addParameter(ParameterSpec.builder(Long.class, "id").addAnnotation(PathVariable.class).build());
		MethodSpec method5 = mb5.build();

		createJavaFile(fileType, rootPackage, className, method1, method2, method3, method4, method5);
		System.out.println("File Creation Done");
	}

	@Override
	void addMethodAnnotation(Builder builder) {

		builder.addAnnotation(RequestMapping.class);

	}

	@Override
	void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(RestController.class);
		classBuilder.addAnnotation(AnnotationSpec.builder(RequestMapping.class)
				.addMember("value", "\"/" + className.toLowerCase() + "s\"").build());
	}

}
