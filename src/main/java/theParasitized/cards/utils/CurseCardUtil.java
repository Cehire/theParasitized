package theParasitized.cards.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import theParasitized.cards.curse.*;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;


public class CurseCardUtil {
    public static ArrayList<AbstractCard> curseCardList;
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
        for (int i = 0; i < numCards; i++) {
            retVal.add(returnRandomCurse());
        }
        reward.cards = retVal;
        return reward;
    }
}