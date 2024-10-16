package aufgabe4;

public final class RedCard extends Card {

    //He Di
    public RedCard(Suit suit, Rank rank) {
        super(suit, rank);
    }

    public RedCard() {
        super(Suit.randomSuit("red"), Rank.randomRank());
    }

}
