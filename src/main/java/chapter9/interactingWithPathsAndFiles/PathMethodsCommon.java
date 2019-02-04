package chapter9.interactingWithPathsAndFiles;

import java.nio.file.Path;
import java.nio.file.Paths;

//Path is not a file but rather a representation

//some operations may be can be accomplished no matter file exist or not,
//some require representation to point to the certain file
public class PathMethodsCommon {
    //1.
    //UNIX ABSOLUTE
    // * /zoo/armadillo/shells.txt
    // * /zoo/../shells.txt
    //UNIX RELATIVE
    // * armadillo/shells.txt
    // * ./shells.txt
    // * ../shells.txt
    //2.
    //UNIX absolute paths starts with forward '/'
    //WINDOWS absolute paths starts with drive letter
    //
    //■ If a path starts with a forward slash, it is an absolute path, such as /bird/parrot.
    //■ If a path starts with a drive letter, it is an absolute path, such as C:\bird\emu.
    //■ Otherwise, it is a relative path, such as ..\eagle.
    //3.
    //System.out.println(Paths.get("/stripes/zebra.exe").isAbsolute());
    //System.out.println(Paths.get("c:/goats/Food.java").isAbsolute());
    //Although the first line outputs true on a Linux or Mac-based system, it outputs false
    //on a Windows-based system since it is missing a drive letter prefix. In the same manner, the
    //second path outputs true on Windows but false on a Linux or Mac-based system, as it is
    //missing the root forward slash, /.
    public static void main(String[] args) {
        isAbsoluteToAbsolutePath();
    }

    private static void isAbsoluteToAbsolutePath() {
        Path pathFile = Paths.get("D:\\install\\file_exist.txt");
        Path pathDirectory = Paths.get("D:\\install\\1");
        Path pathRelative1 = Paths.get("\\install\\file_exist.txt");
        Path pathRelative2 = Paths.get("install\\file_exist.txt");
        Path pathRelative3 = Paths.get("/zoo/armadillo/shells.txt");
        Path pathRelative4 = Paths.get("armadillo/shells.txt");

        //isAbsolute() - returns whether path is absolute
        System.out.println("path [" + pathFile + "] is absolute?" + pathFile.isAbsolute());
        System.out.println("path [" + pathDirectory + "] is absolute?" + pathDirectory.isAbsolute());
        System.out.println("path [" + pathRelative1 + "] is absolute?" + pathRelative1.isAbsolute());
        System.out.println("path [" + pathRelative2 + "] is absolute?" + pathRelative2.isAbsolute());
        //this path are relative, because we are on Windows environment
        //it turns forward slashes to back slashes
        System.out.println("path [" + pathRelative3 + "] is absolute?" + pathRelative3.isAbsolute());
        System.out.println("path [" + pathRelative4 + "] is absolute?" + pathRelative4.isAbsolute());
        System.out.println("***");

        //toAbsolutePath() - if the path is absolute - returns its copy, if relative - append working directory
        System.out.println("[" + pathFile + "] to absolute:" + pathFile.toAbsolutePath());
        System.out.println("[" + pathDirectory + "] to absolute:" + pathDirectory.toAbsolutePath());
        System.out.println("[" + pathRelative1 + "] to absolute:" + pathRelative1.toAbsolutePath());
        System.out.println("[" + pathRelative2 + "] to absolute:" + pathRelative2.toAbsolutePath());
        System.out.println("[" + pathRelative3 + "] to absolute:" + pathRelative3.toAbsolutePath());
        System.out.println("[" + pathRelative4 + "] to absolute:" + pathRelative4.toAbsolutePath());
    }

    private static void relativeAbsolutePrintPathInformation() {
        printPathInformation(Paths.get("/zoo/armadillo/shells.txt"));
        System.out.println();
        printPathInformation(Paths.get("armadillo/shells.txt"));
    }

    public static void printPathInformation(Path path) {
        System.out.println("Filename is: " + path.getFileName());
        System.out.println("Root is: " + path.getRoot());
        Path currentParent = path;
        while ((currentParent = currentParent.getParent()) != null) {
            System.out.println(" Current parent is: " + currentParent);
        }
    }

    private static void getFileNameGetRootGetParent() {
        Path pathFile = Paths.get("D:\\install\\file_exist.txt");
        Path pathDirectory = Paths.get("D:\\install\\1");
        Path pathRelative = Paths.get("\\install\\file_exist.txt");
        Path pathRelative2 = Paths.get("install\\file_exist.txt");

        //getFileName() - new Path object, the farthest element from the root
        System.out.println("pathFile Filename is:" + pathFile.getFileName());
        System.out.println("pathDirectory Filename is:" + pathDirectory.getFileName());
        System.out.println("pathRelative Filename is:" + pathRelative.getFileName());
        System.out.println("***");

        //getRoot() - returns the root, or null if path is relative
        System.out.println("pathFile getRoot():" + pathFile.getRoot());
        System.out.println("pathDirectory getRoot():" + pathDirectory.getRoot());
        System.out.println("pathRelative getRoot():" + pathRelative.getRoot());
        System.out.println(pathRelative.isAbsolute());
        System.out.println("pathRelative2 getRoot():" + pathRelative2.getRoot());
        System.out.println("***");

        //getParent() - new Path object, parent or null if there is no parent
        //if the path is relative, it won't traverse outside the working directory
        System.out.println("pathFile getParent():" + pathFile.getParent());
        System.out.println("pathDirectory getParent():" + pathDirectory.getParent());
        System.out.println("pathRelative getParent():" + pathRelative.getParent());
    }

    private static void getClassToStringGetNameCountGetNameExample() {
        Path path = Paths.get("D:\\install\\file_exist.txt");

        //getClass()
        //currently it is WindowsPath class
        System.out.println("path class is:" + path.getClass().getName());

        //toString()
        //print the string representation of the path
        System.out.println("path is:" + path);

        //getNameCount()
        //counts a names in path EXCLUDING root
        //if in path will be only root - getNameCount() will return '0'
        for (int i = 0; i < path.getNameCount(); i++) {

            //getName(int i)
            //gets Path object from index
            //* its zero-indexed
            //* it EXCLUDED root
            System.out.println("path part #[" + i + "] is:" + path.getName(i));
        }
    }


}
