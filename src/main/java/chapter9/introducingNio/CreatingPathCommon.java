package chapter9.introducingNio;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;

public class CreatingPathCommon {
    public static void main(String[] args) throws URISyntaxException {
        pathCreationThroughURI();
    }

    private static void otherUriSchemesExample() throws URISyntaxException {
        //FileSystemNotFoundException here
        //there is no "http" scheme when iterating over existing FileSystemProvider.installedProviders()
        Path httpUriPath = Paths.get(new URI("http://www.wiley.com"));
        Path ftpUriPath = Paths.get(new URI("ftp://username:password@ftp.the-ftp-server.com"));
        URI ftpUri = ftpUriPath.toUri();
        System.out.println(ftpUri.toString());

//        for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
//            System.out.println(provider.getScheme());
//            System.out.println(provider);
//        }
    }

    //Another way to construct a Path using the Paths class is with a URI value. A uniform
    //resource identifier (URI) is a string of characters that identify a resource. It begins with
    //a schema that indicates the resource type, followed by a path value. Examples of schema
    //values include file://, http://, https://, and ftp://. The java.net.URI class is used to
    //create and manage URI values.
    private static void pathCreationThroughURI() throws URISyntaxException {
        //URI must reference the absolute path
        Path path0 = Paths.get(new URI("file://pandas/cuddly.png"));
        URI uri0 = path0.toUri();
        System.out.println(uri0);
        //printed

        Path path1 = Paths.get(new URI("file://D:/install/file_exist.txt"));
//        System.out.println(path1.toUri());
        //hangs forever

        Path path2 = Paths.get(new URI("file:///D:/install/file_exist.txt"));
        //printed, providing shortcut to file
        System.out.println(path2.toUri());
    }

    private static void simplePathCreationExample() {
        Path pathExist = Paths.get("D:\\install\\file_exist.txt");
        Path pathExistSeparate = Paths.get("D:" + "install" + "file_exist.txt");

        System.out.println(pathExist.toString());
        System.out.println(pathExistSeparate.toString());
        System.out.println(pathExistSeparate.normalize().toString());

        //Path pathDoesntExist = Paths.get("D:\\install\\file_doesnt_exist.txt");
    }

    //Path.get()
    //invokes (so, same as)
    //FileSystems.getDefault().getPath()
    private static void fileSystemGetPath() throws URISyntaxException {
        Path path1 = FileSystems.getDefault().getPath("\\install\\file_exist.txt");
        Path path2 = FileSystems.getDefault().getPath("D:\\install\\file_exist.txt");
        Path path3 = FileSystems.getDefault().getPath("D:" + "install" + "file_exist.txt");

        //connecting to other File System
        FileSystem otherFileSystem = FileSystems.getFileSystem(new URI("http://www.selikoff.net"));
        Path path = otherFileSystem.getPath("duck.txt");
    }

    private static void filePathConverting(){
        Path path1 = Paths.get("D:\\install\\file_exist.txt");
        File file1 = path1.toFile();

        File file2 = new File("D:\\install\\file_exist.txt");
        Path path2 = file2.toPath();
    }

    private static void cleanSlashesExample(){
        Path path2 = Paths.get("E:\\animals\\tigers\\");
        System.out.println(path2);
    }
}
