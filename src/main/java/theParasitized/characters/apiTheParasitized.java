package theParasitized.characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.stances.NeutralStance;
import theParasitized.ModHelper;
import theParasitized.cards.curse.callOfParasites;
import theParasitized.cards.pi_01_strike;
import theParasitized.cards.pi_02_defend;
import theParasitized.cards.pi_96_tactics;
import theParasitized.interfaces.ClickableEnergyPanel;
import theParasitized.powers.pi_initial;
import theParasitized.stances.pi_halfMad_stance;
import theParasitized.stances.pi_mad_stance;
import theParasitized.theParasitizedCore;

import java.util.ArrayList;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;
import static theParasitized.characters.apiTheParasitized.Enums.PI_THE_PARASITIZED;

// 继承CustomPlayer类
public class apiTheParasitized extends CustomPlayer implements ClickableEnergyPanel {
    public static boolean toStage1 = true;
    public static boolean toStage2 = true;
    public static boolean toStage3 = true;

    // 火堆的人物立绘（行动前）
    private static final String MY_CHARACTER_SHOULDER_1 = "parasitizedResources/images/char/shoulder1.png";
    // 火堆的人物立绘（行动后）
    private static final String MY_CHARACTER_SHOULDER_2 = "parasitizedResources/images/char/shoulder2.png";
    // 人物死亡图像
    private static final String CORPSE_IMAGE = "parasitizedResources/images/char/new/down.png";
    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
            "parasitizedResources/images/UI/orb/layer5.png",
            "parasitizedResources/images/UI/orb/layer4.png",
            "parasitizedResources/images/UI/orb/layer3.png",
            "parasitizedResources/images/UI/orb/layer2.png",
            "parasitizedResources/images/UI/orb/layer1.png",
            "parasitizedResources/images/UI/orb/layer6.png",
            "parasitizedResources/images/UI/orb/layer5d.png",
            "parasitizedResources/images/UI/orb/layer4d.png",
            "parasitizedResources/images/UI/orb/layer3d.png",
            "parasitizedResources/images/UI/orb/layer2d.png",
            "parasitizedResources/images/UI/orb/layer1d.png"
    };
    // 每个图层的旋转速度
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    // 人物的本地化文本，如卡牌的本地化文本一样，如何书写见下
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("theParasitized:Api");
    private Texture image1;
    private Texture image2;
    private Texture image3;

    public boolean paraFlag = false;

    public apiTheParasitized(String name) {

        super(name, PI_THE_PARASITIZED,ORB_TEXTURES,"parasitizedResources/images/UI/orb/vfx.png", LAYER_SPEED, null, null);
        this.image1 = ImageMaster.loadImage("parasitizedResources/images/char/stance1.png");
        this.image2 = ImageMaster.loadImage("parasitizedResources/images/char/stance2.png");
        this.image3 = ImageMaster.loadImage("parasitizedResources/images/char/stance3.png");
        this.drawY -= 20.0;
        // 人物对话气泡的大小，如果游戏中尺寸不对在这里修改（libgdx的坐标轴左下为原点）
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);
        // 初始化你的人物，如果你的人物只有一张图，那么第一个参数填写你人物图片的路径。
        this.initializeClass(
                "parasitizedResources/images/char/stance1.png", // 人物图片
                MY_CHARACTER_SHOULDER_2, MY_CHARACTER_SHOULDER_1,
                CORPSE_IMAGE, // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F, // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(3) // 初始每回合的能量
        );


        // 如果你的人物没有动画，那么这些不需要写
        // this.loadAnimation("ExampleModResources/img/char/character.atlas", "ExampleModResources/img/char/characters.json", 1.8F);
        // AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        // e.setTime(e.getEndTime() * MathUtils.random());
        // e.setTimeScale(1.2F);


    }

    // 初始卡组的ID，可直接写或引用变量
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            retVal.add(pi_01_strike.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(pi_02_defend.ID);
        }
        retVal.add(callOfParasites.ID);
        retVal.add(pi_96_tactics.ID);


        return retVal;
    }

    // 初始遗物的ID，可以先写个原版遗物凑数
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("TheParasitized:pi_whiteTwig");
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                characterStrings.NAMES[0], // 人物名字
                characterStrings.TEXT[0], // 人物介绍
                75, // 当前血量
                75, // 最大血量
                0, // 初始充能球栏位
                99, // 初始携带金币
                5, // 每回合抽牌数量
                this, // 别动
                this.getStartingRelics(), // 初始遗物
                this.getStartingDeck(), // 初始卡组
                false // 别动
        );
    }

    // 人物名字（出现在游戏左上角）
    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }


    @Override
    public void update() {
        super.update();
        if (AbstractDungeon.getCurrRoom().isBattleOver){
            toStage1 = toStage2 = toStage3 = true;
            AbstractDungeon.player.img = image1;
        }
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT){
            toStage1 = toStage2 = toStage3 = true;
            AbstractDungeon.player.img = image1;
        }
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
            int n = 0;
            AbstractPlayer p = AbstractDungeon.player;
            for (AbstractCard c : p.hand.group) {
                if (c.type == AbstractCard.CardType.CURSE){
                    n++;
                }
            }
            if (n <= 3 && !p.stance.ID.equals(NeutralStance.STANCE_ID) && toStage1){
                toStage1 = false;
                AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction(NeutralStance.STANCE_ID));
                ModHelper.addToBotAbstract(()->{
                    toStage1 = true;
                    System.out.println("=======stage 0===============");
                    AbstractDungeon.player.img = image1;
                });
            }
            if (n > 3 && n < 6 && !p.stance.ID.equals("TheParasitized:pi_halfMad_stance") && toStage2){
                toStage2 = false;
                AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction(new pi_halfMad_stance()));
                ModHelper.addToBotAbstract(()->{
                    System.out.println("=======stage 1===============");
                    AbstractDungeon.player.img = image2;
                });
            }

            if (n > 5 && !p.stance.ID.equals("TheParasitized:pi_mad_stance") && toStage3){
                toStage3 = false;
                AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction(new pi_mad_stance()));
                ModHelper.addToBotAbstract(()->{
                    System.out.println("=======stage 2===============");
                    AbstractDungeon.player.img = image3;
                });
            }
        }else {
            toStage1 = toStage2 = toStage3 = true;
        }
    }

    @Override
    public void preBattlePrep() {
        super.preBattlePrep();
        this.paraFlag = false;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                AbstractDungeon.player, AbstractDungeon.player,
                new pi_initial(AbstractDungeon.player)));
    }

    // 你的卡牌颜色（这个枚举在最下方创建）
    @Override
    public AbstractCard.CardColor getCardColor() {
        return PI_COLOR;
    }

    // 翻牌事件出现的你的职业牌（一般设为打击）
    @Override
    public AbstractCard getStartCardForEvent() {
        return new pi_01_strike();
    }

    // 卡牌轨迹颜色
    @Override
    public Color getCardTrailColor() {
        return theParasitizedCore.Pi_Color;
    }

    // 高进阶带来的生命值损失
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }


    // 人物选择界面点击你的人物按钮时触发的方法，这里为屏幕轻微震动
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    // 碎心图片
    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        // 有两个参数的，第二个参数表示出现图片时播放的音效
        panels.add(new CutscenePanel("parasitizedResources/images/char/Victory1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("parasitizedResources/images/char/Victory2.png"));
        panels.add(new CutscenePanel("parasitizedResources/images/char/Victory3.png"));
        return panels;
    }

    // 自定义模式选择你的人物时播放的音效
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    // 游戏中左上角显示在你的名字之后的人物名称
    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    // 创建人物实例，照抄
    @Override
    public AbstractPlayer newInstance() {
        return new apiTheParasitized(this.name);
    }

    // 第三章面对心脏说的话（例如战士是“你握紧了你的长刀……”之类的）
    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    // 打心脏的颜色，不是很明显
    @Override
    public Color getSlashAttackColor() {
        return theParasitizedCore.Pi_Color;
    }

    // 吸血鬼事件文本，主要是他（索引为0）和她（索引为1）的区别（机器人另外）
    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    // 卡牌选择界面选择该牌的颜色
    @Override
    public Color getCardRenderColor() {
        return theParasitizedCore.Pi_Color;
    }

    // 第三章面对心脏造成伤害时的特效
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    @Override
    public void onRightClick() {
        System.out.println("==========右键点击了能量球=============");
        this.paraFlag = !this.paraFlag;
        if (this.paraFlag){
            this.image1 = ImageMaster.loadImage("parasitizedResources/images/char/stance1.png");
            this.image2 = ImageMaster.loadImage("parasitizedResources/images/char/stance2.png");
            this.image3 = ImageMaster.loadImage("parasitizedResources/images/char/stance3.png");
        }else {
            this.image1 = ImageMaster.loadImage("parasitizedResources/images/char/stance1.png");
            this.image2 = ImageMaster.loadImage("parasitizedResources/images/char/stance2.png");
            this.image3 = ImageMaster.loadImage("parasitizedResources/images/char/stance3.png");
        }
    }

    // 为原版人物枚举、卡牌颜色枚举扩展的枚举，需要写，接下来要用
    // ***填在SpireEnum中的name需要一致***
    public static class Enums {
        @SpireEnum
        public static PlayerClass PI_THE_PARASITIZED;

        @SpireEnum(name = "PI_COLOR")
        public static AbstractCard.CardColor PI_COLOR;
        @SpireEnum(name = "PI_COLOR_CURSE")
        public static AbstractCard.CardColor PI_COLOR_CURSE;

        @SpireEnum(name = "PI_COLOR")
        public static CardLibrary.LibraryType PI_COLOR_LIB;
        @SpireEnum(name = "PI_COLOR_CURSE")
        public static CardLibrary.LibraryType PI_COLOR_CURSE_LIB;
    }
}
