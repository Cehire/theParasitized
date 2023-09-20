package theParasitized.cards.curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR_CURSE;

public class error extends CustomCard {

    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:error";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/正确错误_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.COMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 0;
    public static final CardType TYPE = CardType.CURSE;
    public static final CardColor COLOR = PI_COLOR_CURSE;
    public static final CardTarget TARGET = CardTarget.SELF;
    public error() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.selfRetain = true;
        this.exhaust = true;
        this.isInnate = true;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new LoseHPAction(abstractPlayer, abstractPlayer,3));
    }
    @Override
    public AbstractCard makeCopy(){
        return new error();
    }


}
