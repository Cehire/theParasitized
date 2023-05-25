package theParasitized.cards.curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.BlueCandle;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theParasitized.cards.utils.CommonUtil;
import theParasitized.powers.pi_sacrifice_power;
import theParasitized.relics.pi_whiteTwig;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class callOfParasites extends CustomCard {

    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:callOfParasites";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/basecard_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.SPECIAL;

    // type, color, cost, cardTarget是固定的
    public static final int COST = -2;
    public static final CardType TYPE = CardType.CURSE;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public callOfParasites() {
        this(0);
    }
    public callOfParasites(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.selfRetain = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isInnate = true;
    }

    @Override
    public void upgrade() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.flash();
            this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 3));
        }else {
            ++this.timesUpgraded;
            this.upgraded = true;
            this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
            this.initializeTitle();
        }
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
    public void triggerOnExhaust() {
        if (AbstractDungeon.player.hasRelic(pi_whiteTwig.ID)) {
            AbstractDungeon.player.getRelic(pi_whiteTwig.ID).flash();
        }

        this.addToBot(new MakeTempCardInHandAction(this.makeCopy()));
    }

    @Override
    public AbstractCard makeCopy(){
        return new callOfParasites(this.timesUpgraded);
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return p.hasPower(pi_sacrifice_power.POWER_ID) || p.hasRelic(BlueCandle.ID);
    }
    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.contains(this)){
            this.flash();
            this.addToBot(new MakeTempCardInHandAction(CommonUtil.returnRandomCurseIncludeError(), 1));
        }
     }

}
