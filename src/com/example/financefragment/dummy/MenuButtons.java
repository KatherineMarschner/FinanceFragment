package com.example.financefragment.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.financeplanner.database.PlannerDefaultQuestionTable;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MenuButtons {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<MenuButton> ITEMS = new ArrayList<MenuButton>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, MenuButton> ITEM_MAP = new HashMap<String, MenuButton>();

	static {
		// Add 3 sample items.
		addItem(new MenuButton("1", PlannerDefaultQuestionTable.TABLE_PLANNER));
		addItem(new MenuButton("2", "Edit"));
	}

	private static void addItem(MenuButton item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class MenuButton {
		public String id;
		public String content;

		public MenuButton(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
