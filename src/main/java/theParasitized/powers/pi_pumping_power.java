package theParasitized.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;


public class pi_pumping_power extends AbstractPower {
    public static final String POWER_ID = "TheParasitized:pi_nesting_power";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = POWER_STRINGS.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    public pi_pumping_power(AbstractCreature owner, int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        String path_128 = "parasitizedResources/images/powers/pi_curse.png";
        String path_48 = "parasitizedResources/images/powers/pi_curse.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path_128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path_48), 0, 0, 32, 32);
        this.updateDescription();
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        this.flashWithoutSound();
        --this.amount;
        if (this.amount == 0) {
            this.flash();
            AbstractDungeon.actionManager.cardQueue.clear();
            for (AbstractCard card : AbstractDungeon.player.limbo.group) {
                AbstractDungeon.effectList.add(new ExhaustCardEffect(card));
            }
            AbstractDungeon.player.limbo.group.clear();
            AbstractDungeon.player.releaseCard();
            AbstractDungeon.overlayMenu.endTurnButton.disable(true);
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "TheParasitized:pi_nesting_power"));
        }
        this.updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount = Math.min(stackAmount, this.amount);
    }
}
