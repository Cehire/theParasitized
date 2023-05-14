package theParasitized.cards.curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.BlueCandle;
import theParasitized.powers.pi_sacrifice_power;

public class huangmou extends CustomCard {

    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:huangmou";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/basecard_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.RARE;

    // type, color, cost, cardTarget是固定的
    public static final int COST = -2;
    public static final CardType TYPE = CardType.CURSE;
    public static final CardColor COLOR = CardColor.CURSE;
    public static final CardTarget TARGET = CardTarget.NONE;
    public huangmou() {
        this(0);
    }
    public huangmou(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.selfRetain = true;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 6;
        this.damage = this.baseDamage;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            this.isInnate = true;
        }
        ++this.timesUpgraded;
        this.baseDamage += 3;
        this.damage = this.baseDamage;
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
    public AbstractCard makeCopy(){
        return new huangmou(this.timesUpgraded);
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return p.hasPower(pi_sacrifice_power.POWER_ID) || p.hasRelic(BlueCandle.ID);
    }
    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        --this.baseMagicNumber;
        this.magicNumber = this.baseMagicNumber;
        if(this.baseMagicNumber ==1 ){
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            this.isGlowing = true;
        }

        if (this.baseMagicNumber == 0){
            this.flash();
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                    if (!monster.isDead && !monster.isDying) {
                        this.addToTop(new LoseHPAction(monster, AbstractDungeon.player,
                                this.damage, AbstractGameAction.AttackEffect.FIRE));
                    }
                }
            }
            this.baseMagicNumber = 3;
            this.magicNumber = this.baseMagicNumber;
        }
    }
}
