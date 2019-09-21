package entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import application.Animation;
import application.Application;

public class Player extends Creature {

    private Animation animDown, animUp, animLeft, animRight;

    public Player(float x, float y) {
        super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 22;
        bounds.y = 44;
        bounds.width = 19;
        bounds.height = 19;
        animDown = new Animation(500, Application.getInstance().getAssets().getAnimatedAssets().get("playerDown").toArray(new BufferedImage[0]));
        animUp = new Animation(500, Application.getInstance().getAssets().getAnimatedAssets().get("playerUp").toArray(new BufferedImage[0]));
        animLeft = new Animation(500, Application.getInstance().getAssets().getAnimatedAssets().get("playerLeft").toArray(new BufferedImage[0]));
        animRight = new Animation(500, Application.getInstance().getAssets().getAnimatedAssets().get("playerRight").toArray(new BufferedImage[0]));
    }

    @Override
    public void tick() {
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        getInput();
        move();
        Application.getInstance().getCamera().centerOnEntity(this);
    }

    @Override
    public void die() {
        System.out.println("You lose");
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (Application.getInstance().getKeyManager().up) {
            yMove = -speed;
        }
        if (Application.getInstance().getKeyManager().down) {
            yMove = speed;
        }
        if (Application.getInstance().getKeyManager().left) {
            xMove = -speed;
        }
        if (Application.getInstance().getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - Application.getInstance().getCamera().getxOffset()), (int) (y - Application.getInstance().getCamera().getyOffset()), width, height, null);
    }

    public void postRender(Graphics g) {

    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }

}
