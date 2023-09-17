//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package theParasitized.monsters;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

public class kaofish extends AbstractMonster {
    public static final String ID = "kaofish";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final int HP_MIN = 40;
    private static final int HP_MAX = 44;
    private static final int A_2_HP_MIN = 42;
    private static final int A_2_HP_MAX = 46;
    private static final float HB_X = 0.0F;
    private static final float HB_Y = -25.0F;
    private static final float HB_W = 260.0F;
    private static final float HB_H = 170.0F;
    private static final int CHOMP_DMG = 11;
    private static final int A_2_CHOMP_DMG = 12;
    private static final int THRASH_DMG = 7;
    private static final int THRASH_BLOCK = 5;
    private static final int BELLOW_STR = 3;
    private static final int A_2_BELLOW_STR = 4;
    private static final int A_17_BELLOW_STR = 5;
    private static final int BELLOW_BLOCK = 6;
    private static final int A_17_BELLOW_BLOCK = 9;
    private int bellowBlock;
    private int chompDmg;
    private int thrashDmg;
    private int thrashBlock;
    private int bellowStr;
    private static final byte CHOMP = 1;
    private static final byte BELLOW = 2;
    private static final byte THRASH = 3;
    private boolean firstMove;
    private boolean hardMode;

    public kaofish(){
        this(0,0);
    };
    public kaofish(float x, float y) {
        this(x, y, false);
    }

    public kaofish(float x, float y, boolean hard) {
        super(NAME, "kaofish", 44, 0.0F, -25.0F, 260.0F, 170.0F, (String)null, x, y);
        this.firstMove = true;
        this.hardMode = hard;
        if (this.hardMode) {
            this.firstMove = false;
        }
        if (AbstractDungeon.ascensionLevel >= 7) {
            this.setHp(42, 46);
        } else {
            this.setHp(40, 44);
        }

        if (AbstractDungeon.ascensionLevel >= 17) {
            this.bellowStr = 5;
            this.bellowBlock = 9;
            this.chompDmg = 12;
            this.thrashDmg = 7;
            this.thrashBlock = 5;
        } else if (AbstractDungeon.ascensionLevel >= 2) {
            this.bellowStr = 4;
            this.bellowBlock = 6;
            this.chompDmg = 12;
            this.thrashDmg = 7;
            this.thrashBlock = 5;
        } else {
            this.bellowStr = 3;
            this.bellowBlock = 6;
            this.chompDmg = 11;
            this.thrashDmg = 7;
            this.thrashBlock = 5;
        }
        this.damage.add(new DamageInfo(this, this.chompDmg));
        this.damage.add(new DamageInfo(this, this.thrashDmg));
    }

    public void usePreBattleAction() {
        if (this.hardMode) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, this.bellowStr), this.bellowStr));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, this.bellowBlock));
        }

    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 1:

                AbstractDungeon.actionManager.addToBottom(new VFXAction(new BiteEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.3F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AttackEffect.NONE));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_JAW_WORM_BELLOW"));
                AbstractDungeon.actionManager.addToBottom(new ShakeScreenAction(0.2F, ShakeDur.SHORT, ShakeIntensity.MED));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, this.bellowStr), this.bellowStr));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, this.bellowBlock));
                break;
            case 3:

                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(1), AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, this.thrashBlock));
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    protected void getMove(int num) {
        if (this.firstMove) {
            this.firstMove = false;
            this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
        } else {
            if (num < 25) {
                if (this.lastMove((byte)1)) {
                    if (AbstractDungeon.aiRng.randomBoolean(0.5625F)) {
                        this.setMove((byte)2, Intent.DEFEND_BUFF);
                    } else {
                        this.setMove((byte)3, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
                    }
                } else {
                    this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
                }
            } else if (num < 55) {
                if (this.lastTwoMoves((byte)3)) {
                    if (AbstractDungeon.aiRng.randomBoolean(0.357F)) {
                        this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
                    } else {
                        this.setMove((byte)2, Intent.DEFEND_BUFF);
                    }
                } else {
                    this.setMove((byte)3, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
                }
            } else if (this.lastMove((byte)2)) {
                if (AbstractDungeon.aiRng.randomBoolean(0.416F)) {
                    this.setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
                } else {
                    this.setMove((byte)3, Intent.ATTACK_DEFEND, ((DamageInfo)this.damage.get(1)).base);
                }
            } else {
                this.setMove((byte)2, Intent.DEFEND_BUFF);
            }

        }
    }

    public void die() {
        super.die();
    }

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("TheParasitized:kaofish");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }





    public static AbstractMonster get() {
        return new kaofish();
    }

}
