import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String pathHtml = "https://skillbox-java.github.io/";
        String pathCsv = "FilesAndNetwork/DataCollector/src/main/data/input";
        String pathFile = "FilesAndNetwork/DataCollector/src/main/data/input";
        String pathOut = "FilesAndNetwork/DataCollector/src/main/data/out";

        DataAccelerator dataAccelerator = new DataAccelerator(pathHtml, pathCsv, pathFile);

        try {

            dataAccelerator.save(pathOut);

        } catch (Exception e){
            System.err.println(e.fillInStackTrace());
        }

    }

}
