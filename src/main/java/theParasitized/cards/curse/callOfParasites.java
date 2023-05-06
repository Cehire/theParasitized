package theParasitized.cards.curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class callOfParasites extends CustomCard {

    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:CallOfParasites";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/basecard_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.CURSE;

    // type, color, cost, cardTarget是固定的
    public static final int COST = -2;
    public static final CardType TYPE = CardType.CURSE;
    public static final CardColor COLOR = CardColor.CURSE;
    public static final CardTarget TARGET = CardTarget.NONE;
    public callOfParasites() {
        this(0);
    }
    public callOfParasites(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.isInnate = true;
        this.selfRetain = true;
        this.baseMagicNumber = 0;
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
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
    }
    @Override
    public AbstractCard makeCopy(){
        return new callOfParasites(this.timesUpgraded);
    }

    //========================================== 额外区域 ===========================
    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if(AbstractDungeon.player.hand.contains(this)){
            this.magicNumber = this.baseMagicNumber;
            for (AbstractCard card : AbstractDungeon.player.hand.group) {
                if(card.type == CardType.CURSE){
                    this.magicNumber += (card.timesUpgraded + 1);
                }
            }
            c.baseDamage += this.magicNumber;
            c.baseBlock += this.magicNumber;
        }
        System.out.println("=============");

    }
/*
        if(AbstractDungeon.player.hand.contains(this)){

        }
 */



    @Override
    public void triggerOnCardPlayed(AbstractCard c) {
        if(AbstractDungeon.player.hand.contains(this)){
            c.baseDamage -= this.magicNumber;
            c.baseBlock -= this.magicNumber;
        }
    }
}
