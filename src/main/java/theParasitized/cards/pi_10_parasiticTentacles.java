package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_10_parasiticTentacles extends CustomCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_10_parasiticTentacles";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/pi_curse.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.RARE;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 1;
    public static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.ENEMY;
    public pi_10_parasiticTentacles() {
        this(0);
    }
    public pi_10_parasiticTentacles(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.damage = this.baseDamage = 2;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.type == CardType.CURSE){
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
        this.upgradeMagicNumber(1);
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
