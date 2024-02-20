package theParasitized.cards.curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theParasitized.theParasitizedCore;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR_CURSE;

public class pi_84_exchange extends CustomCard implements parasitizationCard{
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_84_exchange";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/basecard_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.BASIC;

    // type, color, cost, cardTarget是固定的
    public static final int COST = -2;
    public static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = CardColor.COLORLESS;
    public static final CardTarget TARGET = CardTarget.NONE;
    public boolean flag;

    public pi_84_exchange() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.selfRetain = true;
        this.flag = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void atTurnStartPreDraw() {
        this.flag = true;
    }

    //
//    @Override
//    public void tookDamage() {
//        if (AbstractDungeon.player.hand.contains(this) && !AbstractDungeon.actionManager.turnHasEnded){
//            this.flash();
//            this.addToBot(new GainEnergyAction(1));
//        }
//    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void atTurnStart() {
        theParasitizedCore.exchangeFlag = true;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_84_exchange();
    }
}
