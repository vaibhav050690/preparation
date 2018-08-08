package structural.proxy;

public class RealImage implements Image {
    @Override
    public void dispalyImage() {
        System.out.println("Displaying Image");
    }

    public RealImage(String url)   {
        //load up the image
        loadImage(url);
    }

    //a method that only the real image has
    private void loadImage(String url)  {
        //expensive call to do resource intensive operation to load image
    }
}