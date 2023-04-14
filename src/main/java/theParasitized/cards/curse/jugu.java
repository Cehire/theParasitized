package theParasitized.cards.curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class jugu extends CustomCard {

    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:jugu";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/pi_curse.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.RARE;
    // type, color, cost, cardTarget是固定的
    public static final int COST = -2;
    public static final CardType TYPE = CardType.CURSE;
    public static final CardColor COLOR = CardColor.CURSE;
    public static final CardTarget TARGET = CardTarget.NONE;
    public jugu() {
        this(0);
    }
    public jugu(int upgrades) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = upgrades;
        this.selfRetain = true;
    }
    ArrayList<Boolean> preMonster = null;

    @Override
    public void upgrade() {
        if(!this.upgraded){
            this.isInnate = true;
        }
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CARD_STRINGS.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }
    @Override
    public boolean canUpgrade() {
        return true;
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }
    @Override
    public AbstractCard makeCopy(){
        return new jugu(this.timesUpgraded);
    }

    @Override
    public void update() {

        if(AbstractDungeon.player!=null&&AbstractDungeon.player.hand.contains(this)){
            if(preMonster == null){
                preMonster = new ArrayList<>();
                System.out.println("update===============");
                for (int i = 0; i < AbstractDungeon.getMonsters().monsters.size(); i++) {
                    preMonster.add(AbstractDungeon.getMonsters().monsters.get(i).isDead);
                }
            }
        }
        super.update();
    }
    
    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        super.triggerOnCardPlayed(cardPlayed);
            ArrayList<AbstractMonster> tmp = AbstractDungeon.getMonsters().monsters;
            for (int i = 0; i < preMonster.size(); i++) {
                if (tmp.get(i).isDead && !preMonster.get(i)){
                    this.addToBot(new GainEnergyAction(2));
                    preMonster = new ArrayList<>();
                    for (AbstractMonster abstractMonster : tmp) {
                        preMonster.add(abstractMonster.isDead);
                    }
                }
            }
    }



}
