package theParasitized.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theParasitized.cards.utils.CommonUtil;
import theParasitized.powers.pi_double_power;
import theParasitized.powers.pi_scab_power;
import theParasitized.powers.pi_wither_power;
import theParasitized.stances.pi_halfMad_stance;
import theParasitized.stances.pi_mad_stance;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_54_preAttack extends CustomMutiUpgradeCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_54_preAttack";
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
    public pi_54_preAttack() {
        this(0);
    }
    public pi_54_preAttack(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(
                new ApplyPowerAction(
                        abstractPlayer, abstractPlayer,
                        new pi_double_power(abstractPlayer, this.magicNumber),
                        this.magicNumber)
        );
        if (CommonUtil.Skill(abstractPlayer)){
            if (this.upgraded){
                this.addToBot(new DrawCardAction(2));
            }
            this.addToBot(new DrawCardAction(1));
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
        return new pi_54_preAttack(this.timesUpgraded);
    }
}
