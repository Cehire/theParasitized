package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theParasitized.actions.pi_drawPileToHandAction_specific;
import theParasitized.powers.pi_pumping_power;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_21_pumping extends CustomCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_21_pumping";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 0;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_21_pumping() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
    }
    private final int BaseAttackNum = 2;
    private int attackNum = BaseAttackNum;
    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractPlayer player = AbstractDungeon.player;
        if (player.drawPile.contains(this)){
            if (c.type == CardType.ATTACK){
                this.attackNum--;
                if (this.attackNum<0){
                    this.attackNum = 0;
                }
                if (this.attackNum == 0){
                    System.out.println("======= active!!! ====");
                    this.attackNum = this.BaseAttackNum;
                    this.addToBot(new pi_drawPileToHandAction_specific(1,this));
                }
            }
        }
    }
    @Override
    public void triggerOnEndOfPlayerTurn() {
        this.attackNum = this.BaseAttackNum;
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new DrawCardAction(this.magicNumber));
        if (!this.upgraded){
            this.addToBot(
                    new ApplyPowerAction(
                            abstractPlayer, abstractPlayer,
                            new pi_pumping_power(abstractPlayer, 3),
                            3)
            );
        }else {
            this.addToBot(
                    new ApplyPowerAction(
                            abstractPlayer, abstractPlayer,
                            new pi_pumping_power(abstractPlayer, 5),
                            5)
            );
        }

    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_21_pumping();
    }
}
