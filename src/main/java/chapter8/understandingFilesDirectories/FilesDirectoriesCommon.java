package chapter8.understandingFilesDirectories;

import java.io.File;
import java.io.IOException;

public class FilesDirectoriesCommon {

    static File file = new File("D:\\install\\5291.saz");
    static File directory = new File("D:\\install\\logs");


    public static void main(String[] args) throws IOException {
        renameToExample();
    }

    private static void fileDirectoryCommonMethods() {
        System.out.println("File:");
        System.out.println("exists():" + file.exists());
        System.out.println("getName()" + file.getName());
        System.out.println("getAbsolutePath()" + file.getAbsolutePath());
        System.out.println("isFile()" + file.isFile());
        System.out.println("isDirectory()" + file.isDirectory());
        System.out.println("length()" + file.length());
        System.out.println("lastModified()" + file.lastModified());

        System.out.println("renameTo() - renaming in a separate method");

        System.out.println("getParent()" + file.getParent());

        System.out.println("---");

        System.out.println("Directory:");
        System.out.println("exists():" + directory.exists());
        System.out.println("getName()" + directory.getName());
        System.out.println("getAbsolutePath()" + directory.getAbsolutePath());
        System.out.println("isFile()" + directory.isFile());
        System.out.println("isDirectory()" + directory.isDirectory());
        //shows summ of contained files size
        System.out.println("length()" + directory.length());
        System.out.println("lastModified()" + directory.lastModified());

        System.out.println("renameTo() - renaming in a separate method");

        System.out.println("getParent()" + directory.getParent());
    }


    private static void getParentForRootReturnsItself() {
        System.out.println("getParent()" + new File("D:\\"));
    }

    private static void makingDirectories() {
        System.out.println("mkDir created:" + new File("D:\\007").mkdir());
        System.out.println("mkDirs(with subfolders) created:" + new File("D:\\005\\005").mkdirs());

        new File("D:\\007").delete();
        new File("D:\\005\\005").delete();
        new File("D:\\005").delete();
    }

    //it's just MOVE
    private static void renameToExample() throws IOException {
// File (or directory) with old name
        File file = new File("D:\\123.txt");

// File (or directory) with new name

        //WILL BE CREATED IN PROJECT FOLDER
        File file2 = new File("12.txt");

        if (file2.exists()) {
//            throw new java.io.IOException("file exists");
        }
// Rename file (or directory)
        System.out.println(file.renameTo(file2));
    }

    private static void removingDirectories() {
        System.out.println("mkDir created:" + new File("D:\\007").mkdir());
        System.out.println("mkDirs(with subfolders) created:" + new File("D:\\005\\005").mkdirs());

        System.out.println("category removed:" + new File("D:\\007").delete());
        //directory must be empty before removed
        System.out.println("not empty category removed:" + new File("D:\\005").delete());
        new File("D:\\005\\005").delete();
        new File("D:\\005").delete();
    }

    private static void listFilesForFileReturnsNull() {
        System.out.println("listFiles()" + file.listFiles());
        System.out.println("listFiles()" + directory.listFiles());
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
