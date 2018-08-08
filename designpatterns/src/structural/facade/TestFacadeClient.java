package structural.facade;

public class TestFacadeClient {

    public static void main(String[] args) {
        MyGUIFacade myGUIFacade = new MyGUIFacadeImpl();
        myGUIFacade.drawUI();
    }
}