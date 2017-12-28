import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created by Binod Bhandari on 10/26/17.
 */

/**
 * This class have two columns that have pair on the opposite side. It is same like ranking but it have two
 * columns instead of one. choices are saved in array.
 */

public class Matching extends Question {

    private String[] firstColumn;
    private String[] secondColumn;
    protected int restrictionOfChoices = 0;

    public int getRestrictionOfChoices() {
        return restrictionOfChoices;
    }

    public String[] getChoices() {
        return secondColumn;
    }

    public Response getResponses()
    {
        Response response = new Response();
        String [] responseList = new String[1];
        int optionA = 0;
        int optionB = 0;
        StringBuilder finalAnswer = new StringBuilder();
        String answer = "";
        for (int i = 0; i < this.restrictionOfChoices; i++) {
            do {
                System.out.print("Enter Response" + Integer.toString(i + 1) + ": ");
                Scanner keyboard = new Scanner(System.in);
                answer = keyboard.nextLine();
                if (answer.length() == 3)
                {
                    optionA = Character.getNumericValue(answer.charAt(0));
                    optionB = Character.getNumericValue(answer.charAt(2));
                }
            } while (!(optionA > 0 && optionA <= this.restrictionOfChoices && optionB > 0 && optionB <= this.restrictionOfChoices) ||
                    !(answer.length() > 0 && answer.length() <= 3));
            finalAnswer.append("\n").append(answer);
        }
        responseList[0] = finalAnswer.toString();
        response.setResponse(responseList);
        return response;
    }

    public void setChoices() {
        System.out.println("Enter number for First column: ");
        Scanner keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        int numOfOptionsInFirstColumn = keyboard.nextInt();
        this.restrictionOfChoices = numOfOptionsInFirstColumn;
        this.firstColumn = new String[numOfOptionsInFirstColumn];
        for (int i = 0; i < restrictionOfChoices; i++) {
            int a = i+1;
            System.out.println("Enter choice #" + Integer.toString(a));
            String option = new Scanner(System.in).nextLine();
            this.firstColumn[i] = option;
        }

        System.out.println("Enter choices for Second column: ");
        this.restrictionOfChoices = numOfOptionsInFirstColumn;
        this.secondColumn = new String[numOfOptionsInFirstColumn];
        for (int i = 0; i < restrictionOfChoices; i++) {
            int a = i+1;
            System.out.println("Enter choice #" + Integer.toString(a));
            String option = new Scanner(System.in).next();
            this.secondColumn[i] = option;
        }
    }

    public void modifyChoices() {
        String repeatProcess;
        do {
            System.out.println("Do you wish to modify the options?");
            Scanner keyboard = new Scanner(System.in);
            String userPromptVerify = keyboard.nextLine();
            if (userPromptVerify.toLowerCase().equals("yes")) {
                System.out.println("Would you like to modify column 1 or column2?");
                while(!keyboard.hasNextInt()) {
                    System.out.println("Please enter integer only: ");
                    keyboard.next();
                }
                int listOption = keyboard.nextInt();
                switch (listOption) {
                    case 1:
                        firstColumn = modifyOptionsArray(firstColumn);
                        break;
                    case 2:
                        secondColumn = modifyOptionsArray(secondColumn);
                        break;
                }
            }repeatProcess = userPromptVerify;
        } while (repeatProcess.toLowerCase().equals("yes"));
    }

    public void displayChoicesOptions() {

        for(int i = 0; i < firstColumn.length; i++){
            int a = i+1;
            System.out.println(a + ")" + firstColumn[i]);
            System.out.println(a + ")" +secondColumn[i]);
        }
    }

    protected String[] modifyOptionsArray(String[] options) {

        System.out.println("Which option would you like to modify?");

        IntStream.range(0, options.length).forEach(i -> {
            int a = i + 1;
            String option = options[i];
            System.out.println(a + ") " + option);
        });
        Scanner keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        int optionChoice = keyboard.nextInt();

        System.out.println(options[optionChoice-1]);
        System.out.println("  Enter new option:");
        String newOption = new Scanner(System.in).nextLine();
        options[optionChoice-1] = newOption;
        return options;
    }
}

