package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_16_emergencyRepair extends CustomCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_16_emergencyRepair";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/紧急再生_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.COMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 0;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_16_emergencyRepair() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = 4;
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(
                new GainBlockAction(
                        abstractPlayer, block
                )
        );
        this.addToBot(new HealAction(abstractPlayer, abstractPlayer, this.magicNumber));
        this.addToBot(
                new ExhaustAction(abstractPlayer, abstractPlayer, 1, false)
        );

    }

    @Override
    public void upgrade() {
        this.upgradeBlock(2);
        this.upgradeMagicNumber(1);
        this.upgradeName();
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_16_emergencyRepair();
    }
}
