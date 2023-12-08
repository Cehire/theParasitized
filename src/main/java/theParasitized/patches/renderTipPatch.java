package theParasitized.patches;


import basemod.BaseMod;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.util.ArrayList;


@SpirePatch(
        clz = AbstractMonster.class,
        method = "renderTip"
)
public class renderTipPatch {
    @SpireInsertPatch(rloc = 5)
    public static void cleanTips(AbstractMonster _m){
        ArrayList tips = ReflectionHacks.getPrivate(_m, AbstractCreature.class, "tips");
        tips.clear();
    }
}
