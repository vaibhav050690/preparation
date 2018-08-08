package behavioral.template;

import java.util.AbstractList;

public class Client {

    public static void main(String[] args) {
        CrossCompiler anroidComplier = new AnroidCompiler();
        CrossCompiler iphoneComplier = new IPhoneCompiler();
        anroidComplier.compile();
        iphoneComplier.compile();

    }
}