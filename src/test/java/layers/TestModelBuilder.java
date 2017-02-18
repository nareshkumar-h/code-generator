package layers;

import codegenerator.skeleton.Skeleton;
import codegenerator.skeleton.layers.ModelBuilder;

public class TestModelBuilder {

	public static void main(String[] args) {

		String rootPackage = "com.naresh";
		String className = "Customer";
		Skeleton skeleton = new ModelBuilder(rootPackage, className);
		skeleton.generateFile();
		System.out.println(skeleton);
	}

}
