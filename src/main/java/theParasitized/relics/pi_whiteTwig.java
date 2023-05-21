package theParasitized.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.NeutralStance;
import theParasitized.ModHelper;
import theParasitized.cards.curse.callOfParasites;
import theParasitized.cards.utils.CommonUtil;
import theParasitized.stances.pi_halfMad_stance;
import theParasitized.stances.pi_mad_stance;

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
        this.addToBot(new MakeTempCardInHandAction(new callOfParasites(), 1));
    }


    @Override
    public void onObtainCard(AbstractCard c) {
        int n = 0;
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractCard card : p.hand.group) {
            if (card.type == AbstractCard.CardType.CURSE){
                n++;
            }
        }
        if (n <= 3 && !p.stance.ID.equals(NeutralStance.STANCE_ID)){
            AbstractDungeon.player.img = ImageMaster.loadImage("parasitizedResources/images/char/stance1.png");
            this.addToBot(new ChangeStanceAction(NeutralStance.STANCE_ID));
        }

        if (n > 3 && n < 6 && !p.stance.ID.equals("TheParasitized:pi_halfMad_stance")){
            this.addToBot(new ChangeStanceAction(new pi_halfMad_stance()));
        }

        if (n > 5 && !p.stance.ID.equals("TheParasitized:pi_mad_stance")){
            this.addToBot(new ChangeStanceAction(new pi_mad_stance()));
        }
    }

    @Override
    public void onDrawOrDiscard() {
        int n = 0;
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractCard card : p.hand.group) {
            if (card.type == AbstractCard.CardType.CURSE){
                n++;
            }
        }

        if (n <= 3 && !p.stance.ID.equals(NeutralStance.STANCE_ID)){
            AbstractDungeon.player.img = ImageMaster.loadImage("parasitizedResources/images/char/stance1.png");
            this.addToBot(new ChangeStanceAction(NeutralStance.STANCE_ID));
        }


        if (n > 3 && n < 6 && !p.stance.ID.equals("TheParasitized:pi_halfMad_stance")){
            this.addToBot(new ChangeStanceAction(new pi_halfMad_stance()));
        }

        if (n > 5 && !p.stance.ID.equals("TheParasitized:pi_mad_stance")){
            this.addToBot(new ChangeStanceAction(new pi_mad_stance()));
        }
    }
}