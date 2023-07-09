package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theParasitized.ModHelper;
import theParasitized.powers.pi_choice_power;
import theParasitized.powers.pi_serene_power;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_68_serene extends CustomCard {
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_68_serene";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/power.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 1;
    public static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;

    public pi_68_serene() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ModHelper.addToBotAbstract(()->{
            for (AbstractPower power : abstractPlayer.powers) {
                if (power.type == AbstractPower.PowerType.DEBUFF){
                    this.addToBot(new RemoveSpecificPowerAction(abstractPlayer, null, power.ID));
                }
            }
        });
        this.addToBot(
                new ApplyPowerAction(
                        abstractPlayer, abstractPlayer,
                        new pi_serene_power(abstractPlayer, this.magicNumber),
                        this.magicNumber)
        );
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeBaseCost(0);
        }
    }
    @Override
    public AbstractCard makeCopy(){
        return new pi_68_serene();
    }
}
