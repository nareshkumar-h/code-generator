package codegenerator.builder;

import java.io.File;
import java.io.IOException;

import codegenerator.ProjectFileType;
import codegenerator.layers.ControllerBuilder;
import codegenerator.layers.DAOBuilder;
import codegenerator.layers.DAOInterfaceBuilder;
import codegenerator.layers.DTOBuilder;
import codegenerator.layers.ModelBuilder;
import codegenerator.layers.RESTControllerBuilder;
import codegenerator.layers.RepositoryImplBuilder;
import codegenerator.layers.RepositoryInterfaceBuilder;
import codegenerator.layers.ServiceBuilder;
import codegenerator.layers.ValidatorBuilder;
import codegenerator.util.FileUtil;
import codegenerator.util.IOUtil;

public class LayerBuilder {


	private String rootPackage;
	private String testPackage;

	public LayerBuilder(String rootPackage, String className) {
		this.rootPackage = rootPackage;

	}


	public  void createClass( String className) throws IOException{

		DAOBuilder daoB = new DAOBuilder(rootPackage,className);
		ControllerBuilder controllerB = new ControllerBuilder(rootPackage, className);
		RESTControllerBuilder restControllerB = new RESTControllerBuilder(rootPackage, className);

		DAOInterfaceBuilder daoInterfaceB = new DAOInterfaceBuilder(rootPackage, className);
		ValidatorBuilder validatorB = new ValidatorBuilder(rootPackage, className);
		ModelBuilder modelB = new ModelBuilder(rootPackage, className);
		DTOBuilder dtoB = new DTOBuilder(rootPackage, className);
		ServiceBuilder serviceB = new ServiceBuilder(rootPackage, className);
		RepositoryInterfaceBuilder repoB = new RepositoryInterfaceBuilder(rootPackage, className);
		RepositoryImplBuilder repoImplB = new RepositoryImplBuilder(rootPackage, className);

		modelB.createFile(ProjectFileType.MODEL);
		serviceB.createFile( ProjectFileType.SERVICE);
		validatorB.createFile( ProjectFileType.VALIDATOR);
		controllerB.createFile(ProjectFileType.CONTROLLER);

		daoInterfaceB.createFile( ProjectFileType.DAO);
		daoB.createFile(  ProjectFileType.DAO_IMPL);
		dtoB.createFile( ProjectFileType.DTO);
		repoB.createFile(ProjectFileType.REPOSITORY);

		repoImplB.createFile(ProjectFileType.REPOSITORY_IMPL);

		restControllerB.createFile(ProjectFileType.RESTCONTROLLER);

/*
		//Test Classes
		TestModelBuilder mb= new TestModelBuilder(rootPackage,className);
		mb.createFile(className, ProjectFileType.TEST_MODEL);*/

	}

	public void cleanRootPackage() {


		try {

			String dirName = FileUtil.rootDir + rootPackage.replace(".", "/");
			File f = new File(dirName);
			IOUtil.delete(f);
			System.out.println("Folder :" + dirName +" deleted.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
