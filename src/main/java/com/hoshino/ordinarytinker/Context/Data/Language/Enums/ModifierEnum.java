package com.hoshino.ordinarytinker.Context.Data.Language.Enums;

import com.hoshino.ordinarytinker.Context.Init.OrdinaryTinkerModifier;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public enum ModifierEnum {
    ArmorCoating(OrdinaryTinkerModifier.Armorcoating.getId(),"护甲镀层","armor coating","小时候看这集被格挡了","When I was a child, I watched this episode and was blocked","格挡掉你护甲10% * 全身该词条总等级的伤害(包括主副手），这个计算会在护甲免伤之后计算[注:此格挡实际总数值不会大于穿戴者最大生命20%]","Block 10% of your armor * The total damage of this entry (including both main and off-hand) will be calculated after armor damage reduction [Tip: The actual total value of this block will not exceed 20% of the wearer's maximum health]"),
    Sophisticated(OrdinaryTinkerModifier.SophisticatedStaticModifier.getId(),"精工细作","Sophisticated","独具匠心的锻造","Ingenious forging","提升工具400点基础耐久，如果工具是近战工具则额外增加8%攻速每级，如果为护甲则每级增加1点韧性和护甲","Increases the tool's base durability by 400, adds an additional 8% attack speed per level if the tool is a melee tool, and increases tenacity and armor by 1 per level if it is armor"),
    HighCa(OrdinaryTinkerModifier.HighCa.getId(),"高钙","High in calcium","偏爱","preference","使用时(挖掘/攻击）有概率清除身上负面效果并且获得每级10秒钙质化效果，期间持续恢复耐久并且免疫任何药水的施加,同时会持续恢复生命值，溢出的治疗量会转化为至多6点的伤害吸收","When used (Mining/Attacking), there is a chance to clear negative effects and gain calcification for 10 seconds per level, during which time it continuously restores durability and immunity to any potions, and continuously restores health, and the overflow healing is converted to up to 6 damage absorption"),
    MercuryPoisoning(OrdinaryTinkerModifier.MercuryPoisoning.getId(),"汞中毒","Mercury Poisoning","来尝一口","Come and take a bite","[对普通目标]造成5秒 * 词条等级 的 等同于词条等级的汞中毒效果.[对末影人]造成15秒 * 词条等级 的 词条等级×3的等级的的汞中毒效果,并且额外附带抑影效果.汞中毒状态会持续损失生命值并且减少80%回复血量,并且每次每级灼烧目标3点生命。如果目标为末影人，则每次灼烧还会附带其最大生命3%的伤害","[For normal targets] inflicts 5 seconds * Entry Level of Mercury Poisoning equal to Entry Level. [To Endermen] inflicts 15 seconds * Entry Level × 3 Mercury Poisoning, with additional Suppression Effect. Mercury Poison continuously loses health and reduces health regeneration by 80%, and burns the target by 3 health per level. If the target is an enderman, each burn also deals 3% damage to its maximum health");

    public final ModifierId ModifierId;
    public final String ModifierCn;
    public final String ModifierEn;
    public final String FlavorCn;
    public final String FlavorEn;
    public final String DescriptionCn;
    public final String DescriptionEn;

    ModifierEnum(ModifierId ModifierId, String ModifierCn, String ModifierEn, String FlavorCn, String FlavorEn, String DescriptionCn, String DescriptionEn) {
        this.ModifierId = ModifierId;
        this.ModifierCn=ModifierCn;
        this.ModifierEn=ModifierEn;
        this.FlavorCn = FlavorCn;
        this.FlavorEn = FlavorEn;
        this.DescriptionCn = DescriptionCn;
        this.DescriptionEn = DescriptionEn;
    }
    public ModifierId GetModifierId(){
        return this.ModifierId;
    }
    public String GetModifierCn(){
        return this.ModifierCn;
    }
    public String GetModifierEn(){
        return this.ModifierEn;
    }
    public String GetFlavorCn(){
        return this.FlavorCn;
    }
    public String GetFlavorEn(){
        return this.FlavorEn;
    }
    public String GetDescriptionCn(){
        return this.DescriptionCn;
    }
    public String GetDescriptionEn(){
        return this.DescriptionEn;
    }
}
