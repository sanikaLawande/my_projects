import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber
{
    public static void main(String[] args)
    {
        try {
            Random random = new Random();

            int numberToGuess = random.nextInt(100) + 1;
            Scanner scanner = new Scanner(System.in);
            int Attempts = 0;
            int userGuess;
            System.out.println("Welcome to the Number Guessing Game!!!");

            do
            {
                System.out.print("Enter your guess (1-100): ");
                userGuess = scanner.nextInt();
                Attempts++;

                if (userGuess < numberToGuess)
                {
                    System.out.println("Your guess is too low. Try again.");
                }
                else if (userGuess > numberToGuess)
                {
                    System.out.println("Your guess is too high. Try again.");
                }
            }
            while (userGuess != numberToGuess);

            System.out.println("Congratulations! You guessed the correct number in " + Attempts + " attempts.");

        }catch (Exception e){
            System.out.println("Try again invalid input ..");
        }
    }
}

