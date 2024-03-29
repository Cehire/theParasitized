package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_36_sideStep extends CustomCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_36_sideStep";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/宁静种子_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.COMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = -2;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_36_sideStep() {
        this(0);
    }
    public pi_36_sideStep(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.magicNumber = this.baseMagicNumber = 4;
        this.isEthereal = true;
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void triggerOnExhaust() {
        this.addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,this.magicNumber));
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        this.upgradeMagicNumber(2);
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_36_sideStep(this.timesUpgraded);
    }
}
