package chapter9.interactingWithPathsAndFiles;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InteractingWithFilesCommon {
    //working directory
    //C:\java\projects\ocp
    public static void main(String[] args) {

    }

    private static void readAllLinesExample() {
        //Files.readAllLines() method reads all of the lines of a text file and returns the
        //results as an ordered List of String values

        Path path = Paths.get("D:\\install\\1\\file_exist.txt");
        try {
            //be aware of OutOfMemoryError
            final List<String> lines = Files.readAllLines(path);
            for(String line: lines) {
                System.out.println(line);
            }

        //IOException if the file cannot be read
        } catch (IOException e) {
            // Handle file I/O exception...
        }
    }

    private static void newBufferedWriterExample() {
        //Files.newBufferedWriter(Path,Charset), writes to a file specified at the Path location using a BufferedWriter
        //
        //This code snippet
        //!!! creates a new file with the specified contents,
        //!!! overwriting the file if it already exists
        Path path = Paths.get("D:\\install\\1\\file_exist_write.txt");

        //newBufferedWriter() method also supports taking additional enum
        //values in an optional vararg, such as appending to an existing file instead of overwriting it,
        //although you do not need to memorize this list for the exam.
        try (BufferedWriter writer = Files.newBufferedWriter(path,
                Charset.forName("UTF-8"))) {
            writer.write("Hello World");
        } catch (IOException e) {
            // Handle file I/O exception...
        }
    }

    private static void newBufferedReaderEample() {
        //Files.newBufferedReader(Path,Charset), reads the file specified
        //at the Path location using a java.io.BufferedReader object. It also requires a Charset
        //value to determine what character encoding to use to read the file
        //Charset.defaultCharset() - can be used to get the default Charset for the JVM

        Path path = Paths.get("D:\\install\\1\\file_exist.txt");
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {
            // Read from the stream
            String currentLine;
            while((currentLine = reader.readLine()) != null)
                System.out.println(currentLine);
        } catch (IOException e) {
            // Handle file I/O exception...
        }
    }

    private static void deleteExample() {
        //Files.delete(Path) method deletes a file or empty directory(otherwise - DirectoryNotEmptyException) within the file system.
        //
        //The deleteIfExists(Path) method is identical to the delete(Path) method, except
        //that it will not throw an exception if the file or directory does not exist, but instead it will
        //return a boolean value of false.

        try {
            //deletes the features.txt file in the vulture directory,
            //and it throws a NoSuchFileException if the file or directory does not exist
            Files.delete(Paths.get("/vulture/feathers.txt"));
            //deletes the pigeon directory assuming it is empty. If the pigeon directory does not exist,
            //then the second line will not throw an exception.
            Files.deleteIfExists(Paths.get("/pigeon"));
        } catch (IOException e) {
            // Handle file I/O exception...
        }
    }

    private static void moveExample() {
        //The Files.move(Path,Path) method moves or renames a file or directory within the file system
        //IOE when the file or directory could not be found or moved
        try {
            //renames the zoo directory to zoo-new directory, keeping all of the original contents from the source directory
            Files.move(Paths.get("c:\\zoo"), Paths.get("c:\\zoo-new"));
            //moves the addresses.txt file from the directory user to the directory zoo-new, and it renames it to addresses2.txt.
            Files.move(Paths.get("c:\\user\\addresses.txt"), Paths.get("c:\\zoo-new\\addresses.txt"));
        } catch (IOException e) {
            // Handle file I/O exception...
        }
        //By default, the move() method will follow links, throw an exception if the fi le
        //already exists, and not perform an atomic move. These behaviors can be changed by
        //providing the optional values NOFOLLOW_LINKS, REPLACE_EXISTING, or ATOMIC_MOVE,
        //respectively, to the method. If the fi le system does not support atomic moves, an
        //AtomicMoveNotSupportedException will be thrown at runtime.
        //
        //The Files.move() method can be applied to non-empty directories only if
        //they are on the same underlying drive. While moving an empty directory
        //across a drive is supported, moving a non-empty directory across a drive
        //will throw an NIO.2 DirectoryNotEmptyException.
    }

    private static void copyToFromStreamExample() {
        //In this example, the InputStream and OutputStream parameters could refer to any valid
        //stream, including website connections, in-memory stream resources, and so forth
        try (InputStream is = new FileInputStream("source-data.txt");
             OutputStream out = new FileOutputStream("output-data.txt")) {
            //Copy stream data to file
            //copy(InputStream,Path)
            //also supports optional vararg options
            Files.copy(is, Paths.get("c:\\mammals\\wolf.txt"));
            //Copy file data to stream
            //(Path,OutputStream)
            //does not support optional vararg values, since the data is being written to a stream that may not represent a file system resource
            Files.copy(Paths.get("c:\\fish\\clown.xsl"), out);
        } catch (IOException e) {
            // Handle file I/O exception...
        }
    }

    private static void copyExample() {
        //These behaviors can be altered by providing the additional options NOFOLLOW_LINKS,
        //REPLACE_EXISTING, and COPY_ATTRIBUTES, respectively, as discussed earlier in the chapter.
        try {
            Path pathFileFrom = Paths.get("D:\\install\\1\\file_exist.txt");
            Path pathFileTo = Paths.get("D:\\install\\2\\file_exist_copy.txt");
            Path pathFolderFrom = Paths.get("D:\\install\\1");
            Path pathFolderTo = Paths.get("D:\\install\\2");

            //directory copy are shallow(without subfolders and files)
            Files.copy(pathFolderFrom, pathFolderTo);
            //manually copy what inside
            Files.copy(pathFileFrom, pathFileTo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void creatingCategoryExample() {
        //createDirectory() - like mkdir()
        //createDirectories() - like mkdirs()
        //may throw a checked exception if directory can't be created or already exist
        try {
            //if "bison" folder is not created - exception
            Files.createDirectory(Paths.get("/bison/field"));
            //will create all of parent folders if they are not exist
            Files.createDirectories(Paths.get("/bison/field/pasture/green"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void isSameFileExample() {
        //first isSameFile() checks whether paths are equals
        //isSameFile() doesn't check name content equality,
        //if files have same name and content, but locates in a different directories - isSameFile() returns false

        try {
            Path pathFile = Paths.get("C:\\java\\projects\\ocp\\ocp.iml");
            Path pathRelativeFile = Paths.get("ocp.iml");
            //here paths are not equals but it's a same file
            System.out.println(Files.isSameFile(pathFile, pathRelativeFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //example from a book
        //
        //Let’s assume that all of the files in the following examples exist within the file system and
        //that cobra is a symbolic link to the snake file
        try {
            //link looks to a file, so isSameFile() returns TRUE
            System.out.println(Files.isSameFile(Paths.get("/user/home/cobra"), Paths.get("/user/home/snake")));
            //".." cancel the "tree" path, so it become "/user/monkey", isSameFile() returns TRUE
            System.out.println(Files.isSameFile(Paths.get("/user/tree/../monkey"), Paths.get("/user/monkey")));
            //"." means same directory so it remains "/leaves/giraffe.exe", isSameFile() returns TRUE
            System.out.println(Files.isSameFile(Paths.get("/leaves/./giraffe.exe"), Paths.get("/leaves/giraffe.exe")));
            //assuming no file links to each other, isSameFile() returns FALSE
            System.out.println(Files.isSameFile(Paths.get("/flamingo/tail.data"), Paths.get("/cardinal/tail.data")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //doesn't throw an exception
    private static void existExample() {
        Path pathFile = Paths.get("D:\\install\\1\\file_exist.txt");
        Path pathDirectory = Paths.get("D:\\install\\1");
        Path pathWrongDirectory = Paths.get("D:\\install\\1\\");
        Path pathDoesntExistFile = Paths.get("D:\\install\\1\\123.txt");
        Path pathDoesntExistDirectory = Paths.get("D:\\install\\2");

        Path pathRelativeFile = Paths.get("ocp.iml");
        Path pathRelativeFile01 = Paths.get("/ocp.iml");
        Path pathRelativeFile02 = Paths.get("./ocp.iml");

        Path pathRelativeFile2 = Paths.get(".idea/vcs.xml");
        Path pathRelativeDirectory = Paths.get(".idea");

        System.out.println("pathFile:" + Files.exists(pathFile));
        System.out.println("pathDirectory:" + Files.exists(pathDirectory));
        System.out.println("pathWrongDirectory:" + Files.exists(pathWrongDirectory));
        System.out.println("pathDoesntExistFile:" + Files.exists(pathDoesntExistFile));
        System.out.println("pathDoesntExistDirectory:" + Files.exists(pathDoesntExistDirectory));
        //correct "ocp.iml"
        System.out.println("pathRelativeFile:" + Files.exists(pathRelativeFile));
        //wrong syntax "/ocp.iml"
        System.out.println("pathRelativeFile01:" + Files.exists(pathRelativeFile01));
        //correct "./ocp.iml"
        System.out.println("pathRelativeFile02:" + Files.exists(pathRelativeFile02));

        System.out.println("pathRelativeFile2:" + Files.exists(pathRelativeFile2));
        System.out.println("pathRelativeDirectory:" + Files.exists(pathRelativeDirectory));
    }
}
