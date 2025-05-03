package com.hoshino.ordinarytinker.Event.Client;

import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRenderLogic;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Handler.*;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import java.util.Arrays;
import java.util.function.Predicate;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class HaloRender {
    // 步骤1：定义光环策略接口
    private interface HaloStrategy {
        ResourceLocation getTexture();
        boolean checkCondition(Player player);
        void updateState(boolean state);
    }

    // 步骤2：策略枚举统一管理
    private enum HaloStrategies implements HaloStrategy {
        HOSHINO(
                "hoshino",
                ClientHoshinoHaloHandler::setHasHalo,
                ClientHoshinoHaloHandler::shouldRenderHalo,
                OrdinaryTinkerModifier.Armorcoating.get()
        ),
        AZUSA(
                "azusa",
                ClientAzusaHaloHandler::setHasHalo,
                ClientAzusaHaloHandler::shouldRenderHalo,
                OrdinaryTinkerModifier.Crcs.get()
        ),
        REISA(
                "reisa",
                ClientReisaHaloHandler::setHasHalo,
                ClientReisaHaloHandler::shouldRenderHalo,
                OrdinaryTinkerModifier.Loyal.get()
        ),
        NATSU(
                "natsu",
                ClientNatsuHaloHandler::setHasHalo,
                ClientNatsuHaloHandler::shouldRenderHalo,
                OrdinaryTinkerModifier.Riptide.get()
        ),
        AL1S(
                "al1s",
                ClientAl1sHaloHandler::setHasHalo,
                ClientAl1sHaloHandler::shouldRenderHalo,
                OrdinaryTinkerModifier.LightningBolt.get()
        ),
        MARI(
                "mari",
                ClientMariHaloHandler::setHasHalo,
                ClientMariHaloHandler::shouldRenderHalo,
                OrdinaryTinkerModifier.Crawl.get()
        );

        private final ResourceLocation texture;
        private final BooleanConsumer stateUpdater;
        private final Predicate<Player> conditionChecker;
        private final Modifier modifier;

        HaloStrategies(String textureName,
                       BooleanConsumer stateUpdater,
                       Predicate<Player> conditionChecker,
                       Modifier modifier) {
            this.texture = OrdinaryTinker.getResource("textures/halo/" + textureName + ".png");
            this.stateUpdater = stateUpdater;
            this.conditionChecker = conditionChecker;
            this.modifier = modifier;
        }

        @Override
        public ResourceLocation getTexture() {
            return texture;
        }

        @Override
        public boolean checkCondition(Player player) {
            return conditionChecker.test(player);
        }

        @Override
        public void updateState(boolean state) {
            stateUpdater.accept(state);
        }

        public ModifierId getModifierId() {
            return modifier.getId();
        }
    }

    // 步骤3：重构事件处理方法
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == LogicalSide.CLIENT) {
            Player player = event.player;
            if (player.isSleeping()) return;
            Arrays.stream(HaloStrategies.values()).forEach(strategy -> {
                int level = ModifierUtil.getModifierLevel(
                        player.getMainHandItem(),
                        strategy.getModifierId()
                );
                strategy.updateState(level > 0);
            });
        }
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        PoseStack poseStack = event.getPoseStack();
        float partialTick = event.getPartialTick();

        Arrays.stream(HaloStrategies.values())
                .filter(strategy -> strategy.checkCondition(player))
                .forEach(strategy -> HaloRenderLogic.renderCompleteDynamicHaloHorizontal(
                        poseStack, player, partialTick, strategy.getTexture()
                ));
    }
}
