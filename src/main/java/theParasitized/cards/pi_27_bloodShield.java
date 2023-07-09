package theParasitized.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_27_bloodShield extends CustomMutiUpgradeCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_27_bloodShield";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/power.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 0;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_27_bloodShield() {
        this(0);
    }
    public pi_27_bloodShield(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.block = this.baseBlock = 3;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new LoseHPAction(abstractPlayer,abstractPlayer, 3));
        for (int i = 0; i < 2; i++) {
            this.addToBot(
                    new GainBlockAction(
                            abstractPlayer, block
                    )
            );
        }
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        this.upgradeBlock(2);
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_27_bloodShield(this.timesUpgraded);
    }
}
