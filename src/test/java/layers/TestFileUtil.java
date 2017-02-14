package layers;

import java.io.IOException;

import codegenerator.builder.LayerBuilder;



public class TestFileUtil {

	public static void main(String[] args) throws IOException {

		String rootPackage = "com.naresh";

		LayerBuilder layerObj = new LayerBuilder(rootPackage, "User");

		layerObj.cleanRootPackage();
		layerObj.createClass( "User");
		//layerObj.createClass("Employee");
		//layerObj.createClass( "Ticket");
	}

}
