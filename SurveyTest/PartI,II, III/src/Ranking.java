import java.util.Scanner;

/**
 * Created by Binod Bhandari on 10/26/17.
 */

/**
 * This class rank the choices according to the user preference.
 * Basically ask the number of choices, and then asks them to rank in particular order.
 */


public class Ranking extends Question {

    protected String[] options;
    protected int restrictionOfChoices = 0;

    public int getRestrictionOfChoices() {
        return restrictionOfChoices;
    }

    public String[] getChoices() {
        return options;
    }

    public Response getResponses()
    {
        StringBuilder answer = new StringBuilder();
        Response response = new Response();
        String [] responseList = new String[1];
        for (int i = 0; i < this.restrictionOfChoices; i++)
        {
            int option;
            do {
                System.out.print("Enter Rank " + Integer.toString(i+1) + ": ");
                Scanner keyboard = new Scanner(System.in);
                while(!keyboard.hasNextInt()) {
                    System.out.println("Please enter integer only: ");
                    keyboard.next();
                }
                option = keyboard.nextInt();
            } while (!(option > 0 && option <= this.restrictionOfChoices));
            answer.append(Integer.toString(option)).append(" ");
        }
        responseList[0] = answer.toString();
        response.setResponse(responseList);
        return response;
    }

    public void setChoices() {
        System.out.print("Enter number of options: ");
        Scanner keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        int numOfOptions = keyboard.nextInt();
        this.restrictionOfChoices = numOfOptions;
        this.options = new String[numOfOptions];
        for (int i = 0; i < numOfOptions; i++){
            System.out.println("Enter choice #" + Integer.toString(i+1));
            String option = new Scanner(System.in).nextLine();
            this.options[i]=option;
        }
    }

    public void modifyChoices() {

        String repeatProcess;
        do {
            System.out.print("Do you wish to modify the options?");
            String userPromptVerify = new Scanner(System.in).nextLine();
            if (userPromptVerify.toLowerCase().equals("yes")) {
                System.out.print("Which option would you like to modify?");
                this.displayChoicesOptions();
                Scanner keyboard = new Scanner(System.in);
                while(!keyboard.hasNextInt()) {
                    System.out.println("Please enter integer only: ");
                    keyboard.next();
                }
                int optionChoice = keyboard.nextInt();
                System.out.print(this.options[optionChoice-1]);
                System.out.print("Enter new choice: ");
                String newOption = new Scanner(System.in).nextLine();
                this.options[optionChoice-1] = newOption;
            }
            repeatProcess = userPromptVerify;
        } while (repeatProcess.toLowerCase().equals("yes"));
    }

    public void displayChoicesOptions() {
        for(int i = 0; i < options.length; i++){
            int a = i+1;
            System.out.println(a + ")" + options[i]);
        }
    }

}
