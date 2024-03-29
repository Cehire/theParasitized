package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theParasitized.actions.pi_specialize_action;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_28_specialize extends CustomCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_28_specialize";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/特化_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = -1;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_28_specialize() {
        this(0);
    }
    public pi_28_specialize(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.energyOnUse != 0 || this.upgraded){
            this.addToBot(new pi_specialize_action(this.energyOnUse, this.freeToPlayOnce, this.upgraded));
        }
    }


    @Override
    public void upgrade() {
        this.upgraded = true;
        this.upgradeName();
        this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }


    @Override
    public AbstractCard makeCopy(){
        return new pi_28_specialize();
    }
}
