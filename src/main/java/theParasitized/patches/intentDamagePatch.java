package theParasitized.patches;


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


@SpirePatch(
        clz = AbstractMonster.class,
        method = "renderDamageRange"
)
public class intentDamagePatch {
    @SpirePrefixPatch
    public static SpireReturn Instrument(){
        return SpireReturn.Return();
    }
}
