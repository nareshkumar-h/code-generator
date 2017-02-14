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

		modelB.createFile(className , ProjectFileType.MODEL);
		serviceB.createFile( className, ProjectFileType.SERVICE);
		validatorB.createFile( className, ProjectFileType.VALIDATOR);
		controllerB.createFile(className, ProjectFileType.CONTROLLER);

		daoInterfaceB.createFile( className, ProjectFileType.DAO);
		daoB.createFile( className, ProjectFileType.DAO_IMPL);
		dtoB.createFile( className, ProjectFileType.DTO);
		repoB.createFile(className, ProjectFileType.REPOSITORY);

		repoImplB.createFile(className, ProjectFileType.REPOSITORY_IMPL);

		restControllerB.createFile(className, ProjectFileType.RESTCONTROLLER);


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
