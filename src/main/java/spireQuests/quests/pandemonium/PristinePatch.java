package spireQuests.quests.pandemonium;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import spireQuests.quests.QuestManager;

import java.util.ArrayList;

@SpirePatch2(clz = AbstractDungeon.class, method = "getRewardCards")
public class PristinePatch {

    @SpirePostfixPatch
    public static ArrayList<AbstractCard> addPristineModifier(ArrayList<AbstractCard> __result) {
        if (QuestManager.quests().stream().anyMatch(q -> q instanceof PristineCardsQuest)) {
            int r = AbstractDungeon.cardRng.random(0, 100);
            if (r <= PristineCardsQuest.PRISTINE_CARDS_RATE) {
                r = AbstractDungeon.cardRng.random(__result.size() - 1);
                CardModifierManager.addModifier(__result.get(r), new PristineModifier() );
            }
        }
        return __result;
    }
}
