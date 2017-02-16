package codegenerator.layers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import codegenerator.ProjectFileType;
import codegenerator.util.FileUtil;

public class ControllerBuilder extends CommonFileGenerator{


	public ControllerBuilder(String rootPackage, String className) {
		super(rootPackage,className);
	}

	public void createFile( ProjectFileType fileType) throws IOException{

		 com.squareup.javapoet.MethodSpec.Builder mb_1 = FileUtil.createMethodBuilderWithReturn("create", String.class , "add");
		 mb_1.addAnnotation(AnnotationSpec.builder(GetMapping.class).addMember("value", "\"/create\"").build());
		 MethodSpec method0 = mb_1.build();

		 com.squareup.javapoet.MethodSpec.Builder mb = FileUtil.createMethodBuilderWithReturn("save", String.class , "redirect:/");
		 mb.addAnnotation(AnnotationSpec.builder(PostMapping.class).addMember("value", "\"/save\"").build());
		 mb.addParameter(ParameterSpec.builder(Object.class, "obj").addAnnotation(RequestBody.class).build());
		 MethodSpec method1 = mb.build();

		 com.squareup.javapoet.MethodSpec.Builder mb2 = FileUtil.createMethodBuilderWithReturn("update", String.class , "redirect:/");
		 mb2.addAnnotation(AnnotationSpec.builder(PostMapping.class).addMember("value", "\"/update\"").build());
		 mb2.addParameter(ParameterSpec.builder(Object.class, "obj").addAnnotation(RequestBody.class).build());
		 MethodSpec method2 = mb2.build();

		 Map<Class,String> params = new HashMap<Class,String>();
			params.put(Long.class, "id");


			Map<Class,String> params2 = new HashMap<Class,String>();
			params2.put(ModelMap.class, "modelMap");
			params2.put(String.class, "session");

		 com.squareup.javapoet.MethodSpec.Builder mb3 = FileUtil.createMethodBuilderWithReturn("delete", String.class , "redirect:/");
		 mb3.addParameter(ParameterSpec.builder(Long.class, "id").addAnnotation(RequestParam.class).build());
		 mb3.addAnnotation(AnnotationSpec.builder(GetMapping.class).addMember("value", "\"/delete\"").build());
		 MethodSpec method3 = mb3.build();


		 com.squareup.javapoet.MethodSpec.Builder mb4 = FileUtil.createMethodBuilderWithReturn("index", String.class , "list.jsp" );
		 mb4.addAnnotation(AnnotationSpec.builder(GetMapping.class).addMember("value", "\"/list\"").build());
		 mb4.addParameter(ParameterSpec.builder(ModelMap.class, "modelMap").build());
		 mb4.addParameter(ParameterSpec.builder(String.class, "session").build());
		 MethodSpec method4 = mb4.build();

		 com.squareup.javapoet.MethodSpec.Builder mb5 = FileUtil.createMethodBuilderWithReturn("show", String.class, "edit.jsp");
		 mb5.addParameter(ParameterSpec.builder(Long.class, "id").addAnnotation(RequestParam.class).build());
		 mb5.addParameter(ParameterSpec.builder(ModelMap.class, "modelMap").build());
		 mb5.addAnnotation(AnnotationSpec.builder(GetMapping.class).addMember("value", "\"/show\"").build());
		 MethodSpec method5 = mb5.build();

		createJavaFile(fileType,rootPackage, className,method0, method1,method2,method3,method4,method5);
		System.out.println("File Creation Done");
	}


	@Override
	public void addMethodAnnotation(Builder builder) {

		builder.addAnnotation(RequestMapping.class);

	}



	@Override
	public void addClassAnnotation(Builder classBuilder) {
		classBuilder.addAnnotation(Controller.class);
		classBuilder.addAnnotation(
				AnnotationSpec.builder(RequestMapping.class)
			      .addMember("value", "\"/" + className.toLowerCase()+"s\"").build());
	}

}
