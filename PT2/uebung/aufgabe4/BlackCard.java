package aufgabe4;

public final class BlackCard extends Card {
    public BlackCard(Suit suit, Rank rank) {
        super(suit, rank);
    }

    public BlackCard() {
        super(Suit.SPADES, Rank.SEVEN);
    }

}
