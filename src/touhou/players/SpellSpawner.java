package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPool;
import touhou.ability.Ability;

public class SpellSpawner{
    private int type;
    private Ability ability;
    private Vector2D screenPosition;

    public SpellSpawner(){

    }

    public void setScreenPosition(Vector2D screenPosition) {
        this.screenPosition = screenPosition;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public void run() {
        if (ability.power <= 5){
            type = 0;
        }
        else if (ability.power <= 15){
            type = 1;
        }
        else if (ability.power <= 25){
            type = 2;
        }
        else if (ability.power <= 35){
            type = 3;
        }
        else if (ability.power <= 45){
            type = 4;
        }
        else{
            type = 5;
        }
        shoot();
    }

    private void shoot() {
        System.out.println("####" + type);
        switch (type){
            case 0 :
                PlayerSpell playerSpell = GameObjectPool.recycle(PlayerSpell.class);
                playerSpell.setDefault(0);
                playerSpell.getPosition().set(this.screenPosition.add(0, -30));
                break;
            case 1 :
                playerSpell = GameObjectPool.recycle(PlayerSpell.class);
                playerSpell.setDefault(1);
                playerSpell.getPosition().set(this.screenPosition.add(0, -30));
                break;
            case 2:
                PlayerSpell spell1 = GameObjectPool.recycle(PlayerSpell.class);
                PlayerSpell spell2 = GameObjectPool.recycle(PlayerSpell.class);
                spell1.setDefault(0);
                spell2.setDefault(0);
                spell1.getPosition().set(this.screenPosition.add(-20, -30));
                spell2.getPosition().set(this.screenPosition.add(20, -30));
                break;
            case 3:
                spell1 = GameObjectPool.recycle(PlayerSpell.class);
                spell2 = GameObjectPool.recycle(PlayerSpell.class);
                spell1.setDefault(1);
                spell2.setDefault(1);
                spell1.getPosition().set(this.screenPosition.add(-20, -30));
                spell2.getPosition().set(this.screenPosition.add(20, -30));
                break;
            case 4:
                spell1 = GameObjectPool.recycle(PlayerSpell.class);
                spell2 = GameObjectPool.recycle(PlayerSpell.class);
                PlayerSpell spell3 = GameObjectPool.recycle(PlayerSpell.class);
                spell1.setDefault(1);
                spell2.setDefault(1);
                spell3.setDefault(1);
                spell1.getPosition().set(this.screenPosition.add(-25, -30));
                spell2.getPosition().set(this.screenPosition.add(25, -30));
                spell3.getPosition().set(this.screenPosition.add(0, -30));
                break;
        }
    }
}
