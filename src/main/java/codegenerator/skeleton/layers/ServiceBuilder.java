package codegenerator.skeleton.layers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.squareup.javapoet.MethodSpec.Builder;

import codegenerator.ProjectFileType;
import codegenerator.skeleton.ClassSkeleton;
import codegenerator.util.FileUtil;

public class ServiceBuilder extends ClassSkeleton{

	public ServiceBuilder(String rootPackage,String className) {
		super(rootPackage,className, ProjectFileType.SERVICE);
	}

	@Override
	public void createClass() {

	}

	@Override
	public void createMethod() {

	}

	@Override
	protected void addClassAnnotation() {

		classBuilder.addAnnotation(Service.class);

	}

	@Override
	protected void addMethodAnnotation() {

		Builder method1 = FileUtil.createMethodBuilder("save");

		Builder method2 = FileUtil.createMethodBuilder("update");

		Map<Class,String> params = new HashMap<Class,String>();
		params.put(Long.class, "id");

		Builder method3 = FileUtil.createMethodBuilder("delete", params);
		Builder method4 = FileUtil.createMethodBuilder("findAll", List.class);



		Builder method5 = FileUtil.createMethodBuilder("findById", Object.class, params);

		addMethod( method1,method2, method3, method4, method5);
	}


}
