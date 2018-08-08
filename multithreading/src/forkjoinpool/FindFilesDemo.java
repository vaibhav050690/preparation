package forkjoinpool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SearchFileTask extends RecursiveTask<List<String>>{

    private String path;

    public SearchFileTask(String path){
        this.path = path;
    }

    @Override
    protected List<String> compute() {
        List<String> result = new ArrayList<>();
        List<SearchFileTask> tasks = new ArrayList<>();
        File file = new File(path);
        File[] files = file.listFiles();
        if(files.length == 0){
            return result;
        }
        for(int i =0; i<files.length; i++){
            if(files[i].isDirectory()){
                SearchFileTask searchFileTask = new SearchFileTask(files[i].getAbsolutePath());
                searchFileTask.fork();
                tasks.add(searchFileTask);
            }
            else {
                if(files[i].getName().endsWith(".png") || files[i].getName().endsWith(".jpeg") || files[i].getName().endsWith(".jpg")){
                    result.add(files[i].toString());
                }
            }
        }
        for(SearchFileTask searchFileTask : tasks){
            result.addAll(searchFileTask.join());
        }
        return result;
    }
}

public class FindFilesDemo {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SearchFileTask downloads = new SearchFileTask("/home/vaibhavmishra/Downloads");
        SearchFileTask pictures = new SearchFileTask("/home/vaibhavmishra/Pictures");
        forkJoinPool.submit(downloads);
        forkJoinPool.submit(pictures);
        /*do {
            System.out.println("Paralellism:"+forkJoinPool.getParallelism());
            System.out.println("Active Threads:"+forkJoinPool.getActiveThreadCount());
            System.out.println("Task count:"+forkJoinPool.getQueuedTaskCount());
            System.out.println("Steal count:"+forkJoinPool.getStealCount());
            System.out.println();
            try {
                Thread.sleep(1l);
            } catch (InterruptedException e) {

            }
        }
        while (!downloads.isDone() || !pictures.isDone());*/
        List<String> results = new ArrayList<>();
        results.addAll(downloads.join());
        results.addAll(pictures.join());
        System.out.println(results);
        System.out.println(results.size());


    }
}