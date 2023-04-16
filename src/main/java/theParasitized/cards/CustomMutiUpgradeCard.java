package theParasitized.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CustomMutiUpgradeCard extends CustomCard {


    public CustomMutiUpgradeCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        if(this.upgraded){
            this.name = this.name.split("[+]")[0] + this.timesUpgraded;
        }else {
            this.name = this.name + "+" + this.timesUpgraded;
        }
        this.upgraded = true;
        this.initializeTitle();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public boolean canUpgrade() {
        return true;
    }
}
