package theParasitized.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theParasitized.cards.extra.pi_growth;

public class pi_nesting_power extends AbstractPower {
    public static final String POWER_ID = "TheParasitized:pi_nesting_power";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = POWER_STRINGS.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    public pi_nesting_power(AbstractCreature owner, int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        String path_128 = "parasitizedResources/images/powers/nesting_p.png";
        String path_48 = "parasitizedResources/images/powers/nesting.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path_128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path_48), 0, 0, 32, 32);
        this.updateDescription();
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
            if (this.owner.isPlayer){
                this.flash();
                for (int i = 0; i < this.amount; i++) {
                    this.flash();
                    this.addToBot(new MakeTempCardInHandAction(new pi_growth(), 1));
                }
            }
    }

}
