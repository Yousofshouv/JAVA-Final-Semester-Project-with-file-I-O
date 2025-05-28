public abstract class PersonBase {
    protected String name;
    protected String id;

    public PersonBase(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public abstract String displayInfo();
}
