/**
 * Represents a dealer in blackjack
 */
import java.util.Scanner;
public class Dealer
{
   private Player player;
   private Hand hand;
   private int numCardsLeft;
   private int theNumDecks;
   private int reshuffleNum;
   private Shoe shoe;
   private int handsWon;
   private int handsLost;
   private int handsTied;
   private boolean boughtInsurance;
   private String playerName;
  
   /**Constructs a dealer by calling startGame()
    */
    Dealer()
    {

        Scanner name = new Scanner(System.in);
        System.out.println("Welcome to Mika's Casino!");
        System.out.println("What is your name? ");
        playerName = name.nextLine();
        System.out.println("Hello, " + playerName + "!\n");
        Scanner houseRules = new Scanner(System.in);
        System.out.println("Would you like to know our house rules?");
        String readHouseRules = houseRules.nextLine();
        while(!(readHouseRules.equalsIgnoreCase("yes") || readHouseRules.equalsIgnoreCase("no")))
        {
                System.out.println("I'm sorry, please say yes or no.");
                System.out.println("Would you like to know our house rules?");
                readHouseRules = houseRules.nextLine();
        }
        if(readHouseRules.equalsIgnoreCase("yes"))
        {
            houseRulesText();
        }
        else if(readHouseRules.equalsIgnoreCase("no"))
        {
            System.out.println("Ah, an experienced player. I should have known!");
        }
        Scanner num = new Scanner(System.in);
        
        System.out.println("How much money did you bring today?\n");
        int bankRoll = 0;
        while(num.hasNextInt() == false)
        {
            try
            {
                bankRoll = Integer.parseInt(num.nextLine());
            }
            catch(NumberFormatException e)
            {
                System.out.println("I'm sorry. Please tell me the amount of money \nyou have to the nearest whole dollar.");
            }
        }
        bankRoll = num.nextInt();
        while(bankRoll < 100)
        {
            System.out.println("I'm sorry, you must bring atleast $100 to our casino.");
            while(num.hasNextInt() == false)
            {
                try
                {
                    bankRoll = Integer.parseInt(num.nextLine());
                }
                catch(NumberFormatException e)
                {
                    System.out.println("I'm sorry. Please tell me the amount of money \nyou have to the nearest whole dollar.\n");
                }
            }
            bankRoll = num.nextInt();
        }
        System.out.println("How many decks are we playing with? ");
        theNumDecks = 0;
        while(num.hasNextInt() == false)
        {
            try
            {
                theNumDecks = Integer.parseInt(num.nextLine());
            }
            catch(NumberFormatException e)
            {
                System.out.println("I'm sorry. Please tell me the number of decks \nyou have to the nearest deck.\n");
            }
        }
        theNumDecks = num.nextInt();
        while(theNumDecks < 5)
        {
            System.out.println("I'm sorry, you must use atleast 5 decks to play blackjack in our casino.");
            while(num.hasNextInt() == false)
            {
                try
                {
                    theNumDecks = Integer.parseInt(num.nextLine());
                }
                catch(NumberFormatException e)
                {
                    System.out.println("I'm sorry. Please tell me the number of decks \nyou have to the nearest deck.\n");
                }
            }
            
            theNumDecks = num.nextInt();
        }
        theNumDecks = 10;
        hand = new Hand();
        shoe = new Shoe(theNumDecks);
        numCardsLeft = shoe.numCardsInShoe();
        reshuffleNum = (int)(numCardsLeft * 0.2);
        Player thePlayer = new Player(playerName, bankRoll);
       //Player thePlayer = new Player("playerName", 1000);
        player = thePlayer;
        handsWon = 0;
        handsLost = 0;
        handsTied = 0;
        this.startGame();
        
    }
    
