package codegenerator;

public enum ProjectFileType {

	MODEL("model", ""),CONTROLLER("controller", "Controller"),
	RESTCONTROLLER("controller.rest", "Controller"),
	VALIDATOR("validator", "Validator"),
	DAO("dao", "DAO", "interface"),
	DAO_IMPL("dao.impl", "Impl"),SERVICE("service", "Service", "interface"),
	SERVICE_IMPL("service.impl", "ServiceImpl"),DTO("dto", "DTO"),
	REPOSITORY("dao", "Repository","interface"),
	REPOSITORY_IMPL("dao.impl", "RepositoryImpl"),
	TEST_MODEL("model", "");

	private String packageName;
	private String classSuffix;
	private String type ;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private ProjectFileType(String packageName, String classSuffix){
		this.packageName = packageName;
		this.classSuffix = classSuffix;
		this.type = "class";
	}

	private ProjectFileType(String packageName, String classSuffix, String type){
		this.packageName = packageName;
		this.classSuffix = classSuffix;
		this.type=type;
	}

	public String getPackageName(){
		return this.packageName;
	}

	public String getClassSuffix(){
		return this.classSuffix;
	}
}
