package layers;

import codegenerator.skeleton.Skeleton;
import codegenerator.skeleton.layers.ServiceImplBuilder;

public class TestServiceImplBuilder {

	public static void main(String[] args) {

		String rootPackage = "com.naresh";
		String className = "Customer";
		Skeleton skeleton = new ServiceImplBuilder(rootPackage, className);
		skeleton.generateFile();
		System.out.println(skeleton);
	}

}
