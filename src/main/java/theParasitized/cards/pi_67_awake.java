package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theParasitized.ModHelper;
import theParasitized.actions.pi_alacrity_action;
import theParasitized.powers.pi_awake_power;
import theParasitized.stances.pi_mad_stance;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_67_awake extends CustomCard {
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_67_awake";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.RARE;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_67_awake() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 5;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ModHelper.addToBotAbstract(()->{
            for (AbstractCard card : abstractPlayer.hand.group) {
                if (card.type == CardType.CURSE){
                    this.addToTop(new ExhaustSpecificCardAction(card, abstractPlayer.hand));
                }
            }
        });
        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, this.magicNumber), this.magicNumber));
        if (this.upgraded){
            this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new pi_awake_power(abstractPlayer, 2), 2));
        }else {
            this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new pi_awake_power(abstractPlayer, 1), 1));
        }

    }



    @Override
    public void upgrade() {
        this.upgraded = true;
        this.upgradeMagicNumber(1);
        this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeTitle();
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_67_awake();
    }
}
