package theParasitized.patches;


import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


@SpirePatch(
        clz = AbstractCard.class,
        method = "getDynamicValue",
        paramtypez = {char.class}
)
public class renderCardDamagePatch {

    @SpirePrefixPatch
    public static SpireReturn<String> changeDamageDes(AbstractCard _card, char key){
        System.out.println(key);

        if (key == 'D'){
            System.out.println("====================================================");
            return SpireReturn.Return("damage");
        }

        return SpireReturn.Continue();
    }
}
