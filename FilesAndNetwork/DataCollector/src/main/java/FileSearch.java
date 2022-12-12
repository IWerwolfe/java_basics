import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FileSearch {

    private static ArrayList<File> fileList;
    private static ArrayList<String> fileTypeList;

    public static void printFiles(String path, ArrayList<String> fileType) {
        getFileList(path, fileType).forEach(System.out::println);
    }

    public static void printFiles(String path, String fileType) {
        getFileList(path, fileType).forEach(System.out::println);
    }

    public static ArrayList<File> getFileList(String path, ArrayList<String> fileType){
        fileTypeList = fileType;
        return getFileList(path);
    }

    public static ArrayList<File> getFileList(String path, String fileType){
        fileTypeList = new ArrayList<>();
        fileTypeList.addAll(Arrays.asList(fileType.split(",")));
        return getFileList(path);
    }

    private static ArrayList<File> getFileList(String path){
        fileList = new ArrayList<>();
        addFileRecursion(new File(path), getRegex());
        return fileList;
    }

    private static void addFileRecursion(File file, String regex){

        if (file.isDirectory()){
            Arrays.stream(file.listFiles()).forEach(f -> addFileRecursion(f, regex));
            return;
        }

        if (file.getName().matches(regex)){
            fileList.add(file);
        }
    }

    private static String getRegex(){

        StringBuilder regex = new StringBuilder(".+\\.(");
        fileTypeList.forEach(f -> {
            String nameType = f.replaceAll("\\W", "");
            regex.append(nameType).append("|").append(nameType.toLowerCase()).append("|");
        });
        regex.deleteCharAt(regex.length()-1).append(")");
        return regex.toString();
    }
}
