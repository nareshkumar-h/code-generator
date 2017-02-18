package layers;

import codegenerator.skeleton.Skeleton;
import codegenerator.skeleton.layers.DAOBuilder;

public class TestDAOBuilder {

	public static void main(String[] args) {

		String rootPackage = "com.naresh";
		String className = "Customer";
		Skeleton skeleton = new DAOBuilder(rootPackage, className);
		skeleton.generateFile();
		System.out.println(skeleton);
	}

}
