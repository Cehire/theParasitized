package theParasitized;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theParasitized.cards.*;
import theParasitized.cards.curse.*;
import theParasitized.cards.extra.pi_growth;
import theParasitized.characters.apiTheParasitized;

import java.util.ArrayList;

import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;
import static theParasitized.characters.apiTheParasitized.Enums.PI_THE_PARASITIZED;

@SpireInitializer
public class theParasitizedCore implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber {

    // ===================== to do
    // 人物选择界面按钮的图片
    private static final String MY_CHARACTER_BUTTON = "parasitizedResources/images/char/Character_Button.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "parasitizedResources/images/char/Character_Portrait.jpg";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "parasitizedResources/images/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "parasitizedResources/images/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "parasitizedResources/images/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "parasitizedResources/images/char/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "parasitizedResources/images/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "parasitizedResources/images/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "parasitizedResources/images/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "parasitizedResources/images/char/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "parasitizedResources/images/char/cost_orb.png";
    // ============================ to do
    public static final Color Pi_Color = new Color(236.0F/255.0F,102.0F/255.0F,172.0F/255.0F,1);

    // 构造方法
    public theParasitizedCore() {
        BaseMod.subscribe(this);
        BaseMod.addColor(PI_COLOR, Pi_Color, Pi_Color, Pi_Color, Pi_Color, Pi_Color, Pi_Color, Pi_Color,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,ENEYGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);

    }

    @Override
    public void receiveEditCharacters(){
        BaseMod.addCharacter(new apiTheParasitized(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, PI_THE_PARASITIZED);
    }

    public static void initialize() {
        new theParasitizedCore();
    }

    // =============  卡牌注册在这里  =============
    @Override
    public void receiveEditCards() {
        //===============================   curse part  ==============================
        BaseMod.addCard(new baseCurse());
        BaseMod.addCard(new callOfParasites());
        BaseMod.addCard(new changbi());
        BaseMod.addCard(new zhuxing());
        BaseMod.addCard(new xiezeng());
        BaseMod.addCard(new duxing());
        BaseMod.addCard(new huangmou());
        BaseMod.addCard(new jugu());
        BaseMod.addCard(new error());
        //===============================   extra part  ==============================
        BaseMod.addCard(new pi_growth());
        //===============================   normal part  ==============================
        BaseMod.addCard(new pi_01_strike());
        BaseMod.addCard(new pi_02_defend());
        BaseMod.addCard(new pi_03_eclosion());
        BaseMod.addCard(new pi_04_choice());
        BaseMod.addCard(new pi_06_purification());
        BaseMod.addCard(new pi_07_combo());
        BaseMod.addCard(new pi_08_hunt());
        BaseMod.addCard(new pi_10_parasiticTentacles());
        BaseMod.addCard(new pi_11_callOfSwarm());
        BaseMod.addCard(new pi_12_deterioration());
        BaseMod.addCard(new pi_14_nesting());
        BaseMod.addCard(new pi_15_whipping());
        BaseMod.addCard(new pi_16_emergencyRepair());
        BaseMod.addCard(new pi_17_graspingAtCurses());
        BaseMod.addCard(new pi_18_sacrifice());
        BaseMod.addCard(new pi_21_pumping());
        BaseMod.addCard(new pi_23_paraBurst());
        BaseMod.addCard(new pi_24_deadlySlash());
        BaseMod.addCard(new pi_25_activate());
        BaseMod.addCard(new pi_27_bloodShield());
        BaseMod.addCard(new pi_28_specialize());
        BaseMod.addCard(new pi_29_ironWave());
        BaseMod.addCard(new pi_30_thornyFlesh());
        BaseMod.addCard(new pi_31_witherStrike());
        BaseMod.addCard(new pi_32_witherAll());
        BaseMod.addCard(new pi_33_cognitiveFilter());
        BaseMod.addCard(new pi_34_poisonFog());
        BaseMod.addCard(new pi_35_olfactoryAggression());
        BaseMod.addCard(new pi_36_sideStep());
        BaseMod.addCard(new pi_39_witherEnchantment());
        BaseMod.addCard(new pi_41_crustShield());
        BaseMod.addCard(new pi_42_errorStrike());
    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if(Settings.language == Settings.GameLanguage.ZHS){
            lang = "ZHS";
        }else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "parasitizedResources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "parasitizedResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "parasitizedResources/localization/" + lang + "/powers.json");
    }
}
