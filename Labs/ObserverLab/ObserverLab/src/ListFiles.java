import java.io.File;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created by Binod Bhandari on 12/2/17.
 */
public class ListFiles extends ObserverClass {

    public ListFiles(HelperClass helperClass){
        this.helperClass = helperClass;
        this.helperClass.attach(this);
    }

    public String[] listallofTheFiles() throws NullPointerException {
        System.out.println("Please enter the path");
        Scanner keyboard = new Scanner(System.in);
        Main.filePath = keyboard.nextLine();
        File file = new File(Main.filePath);
        File[] listFiles = file.listFiles();
        int a = 0;
        for (int i = 0; i < listFiles.length; i++) {
            File file1 = listFiles[i];
            if (file1.isFile() && file1.getName().endsWith(".txt")) {
                a = a + 1;
                String name = file1.getName();
                System.out.println();
                System.out.println(a + ") " + name);
            }
        }
        System.out.println();
        return file.list();
    }

    @Override
    public void update() {
        System.out.println("Lists: " + Main.listCount);
    }

}
