package chapter9.interactingWithPathsAndFiles;

import java.nio.file.FileVisitOption;
import java.nio.file.LinkOption;
import java.nio.file.StandardCopyOption;

import static java.nio.file.FileVisitOption.FOLLOW_LINKS;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardCopyOption.*;

public class OptionalArguments {
    public static void main(String[] args) {
        //If provided, symbolic links when encountered
        //will not be traversed. Useful for performing
        //operations on symbolic links themselves rather
        //than their target.
        LinkOption nofollowLinks = NOFOLLOW_LINKS;

        //If provided, symbolic links when encountered
        //will be traversed.
        FileVisitOption followLinks = FOLLOW_LINKS;

        //If provided, all metadata about a file will be
        //copied with it.
        StandardCopyOption copyAttributes = COPY_ATTRIBUTES;

        //If provided and the target file exists, it will
        //be replaced; otherwise, if it is not provided,
        //an exception will be thrown if the file already
        //exists.
        StandardCopyOption replaceExisting = REPLACE_EXISTING;

        //The operation is performed in an atomic
        //manner within the file system, ensuring that
        //any process using the file sees only a complete
        //record. Method using it may throw an exception
        //if the feature is unsupported by the file system.
        StandardCopyOption atomicMove = ATOMIC_MOVE;
    }
}
