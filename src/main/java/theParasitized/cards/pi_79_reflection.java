package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theParasitized.actions.pi_drawPileToHandAction_specific;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_79_reflection extends CustomCard {
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_79_reflection";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/attack.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 2;
    public static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.ENEMY;
    public pi_79_reflection() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 14;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(
                new DamageAction(
                        abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)
                )
        );
        this.addToBot(
                new ApplyPowerAction(
                        abstractMonster, abstractPlayer,
                        new VulnerablePower(abstractMonster, this.magicNumber, false),
                        this.magicNumber)
        );
    }


    @Override
    public void tookDamage() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player.drawPile.contains(this)){
            this.addToBot(new pi_drawPileToHandAction_specific(1, this));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeDamage(5);
        }
    }
    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_79_reflection();
    }
}
