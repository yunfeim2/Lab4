import java.util.Scanner;

/**
 * Class that implements a Pokemon colosseum.
 * <p>
 * The Pokemon battles that you might have grown up to know and love are now console based! The
 * Colosseum class is where all the battles will go down. We will build our Pokemon, let them
 * battle, and see who will be the winner!
 *
 * @see <a href="https://cs125.cs.illinois.edu/lab/4/">Lab 4 Description</a>
 */
public class Colosseum {
    /**
     * The maximum number of hit points we will allow a Pokemon to start with.
     */
    static final int MAX_HIT_POINTS = 50;

    /**
     * The maximum number of rounds we will let the Pokemon battle.
     */
    static final int MAX_NUM_ROUNDS = 10;

    /**
     * The first Pokemon we will be fighting.
     */
    static Pokemon firstPokemon;

    /**
     * The second Pokemon we will be fighting.
     */
    static Pokemon secondPokemon;

    /**
     * Input scanner. Use this to take in user's input for buildPokemon(). <br>
     * Useful functions: next(), nextInt() .
     */
    static Scanner myScan;

    /**
     * How we will build our Pokemon to battle.
     * <p>
     * Obtain user input to set Pokemon's member variables
     * <p>
     * Requirements we should check the user for: <br>
     * - Hit points are between 1 and MAX_HIT_POINTS <br>
     * - No more than 50 points are split between attack level and defense level <br>
     * - Attack level and defense level must have at least 1 point each <br>
     * Example of how this will look to the user:
     * <p>
     * Please name your Pokemon: Dolphin <br>
     * How many hit points will it have? (1-50): 50 <br>
     * Split fifty points between attack level and defense level <br>
     * Enter your attack level (1-49): 47 <br>
     * Enter your defense level (1-3): 3 <br>
     * <br>
     * Example of checking for bad input: <br>
     * <br>
     * Please name your Pokemon: Fire <br>
     * How many hit points will it have? (1-50): 0 <br>
     * Sorry. Hit points must be between 1 and 50: 55 <br>
     * Sorry. Hit points must be between 1 and 50: 50 <br>
     * Split fifty points between attack level and defense level <br>
     * Enter your attack level (1-49): -10 <br>
     * Sorry. The attack level must be between 1 and 49: 73 <br>
     * Sorry. The attack level must be between 1 and 49: 27 <br>
     * Enter your defense level (1-23):  24 <br>
     * Sorry. The defense level must be between 1 and 23: 23
     *
     * @return tempPokemon - the Pokemon we built and are going to set our fighting Pokemon to <br>
     *         (Look, we can r eturn objects too!)
     *         <p>
     *         Implement this function.
     */
    public static Pokemon buildPokemon() {
        Pokemon tempPokemon = new Pokemon();
        System.out.println("please enter the name:");
        String a = myScan.next();
        tempPokemon.name = a;

        int hitPoint = 0;
        while (hitPoint < 1 || hitPoint > MAX_HIT_POINTS) {
            System.out.println("Please enter the hitpoints: ");
            hitPoint = myScan.nextInt();
            if (hitPoint < 1 || hitPoint > MAX_HIT_POINTS) {
                System.out.println("Sorry. Hit points must be between 1 and 50, please reenter!");
            }
        }
        tempPokemon.hitPoints = hitPoint;
        int attactPoint = 0;
        while (attactPoint < 1 || attactPoint > 49) {
            System.out.println("please enter attactpoint :");
            attactPoint = myScan.nextInt();
            if (attactPoint < 1 || attactPoint > 49) {
                System.out.println("Sorry. The attack level must be between 1 and 49,please reenter!");
            }
        }
        tempPokemon.attackLevel = attactPoint;
        int denfencePoint = 0;
        while (denfencePoint < 1 || denfencePoint > 23) {
            System.out.println("please enter denfence points:");
            denfencePoint = myScan.nextInt();
            if (denfencePoint < 0 || denfencePoint > 23) {
                System.out.println("Sorry. The defense level must be between 1 and 23, please reenter!");
            }
        }
        tempPokemon.defenseLevel = denfencePoint;
        return tempPokemon;
    }

    /**
     * Prints who is ahead.
     * <p>
     * Compares the two Pokemon to see if there's a tie, or if a pokemon is currently winning.
     * <p>
     * Example: <br>
     * Fire has 41 hit points <br>
     * Dolphin has 44 hit points <br>
     * <br>
     * Print "Dolphin is currently ahead!"
     * <p>
     * Implement this function.
     */
    public static void printWhoIsAhead() {
        determineOrder();
    }

    /**
     * Prints out the overall winner of the battle.
     * <p>
     * This will only be called if there is not a tie, so you don't need to worry about this case.
     * <p>
     * Write this function.
     */
    public static void determineWinner() {
        firstPokemon.attack(secondPokemon);
    }

    /**
     * Initializes the member Pokemons.
     * <p>
     * You do not need to modify this function.
     */
    public static void initializePokemon() {
        System.out.println("Player 1, build your Pokemon!");
        System.out.println("=================");
        firstPokemon = buildPokemon();
//        firstPokemon.name = "Chuchu";

        System.out.println("");

        System.out.println("Player 2, build your Pokemon!");
        System.out.println("==================");
        secondPokemon = buildPokemon();
//        secondPokemon.name = "Xyz";
    }

    /**
     * Determines the order of which Pokemon will go first.
     * <p>
     * Uses a 2-sided die to roll for first.
     * <p>
     * You do not need to modify this function.
     */
    public static void determineOrder() {
        /*
         * Use random throw to decide ordering.
         */
        final Dice d2 = new Dice(2);
        System.out.println("\nPlayer 1 will roll a D2, to decide who goes first.");
        final int firstTurn = d2.roll();
        System.out.print("Player 1 rolls a " + firstTurn + " and will go ");
        if (firstTurn == 1) {
            System.out.print("first");
        } else {
            /*
             * Swap Pokemon for second outcome.
             */
            System.out.print("second");
            Pokemon tempPokemon = new Pokemon();
            tempPokemon = firstPokemon;
            firstPokemon = secondPokemon;
            secondPokemon = tempPokemon;
        }
    }
    /**
     * Conducts the Pokemon battle.
     * <p>
     * You do not need to modify this function.
     *
     * @param unused unused input arguments.
     */
    public static void main(final String[] unused) {
        myScan = new Scanner(System.in);
        initializePokemon();
        determineOrder();
        System.out.println("");
        boolean ifWinner = false;

        /*
         * Let the battle begin!
         */
        for (int i = 0; i < MAX_NUM_ROUNDS && !ifWinner; i++) {
            System.out.println("");
            System.out.println("Round " + (i + 1) + "!");
            System.out.println("");

            ifWinner = firstPokemon.attack(secondPokemon);
            if (!ifWinner) {
                ifWinner = secondPokemon.attack(firstPokemon);
                if (!ifWinner) {
                    printWhoIsAhead();
                }

            }
        }
        System.out.println("");

        if (!ifWinner) {
            System.out.println("It's a tie!");
        } else {
            determineWinner();
        }

        myScan.close();
    }
}
