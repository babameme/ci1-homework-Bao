package touhou.players;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import touhou.ability.Ability;

import java.util.Random;

public class SpellSpawner{
    private FrameCounter spawnCounter;
    Random random;
    int type;
    private Ability playerAbility;
    private Vector2D position;

    public SpellSpawner(Ability ability, Vector2D position) {
        super();
        type = 1;
        this.playerAbility = ability;
        //this.position = position;
    }

    public void cast(int x, int y, int dx, int dy){
        //System.out.println(Integer.toString(x)+ " -- " + Integer.toString(y));
        PlayerSpell playerSpell = new PlayerSpell();
        playerSpell.getPosition().set(position.add(x,y));
        playerSpell.getDirection().set(dx,dy);
        GameObject.add(playerSpell);
    }

    void castType1(){
        cast(0, -20, 0, -10);
    }

    void castType2(){
        cast(-10, -20, 0, -10);
        cast(10, -20, 0, -10);
    }

    void castType3(){
        for (int i = -1; i <=1 ; i++) {
            cast(i * 10, -20, i, -10);
        }
    }

    public void shoot(Vector2D position){
        if (playerAbility.getPower() < 10){
            type = 1;
        }
        else if (playerAbility.getPower() < 20){
            type = 2;
        }
        else {
            type = 3;
        }
        this.position = position;
        switch (type){
            case 1 :
                castType1();
                break;
            case 2:
                castType2();
                break;
            case 3:
                castType3();
                break;
        }
    }

    public void setType(int type) {
        this.type = type;
    }
}
