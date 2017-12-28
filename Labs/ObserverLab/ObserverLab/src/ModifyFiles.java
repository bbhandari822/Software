import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Binod Bhandari on 12/2/17.
 */
public class ModifyFiles extends ObserverClass{

    public ModifyFiles(HelperClass helperClass){
        this.helperClass = helperClass;
        this.helperClass.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Modifications: " + Main.modifyCount);
    }

    public void modifyFile() throws IOException {

        String line = "";
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

        for (int i = 0; i < txtFiles.size(); i++) {
            String name = txtFiles.get(i);
            System.out.println();
            System.out.println((i+1) + ") " + name);
        }

        System.out.println("Which file do you want to modify: ");
        Scanner keyboard1 = new Scanner(System.in);
        while (!keyboard1.hasNextInt()) {
            System.out.println("Please use integer only");
            keyboard1.nextLine();
        }

        int optionChoosed = keyboard1.nextInt();

        String temp= txtFiles.get(optionChoosed-1);

        File file1 = new File(Main.filePath+File.separator+ temp);


        FileReader fileReader = new FileReader(file1);
        BufferedReader bufferedReader1 = new BufferedReader(fileReader);
        FileReader fileReader2 = new FileReader(file1);
        BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

        ArrayList<String> linesReadFromTextFile = new ArrayList<>();
        while ((line = bufferedReader1.readLine()) != null) {
            linesReadFromTextFile.add(line);
        }
        while ((line = bufferedReader2.readLine()) != null) {
            linesReadFromTextFile.add(line);
        }




        Files.write(Paths.get((Main.filePath+File.separator+temp)), linesReadFromTextFile, StandardCharsets.UTF_8);
    }
}
