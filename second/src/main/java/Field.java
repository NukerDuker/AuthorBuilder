public class Field {

    private String name;

    private String argumentType;

    private String className;

    public Field(String name, String argumentType, String className) {
        this.name = name;
        this.argumentType = argumentType;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public String getArgumentType() {
        return argumentType;
    }

    public String getClassName() {
        return className;
    }
}
