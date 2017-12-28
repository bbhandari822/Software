import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created by Binod Bhandari on 12/2/17.
 */

public class DeleteFiles extends ObserverClass {

    public DeleteFiles(HelperClass helperClass){
        this.helperClass = helperClass;
        this.helperClass.attach(this);
    }

    public void deleteFilesInTheFolder() {

        File file = new File(Main.filePath);
        File[] listFiles = file.listFiles();
        int a = 0;

        List<String> txtFiles = new ArrayList<>();

        for (int i = 0; i < listFiles.length; i++) {
            File file1 = listFiles[i];
            if (file1.isFile() && file1.getName().endsWith(".txt")) {
                txtFiles.add(file1.getName());
            }

        }
        for(int j = 0; j < txtFiles.size(); j++){
            a = j+1;
            System.out.println(a + ") " + txtFiles.get(j));

        }

        System.out.println("Which file do you want to delete: ");
        Scanner keyboard1 = new Scanner(System.in);
        while (!keyboard1.hasNextInt()) {
            System.out.println("Please use integer only");
            keyboard1.nextLine();
        }
        int optionChoosed = keyboard1.nextInt();
        try {
            Files.deleteIfExists(Paths.get(Main.filePath  +File.separator+ txtFiles.get(optionChoosed-1)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        System.out.println("Deletes: " + Main.deleteCount);
    }
}
