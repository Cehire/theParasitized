package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theParasitized.cards.curse.parasitizationCard;
import theParasitized.cards.utils.CommonUtil;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_100_gongmin extends CustomCard {
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_100_gongmin";
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
    public pi_100_gongmin() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.applyPowers();
        this.addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        if(CommonUtil.Skill(abstractPlayer)){
            this.addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        }
    }

    @Override
    public void applyPowers() {
        int n = 0;
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card instanceof parasitizationCard){
                n++;
            }
        }
        this.baseBlock = n * this.magicNumber;
        super.applyPowers();
    }

    @Override
    public void upgrade() {
        this.upgradeMagicNumber(1);
        this.upgradeName();
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_100_gongmin();
    }
}
