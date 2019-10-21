

/**
* The class Card has all of the information of the card.
* instance varuables are suit: String, and rank: String
* methods are getCard(), getSuit(), getValue(), getRank(), and toString()
*/
public class Card
{
    private String suit;
    private String rank;
    int ace = 11;
    /**
     * Constructs the card of the class
     * @Param theSuit --> suit of the card
     * @Param theRank --> rank of the card
     */
     
    public Card(String theSuit, String theRank)
    {
        suit = theSuit;
        rank = theRank;
    }
    /**
    * Return the suit of the card
    *@return the String suit
    */
    
    public String getSuit()
    {
    	return suit;
    }
    
    /**
    *Return the value of the card and sets the rank
    *@return the int value
    */
    
    public int getValue()
    {
    	if(rank.equalsIgnoreCase("two") || rank.equalsIgnoreCase("2"))
    	{
    	    rank = "Two";
    	    return 2;
    	}
    	else if(rank.equalsIgnoreCase("three") || rank.equalsIgnoreCase("3"))
    	{
    	    rank = "Three";
    	    return 3;
    	}
        	else if(rank.equalsIgnoreCase("four") || rank.equalsIgnoreCase("4"))
    	{
    	    rank = "Four";
        return 4;
        	}
        else if(rank.equalsIgnoreCase("five") || rank.equalsIgnoreCase("5"))
    	{
        	rank = "Five";
    	    return 5;
        }
        else if(rank.equalsIgnoreCase("six") || rank.equalsIgnoreCase("6"))
    	{
    	    rank = "Six";
    	    return 6;
    	}
    	else if(rank.equalsIgnoreCase("seven") || rank.equalsIgnoreCase("7"))
    	{
            rank = "Seven";
        	return 7;
        }
        else if(rank.equalsIgnoreCase("eight") || rank.equalsIgnoreCase("8"))
        {
            rank = "Eight";
            return 8;
        }
        else if(rank.equalsIgnoreCase("nine") || rank.equalsIgnoreCase("9"))
        {
            rank = "Nine";
            return 9;
        }
        else if(rank.equalsIgnoreCase("ten") || rank.equalsIgnoreCase("10"))
        {
            rank = "Ten";
            return 10;
        }
        else if(rank.equalsIgnoreCase("jack") || rank.equalsIgnoreCase("11"))
        {
            rank = "Jack";
            return 10;
        }
        else if(rank.equalsIgnoreCase("queen") || rank.equalsIgnoreCase("12"))
        {
            rank = "Queen";
            return 10;
        }
        else if(rank.equalsIgnoreCase("king") || rank.equalsIgnoreCase("13"))
        {
            rank = "King";
            return 10;
        }
        else if(rank.equalsIgnoreCase("ace") || rank.equalsIgnoreCase("14") || rank.equalsIgnoreCase("1"))
        {
            rank = "Ace";
            return ace;
            
        }
        else
        {
            return -1;
        }
    }
        
    /**
    * switches the value of the ace from 11 to 1
    */
    public void switchAce()
    {
        ace = 1;
    }
    
        
    /**
    * returns the rank of the card as a string
    *@return rank
    */
    public String getRank()
    {
    	if(rank.equalsIgnoreCase("two") || rank.equalsIgnoreCase("2"))
    	{
    	    rank = "Two";
    	    return rank;
    	}
    	else if(rank.equalsIgnoreCase("three") || rank.equalsIgnoreCase("3"))
    	{
        	rank = "Three";
    	    return rank;
    	}
    	else if(rank.equalsIgnoreCase("four") || rank.equalsIgnoreCase("4"))
    	{
            rank = "Four";
            return rank;
        }
        else if(rank.equalsIgnoreCase("five") || rank.equalsIgnoreCase("5"))
        {
            rank = "Five";
            return rank;
        }
        else if(rank.equalsIgnoreCase("six") || rank.equalsIgnoreCase("6"))
        {
            rank = "Six";
            return rank;
        }
        else if(rank.equalsIgnoreCase("seven") || rank.equalsIgnoreCase("7"))
        {
            rank = "Seven";
            return rank;
        }
        else if(rank.equalsIgnoreCase("eight") || rank.equalsIgnoreCase("8"))
        {
            rank = "Eight";
            return rank;
        }
        else if(rank.equalsIgnoreCase("nine") || rank.equalsIgnoreCase("9"))
        {
            rank = "Nine";
            return rank;
        }
        else if(rank.equalsIgnoreCase("ten") || rank.equalsIgnoreCase("10"))
        {
            rank = "Ten";
            return rank;
        }
        else if(rank.equalsIgnoreCase("jack") || rank.equalsIgnoreCase("11"))
        {
            rank = "Jack";
            return rank;
        }
        else if(rank.equalsIgnoreCase("queen") || rank.equalsIgnoreCase("12"))
        {
            rank = "Queen";
            return rank;
        }
        else if(rank.equalsIgnoreCase("king") || rank.equalsIgnoreCase("13"))
        {
            rank = "King";
            return rank;
        }
        else if(rank.equalsIgnoreCase("ace") || rank.equalsIgnoreCase("14") || rank.equalsIgnoreCase("1"))
        {
            rank = "Ace";
            return rank;
        }
        else
        {
            return "-1";
        }
    }
       
    /**
    *The toString of the class
    *@return name, suit, and value in one string
    */
        
    public String toString()
    {
    	return this.getRank() + " of " + this.getSuit() + ". Value: " + getValue();
    }

}


