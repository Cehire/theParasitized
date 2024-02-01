package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theParasitized.cards.utils.CommonUtil;
import theParasitized.powers.pi_double_power;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_54_preAttack extends CustomCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_54_preAttack";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/太刀_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_54_preAttack() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(
                new ApplyPowerAction(
                        abstractPlayer, abstractPlayer,
                        new pi_double_power(abstractPlayer, 1),
                        1)
        );
        if (CommonUtil.Skill(abstractPlayer)){
            this.addToBot(new DrawCardAction(this.magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }



    @Override
    public AbstractCard makeCopy(){
        return new pi_54_preAttack();
    }
}
