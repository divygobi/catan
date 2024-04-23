package com.mygdx.catan;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Player {
    HashMap<Hex.Resource, Integer> resourceCards;
    ArrayList<String> devCards;
    int turn;
    private Color color;
    int playerId;
    //When a dice is rolled, go over all the vertices, check the hexes and then get dev cards based on that
    private ArrayList<Vertex> verticesOwned;
    private ArrayList<Edge> edgesOwned;
    Label titleLabel;
    Label woodLabel;
    Label wheatLabel;
    Label rockLabel;
    Label clayLabel;
    Label sheepLabel;
    Skin skin = new Skin(Gdx.files.internal("uiskin.json"));


    public Player(int playerId) {
        verticesOwned = new ArrayList<>();
        edgesOwned = new ArrayList<>();
        this.playerId = playerId;
        this.turn = 0;
        resourceCards = new HashMap<>();
        resourceCards.put(Hex.Resource.WHEAT, 10);
        resourceCards.put(Hex.Resource.ROCK, 10);
        resourceCards.put(Hex.Resource.WOOD, 10);
        resourceCards.put(Hex.Resource.CLAY, 10);
        resourceCards.put(Hex.Resource.SHEEP, 10);
        switch (playerId) {
            case 1:
                this.color = Color.BLUE;
                break;
            case 2:
                this.color = Color.RED;
                break;
            case 3:
                this.color = Color.LIME;
                break;
            case 4:
                this.color = Color.WHITE;
                break;
        }

        this.titleLabel = new Label("Player " + this.playerId + "'s resources:", skin);
        titleLabel.setColor(color);
        this.woodLabel = new Label("Wood:" + this.resourceCards.get(Hex.Resource.WOOD), skin);
        woodLabel.setColor(color);
        this.wheatLabel = new Label("Wheat:" + this.resourceCards.get(Hex.Resource.WHEAT), skin);
        wheatLabel.setColor(color);
        this.rockLabel = new Label("Rock:" + this.resourceCards.get(Hex.Resource.ROCK), skin);
        rockLabel.setColor(color);
        this.clayLabel = new Label("Clay:" + this.resourceCards.get(Hex.Resource.CLAY), skin);
        clayLabel.setColor(color);
        this.sheepLabel = new Label("Sheep:" + this.resourceCards.get(Hex.Resource.SHEEP), skin);
        sheepLabel.setColor(color);
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

    //state vars
    private boolean isPlacingRoad;
    private boolean isPlacingSettlement;
    private boolean isPlacingCity;


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void addResource(int amount, Hex.Resource resource) {
        this.resourceCards.put(resource, this.resourceCards.get(resource) + amount);
        updateTextLabels(resource);
    }

    public void updateTextLabels(Hex.Resource resource) {
        switch (resource) {
            case WOOD:
                this.woodLabel.setText("Wood:" + this.resourceCards.get(Hex.Resource.WOOD));
                break;
            case WHEAT:
                this.wheatLabel.setText("Wheat:" + this.resourceCards.get(Hex.Resource.WHEAT));
                break;
            case ROCK:
                this.rockLabel.setText("Rock:" + this.resourceCards.get(Hex.Resource.ROCK));
                break;
            case CLAY:
                this.clayLabel.setText("Clay:" + this.resourceCards.get(Hex.Resource.CLAY));
                break;
            case SHEEP:
                this.sheepLabel.setText("Sheep:" + this.resourceCards.get(Hex.Resource.SHEEP));
                break;
            default:
                break;
        }
    }

    public void subtractResource(int amount, Hex.Resource resource) {
        this.resourceCards.put(resource, this.resourceCards.get(resource) - amount);
        updateTextLabels(resource);
    }

    public void colonizeEdge(Edge e) {
        this.edgesOwned.add(e);
        subtractResource(1,Hex.Resource.WOOD);
        subtractResource(1,Hex.Resource.CLAY);

    }

    public void colonizeVertex(Vertex v){
        this.verticesOwned.add(v);
        if(!v.hasCity() && !v.hasSettlement()) {
            subtractResource(1, Hex.Resource.WOOD );
            subtractResource(1, Hex.Resource.CLAY );
            subtractResource(1, Hex.Resource.WHEAT );
            subtractResource(1, Hex.Resource.SHEEP );
            v.setHasSettlement(true);
        }
        else if(!v.hasCity()){
            subtractResource(2, Hex.Resource.WHEAT );
            subtractResource(3, Hex.Resource.ROCK );
            v.setHasCity(true);
        }
    }

    public Boolean checkRoadPossible(){
        return this.resourceCards.get(Hex.Resource.WOOD) >= 1 && this.resourceCards.get(Hex.Resource.CLAY) >= 1;
    }

    public Boolean checkSettlementPossible(){
        return this.resourceCards.get(Hex.Resource.WOOD) >= 1 && this.resourceCards.get(Hex.Resource.CLAY) >= 1 &&
                this.resourceCards.get(Hex.Resource.WHEAT) >= 1 && this.resourceCards.get(Hex.Resource.SHEEP) >= 1;
    }

    public Boolean checkCityPossible(){
        return this.resourceCards.get(Hex.Resource.WHEAT) >= 2 && this.resourceCards.get(Hex.Resource.ROCK) >= 3;
    }

    public Boolean checkDevPossible(){
        return this.resourceCards.get(Hex.Resource.WHEAT) >= 1 && this.resourceCards.get(Hex.Resource.ROCK) >= 1 &&
                this.resourceCards.get(Hex.Resource.SHEEP) >= 1;
    }

    public ArrayList<Edge> getEdgesOwned(){
        return this.edgesOwned;
    }

    public ArrayList<Vertex> getVerticesOwned(){
       return this.verticesOwned;
    }


}
