package theParasitized.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import java.util.Iterator;

public class pi_hunt_action extends AbstractGameAction {
    private DamageInfo info;
    private AbstractCard theCard = null;

    public pi_hunt_action(AbstractCreature target, DamageInfo info) {
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.NONE));
            this.target.damage(this.info);
            if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && (((AbstractMonster)this.target).type == AbstractMonster.EnemyType.ELITE) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();
                int effectCount = 0;
                while(var2.hasNext()) {
                    AbstractCard c = (AbstractCard)var2.next();
                    if (c.canUpgrade() && c.type == AbstractCard.CardType.CURSE) {
                        ++effectCount;
                        if (effectCount <= 20){
                            float x = MathUtils.random(0.1F, 0.9F) * (float)Settings.WIDTH;
                            float y = MathUtils.random(0.2F, 0.8F) * (float)Settings.HEIGHT;
                            AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), x, y));
                            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
                            this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                        }
                        c.upgrade();
                        AbstractDungeon.player.bottledCardUpgradeCheck(c);
                    }
                }
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        this.tickDuration();

    }
}
