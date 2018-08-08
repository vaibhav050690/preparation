package structural.composite;


import java.util.ArrayList;
import java.util.List;

//Component base class
abstract class FileComponent {

    private String name;
    private long created;
    private long lastUpdated;
    private long lastAccessed;
    private Directory parent;

    public FileComponent(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        created = System.currentTimeMillis();
        lastUpdated = System.currentTimeMillis();
        lastAccessed = System.currentTimeMillis();
    }

    public abstract void printName();

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public long getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(long lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }
}


//Leaf Node
class File extends FileComponent{

    private String contents;
    private int size;

    public File(String name, Directory parent, int size){
        super(name,parent);
        this.size = size;
    }

    //getters and setters
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public void printName() {
        System.out.println("-----"+this.getName());
    }
}

class Directory extends FileComponent{

    private List<FileComponent> contents;

    public Directory(String name, Directory parent){
        super(name,parent);
        contents = new ArrayList<FileComponent>();
    }

    public void add(FileComponent component){
        contents.add(component);
    }

    public void delete(FileComponent component){
        contents.remove(component);
    }

    public int numberOfFiles()
    {
        int count = 0;
        for (FileComponent c : contents)
        {
            if (c instanceof Directory)
            {
                count++; // Directory counts as a file
                Directory d = (Directory) c;
                count += d. numberOfFiles();
            }
            else if (c instanceof File)
                count++;
        }
        return count;
    }


    @Override
    public void printName() {
        System.out.println(this.getName());
        if(!contents.isEmpty()){
            for(FileComponent component : contents){
                component.printName();
            }
        }
    }
}


public class CompositeClient{

    public static void main(String[] args) {
        Directory root = new Directory("/",null);
        Directory home = new Directory("home",root);
        Directory opt = new Directory("opt",root);

        Directory documents = new Directory("documents",root);
        File java = new File("java",documents,100);
        File c = new File("c",documents,100);
        documents.add(java);
        documents.add(c);

        Directory downloads = new Directory("downloads",root);
        Directory movies = new Directory("movies",downloads);
        File got = new File("game of thrones",downloads,1024);
        File bbt = new File("big bang theory",downloads,1024);
        downloads.add(movies);
        movies.add(got);
        movies.add(bbt);

        root.add(home);
        root.add(opt);
        root.add(documents);
        root.add(downloads);

        root.printName();


    }

}

