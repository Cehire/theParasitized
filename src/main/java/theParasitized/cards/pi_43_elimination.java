package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theParasitized.ModHelper;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_43_elimination extends CustomCard {
    //func test ok
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_43_elimination";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.ENEMY;
    public pi_43_elimination() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ModHelper.addToBotAbstract(()->{
            int n = 0;
            for (AbstractPower power : abstractMonster.powers) {
                if (power.type == AbstractPower.PowerType.DEBUFF){
                    n++;
                }
            }
            if (n > 2){
                this.addToBot(new VFXAction(new LightningEffect(abstractMonster.drawX, abstractMonster.drawY), 0.05F));
                if (this.upgraded){
                    this.addToBot(new LoseHPAction(abstractMonster, abstractPlayer, 30));
                }else{
                    this.addToBot(new LoseHPAction(abstractMonster, abstractPlayer, 40));
                }
            }
        });
    }

    @Override
    public void upgrade() {
        this.upgraded = true;
        this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeDescription();
        this.initializeTitle();
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_43_elimination();
    }
}
