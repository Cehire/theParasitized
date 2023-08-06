package theParasitized.event;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.localization.EventStrings;

public class testEvent extends AbstractEvent {
    public static final String ID = "PI_TESTEVENT";
    private static final EventStrings EVENT_STRINGS = CardCrawlGame.languagePack.getEventString("PI_TESTEVENT");
    public static final String NAME = EVENT_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = EVENT_STRINGS.DESCRIPTIONS;
    private static final String[] OPTIONS = EVENT_STRINGS.OPTIONS;
    private static final String INTRO_MSG = EVENT_STRINGS.DESCRIPTIONS[0];
    private testEvent.CurScreen screen;

    public testEvent() {
        this.screen = testEvent.CurScreen.INTRO;
        this.initializeImage("parasitizedResources/images/events/sphereClosed.png", 1120.0F * Settings.scale, AbstractDungeon.floorY - 50.0F * Settings.scale);
        this.body = INTRO_MSG;
        this.roomEventText.addDialogOption(OPTIONS[0]);
        this.roomEventText.addDialogOption(OPTIONS[1]);
        this.hasDialog = true;
        this.hasFocus = true;
    }

    public void update() {
        super.update();
        if (!RoomEventDialog.waitForInput) {
            this.buttonEffect(this.roomEventText.getSelectedOption());
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch(this.screen) {
            case INTRO:
                switch(buttonPressed) {
                    case 0:
                        this.screen = testEvent.CurScreen.END;
                        this.roomEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.roomEventText.updateDialogOption(0, OPTIONS[2]);
                        this.roomEventText.clearRemainingOptions();
                        return;
                    case 1:
                        this.screen = testEvent.CurScreen.END;
                        this.roomEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.roomEventText.updateDialogOption(0, OPTIONS[2]);
                        this.roomEventText.clearRemainingOptions();
                        return;
                    default:
                        return;
                }
            case END:
                this.openMap();
        }
    }
    private enum CurScreen {
        INTRO,
        PRE_COMBAT,
        END;
        CurScreen() {
        }
    }
}

