package theParasitized.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_94_chewing extends CustomMutiUpgradeCard {
    //
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_94_chewing";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_94_chewing() {
        this(0);
    }
    public pi_94_chewing(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.block = this.baseBlock = 6;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(
                new GainBlockAction(
                        abstractPlayer, block
                )
        );

    }

    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        if (AbstractDungeon.player.hand.contains(this) && cardPlayed.type == CardType.ATTACK){
            this.block = this.baseBlock * 2;
        }
    }

    @Override
    public void onMoveToDiscard() {
        this.block = this.baseBlock;
    }

    @Override
    public void tookDamage() {
        this.damage = this.baseDamage * 2;
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        this.upgradeBlock(2);
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_94_chewing(this.timesUpgraded);
    }
}
