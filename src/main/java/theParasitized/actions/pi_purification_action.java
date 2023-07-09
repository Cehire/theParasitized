package theParasitized.actions;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class pi_purification_action extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private int upgrades;

    public pi_purification_action(int upgrades) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgrades = upgrades;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (this.p.hand.size() == 1) {
                if (this.p.hand.getBottomCard().type == AbstractCard.CardType.CURSE && this.p.hand.getBottomCard().cardID
                        .equals("TheParasitized:callOfParasites")) {
                    this.addToTop(new GainEnergyAction(2 + this.upgrades));
                } else{
                    this.addToTop(new GainEnergyAction(1 + this.upgrades));
                }

                this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
                this.tickDuration();
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                for (AbstractCard card : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    if (card.type == AbstractCard.CardType.CURSE && this.p.hand.getBottomCard().cardID
                            .equals("TheParasitized:callOfParasites")) {
                        this.addToTop(new GainEnergyAction(2 + this.upgrades));
                    } else{
                        this.addToTop(new GainEnergyAction(1 + this.upgrades));
                    }
                    this.p.hand.moveToExhaustPile(card);
                }
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            }

            this.tickDuration();
        }
    }
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("RecycleAction");
        TEXT = uiStrings.TEXT;
    }
}
