package theParasitized.interfaces;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.evacipated.cardcrawl.mod.stslib.patches.HitboxRightClick;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface ClickableEnergyPanel {

    default void clickUpdate() {
        if (!(this instanceof AbstractPlayer)) {
            throw new NotImplementedException();
        } else {
            if (InputHelper.justReleasedClickRight){
                onRightClick();
            }
        }
    }
    void onRightClick();
}
