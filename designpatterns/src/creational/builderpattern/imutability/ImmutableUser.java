package creational.builderpattern.imutability;

import java.util.Date;

//refer this:- https://dzone.com/articles/immutability-with-builder-design-pattern

public final class ImmutableUser {
    private final String username;
    private final String password;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final Date creationDate;

    @Override
    public String toString() {
        return "ImmutableUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    private ImmutableUser(ImmutableUserBuilder builder){
        username = builder.username;
        password = builder.password;
        firstname = builder.firstname;
        lastname = builder.lastname;
        email = builder.email;
        creationDate = builder.creationDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreationDate() {
        return new Date(creationDate.getTime());
    }


    public static class ImmutableUserBuilder {
        private final String username;
        private final String password;

        private String firstname;
        private String lastname;
        private String email;
        private Date creationDate;

        public ImmutableUserBuilder(String username, String password) {
            this.username = username;
            this.password = password;
            this.creationDate = new Date();
        }

        public ImmutableUser build(){
            return new ImmutableUser(this);
        }

        public ImmutableUserBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public ImmutableUserBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public ImmutableUserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }
    }
}

