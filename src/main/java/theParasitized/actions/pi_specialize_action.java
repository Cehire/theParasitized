package theParasitized.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;

public class pi_specialize_action extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList();
    private boolean upgraded = false;
    private int times;
    private int energy = 0;

    public pi_specialize_action(int times, boolean freeToPlayOnce) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.times = times;
        this.freeToPlayOnce = freeToPlayOnce;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            for (AbstractCard card : this.p.hand.group) {
                if (!card.canUpgrade()) {
                    this.cannotUpgrade.add(card);
                }
            }

            if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {
                for (AbstractCard card : this.p.hand.group) {
                    if (card.canUpgrade()) {
                        energy = 0;
                        while (card.canUpgrade() && times > 0){
                            card.upgrade();
                            card.superFlash();
                            card.applyPowers();
                            System.out.println(times);
                            times--;
                            energy++;
                        }
                        if (!this.freeToPlayOnce) {
                            this.p.energy.use(energy);
                        }
                        this.isDone = true;
                        return;
                    }
                }
            }

            this.p.hand.group.removeAll(this.cannotUpgrade);
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, true);
                this.tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 1) {
                energy = 0;
                while (this.p.hand.getTopCard().canUpgrade() && times > 0){
                    this.p.hand.getTopCard().upgrade();
                    this.p.hand.getTopCard().superFlash();
                    this.p.hand.getTopCard().applyPowers();
                    System.out.println(times);
                    times--;
                    energy++;
                }
                if (!this.freeToPlayOnce) {
                    this.p.energy.use(energy);
                }
                this.returnCards();
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                energy = 0;
                while(c.canUpgrade() && times > 0){
                    c.upgrade();
                    c.superFlash();
                    c.applyPowers();
                    System.out.println(times);
                    times--;
                    energy++;
                }
                if (!this.freeToPlayOnce) {
                    this.p.energy.use(energy);
                }
                this.p.hand.addToTop(c);
            }
            this.returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }

    private void returnCards() {
        for (AbstractCard card : this.cannotUpgrade) {
            this.p.hand.addToTop(card);
        }
        this.p.hand.refreshHandLayout();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ArmamentsAction");
        TEXT = uiStrings.TEXT;
    }

}
