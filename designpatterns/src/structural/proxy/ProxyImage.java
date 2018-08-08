package structural.proxy;

public class ProxyImage implements Image {

    private String url;
    private RealImage realImage = null;

    public ProxyImage(String url){
        this.url = url;
    }


    @Override
    //this method delegates to the real image
    public void dispalyImage() {
        //here we can have access control checks as well.
        if(realImage != null){
            realImage = new RealImage(this.url);
            realImage.dispalyImage();
        }

    }
}