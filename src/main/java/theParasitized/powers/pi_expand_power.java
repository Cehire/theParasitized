package theParasitized.powers;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theParasitized.ModHelper;

public class pi_expand_power extends AbstractPower {
    //test ok
    public static final String POWER_ID = "TheParasitized:pi_expand_power";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = POWER_STRINGS.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    public pi_expand_power(AbstractCreature owner, int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        String path_128 = "parasitizedResources/images/powers/choice_p.png";
        String path_48 = "parasitizedResources/images/powers/choice.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path_128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path_48), 0, 0, 32, 32);
        this.updateDescription();
    }
    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount;
    }

    @Override
    public void onRemove() {
        ModHelper.addToBotAbstract(()->{
            BaseMod.MAX_HAND_SIZE -= this.amount;
            System.out.println("==============MAX_HAND_SIZE:"+BaseMod.MAX_HAND_SIZE  +"============");
        });
    }

    @Override
    public void onVictory() {
        if(AbstractDungeon.player.hasRelic("TheParasitized:pi_parasitedCore")){
            BaseMod.MAX_HAND_SIZE = 12;
        }else {
            BaseMod.MAX_HAND_SIZE = 10;
        }
    }
}