    /*
     * starts the game by reshuffling and beginning the methods necessary to begin the game 
     */
    public void startGame()
    {
        
        //shoe = new Shoe(10);
        boughtInsurance = false;
        shoe.reshuffle(theNumDecks);
        player.getHand().clearHand();
        this.getHand().clearHand();
        player.placeBet();
        //player.hit(new Card("Clubs", "Two"));
        //this.hit(new Card("Clubs", "King"));
        //player.hit(new Card("Clubs", "Four"));
        //this.hit(new Card("Clubs", "Ace"));
        player.hit(shoe);
        this.getHand().addCard(shoe);
        player.hit(shoe);
        this.getHand().addCard(shoe);
        System.out.println("Your hand: ");
        System.out.println(player.getHand().toString());
        System.out.println("Your cards' total value: " + player.getHand().getValue() + "\n");
        this.checkBlackJack();
    }
    
    /**Returns the shoe of the dealer
     * @return shoe
     */
    public Shoe getShoe()
    {
        return shoe;
    }
    
    
    /**Returns the hand of the dealer
     * @return hand
     */
    public Hand getHand()
    {
        return hand;
    }
     
    /*
     * adds a card to the dealer's hand if the amount is less than 17
     */
    public void hit()
    {
        if(numCardsLeft < reshuffleNum)
        {
            shoe.reshuffle(theNumDecks);
        }
        while(this.getHand().getValue() < 16)
        {
            hand.addCard(shoe);
        }
        this.stand();
    }
    
    /*
     * TESTER METHOD!!!!!!!!!!!
     * adds a specific card to the hand
     * @param Card card1
     */
    public void hit(Card card1)
    {
        hand.addCard(card1);    
    }
    
    /*
     * makes the dealer stand and displays their hand. then goes to results
     */
    public void stand()
    {
        System.out.println(this.toString());
        results();
    }
    
  
    /*
     * checks if the player has blackjack has blackjack 
     */
    public void checkBlackJack()
    {
        if(player.getHand().isBlackjack())
        {
            System.out.println(this.toString());
            results();
        }
        else
        {
            playersTurn();
        }
    }
    
    /*
     * allows the player to hit stay, or double down
     */
    public void playersTurn()
    {
        String decision = "";
        System.out.println("The dealer's second card is:\n" + this.getHand().getCard(1) + "\n");
        Scanner playerDecision = new Scanner(System.in);
        if(this.getHand().getCard(1).getRank().equalsIgnoreCase("Ace"))
        {
            System.out.println("The dealer has an ace!\nWould you like to purchase insurance for the possibility of the dealer having blackjack?");
            decision = playerDecision.nextLine();
            while(!(decision.equalsIgnoreCase("yes") || decision.equalsIgnoreCase("no")))
            {
                System.out.println("\nThat is not a valid option.\nPlease say yes or no.");
                decision = playerDecision.nextLine();
            }
            if(decision.equalsIgnoreCase("yes"))
            {
                boughtInsurance = true;
                int insurance = player.getBet()/2;
                System.out.println("Your insurance is $" + insurance + ".\nIf the dealer has blackjack, you'll break even on the hand. Otherwise, you lose your insurance.");
            }
            else
            {
                System.out.println("You chose not to buy insurance.");  
            }
            System.out.println("Now, back to the game!\n");
        }
        if(this.getHand().isBlackjack())
        {
            System.out.println(this.toString());
            results();
        }
        System.out.println("\nWould you like to hit, stand, or double down?");
        decision = playerDecision.nextLine();
        while(!(decision.equalsIgnoreCase("double down") || decision.equalsIgnoreCase("hit") || decision.equalsIgnoreCase("stand")))
        {
            System.out.println("\nThat is not a valid option.\nWould you like to hit, stand, or double down?");
            decision = playerDecision.nextLine();
        }
        if(decision.equalsIgnoreCase("double down"))
        {
            if(player.getBet() > player.bankRoll)
            {
                System.out.println("I'm sorry, you don't have enough money to double down.");
                System.out.println("Would you like to hit or stand?");
                decision = playerDecision.nextLine();
                while(!(decision.equalsIgnoreCase("hit") || decision.equalsIgnoreCase("stand")))
                {
                    System.out.println("\nThat is not a valid option.\nWould you like to hit or stand?");
                    decision = playerDecision.nextLine();
                }
            }
            else
            {
                player.doubleDown(shoe);
                this.stand();
            }
        }
        if(decision.equalsIgnoreCase("hit"))
        {
            while((decision.equalsIgnoreCase("hit")) && (player.getHand().getValue() < 21))
            {
                if(numCardsLeft < reshuffleNum)
                {
                    shoe.reshuffle(theNumDecks);
                }
                player.hit(this.getShoe());
                System.out.println(player.toString());
                if(player.getHand().getValue() < 21)
                {
                    System.out.println("Would you like to hit or stand?");
                    decision = playerDecision.nextLine();
                }
            }
            dealersTurn();
            
        }
        if(decision.equalsIgnoreCase("stand"))
        {
            player.stand();
            dealersTurn();
        }
        
        
    }
    
