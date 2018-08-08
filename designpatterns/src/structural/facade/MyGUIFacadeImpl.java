package structural.facade;

public class MyGUIFacadeImpl implements MyGUIFacade {

    private Content content;
    private MenuBar menuBar;
    private TitleBar titleBar;

    public MyGUIFacadeImpl(){
        content = new Content();
        menuBar = new MenuBar();
        titleBar = new TitleBar();
    }

    @Override
    public void drawUI() {
        menuBar.addMenuBar();
        titleBar.addTitleBar();
        content.addButtons();
        content.addCheckBoxes();
        content.addTextFields();
    }
}