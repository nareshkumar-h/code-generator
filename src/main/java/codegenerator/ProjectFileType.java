package codegenerator;

public enum ProjectFileType {

	MODEL("model", ""),CONTROLLER("controller", "Controller"),
	RESTCONTROLLER("controller.rest", "Controller"),
	VALIDATOR("validator", "Validator"),
	DAO("dao", "DAO"),
	DAO_IMPL("dao.impl", "Impl"),SERVICE("service", "Service"),DTO("dto", "DTO"),
	REPOSITORY("dao", "Repository"),
	REPOSITORY_IMPL("dao.impl", "RepositoryImpl"),
	TEST_MODEL("model", "");

	private String packageName;
	private String classSuffix;

	private ProjectFileType(String packageName, String classSuffix){
		this.packageName = packageName;
		this.classSuffix = classSuffix;
	}

	public String getPackageName(){
		return this.packageName;
	}

	public String getClassSuffix(){
		return this.classSuffix;
	}
}
