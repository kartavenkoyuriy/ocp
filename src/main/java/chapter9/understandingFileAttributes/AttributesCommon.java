package chapter9.understandingFileAttributes;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;

public class AttributesCommon {
    public static void main(String[] args) {
        //Files.getOwner(Path) method returns an instance of UserPrincipal that
        //represents the owner of the fi le within the fi le system

        //Files.setOwner(Path,UserPrincipal) - to set the owner, called Files

        //In order to set a fi le owner to an arbitrary user, the NIO.2 API provides a
        //UserPrincipalLookupService helper class for fi nding a UserPrincipal record for a
        //particular user within a fi le system

        //getting user info by user name
        try {
            Path path = Paths.get("D:\\install\\1\\file_exist.txt");
            //UserPrincipalNotFoundException can be thrown
            UserPrincipal owner = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("jane");
            System.out.println("UserPrincipal owner:" + owner);

            //or

            UserPrincipal jane = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("jane");
            System.out.println("UserPrincipal jane:" + jane);

        } catch (IOException e) {
            //
        }

        try {
            // Read owner of file
            Path path = Paths.get("D:\\install\\1\\file_exist.txt");
            System.out.println(Files.getOwner(path).getName());
//            // Change owner of file
//            UserPrincipal owner = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("jane");
//            Files.setOwner(path, owner);
//            // Output the updated owner information
//            System.out.println(Files.getOwner(path).getName());
        } catch (IOException e) {
            // Handle file I/O exception...
        }
    }

    private static void lastModifiedExample() {
        //Files.getLastModifiedTime(Path) return a FileTime object
        //
        //FileTime class is a simple container class that
        //stores the date/time information about when a fi le was accessed, modified, or created

        //Files.setLastModifiedTime(Path,FileTime) method for updating
        //
        //FileTime class also has a static fromMillis() method

        //can throw a checked IOException when the file is accessed or modified
        try {
            final Path path = Paths.get("D:\\install\\1\\file_exist.txt");
            System.out.println(Files.getLastModifiedTime(path).toMillis());
//            System.out.println(Instant.ofEpochSecond(Files.getLastModifiedTime(path).toMillis()));
            Files.setLastModifiedTime(path,
                    FileTime.fromMillis(System.currentTimeMillis()));
            System.out.println(Files.getLastModifiedTime(path).toMillis());
//            System.out.println(Instant.ofEpochSecond(Files.getLastModifiedTime(path).toMillis()));
        } catch (IOException e) {
            // Handle file I/O exception...
        }
    }

    private static void fileSizeExample() {
        //Files.size(Path) method is used to determine the size of the fi le in bytes
        //
        //size returned by this method represents the conceptual size of the data, and this may differ from
        //the actual size on the persistence storage device due to fi le system compression and organization
        try {
            //Calling Files.size() on a directory is system dependent and undefined. If you need to
            //determine the size of a directory and its contents, you’ll need to walk the directory tree
            System.out.println(Files.size(Paths.get("D:\\install\\1\\file_exist.txt")));
        } catch (IOException e) {
            // Handle file I/O exception...
        }
    }

    //do not throw exceptions if the file does not
    //exist but instead return false
    private static void isReadableIsExecutableExample() {
        //returns true if the baby.png file exists and its contents are readable,
        //based on the permission rules of the underlying file system
        System.out.println(Files.isReadable(Paths.get("/seal/baby.png")));
        //returns true if the baby.png file is marked executable within the file system
        //
        //Note that the file extension does not necessary determine whether a file is executable
        System.out.println(Files.isExecutable(Paths.get("/seal/baby.png")));
    }

    private static void isHiddenExample() {
        //Files.isHidden(Path) method to determine whether a
        //file or directory is hidden within the file system
        //
        //In Linux- or Mac-based systems, this
        //is often denoted by file or directory entries that begin with a period character (.)

        try {
            System.out.println(Files.isHidden(Paths.get("/walrus.txt")));
        } catch (IOException e) {
            // Handle file I/O exception...
        }
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
