package aufgabe4;

import java.util.Random;

public abstract class Card {

    protected final Suit suit;
    protected final Rank rank;

    protected Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }


    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS;
        private static final Random PRNG = new Random();

        public static Suit randomSuit()  {
            Suit[] directions = values();
            return directions[PRNG.nextInt(directions.length)];
        }
    }

    public enum Rank {
        SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

        private static final Random PRNG = new Random();

        public static Rank randomRank()  {
            Rank[] directions = values();
            return directions[PRNG.nextInt(directions.length)];
        }
    }


    public String toString() {
        String color = "";
        if (this.suit == Suit.DIAMONDS || this.suit == Suit.HEARTS) {
            color = "rot";
        } else {
            color = "schwartz";
        }
        return this.rank + " of " + this.suit + " (" + color + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Card c)) {
            return false;
        }

        return this.suit == c.suit && this.rank == c.rank;
    }

    //Getter und setter
    public final Suit getSuit() {
        return suit;
    }

    public final Rank getRank() {
        return rank;
    }

}
