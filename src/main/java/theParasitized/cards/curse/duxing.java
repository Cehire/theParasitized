package theParasitized.cards.curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.BlueCandle;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theParasitized.powers.pi_sacrifice_power;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class duxing extends CustomCard {

    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:duxing";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/basecard_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.RARE;

    // type, color, cost, cardTarget是固定的
    public static final int COST = -2;
    public static final CardType TYPE = CardType.CURSE;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public duxing() {
        this(0);
    }
    public duxing(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.selfRetain = true;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isInnate = true;
    }

    @Override
    public void upgrade() {
            ++this.timesUpgraded;
            this.upgradeMagicNumber(1);
            this.upgraded = true;
            this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
            this.initializeTitle();
    }
    @Override
    public boolean canUpgrade() {
        return true;
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractPlayer.hasPower(pi_sacrifice_power.POWER_ID)){
            for (AbstractPower power : abstractPlayer.powers) {
                if (power.ID.equals(pi_sacrifice_power.POWER_ID)){
                    power.flash();
                    this.addToBot(new DrawCardAction(power.amount));}
            }
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return p.hasPower(pi_sacrifice_power.POWER_ID) || p.hasRelic(BlueCandle.ID);
    }
    @Override
    public AbstractCard makeCopy(){
        return new duxing(this.timesUpgraded);
    }

    public void tookDamage() {

        if (AbstractDungeon.player.hand.contains(this)){
            this.flash();
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                    if (!monster.isDead && !monster.isDying) {
                        this.addToBot(new ApplyPowerAction(monster, AbstractDungeon.player,
                                new PoisonPower(monster, AbstractDungeon.player, this.magicNumber), this.magicNumber));
                        }
                }
            }
        }
    }



}
