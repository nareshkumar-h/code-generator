package codegenerator.skeleton.layers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.WildcardTypeName;

import codegenerator.ProjectFileType;
import codegenerator.skeleton.ClassSkeleton;
import codegenerator.util.FileUtil;

public class ServiceImplBuilder extends ClassSkeleton{

	public ServiceImplBuilder(String rootPackage,String className) {
		super(rootPackage,className, ProjectFileType.SERVICE_IMPL);
	}


	@Override
	protected void addClassAnnotation() {


		classBuilder.addAnnotation(Service.class);
		FieldSpec fieldSpec = FieldSpec.builder(Object.class, "dao", Modifier.PRIVATE)
				.addAnnotation(Autowired.class)
				.build();
		classBuilder.addField(fieldSpec);


	}

	@Override
	protected void addMethodAnnotation() {


		Map<Class,String> params1 = new HashMap<Class,String>();
		params1.put(Object.class, "obj");


		Builder method1 = FileUtil.createMethodBuilder("save", params1);
		method1.addStatement("dao.save(obj)");


		Builder method2 = FileUtil.createMethodBuilder("update" , params1);
		method2.addStatement("dao.update(obj)");

		Map<Class,String> params = new HashMap<Class,String>();
		params.put(Long.class, "id");

		Builder method3 = FileUtil.createMethodBuilder("delete", params);
		method3.addStatement("dao.delete(id)");


		Builder method4 = FileUtil.createMethodBuilder("findAll", List.class);
		method4.addStatement("return dao.findAll()");

		TypeName typeName= ParameterizedTypeName.get(ClassName.get(List.class), WildcardTypeName.subtypeOf(Object.class));
		method4.returns(typeName);

		Builder method5 = FileUtil.createMethodBuilder("findById", Object.class, params);
		method5.addStatement("return dao.findById(id)");

		addMethod( method1,method2, method3, method4, method5);

	}


}
