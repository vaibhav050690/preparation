public class FinalizeTest {

    public static final String s=  "test";

    protected void finalize() throws Throwable{
        if(true)
            throw new Exception("exc");
        super.finalize();
    }

    public static void main(String[] args) {
        FinalizeTest ft = new FinalizeTest();
        ft = null;
        System.runFinalizersOnExit(true);
    }
}