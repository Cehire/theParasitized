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
import com.megacrit.cardcrawl.relics.BlueCandle;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theParasitized.powers.pi_sacrifice_power;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;
import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR_CURSE;

public class xiezeng extends CustomCard {

    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:xiezeng";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/basecard_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = -2;
    public static final CardType TYPE = CardType.CURSE;
    public static final CardColor COLOR = PI_COLOR_CURSE;
    public static final CardTarget TARGET = CardTarget.SELF;
    private int times;
    public xiezeng() {
        this(0);
    }
    public xiezeng(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.selfRetain = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isInnate = true;
        this.times = 0;
    }

    @Override
    public void upgrade() {
        if(AbstractDungeon.player == null){
            System.out.println("aaaaaa");
        }else if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()){
            boolean flag = true;
            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : trace) {
                if (element.getClassName().equals("com.megacrit.cardcrawl.screens.select.HandCardSelectScreen")) {
                    flag = false;
                    break;
                }
            }
            if (flag){
                this.flash();
                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    this.addToBot(new DrawCardAction(2));
                }
            }
        }
    }
    @Override
    public boolean canUpgrade() {
        return AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT;
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
    public AbstractCard makeCopy(){
        return new xiezeng(this.timesUpgraded);
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return p.hasPower(pi_sacrifice_power.POWER_ID) || p.hasRelic(BlueCandle.ID);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.times++;
        if (this.times==3){
            this.flash();
            this.addToBot(new DrawCardAction(this.magicNumber));
            this.times++;
        }
    }

    @Override
    public void atTurnStart() {
        this.times = 0;
    }
}
