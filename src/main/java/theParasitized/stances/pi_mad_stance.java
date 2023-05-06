package theParasitized.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import theParasitized.stances.particle.pi_mad_particle;

public class pi_mad_stance extends AbstractStance {
    public static final String STANCE_ID = "TheParasitized:pi_mad_stance";
    private static final StanceStrings STANCE_STRINGS = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static long sfxId;
    public pi_mad_stance(){
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
        AbstractDungeon.player.img = ImageMaster.loadImage("parasitizedResources/images/char/stance3.png");
        ImageMaster.loadImage("parasitizedResources/images/char/stance3.png").bind();

        if (sfxId != -1L) {
            this.stopIdleSfx();
        }

        CardCrawlGame.sound.play("STANCE_ENTER_DIVINITY");
        sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_DIVINITY");
    }

    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.2F;
                DivinityParticleEffect effect = new DivinityParticleEffect();
                AbstractDungeon.effectsQueue.add(new pi_mad_particle());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
            StanceAuraEffect effect = new StanceAuraEffect("Wrath");
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Wrath"));
        }
    }
}
