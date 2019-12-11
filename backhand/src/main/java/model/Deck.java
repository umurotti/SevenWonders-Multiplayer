/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author akın
 */
public class Deck {
    private List<Card> listOfCard;

    public Deck(List<Card> listOfCard) {
        this.listOfCard = listOfCard;
    }

    public Card[] prepareCards(int noOfPlayers) {
        int noOfCards = 0;
        for(int a = 0; a < listOfCard.size();a++)
        {
            if(listOfCard.get(a).getMinPlayerNo() <= noOfPlayers)
                noOfCards++;
        }
        Card[] cards = new Card[noOfCards];
        int count = 0;
        for(int a = 0; a<listOfCard.size(); a++)
        {
            if(listOfCard.get(a).getMinPlayerNo() <= noOfPlayers)
                cards[count++] = listOfCard.get(a);
        }
        return cards;
    }
}
