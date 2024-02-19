package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theParasitized.cards.extra.pi_bodyEnhance_1;
import theParasitized.cards.extra.pi_bodyEnhance_2;
import theParasitized.cards.extra.pi_bodyEnhance_3;
import theParasitized.cards.utils.CommonUtil;

import java.util.ArrayList;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class pi_51_bodyEnhance extends CustomCard {
    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:pi_51_bodyEnhance";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/身体强化_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.UNCOMMON;

    // type, color, cost, cardTarget是固定的
    public static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public pi_51_bodyEnhance() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
        this.block = this.baseBlock = 4;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {


        if (CommonUtil.Skill(abstractPlayer)){
            this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, this.magicNumber), this.magicNumber));
            this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DexterityPower(abstractPlayer, this.magicNumber), this.magicNumber));
            this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new PlatedArmorPower(abstractPlayer, this.baseBlock), this.baseBlock));

        }else {
            this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, this.baseBlock), this.baseBlock));
        }
    }

    @Override
    public void upgrade() {
        this.upgradeMagicNumber(1);
        this.upgradeBlock(2);
        this.upgradeName();
    }

    @Override
    public AbstractCard makeCopy(){
        return new pi_51_bodyEnhance();
    }
}
