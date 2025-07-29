package com.hoshino.ordinarytinker.Content.Modifier;

import com.c2h6s.etstlib.util.DynamicComponentUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Arrays;
import java.util.List;

public class IronHeart extends Modifier implements TooltipModifierHook {
    private final int[] color = new int[]{0x485876, 0xcbc1ff, 0xe1e3ff};

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOLTIP);
    }

    @Override
    public @NotNull List<Component> getDescriptionList() {
        int[] a = new int[]{};
        if (descriptionList == null) {
            descriptionList = Arrays.asList(
                    DynamicComponentUtil.ScrollColorfulText.getColorfulText(getTranslationKey() + ".flavor", null, color, 20, 20, true),
                    DynamicComponentUtil.ScrollColorfulText.getColorfulText(getTranslationKey() + ".description", null, color, 20, 20, true));
        }
        return descriptionList;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        tooltip.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText("我的天呢,它可真硬", null, color, 20, 20, true));
    }
}
