package theParasitized.cards.curse;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.actions.unique.ExhaustAllNonAttackAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.BlueCandle;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.NecronomicurseEffect;
import theParasitized.ModHelper;
import theParasitized.cards.utils.CommonUtil;
import theParasitized.powers.pi_sacrifice_power;
import theParasitized.relics.pi_whiteTwig;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;

public class callOfParasites extends CustomCard {

    //===============  需要改的地方 ====================
    public static final String ID = "TheParasitized:callOfParasites";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "parasitizedResources/images/cards/basecard_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    public static final CardRarity RARITY = CardRarity.SPECIAL;
    // type, color, cost, cardTarget是固定的
    public static final int COST = -2;
    public static final CardType TYPE = CardType.CURSE;
    public static final CardColor COLOR = PI_COLOR;
    public static final CardTarget TARGET = CardTarget.SELF;
    public callOfParasites() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.selfRetain = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isInnate = true;
    }

    @Override
    public void onRemoveFromMasterDeck() {
        if (AbstractDungeon.player.hasRelic(pi_whiteTwig.ID)) {
            AbstractDungeon.player.getRelic(pi_whiteTwig.ID).flash();
        }
        AbstractDungeon.effectsQueue.add(new NecronomicurseEffect(new callOfParasites(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void triggerOnExhaust() {
        if (AbstractDungeon.player.hasRelic(pi_whiteTwig.ID)) {
            AbstractDungeon.player.getRelic(pi_whiteTwig.ID).flash();
        }

        this.addToTop(new MakeTempCardInHandAction(this.makeCopy()));
    }

    @Override
    public AbstractCard makeCopy(){
        return new callOfParasites();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.contains(this)){
            this.flash();
            this.addToBot(new MakeTempCardInHandAction(new baseCurse(), 1));
        }
     }


}
