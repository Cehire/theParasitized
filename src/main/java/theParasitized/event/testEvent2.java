package theParasitized.event;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Shame;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class testEvent2 extends AbstractImageEvent {
    public static final String ID = "PI_TESTEVENT2";
    private static final EventStrings EVENT_STRINGS = CardCrawlGame.languagePack.getEventString("PI_TESTEVENT2");
    public static final String NAME = EVENT_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = EVENT_STRINGS.DESCRIPTIONS;
    private static final String[] OPTIONS = EVENT_STRINGS.OPTIONS;
    private static final String INTRO_MSG = EVENT_STRINGS.DESCRIPTIONS[0];
    private int screenNum = 0;

    public testEvent2() {
        super(NAME, DESCRIPTIONS[0], "parasitizedResources/images/events/kaoyu.png");
        if (AbstractDungeon.player.gold >= 85) {
            this.imageEventText.setDialogOption(OPTIONS[0] + 85 + OPTIONS[1], AbstractDungeon.player.gold < 85);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[2] + 85 + OPTIONS[3], AbstractDungeon.player.gold < 85);
        }
        this.imageEventText.setDialogOption(OPTIONS[4]);
        this.imageEventText.setDialogOption(OPTIONS[5]);
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        if (AbstractDungeon.player.gold >= 85) {
                            this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                            this.imageEventText.clearRemainingOptions();
                        }
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                    default:
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        this.openMap();
                }

                this.screenNum = 1;
                break;
            case 1:
                this.openMap();
        }
    }
}

