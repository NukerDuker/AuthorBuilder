public class Author {

    @BuilderField
    private String firstName;

    @BuilderField
    private String lastName;

    @BuilderField
    private String biography;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}