package theParasitized;

import basemod.interfaces.EditCardsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class theParasitizedCore implements EditCardsSubscriber {
    // 构造方法
    public theParasitizedCore() {
    }

    public static void initialize() {
        new theParasitizedCore();
    }

    // =============  卡牌注册在这里  =============
    @Override
    public void receiveEditCards() {

    }
}
