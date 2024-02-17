package theParasitized.patches;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import java.lang.reflect.Field;
import java.util.Iterator;
import javassist.CtBehavior;
import theParasitized.cards.attackCard;
import theParasitized.cards.curse.parasitizationCard;

@SpirePatch(
        clz = DrawPilePanel.class,
        method = "render"
)
public class RenderDrawPilePatch {
    private static final float HB_W;
    private static final float HB_H;
    private static Field frameShadowColorField;

    public RenderDrawPilePatch() {
    }

    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert(DrawPilePanel __instance, SpriteBatch sb) {
        if (!AbstractDungeon.isScreenUp) {
            AbstractCard hovered = null;
            int hoveredIndex = -1;
            int i = 0;
            for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
                if (card instanceof attackCard){
                    ++i;
                }
            }

            for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
                if (card instanceof attackCard){
                    --i;
                    AbstractCard ret = renderCard(__instance, sb, card, i, 0.45F, true);
                    if (ret != null) {
                        hovered = ret;
                        hoveredIndex = i;
                    }
                }
            }
            if (hovered != null) {
                renderCard(__instance, sb, hovered, hoveredIndex, 0.8F, false);
            }

        }
    }

    private static boolean isMintySpireExists() {
        try {
            Class.forName("mintySpire.utility.StsLibChecker");
            return true;
        } catch (ClassNotFoundException var1) {
            return false;
        }
    }

    private static AbstractCard renderCard(DrawPilePanel __instance, SpriteBatch sb, AbstractCard card, int i, float scale, boolean hitbox) {
        AbstractCard hovered = null;
        float prev_current_x = card.current_x;
        float prev_current_y = card.current_y;
        float prev_drawScale = card.drawScale;
        float prev_angle = card.angle;
        card.current_x = __instance.current_x + (float)(hitbox ? 75 : 245) * Settings.scale;
        card.current_y = __instance.current_y + (float)(220 + i * 27) * Settings.scale;
        if (isMintySpireExists() && (AbstractDungeon.player.hasRelic("Frozen Eye") || AbstractDungeon.isScreenUp)) {
            card.current_y += 310.0F;
        }

        card.drawScale = scale;
        card.angle = 0.0F;
        card.lighten(true);
        if (hitbox) {
            card.hb.move(card.current_x, card.current_y);
            card.hb.resize(HB_W * card.drawScale, HB_H * card.drawScale);
            card.hb.update();
            if (card.hb.hovered) {
                hovered = card;
            }
        }

        Color frameShadowColor = null;
        float prev_frameShadow_a = 0.0F;
        if (hitbox) {
            try {
                if (frameShadowColorField == null) {
                    frameShadowColorField = AbstractCard.class.getDeclaredField("frameShadowColor");
                    frameShadowColorField.setAccessible(true);
                }

                frameShadowColor = (Color)frameShadowColorField.get(card);
                prev_frameShadow_a = frameShadowColor.a;
                frameShadowColor.a = 0.0F;
            } catch (NoSuchFieldException | IllegalAccessException var14) {
                var14.printStackTrace();
            }
        }

        card.render(sb);
        if (hitbox) {
            frameShadowColor.a = prev_frameShadow_a;
        }

        card.current_x = prev_current_x;
        card.current_y = prev_current_y;
        card.drawScale = prev_drawScale;
        card.angle = prev_angle;
        return hovered;
    }

    static {
        HB_W = 300.0F * Settings.scale;
        HB_H = 420.0F * Settings.scale;
        frameShadowColorField = null;
    }

    public static class Locator extends SpireInsertLocator {
        public Locator() {
        }

        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(Hitbox.class, "render");
            return LineFinder.findInOrder(ctBehavior, methodCallMatcher);
        }
    }
}
