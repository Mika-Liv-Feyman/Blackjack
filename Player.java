/**
 * Represents a player in blackjack
 */
import java.util.Scanner;
public class Player
{
   private String name;
   private Hand hand;
   public int bankRoll;
   private int bet;
  
   /**Constructs a player
    * @param String theName
    * @param int theBankRoll
    */
    public Player(String theName, int theBankRoll)
    {
        hand = new Hand();
        name = theName;
        bankRoll = theBankRoll;
        bet = 0;
    }
    
    /**Return the name of the player
     * @return String name
     */
    public String getName()
    {
        return name;
    }
    
    /**Returns the hand of the player
     * @return hand
     */
    public Hand getHand()
    {
        return hand;
    }
     
    /**Returns the bankroll of the player
     * @return int bankRoll
     */
    public int getBankRoll()
    {
        return bankRoll;
    }
    
    /**
     * gets the bet that the player makes
     * @return bet
     */
    public int getBet()
    {
        return bet;
    }
     
    /**
     * the user inputted bet of the player
     */
    public void placeBet()
    {
        Scanner betAmount = new Scanner(System.in);
        System.out.println("How much would you like to bet? ");
        while(betAmount.hasNextInt() == false)
        {
            try
            {
                bet = Integer.parseInt(betAmount.nextLine());
            }
            catch(NumberFormatException e)
            {
                System.out.println("I'm sorry. Please tell me the amount of money \nyou want to bet to the nearest whole dollar.");
            }
        }
        bet = betAmount.nextInt();
        while(bankRoll < bet)
        {
            System.out.println("\nI'm sorry. You cannot bet more money than you have.\nPlease bet an amount below $" + bankRoll + ".");
            while(betAmount.hasNextInt() == false)
            {
                try
                {
                    bet = Integer.parseInt(betAmount.nextLine());
                }
                catch(NumberFormatException e)
                {
                    System.out.println("I'm sorry. Please tell me the amount of money \nyou want to bet to the nearest whole dollar.\n");
                }
            }
            bet = betAmount.nextInt();
        }
        while(bet < 1)
        {
            System.out.println("I'm sorry, you must bet atleast $1 in our casino.");
            while(betAmount.hasNextInt() == false)
            {
                try
                {
                    bet = Integer.parseInt(betAmount.nextLine());
                }
                catch(NumberFormatException e)
                {
                    System.out.println("I'm sorry. Please tell me the amount of money \nyou want to bet to the nearest whole dollar.\n");
                }
            }
            bet = betAmount.nextInt();
        }
        bankRoll -= bet;
        System.out.println("\nYou bet $" + bet + ".");
        System.out.println("Your current balance is $" + bankRoll +"\n");
        
    }
    
    /*
     * adds a card to the player's hand and calls placeBet if the boolean is true
     * @param int bet
     */
    public void hit(Shoe shoe)
    {
        hand.addCard(shoe);    
    }
    
    /*
     * TESTER METHOD!!!!!!!!!!!
     */
    public void hit(Card card1)
    {
        hand.addCard(card1);    
    }
    
    /*
     * makes the player stand and displays their hand. then goes to results
     */
    public void stand()
    {
        System.out.println("Your hand: ");
        System.out.println(this.getHand().toString());
        System.out.println("Your cards' total value: " + this.getHand().getValue() + "\n");
    }
    
    
    /*
     * doubles the bet and adds one more card then forces the player to stay
     */
    public void doubleDown(Shoe shoe)
    {
        this.bankRoll -= bet;
        bet *= 2;
        hand.addCard(shoe);
        this.stand();

    }
    
    /*
     * toString with players name, bankRoll, cards
     * @return String
     */
    public String toString()
    {
       String str = "\nYou bet $" + bet +".\nYour current balance is $" + this.getBankRoll() + ".\nYour hand:\n" + this.getHand().toString() + "\nYour cards' total value: " + this.getHand().getValue() + "\n";
       return str;
    }
}
