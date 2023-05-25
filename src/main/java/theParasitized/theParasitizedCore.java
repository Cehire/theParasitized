package theParasitized;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import theParasitized.cards.*;
import theParasitized.cards.curse.*;
import theParasitized.cards.extra.pi_growth;
import theParasitized.cards.extra.pi_intoHalfMad;
import theParasitized.cards.extra.pi_intoMad;
import theParasitized.characters.apiTheParasitized;
import theParasitized.relics.pi_whiteTwig;


import static theParasitized.characters.apiTheParasitized.Enums.PI_COLOR;
import static theParasitized.characters.apiTheParasitized.Enums.PI_THE_PARASITIZED;

@SpireInitializer
public class theParasitizedCore implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber
, EditRelicsSubscriber {

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
        BaseMod.addCard(new pi_intoMad());
        BaseMod.addCard(new pi_intoHalfMad());
        //===============================   normal part  ==============================
        BaseMod.addCard(new pi_01_strike());
        BaseMod.addCard(new pi_35_olfactoryAggression());
        BaseMod.addCard(new pi_15_whipping());
        BaseMod.addCard(new pi_63_crossSlash());
        BaseMod.addCard(new pi_64_doubleBlade());
        BaseMod.addCard(new pi_80_feignedAttack());
        BaseMod.addCard(new pi_42_errorStrike());
        BaseMod.addCard(new pi_74_fanaticism());
        BaseMod.addCard(new pi_07_combo());
        BaseMod.addCard(new pi_81_bolsterStrike());
        BaseMod.addCard(new pi_82_extraSlash());
        BaseMod.addCard(new pi_31_witherStrike());
        BaseMod.addCard(new pi_29_ironWave());
        BaseMod.addCard(new pi_78_assimilation());
        BaseMod.addCard(new pi_61_whirlwind());
        BaseMod.addCard(new pi_71_headOn());
        BaseMod.addCard(new pi_10_parasiticTentacles());
        BaseMod.addCard(new pi_72_fall());
        BaseMod.addCard(new pi_73_pureBlade());
        BaseMod.addCard(new pi_76_erosion());
        BaseMod.addCard(new pi_75_longArmStrike());
        BaseMod.addCard(new pi_83_illusionSlash());
        BaseMod.addCard(new pi_79_reflection());
        BaseMod.addCard(new pi_46_resentment());
        BaseMod.addCard(new pi_24_deadlySlash());
        BaseMod.addCard(new pi_58_parasiticArm());
        BaseMod.addCard(new pi_23_paraBurst());
        BaseMod.addCard(new pi_69_parasite());
        BaseMod.addCard(new pi_02_defend());
        BaseMod.addCard(new pi_16_emergencyRepair());
        BaseMod.addCard(new pi_85_parry());
        BaseMod.addCard(new pi_36_sideStep());
        BaseMod.addCard(new pi_86_nourishment());
        BaseMod.addCard(new pi_87_waited());
        BaseMod.addCard(new pi_88_phantom());
        BaseMod.addCard(new pi_94_chewing());
        BaseMod.addCard(new pi_43_elimination());
        BaseMod.addCard(new pi_40_speedup());
        BaseMod.addCard(new pi_21_pumping());
        BaseMod.addCard(new pi_28_specialize());
        BaseMod.addCard(new pi_30_thornyFlesh());
        BaseMod.addCard(new pi_34_poisonFog());
        BaseMod.addCard(new pi_54_preAttack());
        BaseMod.addCard(new pi_50_theEnd());
        BaseMod.addCard(new pi_89_tractionNet());
        BaseMod.addCard(new pi_51_bodyEnhance());
        BaseMod.addCard(new pi_90_reekGas());
        BaseMod.addCard(new pi_91_bolsterFlesh());
        BaseMod.addCard(new pi_06_purification());
        BaseMod.addCard(new pi_93_upheaval());
        BaseMod.addCard(new pi_92_impetus());
        BaseMod.addCard(new pi_03_eclosion());
        BaseMod.addCard(new pi_32_witherAll());
        BaseMod.addCard(new pi_25_activate());
        BaseMod.addCard(new pi_48_alacrity());
        BaseMod.addCard(new pi_60_explosion());
        BaseMod.addCard(new pi_65_suppress());
        BaseMod.addCard(new pi_67_awake());
        BaseMod.addCard(new pi_12_deterioration());
        BaseMod.addCard(new pi_14_nesting());
        BaseMod.addCard(new pi_17_graspingAtCurses());
        BaseMod.addCard(new pi_33_cognitiveFilter());
        BaseMod.addCard(new pi_52_getMad());
        BaseMod.addCard(new pi_66_skillful());
        BaseMod.addCard(new pi_68_serene());
        BaseMod.addCard(new pi_18_sacrifice());
        BaseMod.addCard(new pi_47_scab());
        BaseMod.addCard(new pi_11_callOfSwarm());
        BaseMod.addCard(new pi_39_witherEnchantment());
        BaseMod.addCard(new pi_70_stress());
        BaseMod.addCard(new pi_45_infection());
        BaseMod.addCard(new pi_04_choice());
        BaseMod.addCard(new pi_27_bloodShield());
        BaseMod.addCard(new pi_41_crustShield());
        BaseMod.addCard(new pi_95_prepare());
        BaseMod.addCard(new pi_84_exchange());

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
        BaseMod.loadCustomStringsFile(RelicStrings.class, "parasitizedResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(StanceStrings.class, "parasitizedResources/localization/" + lang + "/stances.json");

    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new pi_whiteTwig(), RelicType.SHARED);
    }
}
