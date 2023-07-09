package theParasitized.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theParasitized.actions.pi_drawPileToHandAction_specific;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_91_bolsterFlesh extends CustomMutiUpgradeCard {
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_91_bolsterFlesh";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.COMMON;
    private boolean flag = false;
    // type, color, cost, cardTarget是固定的
    public static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_91_bolsterFlesh() {
        this(0);
    }
    public pi_91_bolsterFlesh(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.block = this.baseBlock = 7;
        this.magicNumber = this.baseMagicNumber = 1;
        this.selfRetain = true;
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
                new GainBlockAction(
                        abstractPlayer, block
                )
        );
        if (flag){
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
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        this.upgradeBlock(3);
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_91_bolsterFlesh(this.timesUpgraded);
    }
}
