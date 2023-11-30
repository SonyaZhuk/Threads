package concurrency.executors.forkjoinpool.folder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>> {
    private final String path;
    private final String extension;

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {

        //List to store the names of the files stored in the folder.
        List<String> list = new ArrayList<>();

        //FolderProcessor tasks to store the subtasks that are going to process the subfolders stored in the folder
        List<FolderProcessor> tasks = new ArrayList<>();

        File file = new File(path);

        File content[] = file.listFiles();
        //For each element in the folder, if there is a subfolder, create a new FolderProcessor object
        //and execute it asynchronously using the fork() method.
        if (content != null) {
            for (int i = 0; i < content.length; i++) {
                if (content[i].isDirectory()) {
                    FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
                    task.fork();
                    tasks.add(task);
                }
                //Otherwise, compare the extension of the file with the extension you are looking for using
                // the checkFile() method and, if they are equal, store the full path of the file in the list
                // of strings declared earlier.
                else {
                    if (checkFile(content[i].getName())) {
                        list.add(content[i].getAbsolutePath());
                    }
                }
            }
        }

        addResultsFromTasks(list, tasks);
        return list;
    }

    //For each task stored in the list of tasks, call the join() method that
    // will wait for its finalization and then will return the result of the task.
    //Add that result to the list of strings using the addAll() method.
    private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
        for (FolderProcessor item : tasks) {
            list.addAll(item.join());
        }
    }
    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }
}
