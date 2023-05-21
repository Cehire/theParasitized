package theParasitized.cards.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import theParasitized.cards.curse.*;
import theParasitized.stances.pi_halfMad_stance;
import theParasitized.stances.pi_mad_stance;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;


public class CommonUtil {
    public static ArrayList<AbstractCard> curseCardList = new ArrayList<>();
    public static ArrayList<AbstractCard> curseCardListAndError = new ArrayList<>();
    static {
        curseCardList.add(new changbi());
        curseCardList.add(new duxing());
        curseCardList.add(new huangmou());
        curseCardList.add(new jugu());
        curseCardList.add(new xiezeng());
        curseCardList.add(new zhuxing());
        curseCardListAndError.add(new changbi());
        curseCardListAndError.add(new duxing());
        curseCardListAndError.add(new huangmou());
        curseCardListAndError.add(new jugu());
        curseCardListAndError.add(new xiezeng());
        curseCardListAndError.add(new zhuxing());
        curseCardListAndError.add(new error());
    }

    public static AbstractCard returnRandomCurse(){
        return (AbstractCard)curseCardList.get(cardRandomRng.random(curseCardList.size() - 1));
    }

    public static AbstractCard returnRandomCurseIncludeError(){
        return (AbstractCard)curseCardListAndError.get(cardRandomRng.random(curseCardList.size() - 1));
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


    public static boolean Skill(AbstractPlayer player){
        return !player.stance.ID.equals(pi_halfMad_stance.STANCE_ID) && !player.stance.ID.equals(pi_mad_stance.STANCE_ID);
    }
}