    /*
     * makes the dealer hit
     */
    public void dealersTurn()
    {
        this.hit();
    }
    
    /*
     * This adjusts the bankroll with the results of the round and calls playAgain
     */
    public void results()
    {
        if(boughtInsurance == true)
        {
            if(this.getHand().isBlackjack() == false)
            {
                int insurance = player.getBet() / 2;
                player.bankRoll -= insurance;
                System.out.println("You bought insurance, but unfortunately, the dealer did not have blackjack.\nWe subtracted $" + insurance + " from your balance.\n Your new balance is $" + player.bankRoll);
            }
        }
        resultsInfo();
        playAgain();
    }
    /** 
     * a method that stores the repetitive information in results
     */
    public void resultsInfo()
    {
        if((boughtInsurance == true) && (player.getHand().isBlackjack() && this.getHand().isBlackjack()))
        {
            int pBet = player.getBet();
            player.bankRoll += pBet;
            System.out.println("You and the dealer both had blackjack! You had insurance so your bet was returned back to your account.\nYou have $" + player.bankRoll + ".");
            handsTied++;
        }
        else if((boughtInsurance == false) && (player.getHand().isBlackjack() && this.getHand().isBlackjack()))
        {
            int pBet = player.getBet();
            player.bankRoll += pBet;
            System.out.println("You and the dealer both had blackjack! Your bet was returned back to your account.\nYou have $" + player.bankRoll + ".");
            handsTied++;
        }
        else if((boughtInsurance == true) && (this.getHand().isBlackjack()))
        {
            int pBet = player.getBet();
            player.bankRoll += pBet;
            System.out.println("The dealer had blackjack but luckily, you had insurance!");
            System.out.println("Your bet was returned back to your account.\nYou have $" + player.bankRoll + ".");
            handsWon++;
        }
        else if(player.getHand().isBust() && this.getHand().isBust())
        {
            int pBet = player.getBet();
            player.bankRoll += pBet;
            System.out.println("You and the dealer both busted! Your bet was returned back to your account.\nYou have $" + player.bankRoll + ".");
            handsTied++;
        }
        else if((player.getHand().getValue() == this.getHand().getValue()))
        {
            int pBet = player.getBet();
            player.bankRoll += pBet;
            System.out.println("You and the dealer tied! Your bet was returned back to your account.\nYou have $" + player.bankRoll + ".");
            handsTied++;
        }
        else if(player.getHand().isBlackjack())
        {
            int pBet = player.getBet();
            player.bankRoll += pBet;
            pBet *= 1.5;
            player.bankRoll += pBet;
            System.out.println("Congratulations! You got blackjack!\nYou won $" + pBet + "! You now have $" + player.bankRoll + ".");
            handsWon++;
        }
        else if(player.getHand().isFiveCardCharlie())
        {
            int pBet = player.getBet();
            player.bankRoll += pBet;
            pBet *= 1.5;
            player.bankRoll += pBet;
            System.out.println("Congratulations! You got five card charlie!\nYou won $" + pBet + "! You now have $" + player.bankRoll + ".");
            handsWon++;
        }
        else if((player.getHand().getValue() > this.getHand().getValue() && player.getHand().isBust() == false) || (this.getHand().isBust() && player.getHand().isBust() == false)) 
        {
            int pBet = player.getBet();
            player.bankRoll += pBet;
            player.bankRoll += pBet;
            System.out.println("Congratulations! You beat the dealer's hand!\nYou won $" + pBet + "! You now have $" + player.bankRoll + ".");
            handsWon++;
        }
        else if(player.getHand().getValue() > 21)
        {
            int pBet = player.getBet();
            System.out.println("Oh no! You busted!\nYou lost $" + pBet + "! You now have $" + player.bankRoll + ".");
            handsLost++;
        }
        else
        {
            int pBet = player.getBet();
            System.out.println("Oh no! The dealer won.\nYou lost $" + pBet + "! You now have $" + player.bankRoll + ".");
            handsLost++;
        }
    }
    /**
     * has the user choose whether or not to play again and calls startGame()
     */
    public void playAgain()
    {
        Scanner playAgainDecision = new Scanner(System.in);
        System.out.println("Would you like to play again?");
        String playAgainDec = playAgainDecision.nextLine();
        while(!(playAgainDec.equalsIgnoreCase("yes")) && !(playAgainDec.equalsIgnoreCase("no")))
        {
            System.out.println("I'm sorry, this is not a valid option. Please say yes or no.");
            playAgainDec = playAgainDecision.nextLine();

        }
        if(playAgainDec.equalsIgnoreCase("yes") && player.bankRoll > 10)
        {
            System.out.println("\n");
            System.out.println("Just so you know, " + playerName + " you have $" + player.bankRoll + " right now.\n");
            this.startGame();
        }
        if(playAgainDec.equalsIgnoreCase("yes") && player.bankRoll <= 10)
        {
            this.lowMoney();
            System.out.println("\n");
            this.startGame();
        }
        if(playAgainDec.equalsIgnoreCase("no"))
        {
            endGame();
        }
    }
    /*
     * This is the text of the house rules.
     */
    public void houseRulesText()
    {
        System.out.println("\n");
        System.out.println("************************************************************");
        System.out.println("|  The objective of blackjack is to get the value of your  |");
        System.out.println("|  hand as close to 21 as possible without going over. The |");
        System.out.println("|  value of the face cards are 10 other than ace. The ace  |");
        System.out.println("| is worth 11 unless a value of 11 will cause your hand to |");
        System.out.println("|  bust (exceed 21). In this case, the ace is worth 1. The |");
        System.out.println("| dealer must draw until 17 and then stand (end the turn). |");
        System.out.println("|  Before each round, you place a bet on your hand. If you |");
        System.out.println("|   get blackjack or five card charlie (getting 5+ cards   |");
        System.out.println("|   in your hand without busting), you earn back 1.5x your |");
        System.out.println("|  original bet. If you get a higher value than the dealer |");
        System.out.println("| or the dealer busts, you earn back 1x your original bet. |");
        System.out.println("|  If the dealer gets blackjack, gets a higher amount than |");
        System.out.println("| you, or you bust, you lose your bet. If you tie, you get |");
        System.out.println("|  your bet amount back. On your first turn, you are given |");
        System.out.println("|    the option to double down. This doubles your bet on   |");
        System.out.println("|  the condition that you draw exactly one more card and   |");
        System.out.println("| then stand. fter each round, you are given the option to |");
        System.out.println("| keep playing or to take your money and leave. If you run |");
        System.out.println("|  out of money, you can give us more or leave the casino. |");
        System.out.println("|                Good luck and have fun!                   |");
        System.out.println("************************************************************");
        System.out.println("\n");    
    }
    
