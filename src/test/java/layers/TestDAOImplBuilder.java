package layers;

import codegenerator.skeleton.Skeleton;
import codegenerator.skeleton.layers.DAOImplBuilder;

public class TestDAOImplBuilder {

	public static void main(String[] args) {

		String rootPackage = "com.naresh";
		String className = "Customer";
		Skeleton skeleton = new DAOImplBuilder(rootPackage, className);
		skeleton.generateFile();
	}

}
