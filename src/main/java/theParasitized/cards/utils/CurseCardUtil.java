package theParasitized.cards.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import theParasitized.cards.curse.*;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;


public class CurseCardUtil {
    public static ArrayList<AbstractCard> curseCardList = new ArrayList<>();
    static {
        curseCardList.add(new changbi());
        curseCardList.add(new duxing());
        curseCardList.add(new huangmou());
        curseCardList.add(new jugu());
        curseCardList.add(new xiezeng());
        curseCardList.add(new zhuxing());
    }

    public static AbstractCard returnRandomCurse(){
        return (AbstractCard)curseCardList.get(cardRandomRng.random(curseCardList.size() - 1));
    }

    public static RewardItem getCurseReward(){
        RewardItem reward = new RewardItem();
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        int numCards = 3;
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            numCards = relic.changeNumberOfCardsInReward(numCards);
        }
        AbstractCard c1;
        AbstractCard c2;
        AbstractCard c3;
        c1 = returnRandomCurse();
        c2 = returnRandomCurse();
        while (c1.cardID.equals(c2.cardID)){
            c2 = returnRandomCurse();
        }
        c3 = returnRandomCurse();
        while (c1.cardID.equals(c3.cardID)||c2.cardID.equals(c3.cardID)){
            c3 = returnRandomCurse();
        }
        retVal.add(c1);
        retVal.add(c2);
        retVal.add(c3);
        reward.cards = retVal;
        return reward;
    }
}
