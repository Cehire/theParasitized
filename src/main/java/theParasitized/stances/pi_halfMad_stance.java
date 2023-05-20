package theParasitized.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;

public class pi_halfMad_stance extends AbstractStance {
    public static final String STANCE_ID = "TheParasitized:pi_halfMad_stance";
    private static final StanceStrings STANCE_STRINGS = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static long sfxId;
    public pi_halfMad_stance(){
        this.ID = STANCE_ID;
        this.name = STANCE_STRINGS.NAME;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description =STANCE_STRINGS.DESCRIPTION[0];
    }

    @Override
    public void onEnterStance() {
        AbstractDungeon.player.img = ImageMaster.loadImage("parasitizedResources/images/char/stance2.png");
        if (sfxId != -1L) {
            this.stopIdleSfx();
        }

        CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
        sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_WRATH");
    }

    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.05F;
                AbstractDungeon.effectsQueue.add(new WrathParticleEffect());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
        }
    }

}