    /*
     * tells user their ending balance and how many wins/losses they had. 
     * Also calls System.exit(1) to stop the program
     */
    public void endGame()
    {
        if(handsWon == 1 && handsLost == 1 && handsTied == 1)
        {
            System.out.println("\n\nYou're leaving with $" + player.bankRoll + ".\nYou won " + handsWon + " hand, lost " + handsLost + " hand, and tied " + handsTied + " hand. Come back soon!");
        }
        else if(handsWon == 1)
        {
            System.out.println("\n\nYou're leaving with $" + player.bankRoll + ".\nYou won " + handsWon + " hand, lost " + handsLost + " hands, and tied " + handsTied + " hands. Come back soon!");
        }
        else if(handsLost == 1)
        {
            System.out.println("\n\nYou're leaving with $" + player.bankRoll + ".\nYou won " + handsWon + " hands, lost " + handsLost + " hand, and tied " + handsTied + " hand. Come back soon!");
        }
        else if(handsTied == 1)
        {
           System.out.println("\n\nYou're leaving with $" + player.bankRoll + ".\nYou won " + handsWon + " hands, lost " + handsLost + " hands, and tied " + handsTied + " hand. Come back soon!");
        }
        else
        {
            System.out.println("\n\nYou're leaving with $" + player.bankRoll + ".\nYou won " + handsWon + " hands, lost " + handsLost + " hands, and tied " + handsTied + " hands. Come back soon!");
        }
        
        System.exit(1);
        
    }
    
