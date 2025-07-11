package com.hoshino.ordinarytinker.Content.Modifier;

import com.c2h6s.etstlib.util.DynamicComponentUtil;
import com.hoshino.ordinarytinker.Content.Entity.EagleAmmo;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerSound;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import java.util.Arrays;
import java.util.List;

public class StarFall extends Modifier implements ProjectileHitModifierHook {
    public static final int[] starFallColor=new int[]{0x5555ff,0x55aaff,0x100ff};

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if(!(attacker instanceof Player player))return false;
        var level=player.level();
        var eagleAmmo=new EagleAmmo(attacker,attacker.level(),hit.getLocation());
        var eagleAmmo2=new EagleAmmo(attacker,attacker.level(),hit.getLocation());
        var eagleAmmo3=new EagleAmmo(attacker,attacker.level(),hit.getLocation());
        eagleAmmo.setWaitTime(40);
        eagleAmmo2.setWaitTime(60);
        eagleAmmo3.setWaitTime(80);
        attacker.level().addFreshEntity(eagleAmmo);
        attacker.level().addFreshEntity(eagleAmmo2);
        attacker.level().addFreshEntity(eagleAmmo3);
        level.playSound(null,player.getOnPos(), OrdinaryTinkerSound.eagle_aim.get(), SoundSource.AMBIENT,1f,1);
        return false;
    }


    @Override
    public @NotNull List<Component> getDescriptionList() {
        if (descriptionList == null) {
            String description="弓箭射中的位置会引发一颗流星砸去,对范围内5格敌人造成等同于350%原箭矢伤害的魔法伤害并且让被命中的敌人宕机一秒,每击中一个目标增加5星尘," +
                          "\n当收集足够75星尘转而造成一次天瀑打击,范围增加至10格，击飞敌人，生成一道半径40格的冲击波,被冲击波波及到的单位还会被施加缓慢3和高额伤害";
            String flavor="无人可以践踏群星";
            descriptionList = Arrays.asList(
                    DynamicComponentUtil.ScrollColorfulText.getColorfulText(flavor,null,starFallColor,20,20,false),
                    DynamicComponentUtil.ScrollColorfulText.getColorfulText(description,null,starFallColor,20,20,false));
        }
        return descriptionList;
    }

    @Override
    public Component getDisplayName() {
        return DynamicComponentUtil.ScrollColorfulText.getColorfulText("星落/天瀑",null,starFallColor,20,20,false);
    }
}
