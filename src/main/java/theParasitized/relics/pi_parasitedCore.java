package theParasitized.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

// 继承CustomRelic
public class pi_parasitedCore extends CustomRelic {
    // 遗物ID
    public static final String ID = "TheParasitized:pi_parasitedCore";
    // 图片路径
    private static final String IMG_PATH = "parasitizedResources/images/relics/parasitedCore.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public pi_parasitedCore() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }
    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new pi_parasitedCore();
    }

    @Override
    public void onEquip() {
        BaseMod.MAX_HAND_SIZE += 2;
    }
}