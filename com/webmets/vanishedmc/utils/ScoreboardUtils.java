package com.webmets.vanishedmc.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;

public class ScoreboardUtils {

	public static List<String> getScoreboardContent() {
		List<String> result = new ArrayList<>();
		Minecraft mc = Minecraft.getMinecraft();
		Scoreboard scoreboard = mc.theWorld.getScoreboard();
		ScoreObjective objective = null;
		ScorePlayerTeam var15 = scoreboard.getPlayersTeam(mc.thePlayer.getName());

		if (var15 != null) {
			int var16 = var15.func_178775_l().func_175746_b();

			if (var16 >= 0) {
				objective = scoreboard.getObjectiveInDisplaySlot(3 + var16);
			}
		}

		ScoreObjective var161 = objective != null ? objective : scoreboard.getObjectiveInDisplaySlot(1);

		if (var161 == null) {
			return null;
		} else {
			objective = var161;
		}

		Scoreboard board = objective.getScoreboard();
		Collection collection = board.getSortedScores(objective);

		ArrayList list = Lists.newArrayList(Iterables.filter(collection, new Predicate() {
			public boolean func_178903_a(Score score) {
				return score.getPlayerName() != null && !score.getPlayerName().startsWith("#");
			}

			public boolean apply(Object p_apply_1_) {
				return this.func_178903_a((Score) p_apply_1_);
			}
		}));
		ArrayList finalList;

		if (list.size() > 15) {
			finalList = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
		} else {
			finalList = list;
		}
		
		Iterator finalIterator = finalList.iterator();
		result.add(objective.getDisplayName());
		
		while(finalIterator.hasNext()) {
			Score score = (Score) finalIterator.next();
			ScorePlayerTeam team = scoreboard.getPlayersTeam(score.getPlayerName());
			String text = ScorePlayerTeam.formatPlayerName(team, score.getPlayerName());
			result.add(text);
		}
		
		return result;
	}
}
