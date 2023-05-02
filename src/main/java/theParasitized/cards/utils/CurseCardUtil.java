package theParasitized.cards.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theParasitized.theParasitizedCore;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;


public class CurseCardUtil {

    public static AbstractCard returnRandomCurse(){
        return (AbstractCard)theParasitizedCore.curseCardList.get(cardRandomRng.random(theParasitizedCore.curseCardList.size() - 1));
    }


}
