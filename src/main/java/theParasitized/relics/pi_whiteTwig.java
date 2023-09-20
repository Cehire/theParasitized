package theParasitized.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theParasitized.ModHelper;
import theParasitized.cards.pi_84_exchange;

// 继承CustomRelic
public class pi_whiteTwig extends CustomRelic {
    // 遗物ID
    public static final String ID = "TheParasitized:pi_whiteTwig";
    // 图片路径
    private static final String IMG_PATH = "parasitizedResources/images/relics/whiteTwig.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public pi_whiteTwig() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new pi_whiteTwig();
    }

    @Override
    public void atBattleStartPreDraw() {
        this.flash();
        this.addToBot(new DrawCardAction(1));
        ModHelper.addToBotAbstract(()->{
            AbstractDungeon.player.drawPile.addToRandomSpot(new pi_84_exchange());
        });
    }
}