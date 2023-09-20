package theParasitized.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_86_nourishment extends CustomMutiUpgradeCard {
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_86_nourishment";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    private boolean flag = false;
    public pi_86_nourishment() {
        this(0);
    }
    public pi_86_nourishment(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.magicNumber = this.baseMagicNumber = 4;
        this.baseBlock = 2;
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new HealAction(abstractPlayer, abstractPlayer,this.magicNumber));
        if (this.flag){
            this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, this.baseBlock), this.baseBlock));
        }
        this.flag = false;
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.contains(this)){
            this.addToBot(new ExhaustAction(1, false, false, false));
        }
    }

    @Override
    public void onRetained() {
        this.flag = true;
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        this.upgradeMagicNumber(2);
        this.upgradeBlock(1);
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_86_nourishment(this.timesUpgraded);
    }
}
