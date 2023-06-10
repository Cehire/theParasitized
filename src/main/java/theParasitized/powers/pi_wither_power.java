package theParasitized.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class pi_wither_power extends AbstractPower {
    public static final String POWER_ID = "TheParasitized:pi_wither_power";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = POWER_STRINGS.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    public pi_wither_power(AbstractCreature owner, int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;
        this.amount = amount;
        String path_128 = "parasitizedResources/images/powers/wither_p.png";
        String path_48 = "parasitizedResources/images/powers/wither.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path_128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path_48), 0, 0, 32, 32);
        this.updateDescription();
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()){
            this.flashWithoutSound();
            if (this.amount < 10){
                this.addToBot(new LoseHPAction(this.owner, AbstractDungeon.player, this.amount, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }else {
                this.addToBot(new LoseHPAction(this.owner, AbstractDungeon.player, this.amount, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
            this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.amount));
            this.amount /= 2;
            if (this.amount==0){
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
            }
        }
    }
}
