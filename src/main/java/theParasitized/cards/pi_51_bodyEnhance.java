package theParasitized.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theParasitized.cards.extra.pi_bodyEnhance_1;
import theParasitized.cards.extra.pi_bodyEnhance_2;
import theParasitized.cards.extra.pi_bodyEnhance_3;
import theParasitized.cards.utils.CommonUtil;

import java.util.ArrayList;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_51_bodyEnhance extends CustomMutiUpgradeCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_51_bodyEnhance";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_51_bodyEnhance() {
        this(0);
    }
    public pi_51_bodyEnhance(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.magicNumber = this.baseMagicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (CommonUtil.Skill(abstractPlayer)){
            ArrayList<AbstractCard> stanceChoices = new ArrayList();
            stanceChoices.add(new pi_bodyEnhance_1(this.timesUpgraded));
            stanceChoices.add(new pi_bodyEnhance_2(this.timesUpgraded));
            stanceChoices.add(new pi_bodyEnhance_3(this.timesUpgraded));
            this.addToBot(new ChooseOneAction(stanceChoices));
        }else {
            this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, this.magicNumber), this.magicNumber));
        }
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
        return new pi_51_bodyEnhance(this.timesUpgraded);
    }
}
