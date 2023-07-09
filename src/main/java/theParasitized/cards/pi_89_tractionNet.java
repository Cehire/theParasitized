package theParasitized.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_89_tractionNet extends CustomMutiUpgradeCard {
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_89_tractionNet";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.ENEMY;
    public pi_89_tractionNet() {
        this(0);
    }
    public pi_89_tractionNet(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.magicNumber = this.baseMagicNumber = 3;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new StrengthPower(abstractMonster, -this.magicNumber), -this.magicNumber));
        if(this.upgraded){
            this.addToBot(
                    new ApplyPowerAction(
                            abstractMonster, abstractPlayer,
                            new VulnerablePower(abstractMonster, 2, false),
                            2)

            );
            this.addToBot(
                    new ApplyPowerAction(
                            abstractMonster, abstractPlayer,
                            new WeakPower(abstractMonster, 2, false),
                            2)

            );
        }else{
            this.addToBot(
                    new ApplyPowerAction(
                            abstractMonster, abstractPlayer,
                            new VulnerablePower(abstractMonster, 1, false),
                            3)

            );
            this.addToBot(
                    new ApplyPowerAction(
                            abstractMonster, abstractPlayer,
                            new WeakPower(abstractMonster, 1, false),
                            3)

            );
        }

    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        this.upgradeMagicNumber(1);
        this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_89_tractionNet(this.timesUpgraded);
    }
}
