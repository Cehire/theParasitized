package theParasitized.stances.particle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;

public class pi_stance_generator extends AbstractGameEffect {
    private float x;
    private float y;
    private String stanceId;
    public pi_stance_generator(float x, float y, String stanceId) {
        this.x = x;
        this.y = y;
        this.stanceId = stanceId;
    }

    @Override
    public void update() {
        if (!this.stanceId.equals("Calm")){

        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
