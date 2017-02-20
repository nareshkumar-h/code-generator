package layers;

import codegenerator.skeleton.Skeleton;
import codegenerator.skeleton.layers.ServiceBuilder;

public class TestServiceBuilder {

	public static void main(String[] args) {

		String rootPackage = "com.naresh";
		String className = "Customer";
		Skeleton skeleton = new ServiceBuilder(rootPackage, className);
		skeleton.generateFile();
		System.out.println(skeleton);
	}

}
