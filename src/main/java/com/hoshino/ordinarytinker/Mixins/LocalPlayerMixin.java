package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PlayerRideableJumping;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {
    @Shadow
    public Input input;
    @Shadow
    @Final
    protected Minecraft minecraft;

    @Shadow
    @Final
    public ClientPacketListener connection;

    public LocalPlayerMixin(ClientLevel pClientLevel, GameProfile pGameProfile) {
        super(pClientLevel, pGameProfile);
    }

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void holdingFly(CallbackInfo ci) {
        if (ModifierLevel.EquipHasModifierlevel(this, OrdinaryTinkerModifier.iceBloodStaticModifier.getId())) {
            int j = 0;
            if (this.input.shiftKeyDown) {
                --j;
            }
            if (this.input.jumping) {
                ++j;
            }
            if (j != 0) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, (float) j * this.getAbilities().getFlyingSpeed() * 3.0F, 0.0D));
            }
        }
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V"), cancellable = true)
    private void prevent(CallbackInfo ci) {
        if (ModifierLevel.EquipHasModifierlevel(this, OrdinaryTinkerModifier.iceBloodStaticModifier.getId())) {
            super.aiStep();
            ci.cancel();
        }
    }
}
