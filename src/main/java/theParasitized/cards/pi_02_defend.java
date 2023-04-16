package theParasitized.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_02_defend extends CustomMutiUpgradeCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_02_defend";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/pi_curse.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.BASIC;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_02_defend() {
        this(0);
    }
    public pi_02_defend(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.block = this.baseBlock = 5;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(
                new GainBlockAction(
                         abstractPlayer, block
                )
        );

    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.upgradeBlock(3);
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_02_defend(this.timesUpgraded);
    }
}
