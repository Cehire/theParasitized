package theParasitized.patches;


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theParasitized.interfaces.ClickableEnergyPanel;


@SpirePatch(
        clz = EnergyPanel.class,
        method = "update"
)
public class ClickableEnergyPanelUpdatePrefixPatch {
    @SpireInsertPatch(rloc = 9)
    public static void addClickListener(){
        if (AbstractDungeon.player instanceof ClickableEnergyPanel) {
            ((ClickableEnergyPanel)AbstractDungeon.player).clickUpdate();
        }
    }
}
