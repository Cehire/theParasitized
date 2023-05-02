package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theParasitized.ModHelper;
import theParasitized.cards.utils.CurseCardUtil;

public class pi_12_deterioration extends CustomCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_12_deterioration";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/pi_curse.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.BASIC;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.RED;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_12_deterioration() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ExhaustAction(this.magicNumber, false, true, true));
        ModHelper.addToBotAbstract(
                ()->{
                    for (int i = 0; i < this.magicNumber; i++) {
                        AbstractDungeon.player.hand.addToHand(CurseCardUtil.returnRandomCurse());
                    }
                }
        );

    }

    @Override
    public void upgrade() {
        this.upgraded = true;
        this.upgradeMagicNumber(1);
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_12_deterioration();
    }
}
