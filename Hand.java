/**
 * represents a shoe in blackjack
 */
import java.util.ArrayList;
public class Hand
{
    private ArrayList<Card> hand;
    private int value;
    
    /**
    * constructs the hand class and sets value to 0
    */
    public Hand()
    {
        hand = new ArrayList<Card>();
        value = 0;
    }
    
    
    /**
    * returns arraylist of cards in hand
    * @return hand
    */
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    /**
    * adds selected User Inputed card to hand 
    * @param Card
    */
    public void addCard(Card c)
    {
        hand.add(c);
    }
    
    /**
    * adds cards to hand from Shoe
    * @param Shoe s1
    */
    public void addCard(Shoe s1)
    {
        hand.add(s1.drawCard());
        
    }
    
    /**
    * clears the hand
    */
    public void clearHand()
    {
        for(int i = hand.size() - 1; i >= 0; i--)
        {
            hand.remove(i);
        }
        value = 0;
    }
    
    
    /**
    * returns the number of cards in the hand
    * @return int hand.size()
    */
    public int getNumCards()
    {
        return hand.size();
    }
    
    /**
    * returns the current card
    * @param int i
    * @return Card 
    */
    public Card getCard(int i)
    {
        return hand.get(i);
    }
    
    
    /**
    * returns the value of cards in the deck and calls switchAce() if value is > 21
    * @return int of the value of the hands in the deck
    */
    public int getValue()
    {
        int value = 0;
        for(int i = 0; i < hand.size(); i++)
        {
            value += this.getCard(i).getValue();
        }
        if(value > 21)
        {
            switchAce();
            value = 0;
            for(int j = 0; j < hand.size(); j++)
            {
                value += this.getCard(j).getValue();
            }
        }
        
        
        return value;
    }
    
    /**
    * returns whether or not the hand has a blackjack
    * @return boolean blackjack
    */
    public boolean isBlackjack()
    {
        if(getNumCards() == 2 && getValue() == 21)
        {
            return true;
        }
        return false;
    }
    
    /**
    * returns whether or not the hand is a five card charlie
    * @return boolean true or false
    */
    public boolean isFiveCardCharlie()
    {
        if(getNumCards() >= 5 && getValue() <= 21)
        {
            return true;
        }
        return false;
    }
    
    /**
    * returns whether or not the hand will bust
    * @return boolean true or false
    */
    public boolean isBust()
    {
        if(getValue() > 21)
        {
            return true;
        }
        return false;
    }
    
    /**
    * returns how many aces the hand has
    * @return int aces
    */
    public int numAces()
    {
        int aces = 0;
        for(int i = 0; i < hand.size(); i++)
        {
            if(this.getCard(i).getRank().equals("Ace"))
            {
                aces += 1;
            }
        }
        return aces;
    }
    
    /*
    *switches the ace from 11 to 1 if the user chooses to or if not switching will make the hand bust
    * @param boolean makeSwitch
    */
    
    public void switchAce()
    {
        if(numAces() > 0)
        {
            int count = numAces();
            while(count > 0)
            {
                count--;
                int location = 0;
                for(int i = 0; i < this.getNumCards(); i++)
                { 
                    if(this.getCard(i).getValue() == 11)
                    {
                        location = i;
                    }
                }
                this.getCard(location).switchAce();
                    
            }
        }
            
            
    }
    
    
    
     /**
    *returns toString of the cards in the hand
    *@return a string
    */
    public String toString()
    {
       String str = "";
       for(int i = 0; i < this.getNumCards(); i++)
       {
           str += this.getCard(i).toString() + "\n";
       }
       return str;
    }
      
}
