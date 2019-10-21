/**
 * represents a shoe in blackjack
 */
import java.util.ArrayList;
import java.util.Random;
public class Shoe
{
    private ArrayList<Card> shoe;
    private int numDecks;
    private int numCards;
    
    /**
    * constructs the shoe class
    * @param theNumDecks: int
    */
    public Shoe(int theNumDecks)
    {
        shoe = new ArrayList<Card>();
        numDecks = theNumDecks;
        for(int i = 0; i < numDecks; i++)
        {
            Deck d = new Deck();
            for(int j = 0; j < d.numCardsInDeck(); j++)
            {
                shoe.add(d.getDeck().get(j));
            }
        }
        numCards = shoe.size();
    }

    
    /**
    * Returns the integer number of cards in the shoe
    * @return int
    */
    public int numCardsInShoe()
    {
        return shoe.size();
    }
    
    /**
    * Returns the next card in the shoe and removes it
    * @return Card drawn
    */
    
    public Card drawCard()
    {
        Card drawn = shoe.remove(0);
        return drawn;
    }
    
    /**
    * Returns the card at an integer value
    * @return shoe.get(i);
    */
    public Card getCardAt(int i)
    {
        return shoe.get(i);
    }
    /**
    * Shuffles the deck of cards by using a random number
    */
    
    public void shuffle()
    {
        for(int j = 0; j < 10; j++)
        {
            for(int i = 0; i < numCardsInShoe(); i++)
            {
                int a = (int)(Math.random() * numCardsInShoe());
                Card first = getCardAt(a);
                Card second = getCardAt(i);
                shoe.set(i, first);
                shoe.set(a, second);
            }
        }
        
    }
    
    /**
    * Reshuffles the deck
    */
    
    public void reshuffle(int numberOfDecks)
    {
        shoe.clear();
        for(int i = 0; i < numberOfDecks; i++)
        {
            Deck d = new Deck();
            for(int j = 0; j < 52; j++)
            {
                shoe.add(d.getDeck().get(j));
            }
        }
        shuffle();
    }
    
    
    
     /**
    * toString of the class returns the card drawn and number of card
    *@return a string
    */
    public String toString()
    {
       String str = "";
       for(int i = 0; i < shoe.size(); i++)
       {
           str += shoe.get(i) + "\n";
       }
       return str;
    }

}
