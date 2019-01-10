package chapter8.understandingFilesDirectories;

import java.io.File;

public class FilesDirectoriesCommon {

    public static void main(String[] args) {
        File file = new File("D:\\install\\5291.saz");
        System.out.println("File:");
        System.out.println("exists():" + file.exists());
        System.out.println("getName()" + file.getName());
        System.out.println("getAbsolutePath()" + file.getAbsolutePath());
        System.out.println("isFile()" + file.isFile());
        System.out.println("isDirectory()" + file.isDirectory());
        System.out.println("length()"+file.length());
        System.out.println("lastModified()"+file.lastModified());

        System.out.println("delete() - IN A SEPARATE METHOD TRY TO DELETE NOT EMPTY FOLDER. DELETE METHOD. IF A DIRECTORY - MUST BE EMPTY");
        System.out.println("renameTo() - renaming in a separate method");


        System.out.println("---");

        File directory = new File("D:\\install\\5291.saz");

    }

    private static void creatingFileExample() {
        File file = new File("D:\\install\\5291.saz");
        File file1 = new File("D:\\123.txt");
        File file2 = new File("D:\\321.txt");
        System.out.println(file.exists());
        System.out.println(file1.exists());
        System.out.println(file2.exists());
    }

    private static void separatorPropertyFetchingExample() {
        System.out.println(System.getProperty("file.separator"));
        System.out.println(File.separator);
    }
}
