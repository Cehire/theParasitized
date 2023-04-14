package theParasitized;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import theParasitized.cards.curse.*;

@SpireInitializer
public class theParasitizedCore implements EditCardsSubscriber, EditStringsSubscriber {
    // 构造方法
    public theParasitizedCore() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new theParasitizedCore();
    }

    // =============  卡牌注册在这里  =============
    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new baseCurse());
        BaseMod.addCard(new callOfParasites());
        BaseMod.addCard(new changbi());
        BaseMod.addCard(new zhuxing());
        BaseMod.addCard(new xiezeng());
        BaseMod.addCard(new duxing());
        BaseMod.addCard(new huangmou());
        BaseMod.addCard(new jugu());
    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if(Settings.language == Settings.GameLanguage.ZHS){
            lang = "ZHS";
        }else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "parasitizedResources/localization/" + lang + "/cards.json");

    }
}