    /*
     * Allows the user to add more money if they have at or less than $10
     */
    public void lowMoney()
    {
        
        if(player.bankRoll <= 10)
        {
            Scanner addMoney = new Scanner(System.in);
            System.out.println("\nYou seem to be running low on cash.\nWould you like to add more money to your balance?");
            String add = addMoney.nextLine();
            if(add.equalsIgnoreCase("no"))
            {
                System.out.println("If you don't add money to your balance, I will have to ask you to leave.\nWould you like to add more money to your balance now?");
                add = addMoney.nextLine();
                while(!((add.equalsIgnoreCase("no")) || (add.equalsIgnoreCase("yes"))))
                {
                    System.out.println("This is not a valid option. Please say yes or no.");
                    add = addMoney.nextLine();
                }
                if(add.equalsIgnoreCase("no"))
                {
                    endGame();
                }
            }
            if(add.equalsIgnoreCase("yes"))
            {
                System.out.println("Alright, how much money would you like to add?");
                int moneyAdded = 0;
                while(addMoney.hasNextInt() == false)
                {
                    try
                    {
                        moneyAdded = Integer.parseInt(addMoney.nextLine());
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("I'm sorry. Please tell me the amount of money\nyou would like to add to the nearest whole dollar.\n");
                    }
                }
                moneyAdded = addMoney.nextInt();
                while(moneyAdded <= 0)
                {
                    System.out.println("Please add an amount greater than $0.");
                    while(addMoney.hasNextInt() == false)
                    {
                        try
                        {
                            moneyAdded = Integer.parseInt(addMoney.nextLine());
                        }
                        catch(NumberFormatException e)
                        {
                            System.out.println("I'm sorry. Please tell me the amount of money\nyou would like to add to the nearest whole dollar.\n");
                        }
                    }
                    moneyAdded = addMoney.nextInt();
                    
                }
                player.bankRoll += moneyAdded;
                System.out.println("Your new balance is: " + player.bankRoll);
            }
            else 
            {
                while(!((add.equalsIgnoreCase("no")) || (add.equalsIgnoreCase("yes"))))
                {
                    System.out.println("This is not a valid option. Please say yes or no.");
                    add = addMoney.nextLine();
                }
            }
            
        }
    }
    
    /*
     * toString with the dealer's hand 
     * @return String
     */
    public String toString()
    {
       String str = "\nDealer's Hand:\n" + this.getHand().toString() + "\nDealer's cards' total value: " + this.getHand().getValue() + "\n";
       return str;
    }
}
