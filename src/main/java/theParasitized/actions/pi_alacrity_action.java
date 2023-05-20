package theParasitized.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class pi_alacrity_action extends AbstractGameAction {

    public pi_alacrity_action() {
        this.duration = 0.001F;
    }
    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));
        this.tickDuration();
        if (this.isDone) {
            for (AbstractCard card : DrawCardAction.drawnCards) {
                if (card.type == AbstractCard.CardType.SKILL){
                    card.freeToPlayOnce = true;
                }
                }
            }
        }


    }
