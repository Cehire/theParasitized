package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import theParasitized.ModHelper;
import theParasitized.actions.pi_drawPileToHandAction_specific;
import theParasitized.cards.curse.error;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_42_errorStrike extends CustomCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_42_errorStrike";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/attack.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.COMMON;

    private final int BaseAttackNum = 1;
    private int attackNum = BaseAttackNum;
    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractPlayer player = AbstractDungeon.player;
        if (player.drawPile.contains(this)){
            if (c.type == CardType.ATTACK){
                this.attackNum--;
                if (this.attackNum<0){
                    this.attackNum = 0;
                }
                if (this.attackNum == 0){
                    System.out.println("======= active!!! ====");
                    this.attackNum = this.BaseAttackNum;
                    this.addToBot(new pi_drawPileToHandAction_specific(1,this));
                }
            }
        }
    }
    @Override
    public void triggerOnEndOfPlayerTurn() {
        this.attackNum = this.BaseAttackNum;
    }

    // type, color, cost, cardTarget是固定的
    public static final int COST = 1;
    public static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.ENEMY;
    public pi_42_errorStrike() {
        this(0);
    }
    public pi_42_errorStrike(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.damage = this.baseDamage = 14;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(
                new DamageAction(
                        abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)
                )
        );

        this.addToBot(new MakeTempCardInHandAction(new error(), 1));
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        this.upgradeDamage(4);
    }
    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_42_errorStrike(this.timesUpgraded);
    }
}
