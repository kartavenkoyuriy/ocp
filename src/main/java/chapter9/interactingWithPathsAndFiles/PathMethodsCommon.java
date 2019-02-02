package chapter9.interactingWithPathsAndFiles;

import java.nio.file.Path;
import java.nio.file.Paths;

//Path is not a file but rather a representation

//some operations may be can be accomplished no matter file exist or not,
//some require representation to point to the certain file
public class PathMethodsCommon {
    public static void main(String[] args) {

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
