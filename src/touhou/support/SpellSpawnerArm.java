package touhou.support;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import touhou.ability.Ability;
import touhou.players.PlayerSpell;

import java.util.Random;

public class SpellSpawnerArm extends GameObject{
    int type;
    private Ability playerAbility;
    private FrameCounter spawnCounter;

    public SpellSpawnerArm(Ability ability) {
        super();
        type = 1;
        this.playerAbility = ability;
        spawnCounter = new FrameCounter(20);
        //this.position = position;
    }

    public void cast(int x, int y, int dx, int dy){
        //System.out.println(Integer.toString(x)+ " -- " + Integer.toString(y));
        EngineArmSpell engineArmSpell = new EngineArmSpell();
        engineArmSpell.getPosition().set(screenPosition.add(x,y));
        engineArmSpell.getDirection().set(dx,dy);
        GameObject.add(engineArmSpell);
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

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if (playerAbility.getPower() <= 5){
            type = 1;
        }
        else if (playerAbility.getPower() <= 15){
            type = 2;
        }
        else {
            type = 3;
        }
        if (spawnCounter.run()){
            spawnCounter.reset();
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
    }

    public void setType(int type) {
        this.type = type;
    }
}
