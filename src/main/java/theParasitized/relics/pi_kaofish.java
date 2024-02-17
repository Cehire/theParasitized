package theParasitized.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theParasitized.characters.apiTheParasitized;

// 继承CustomRelic
public class pi_kaofish extends CustomRelic {
    // 遗物ID
    public static final String ID = "TheParasitized:pi_kaofish";
    // 图片路径
    private static final String IMG_PATH = "parasitizedResources/images/relics/kaofish.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.SPECIAL;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public pi_kaofish() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }
    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new pi_kaofish();
    }

    @Override
    public void onEquip() {
        apiTheParasitized.image1 =ImageMaster.loadImage("parasitizedResources/images/char/yu/stance1.png");
        apiTheParasitized.image2 =ImageMaster.loadImage("parasitizedResources/images/char/yu/stance2.png");
        apiTheParasitized.image3 =ImageMaster.loadImage("parasitizedResources/images/char/yu/stance3.png");
    }

    @Override
    public void onLoseHp(int damageAmount) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, 3), 3));
    }


}