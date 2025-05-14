package com.hoshino.ordinarytinker.Content.Modifier;

import com.hoshino.ordinarytinker.Register.OrdinaryTinkerItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.*;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ProcessLootModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.BowAmmoModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;
import java.util.function.Predicate;


public class Test extends Modifier implements BowAmmoModifierHook , ProcessLootModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,ModifierHooks.BOW_AMMO);
    }

    @Override
    public ItemStack findAmmo(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, ItemStack standardAmmo, Predicate<ItemStack> ammoPredicate) {
        return new ItemStack(OrdinaryTinkerItem.special_arrow.get());
    }

    @Override
    public void shrinkAmmo(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull LivingEntity shooter, @NotNull ItemStack ammo, int needed) {
    }

    @Override
    public void processLoot(IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot, LootContext context) {

    }
    //    public Attribute[] armorAttribute=new Attribute[]{
//            Attributes.ARMOR,Attributes.ARMOR_TOUGHNESS,Attributes.MOVEMENT_SPEED,Attributes.MAX_HEALTH,Attributes.FLYING_SPEED,Attributes.JUMP_STRENGTH,Attributes.KNOCKBACK_RESISTANCE
//    };
//    public Attribute[] weaponAttribute=new Attribute[]{
//            Attributes.ATTACK_SPEED,Attributes.ATTACK_DAMAGE,Attributes.ATTACK_KNOCKBACK
//    };
//    public UUID UUIDFromWeapon(EquipmentSlot slot, ModifierId modifierId){
//        return  UUID.nameUUIDFromBytes((slot.getName() +modifierId.toString()).getBytes(StandardCharsets.UTF_8));
//    }
//
//    @Override
//    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
//        if(tool.hasTag(TinkerTags.Items.ARMOR)){
//            for (Attribute attribute : armorAttribute) {
//                AttributeModifier attributeModifier = new AttributeModifier(UUIDFromWeapon(slot,this.getId()), attribute.getDescriptionId(), 1, AttributeModifier.Operation.ADDITION);
//                consumer.accept(attribute, attributeModifier);
//            }
//        }
//    }
}
