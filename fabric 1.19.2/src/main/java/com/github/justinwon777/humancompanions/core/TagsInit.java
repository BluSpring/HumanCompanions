package com.github.justinwon777.humancompanions.core;
import com.github.justinwon777.humancompanions.HumanCompanions;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagsInit {
	public static final class Items {
		public static final TagKey<Item> AXES = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(HumanCompanions.MOD_ID,"axes"));
		public static final TagKey<Item> SWORDS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(HumanCompanions.MOD_ID,"swords"));
	}
}
