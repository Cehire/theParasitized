package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theParasitized.cards.curse.parasitizationCard;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_10_parasiticTentacles extends CustomCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_10_parasiticTentacles";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/寄染之触_attack.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 2;
    public static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    public pi_10_parasiticTentacles() {
        this(0);
    }
    public pi_10_parasiticTentacles(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.damage = this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new HealAction(abstractPlayer, abstractPlayer, this.magicNumber));
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card instanceof parasitizationCard){
                this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                this.addToBot(new HealAction(abstractPlayer, abstractPlayer, this.magicNumber));
            }
        }




    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        this.upgradeDamage(2);
    }
    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_10_parasiticTentacles(this.timesUpgraded);
    }
}
