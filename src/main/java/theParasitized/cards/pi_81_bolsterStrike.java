package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theParasitized.actions.pi_drawPileToHandAction_specific;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_81_bolsterStrike extends CustomCard implements attackCard{
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_81_bolsterStrike";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/协增冲击_attack.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.COMMON;
    private boolean isRetained = false;
    // type, color, cost, cardTarget是固定的
    public static final int COST = 1;
    public static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.ENEMY;
    private boolean flag = false;
    public pi_81_bolsterStrike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 7;
        this.selfRetain = true;
        this.magicNumber = this.baseMagicNumber = 1;
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
            this.addToBot(
                    new DamageAction(
                            abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL)
                    )
            );
            if (this.flag){
                this.addToBot(new DrawCardAction(this.magicNumber));
            }
            this.flag = false;
    }

    @Override
    public void onRetained() {
        this.flag = true;
    }

    @Override
    public void upgrade() {
        this.upgradeMagicNumber(1);
        this.upgradeDamage(3);
        this.upgradeName();
    }
    @Override
    public AbstractCard makeCopy(){
        return new pi_81_bolsterStrike();
    }

    @Override
    public int getAttackNum() {
        return this.attackNum;
    }
}
