package theParasitized.patches;


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;


@SpirePatch(
        clz = TopPanel.class,
        method = "renderHP"
)
public class renderMaxHeathPatch {
    @SpireInstrumentPatch
    public static ExprEditor Instrument(){
        return new ExprEditor(){
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if (isOpen(m)){
                    m.replace(
                            "{String[] parts = $3.split(\"/\");" +
                                    "double x = Double.parseDouble(parts[0]);" +
                                    "double y = Double.parseDouble(parts[1]);" +
                                    "int percentage = (int) (x/y * 100);" +
                                    "String formattedPercentage = percentage + \"%\";" +
                                    "$_ = $proceed($1,$2,formattedPercentage,$4,$5,$6);" +
                                    "}"

                    );
                }
            }
            private boolean isOpen(MethodCall m){
                String methodName = m.getMethodName();
                return methodName.equals("renderFontLeftTopAligned");
            }
        };

    }
}
