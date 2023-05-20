package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;

import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theParasitized.ModHelper;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_82_extraSlash extends CustomCard {
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_82_extraSlash";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/attack.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.COMMON;
    // type, color, cost, cardTarget是固定的
    public static final int COST = 1;
    public static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.ENEMY;
    public pi_82_extraSlash() {
        this(0);
    }
    public pi_82_extraSlash(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.damage = this.baseDamage = 7;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(
                new DamageAction(
                        abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)
                )
        );


    }


    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type == CardType.ATTACK){
            AbstractPlayer player = AbstractDungeon.player;
            if (player.drawPile.contains(this)){
                ModHelper.addToBotAbstract(()->{
                    AbstractDungeon.player.drawPile.group.remove(this);
                    AbstractDungeon.getCurrRoom().souls.remove(this);
                    this.exhaustOnUseOnce = false;
                    AbstractDungeon.player.limbo.group.add(this);
                    this.current_y = -200.0F * Settings.scale;
                    this.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                    this.target_y = (float)Settings.HEIGHT / 2.0F;
                    this.targetAngle = 0.0F;
                    this.lighten(false);
                    this.drawScale = 0.12F;
                    this.targetDrawScale = 0.75F;
                    this.applyPowers();
                    if (c.target == CardTarget.ENEMY){
                        this.addToTop(new NewQueueCardAction(this, m, false, true));
                    }else {
                        this.addToTop(new NewQueueCardAction(this, AbstractDungeon.getCurrRoom().monsters.getRandomMonster(), false, true));
                    }
                    this.addToTop(new UnlimboAction(this));
                    if (!Settings.FAST_MODE) {
                        this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                    } else {
                        this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
                    }
                });
            }
        }
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        this.upgradeDamage(3);
    }
    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_82_extraSlash(this.timesUpgraded);
    }
}
