package creational.builderpattern.imutability;

public class TestClient {

    public static void main(String[] args) {
    /*ImmutableUser user = new ImmutableUser.ImmutableUserBuilder("vaibhav123", "1234")
        .setEmail("abc@gmail.com").setFirstname("vaibhav").setLastname("mishra").build();
    System.out.println(user);*/
        ImmutableUser.ImmutableUserBuilder builder = new ImmutableUser.ImmutableUserBuilder("abc","abc");
        builder = builder.setEmail("abc@gmail.com").setFirstname("a").setLastname("b");
        ImmutableUser user1 = builder.build();
        System.out.println(user1);
    }
}