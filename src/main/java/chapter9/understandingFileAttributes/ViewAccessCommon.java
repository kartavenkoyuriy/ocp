package chapter9.understandingFileAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class ViewAccessCommon {

    //A view is a group of related attributes for a particular file system type
    //it need to retrieve set of attribute(faster) instead of one-by-one

    //To request a view, you need to provide both a path
    //to the file or a directory whose information you want to read, as well as a class object

    //Files.readAttributes(), returns a read-only view of the file attributes
    //
    //Files.getFileAttributeView(), returns the underlying
    //attribute view, and it provides a direct resource for modifying file information

    //Attributes class(Files.readAttributes()):
    //BasicFileAttributes - for all
    //DosFileAttributes (extends BasicFileAttributes) - dos/windows
    //PosixFileAttributes (extends BasicFileAttributes) - linux/mac

    //View class(Files.getFileAttributeView()):
    //same as previous classes but + View
    //i.e. PosixFileAttributesView(extends BasicFileAttributesView)
    public static void main(String[] args) {
        updatingFileAttributes();
    }

    private static void updatingFileAttributes() {
        //Files.getFileAttributeView(Path,Class<V>) returns FileAttributeView - method, which returns a view object that
        //we can use to update the file system–dependent attributes

        //Files.getFileAttributeView(Path,Class<V>) method, which returns a view object that
        //we can use to update the file system–dependent attributes

        try {
            Path path = Paths.get("/turtles/sea.txt");
            //view - for writing attributes
            BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
            //data (from view) - for reading attributes
            BasicFileAttributes data = view.readAttributes();
            FileTime lastModifiedTime = FileTime.fromMillis(data.lastModifiedTime().toMillis() + 10_000);
            //need to pass three arguments(null means - 'do not wish to modify')
            //we can set only times(
            view.setTimes(lastModifiedTime, null, null);
        } catch (IOException e) {
            //catch
        }
    }

    private static void readingFileAttributeExample() {
        //Files.readAttributes(Path,Class<A>) - returns FileAttributes
        try {
            Path path = Paths.get("D:\\install\\1\\file_exist.txt");
            BasicFileAttributes data = Files.readAttributes(path,
                    BasicFileAttributes.class);
            System.out.println("Is path a directory? " + data.isDirectory());
            System.out.println("Is path a regular file? " + data.isRegularFile());
            System.out.println("Is path a symbolic link? " + data.isSymbolicLink());
            System.out.println("Path not a file, directory, nor symbolic link? " + data.isOther());
            System.out.println("Size (in bytes): " + data.size());
            System.out.println("Creation date/time: " + data.creationTime());
            System.out.println("Last modified date/time: " + data.lastModifiedTime());
            System.out.println("Last accessed date/time: " + data.lastAccessTime());
            //fileKey() method returns a file system value that represents a unique
            //identifier for the file within the file system or null if it is not supported by the file system
            System.out.println("Unique file identifier (if available): " + data.fileKey());
        } catch (IOException e) {
            //catch
        }
    }
}
