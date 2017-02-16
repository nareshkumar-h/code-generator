package codegenerator.layers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.WildcardTypeName;

import codegenerator.util.FileUtil;

public class RESTBuilder {

	public static Builder createMethod(String methodName, Class clazz) {
		com.squareup.javapoet.MethodSpec.Builder mb2 = FileUtil.createMethodBuilderWithRESTReturn(methodName,
				ResponseEntity.class);
		mb2.addComment(" todo");
		FileUtil.addReturnType(mb2, ResponseEntity.class);
		mb2.addAnnotation(clazz);
		ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(ClassName.get(ResponseEntity.class),
				WildcardTypeName.subtypeOf(Object.class));
		mb2.returns(parameterizedTypeName);

		return mb2;
	}

	public static Builder createMethodWithId(String methodName, Class clazz) {
		com.squareup.javapoet.MethodSpec.Builder mb2 = FileUtil.createMethodBuilderWithRESTReturn(methodName,
				ResponseEntity.class);
		mb2.addAnnotation(AnnotationUtil.getMethodAnnotation(clazz, "{id}"));

		mb2.addParameter(AnnotationUtil.getParameterAnnotation(PathVariable.class, Integer.class, "id"));

		addTryCatch(mb2);

		FileUtil.addReturnType(mb2, ResponseEntity.class);

		ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(ClassName.get(ResponseEntity.class),
				WildcardTypeName.subtypeOf(Object.class));

		mb2.returns(parameterizedTypeName);
		return mb2;
	}

	public static Builder addTryCatch(Builder mb) {

		mb.addCode(CodeBlock.of("try{ \n"));
		mb.addComment(" todo");
		mb.addCode(CodeBlock.of("} catch(Exception e) { "));
		mb.addStatement("e.printStackTrace()");

		FileUtil.addErrorReturnType(mb, ResponseEntity.class);
		mb.addCode(CodeBlock.of("}"));
		return mb;
	}
}
