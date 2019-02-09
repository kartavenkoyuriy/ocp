package chapter8.understandingFileAttributes;

import java.nio.file.Files;
import java.nio.file.Paths;

public class AttributesCommon {
    public static void main(String[] args) {

    }

    private static void isFileDirectoryLinkExample() {
        //Files.isRegularFile(Path) - Java defines a regular file as one that contains content, as opposed to a symbolic link,
        //directory, resource, or other non-regular file that may be present in some operating systems
        //
        //If the symbolic link points to a real file or directory, Java will perform the check on the
        //target of the symbolic link. In other words, it is possible for isRegularFile() to return
        //true for a symbolic link, as long as the link resolves to a regular file

        //returns true if fur.jpg is a directory or a symbolic link to a directory and false otherwise
        Files.isDirectory(Paths.get("/canine/coyote/fur.jpg"));
        //returns true if types.txt points to a regular file
        //or alternatively a symbolic link that points to a regular file
        Files.isRegularFile(Paths.get("/canine/types.txt"));
        //returns true if /canine/coyote is a symbolic link, regardless of
        //whether the file or directory it points to exists
        Files.isSymbolicLink(Paths.get("/canine/coyote"));

        //redundant:
        //if(Files.exists(path) && Files.isDirectory(path)) {
        //use instead:
        //if(Files.isDirectory(path)) {
    }
}
