interface Participant {
    void initialDeal(Deck deck) throws DeckEmptyException;
    void drawCard(Deck deck) throws DeckEmptyException;
    int calculateHandScore();
}
