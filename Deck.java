/**
* This class represents a deck in blackjack
*/
import java.util.ArrayList;
public class Deck
{
    ArrayList<Card> deck;
    /**
    * Constructs the deck of the class
    */
    public Deck()
    {
        /*
        Spades A->k
        Diamonds A->k
        Clubs A-> k
        Hearts k-> A
        */
        int numCards = 52;
        deck = new ArrayList<Card>();
        for(int i = 1; i <= 13; i++)
        {
            String num = String.valueOf(i);
            deck.add(new Card("Hearts", num)); 
        }
        for(int i = 1; i <= 13; i++)
        {
            String num = String.valueOf(i);
            deck.add(new Card("Clubs", num)); 
        }
        for(int i = 13; i >= 1; i--)
        {
            String num = String.valueOf(i);
            deck.add(new Card("Diamonds", num)); 
        }
        for(int i = 13; i >= 1; i--)
        {
            String num = String.valueOf(i);
            deck.add(new Card("Spades", num)); 
        }
    }
   
    /**
    * Returns the integer number of cards in the deck
    * @return int
    */
    public int numCardsInDeck()
    {
        return deck.size();
    }
    
    /**
    * Returns the deck
    * @return Arraylist of the deck
    */
    public ArrayList<Card> getDeck()
    {
        return deck;
    }
    
    
    /**
    * Returns the next card in the deck and removes it
    * @return Card drawn
    */
    
    public Card drawCard()
    {
        Card drawn = deck.remove(0);
        return drawn;
    }
    
     /**
    * toString of the class returns the card drawn and number of card
    *@return a string
    */
    public String toString()
    {
       return  "Num Cards: " + numCardsInDeck() + "\nDrawn Card: " + deck.get(0).toString();
    }


}
