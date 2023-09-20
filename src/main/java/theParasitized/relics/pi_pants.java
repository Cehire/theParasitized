package theParasitized.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.curses.Normality;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

// 继承CustomRelic
public class pi_pants extends CustomRelic {
    // 遗物ID
    public static final String ID = "TheParasitized:pi_pants";
    // 图片路径
    private static final String IMG_PATH = "parasitizedResources/images/relics/pants.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public pi_pants() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }
    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new pi_pants();
    }

    @Override
    public void onEquip() {
        CardCrawlGame.sound.play("GOLD_GAIN");
        AbstractDungeon.player.gainGold(750);
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Normality(), (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));
    }
}