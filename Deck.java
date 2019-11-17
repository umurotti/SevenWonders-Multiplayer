/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author umur
 */
public class Deck {
    private List<Card> listOfCard;
    
    public Deck(List<Card> listOfCard) {
        this.listOfCard = listOfCard;
    }
    
    public Card[] prepareCards(int noOfPlayers) {
        return null;
    }
    
}
