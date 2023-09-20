package theParasitized.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

// 继承CustomRelic
public class pi_germinatingTwig extends CustomRelic {
    // 遗物ID
    public static final String ID = "TheParasitized:pi_germinatingTwig";
    // 图片路径
    private static final String IMG_PATH = "parasitizedResources/images/relics/whiteTwig.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public pi_germinatingTwig() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new pi_germinatingTwig();
    }

    @Override
    public void onLoseHp(int damageAmount) {
        if (!AbstractDungeon.actionManager.turnHasEnded){
            this.flash();
            this.addToBot(new GainEnergyAction(1));
            this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1));
        }
    }
    public void obtain() {
        if (AbstractDungeon.player.hasRelic("TheParasitized:pi_whiteTwig")) {
            this.instantObtain(AbstractDungeon.player, 0, false);
        } else {
            super.obtain();
        }

    }
    @Override
    public void atBattleStartPreDraw() {
        this.flash();
        this.addToBot(new DrawCardAction(1));
    }
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic("TheParasitized:pi_whiteTwig");
    }
}