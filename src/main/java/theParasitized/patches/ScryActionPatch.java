package theParasitized.patches;


import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import theParasitized.interfaces.ScriedAndDiscardSubscriber;


@SpirePatch(
        clz = ScryAction.class,
        method = "update"
)
public class ScryActionPatch {
    @SpireInstrumentPatch
    public static ExprEditor Instrument0(){
        return new ExprEditor(){
            @Override
            public void edit(MethodCall m) throws CannotCompileException{
                if (isOpen(m)){
                    m.replace(

                                    "{$_ = $proceed($$);" +
                                    ScryActionPatch.class.getName()+
                                    ".triggerOnScried($1);" +
                                    ScryActionPatch.class.getName()+
                                    ".changeColor(true);" + "}"

                    );
                }
            }
            private boolean isOpen(MethodCall m){
                String methodName = m.getMethodName();
                return methodName.equals("open");
            }
        };

    }
    @SpireInstrumentPatch
    public static ExprEditor Instrument1(){
        return new ExprEditor(){
            @Override
            public void edit(MethodCall m) throws CannotCompileException{
                if (isMoveToDiscardPile(m)){
                    m.replace(
                            "{$_ = $proceed($$); " +
                                    ScryActionPatch.class.getName()+
                                    ".triggerOnScriedAndDiscarded($1);" +
                                    ScryActionPatch.class.getName()+
                                    ".changeColor(false);" +
                                    "}"
                    );
                }
            }
            private boolean isMoveToDiscardPile(MethodCall m){
                String methodName = m.getMethodName();
                return methodName.equals("moveToDiscardPile");
            }
        };
    }
    public static void triggerOnScriedAndDiscarded(AbstractCard c){
        if (c instanceof ScriedAndDiscardSubscriber){
            ((ScriedAndDiscardSubscriber) c).onScriedAndDiscarded();
        }
    }
    public static void triggerOnScried(CardGroup cardGroup){
        for (AbstractCard card : cardGroup.group) {
            if (card instanceof ScriedAndDiscardSubscriber){
                ((ScriedAndDiscardSubscriber) card).onScried();
            }
        }
    }
    public static void changeColor(boolean flag){
        Color color = new Color(0.2F, 0.9F, 1.0F, 0.25F);
        if (flag){
            color = Color.PURPLE;
        }
        for (AbstractCard card : AbstractDungeon.gridSelectScreen.targetGroup.group) {
            if (card instanceof ScriedAndDiscardSubscriber){
                card.glowColor = color;
            }
        }
    }
}
