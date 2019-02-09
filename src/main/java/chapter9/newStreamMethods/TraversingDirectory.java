package chapter9.newStreamMethods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TraversingDirectory {

    //There are two common strategies associated with walking a directory tree: a depth-first
    //search and a breadth-first search.
    //
    //A depth-first search traverses the structure from the root
    //to an arbitrary leaf and then navigates back up toward the root, traversing fully down any
    //paths it skipped along the way. The search depth is the distance from the root to current
    //node. For performance reasons, some processes have a maximum search depth that is used
    //to limit how many levels deep the search goes before stopping.
    //
    //Alternatively, a breadth-first search starts at the root and processes all elements of each
    //particular depth, or distance from the root, before proceeding to the next depth level. The results
    //are ordered by depth, with all nodes at depth 1 read before all nodes at depth 2, and so on
    public static void main(String[] args) {

    }

    private static void linesVsReadAllLines() {
        try {
            //list. read all file to memory
            Files.readAllLines(Paths.get("birds.txt")).forEach(System.out::println);
            //stream. lazy reading
            Files.lines(Paths.get("birds.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void linesExample() {
        //Files.lines(Path) - similar to Files.readAllLines() - method that returns a Stream<String> object without OOME
        Path path = Paths.get("/fish/sharks.log");
        try {
            System.out.println(Files.lines(path)
                    .filter(s -> s.startsWith("WARN "))
                    .map(s -> s.substring(5))
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            // Handle file I/O exception...
        }
    }

    private static void listExample() {
        //Files.list(Path) - like listFiles(), get the stream of 1 level children
        //Consider the following code snippet, assuming that the current working directory is /zoo:
        try {
            Path path = Paths.get("ducks");
            Files.list(path)
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toAbsolutePath())
                    .forEach(System.out::println);
        } catch (IOException e) {
            // Handle file I/O exception...
        }
    }

    private static void findExample() {
        //Files.find(Path,int,BiPredicate(Path and BasicFileAttributes)) method behaves in a similar manner as the
        //Files.walk() method, except that it requires the depth value to be explicitly set along
        //with a BiPredicate to filter the data.
        //
        //Like walk(), find() also supports the FOLLOW_LINK vararg option.
        Path path = Paths.get("/bigcats");
        long dateFilter = 1420070400000L;
        try {
            Stream<Path> stream = Files.find(path, 10,
                    (p, a) -> p.toString().endsWith(".java")
                            && a.lastModifiedTime().toMillis() > dateFilter);
            stream.forEach(System.out::println);
        } catch (Exception e) {
            // Handle file I/O exception...
        }
    }

    private static void walkEample() {
        Path path = Paths.get("D:\\install");
        try {
            Files.walk(path)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            // Handle file I/O exception...
        }

        //walk(Path,int)
        //you would need to specify a value of at least 1 to print any child record, 0 writes itself

        //may use FOLLOW_LINKS for following links objects, but may cause cycling and FileSystemLoopException
    }
}
