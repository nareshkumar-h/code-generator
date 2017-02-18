package codegenerator.skeleton.layers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.WildcardTypeName;

import codegenerator.ProjectFileType;
import codegenerator.skeleton.ClassSkeleton;
import codegenerator.util.FileUtil;

public class DAOImplBuilder extends ClassSkeleton{

	public DAOImplBuilder(String rootPackage,String className) {
		super(rootPackage,className, ProjectFileType.DAO_IMPL);
	}


	@Override
	protected void addClassAnnotation() {


		classBuilder.addAnnotation(Repository.class);
		classBuilder.addAnnotation(Transactional.class);
		FieldSpec fieldSpec = FieldSpec.builder(JdbcTemplate.class, "jdbcTemplate", Modifier.PRIVATE)
				.addAnnotation(Autowired.class)
				.build();
		classBuilder.addField(fieldSpec);


	}

	@Override
	protected void addMethodAnnotation() {


		Map<Class,String> params1 = new HashMap<Class,String>();
		params1.put(Object.class, "obj");


		Builder method1 = FileUtil.createMethodBuilder("save", params1);

		method1.addStatement("String sql = \"insert into ... ( columnnames )  values ( ? ) \"");
		method1.addStatement("$T [] params = { }" , Object.class);
		method1.addStatement("int rows = jdbcTemplate.update(sql, params)");
		method1.addStatement("System.out.println(\" No of rows inserted:\" + rows ) ");


		Builder method2 = FileUtil.createMethodBuilder("update" , params1);
		method2.addStatement("String sql = \"update ... set ... where id = ?\"");
		method2.addStatement("$T [] params = {  }" , Object.class);
		method2.addStatement("int rows = jdbcTemplate.update(sql, params)");
		method2.addStatement("System.out.println(\" No of rows updated:\" + rows ) ");

		Map<Class,String> params = new HashMap<Class,String>();
		params.put(Long.class, "id");

		Builder method3 = FileUtil.createMethodBuilder("delete", params);
		method3.addStatement("String sql = \"delete from ...\"");
		method3.addStatement("$T [] params = { id }" , Object.class);
		method3.addStatement("int rows = jdbcTemplate.update(sql, params)");
		method3.addStatement("System.out.println(\" No of rows deleted:\" + rows ) ");


		Builder method4 = FileUtil.createMethodBuilder("findAll", List.class);
		method4.addStatement("String sql = \"select * from ...\"");
		method4.addStatement("$T [] params = { }" , Object.class);
		method4.addStatement("List<?> list = jdbcTemplate.query(sql, params, (rs,rowno) -> { \n Object rowObj = null ; \n return rowObj; \n })");
		method4.addStatement("System.out.println(\" List :\" + list.size() ) ");
		method4.addStatement("return list");

		TypeName typeName= ParameterizedTypeName.get(ClassName.get(List.class), WildcardTypeName.subtypeOf(Object.class));
		method4.returns(typeName);

		Builder method5 = FileUtil.createMethodBuilder("findById", Object.class, params);

		method5.addStatement("String sql = \"\"");
		method5.addStatement("$T [] params = { id }" , Object.class);
		method5.addStatement("Object obj = jdbcTemplate.query(sql, params, (rs,rowno) -> { \n Object rowObj = null ; \n return rowObj; \n })");
		method5.addStatement("System.out.println(\" FindById :\" + obj ) ");
		method5.addStatement("return obj");


		addMethod( method1,method2, method3, method4, method5);

	}


}
