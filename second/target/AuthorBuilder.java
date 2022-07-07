public class AuthorBuilder {

    private Author instance = new Author();

    public Author build() {
        return instance;
    }

    public AuthorBuilder setFirstName(java.lang.String value) {
        instance.setFirstName(value);
        return this;
    }

    public AuthorBuilder setLastName(java.lang.String value) {
        instance.setLastName(value);
        return this;
    }

    public AuthorBuilder setBiography(java.lang.String value) {
        instance.setBiography(value);
        return this;
    }

}
