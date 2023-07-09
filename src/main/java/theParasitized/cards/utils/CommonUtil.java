package theParasitized.cards.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
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
    public static CardGroup cardGroup = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup cardGroupIncludeError = new CardGroup(CardGroup.CardGroupType.CARD_POOL);

    static {

        cardGroup.group.add(new changbi());
        cardGroup.group.add(new duxing());
        cardGroup.group.add(new huangmou());
        cardGroup.group.add(new jugu());
        cardGroup.group.add(new xiezeng());
        cardGroup.group.add(new zhuxing());
        cardGroupIncludeError.group.add(new changbi());
        cardGroupIncludeError.group.add(new duxing());
        cardGroupIncludeError.group.add(new huangmou());
        cardGroupIncludeError.group.add(new jugu());
        cardGroupIncludeError.group.add(new xiezeng());
        cardGroupIncludeError.group.add(new zhuxing());
        cardGroupIncludeError.group.add(new error());
    }



    public static AbstractCard returnRandomCurse(){
        return cardGroup.getRandomCard(cardRandomRng);
    }

    public static AbstractCard returnRandomCurseIncludeError(){
        return cardGroupIncludeError.getRandomCard(cardRandomRng);
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


    public static ArrayList<AbstractCard> generateCurseChoices(){
        ArrayList<AbstractCard> derp = new ArrayList();
        derp.add(new error());
        while(derp.size() != 3) {
            boolean dupe = false;
            AbstractCard tmp = null;
            tmp = returnRandomCurseIncludeError();
            for (AbstractCard card : derp) {
                if (card.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }
            if (!dupe) {
                derp.add(tmp.makeCopy());
            }
        }
        return derp;
    }




}
