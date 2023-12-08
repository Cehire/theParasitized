package theParasitized.patches;


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;


@SpirePatch(
        clz = AbstractCreature.class,
        method = "renderBlockIconAndValue"
)
public class renderBlockIconPatch {
    @SpireInstrumentPatch
    public static ExprEditor Instrument(){
        return new ExprEditor(){
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                String s = "11";

                if (isOpen(m)){
                    m.replace(

                            "{" +
                                    "double x = Double.parseDouble($3);" +
                                    "double y = (double) this.maxHealth;" +
                                    "int percentage = (int) (x/y * 100);" +
                                    "String formattedPercentage = percentage + \"%\";" +
                                    "$_ = $proceed($1,$2,formattedPercentage,$4,$5,$6,$7);}"

                    );
                }
            }
            private boolean isOpen(MethodCall m){
                String methodName = m.getMethodName();
                return methodName.equals("renderFontCentered");
            }
        };

    }
}
