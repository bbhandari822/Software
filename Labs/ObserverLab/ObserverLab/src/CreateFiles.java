import java.io.*;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created by Binod Bhandari on 12/2/17.
 */
public class CreateFiles extends ObserverClass {

    public CreateFiles(HelperClass helperClass){
        this.helperClass = helperClass;
        this.helperClass.attach(this);
    }

    public void createFile() throws IOException {
        try {
            System.out.println("Enter name of your new file");
            Scanner key = new Scanner(System.in);
            String fileName = key.nextLine();
            File file = new File(Main.filePath + File.separator+fileName);
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void update() {
        System.out.println("Creates: " + Main.createCount);
    }
}
