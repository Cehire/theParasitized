package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_80_feignedAttack extends CustomCard {
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_80_feignedAttack";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/attack.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.COMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 0;
    public static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.ENEMY;
    public pi_80_feignedAttack() {
        this(0);
    }
    public pi_80_feignedAttack(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.damage = this.baseDamage = 4;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(
                new DamageAction(
                        abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)
                )
        );
        if (this.upgraded){
            this.addToBot(new LoseHPAction(abstractPlayer, abstractPlayer,1));
            this.addToBot(new DrawCardAction(1));
        }else {
            this.addToBot(new LoseHPAction(abstractPlayer, abstractPlayer,3));
            this.addToBot(new DrawCardAction(3));
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
        return new pi_80_feignedAttack(this.timesUpgraded);
    }
}
