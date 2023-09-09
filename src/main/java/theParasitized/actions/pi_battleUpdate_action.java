package theParasitized.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theParasitized.ModHelper;

import java.util.UUID;

public class pi_battleUpdate_action extends AbstractGameAction {
    private final AbstractCard c;
    public pi_battleUpdate_action(AbstractCard card) {
        this.c = card;
    }
    public void update() {
        if (AbstractDungeon.player.hand.contains(this.c)){
            this.c.flash();
            this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 3));
        }
        isDone = true;
    }
}
