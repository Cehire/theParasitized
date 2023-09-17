package theParasitized.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.stances.NeutralStance;
import theParasitized.ModHelper;
import theParasitized.cards.curse.callOfParasites;
import theParasitized.cards.utils.CommonUtil;
import theParasitized.stances.pi_halfMad_stance;
import theParasitized.stances.pi_mad_stance;

// 继承CustomRelic
public class pi_diamondChestplate extends CustomRelic {
    // 遗物ID
    public static final String ID = "TheParasitized:pi_diamondChestplate";
    // 图片路径
    private static final String IMG_PATH = "parasitizedResources/images/relics/diamondChestplate.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.SHOP;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public pi_diamondChestplate() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }
    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new pi_diamondChestplate();
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        return super.onAttackedToChangeDamage(info, damageAmount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (AbstractDungeon.player.currentBlock > 0){
            AbstractDungeon.player.currentBlock += Math.min(info.output, 2);
        }
        return super.onAttacked(info, damageAmount);
    }
}