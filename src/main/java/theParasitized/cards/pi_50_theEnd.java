package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theParasitized.ModHelper;
import theParasitized.actions.pi_alacrity_action;
import theParasitized.cards.curse.error;
import theParasitized.stances.pi_mad_stance;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_50_theEnd extends CustomCard {
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_50_theEnd";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_50_theEnd() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new MakeTempCardInDrawPileAction(this.makeCopy(), 2, true, true));
    }


    @Override
    public void triggerWhenDrawn() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player.drawPile.size() == 10){
            int n = 0;
            for (AbstractCard card : player.drawPile.group) {
                if (card.type == CardType.CURSE){
                    n++;
                }
            }
            if (n == 10){
                for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    this.addToBot(new InstantKillAction(monster));
                }
            }
        }

    }

    @Override
    public void upgrade() {
        this.upgraded = true;
        this.upgradeMagicNumber(2);
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_50_theEnd();
    }
}
