package layers;

import java.io.IOException;

import codegenerator.ProjectFileType;
import codegenerator.layers.DAOInterfaceBuilder;

public class TestInterface {

	static String rootPackage = "com.naresh";

	public static void main(String[] args) throws IOException {

		String className = "User";
		DAOInterfaceBuilder daoInterfaceB = new DAOInterfaceBuilder(rootPackage, className);
		daoInterfaceB.createFile(ProjectFileType.DAO);
	}

}
