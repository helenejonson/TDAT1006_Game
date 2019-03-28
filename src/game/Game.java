package game;

import java.util.ArrayList;
import Main.*;
import Database.*;

public class Game {
    private ArrayList<Creature> creatures = new ArrayList<>();
    public game.Character playerCharacter;
    private Database db = Main.db;
    private int turn = 0;

    public Game(){
        creatures = db.fetchCreaturesFromLobby();
        for (int i = 0; i < this.creatures.size(); i++){
            if (this.creatures.get(i).getPlayerId() == Main.user.getPlayerId()){
                playerCharacter = (game.Character) this.creatures.get(i);
            }
        }
    }

    public void init(){

    }

    public void update(){
        pushCreatureData();
        updateCreatureData();
    }

    public void updateCreatureData(){
        turn = db.fetchPlayerTurn();
        for (int i = 0; i < creatures.size(); i++){
            int playerId = creatures.get(i).getPlayerId();
            ArrayList<Integer> newPos = db.fetchPlayerPos(playerId);
            int newHp = db.fetchPlayerHp(playerId);
            if (playerId != Main.user.getPlayerId()){
                creatures.get(i).setNewPos(newPos.get(0), newPos.get(1));
            }
            creatures.get(i).setHp(newHp);
        }
    }

    public void pushCreatureData(){
        int playerId = playerCharacter.getPlayerId();
        int posX = playerCharacter.getxPos();
        int posY = playerCharacter.getyPos();
        db.setPos(posX, posY, playerId);
        int hp = playerCharacter.getHp();
        db.setHp(hp, playerId);
    }

    public Creature getCreature(int index){
        return this.creatures.get(index);
    }

    public int getAmountOfCreatures(){
        return this.creatures.size();
    }

    public ArrayList<Integer> getPos(int index){
        ArrayList<Integer> pos = new ArrayList<>();
        pos.add(this.creatures.get(index).getxPos());
        pos.add(this.creatures.get(index).getyPos());
        return pos;
    }

    public boolean isYourTurn(){
        if(creatures.get(turn % creatures.size()).getPlayerId() == Main.user.getPlayerId()){
            return true;
        }
        return false;
    }

    public void endTurn(){
        turn++;
        db.incrementPlayerTurn(turn);
    }
}
