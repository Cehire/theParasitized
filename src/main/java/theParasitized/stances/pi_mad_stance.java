package theParasitized.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.CharacterManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import theParasitized.ModHelper;
import theParasitized.characters.apiTheParasitized;
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
        apiTheParasitized.toStage3 = true;
        if (sfxId != -1L) {
            this.stopIdleSfx();
        }
    }

    @Override
    public void onPlayCard(AbstractCard card) {
        if (card.type== AbstractCard.CardType.ATTACK){
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
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
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Wrath"));
        }
    }

    @Override
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, "Draw Reduction", 2));
    }
}
