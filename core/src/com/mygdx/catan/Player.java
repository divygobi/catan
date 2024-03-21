package com.mygdx.catan;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Player {
    HashMap<String, Integer> resourceCards;
    ArrayList<String> devCards;

    private Color color;
    int playerId;
    //When a dice is rolled, go over all the vertices, check the hexes and then get dev cards based on that
    ArrayList<Vertex> verticesOwned;

    public Player(int playerId){
        this.playerId = playerId;
        switch (playerId){
            case 1:
                this.color = Color.BLUE;
                break;
            case 2:
                this.color = Color.RED;
                break;
        }
    }

    Boolean currentlyPlaying;
    private int roads;
    private int longestConnectedRoad;
    private int settlements;
    private int cities;

    private int victoryPoints;

    private int knightCards;

    private boolean hasLongestRoad;
    private boolean hasLargestArmy;


    public void placeSettlement(){

    }

    public void placeRoad(){

    }

    public void upgradeSettlementToCity(){

    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
