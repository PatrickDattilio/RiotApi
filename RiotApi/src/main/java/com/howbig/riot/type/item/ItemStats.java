package com.howbig.riot.type.item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 5/21/2014.
 */
public class ItemStats implements Parcelable {
    public static final Parcelable.Creator<ItemStats> CREATOR = new Parcelable.Creator<ItemStats>() {
        public ItemStats createFromParcel(Parcel source) {
            return new ItemStats(source);
        }

        public ItemStats[] newArray(int size) {
            return new ItemStats[size];
        }
    };
    public double FlatHPPoolMod;
    public double rFlatHPModPerLevel;
    public double FlatMPPoolMod;
    public double rFlatMPModPerLevel;
    public double PercentHPPoolMod;
    public double PercentMPPoolMod;
    public double FlatHPRegenMod;
    public double rFlatHPRegenModPerLevel;
    public double PercentHPRegenMod;
    public double FlatMPRegenMod;
    public double rFlatMpRegenModPerLevel;
    public double PercentMPRegenMod;
    public double FlatArmorMod;
    public double rFlatArmorModPerLevel;
    public double PercentArmorMod;
    public double rFlatArmorPenetrationMod;
    public double rFlatArmorPenetrationModPerLevel;
    public double rPercentArmorPenetrationMod;
    public double rPercentArmorPenetrationModPerLevel;
    public double FlatPhysicalDamageMod;
    public double rFlatPhysicalDamageModPerLevel;
    public double PercentPhysicalDamageMod;
    public double FlatMagicDamageMod;
    public double rFlatMagicDamageModPerLevel;
    public double PercentMagicDamageMod;
    public double FlatMovementSpeedMod;
    public double rFlatMovementSpeedModPerLevel;
    public double PercentMovementSpeedMod;
    public double rPercentMovementSpeedModPerLevel;
    public double FlatAttackSpeedMod;
    public double PercentAttackSpeedMod;
    public double rPercentAttackSpeedModPerLevel;
    public double rFlatDodgeMod;
    public double rFlatDodgeModPerLevel;
    public double PercentDodgeMod;
    public double FlatCritChanceMod;
    public double rFlatCritChanceModPerLevel;
    public double PercentCritChanceMod;
    public double FlatCritDamageMod;
    public double rFlatCritDamageModPerLevel;
    public double PercentCritDamageMod;
    public double FlatBlockMod;
    public double PercentBlockMod;
    public double FlatSpellBlockMod;
    public double rFlatSpellBlockModPerLevel;
    public double PercentSpellBlockMod;
    public double FlatEXPBonus;
    public double PercentEXPBonus;
    public double rPercentCoolDownMod;
    public double rPercentCoolDownModPerLevel;
    public double rFlatTimeDeadMod;
    public double rFlatTimeDeadModPerLevel;
    public double rPercentTimeDeadMod;
    public double rPercentTimeDeadModPerLevel;
    public double rFlatGoldPer10Mod;
    public double rFlatMagicPenetrationMod;
    public double rFlatMagicPenetrationModPerLevel;
    public double rPercentMagicPenetrationMod;
    public double rPercentMagicPenetrationModPerLevel;
    public double FlatEnergyRegenMod;
    public double rFlatEnergyRegenModPerLevel;
    public double FlatEnergyPoolMod;
    public double rFlatEnergyPoolModPerLevel;
    public double PercentLifeStealMod;
    public double PercentSpellVampMod;

    public ItemStats() {
    }

    private ItemStats(Parcel in) {
        this.FlatHPPoolMod = in.readDouble();
        this.rFlatHPModPerLevel = in.readDouble();
        this.FlatMPPoolMod = in.readDouble();
        this.rFlatMPModPerLevel = in.readDouble();
        this.PercentHPPoolMod = in.readDouble();
        this.PercentMPPoolMod = in.readDouble();
        this.FlatHPRegenMod = in.readDouble();
        this.rFlatHPRegenModPerLevel = in.readDouble();
        this.PercentHPRegenMod = in.readDouble();
        this.FlatMPRegenMod = in.readDouble();
        this.rFlatMpRegenModPerLevel = in.readDouble();
        this.PercentMPRegenMod = in.readDouble();
        this.FlatArmorMod = in.readDouble();
        this.rFlatArmorModPerLevel = in.readDouble();
        this.PercentArmorMod = in.readDouble();
        this.rFlatArmorPenetrationMod = in.readDouble();
        this.rFlatArmorPenetrationModPerLevel = in.readDouble();
        this.rPercentArmorPenetrationMod = in.readDouble();
        this.rPercentArmorPenetrationModPerLevel = in.readDouble();
        this.FlatPhysicalDamageMod = in.readDouble();
        this.rFlatPhysicalDamageModPerLevel = in.readDouble();
        this.PercentPhysicalDamageMod = in.readDouble();
        this.FlatMagicDamageMod = in.readDouble();
        this.rFlatMagicDamageModPerLevel = in.readDouble();
        this.PercentMagicDamageMod = in.readDouble();
        this.FlatMovementSpeedMod = in.readDouble();
        this.rFlatMovementSpeedModPerLevel = in.readDouble();
        this.PercentMovementSpeedMod = in.readDouble();
        this.rPercentMovementSpeedModPerLevel = in.readDouble();
        this.FlatAttackSpeedMod = in.readDouble();
        this.PercentAttackSpeedMod = in.readDouble();
        this.rPercentAttackSpeedModPerLevel = in.readDouble();
        this.rFlatDodgeMod = in.readDouble();
        this.rFlatDodgeModPerLevel = in.readDouble();
        this.PercentDodgeMod = in.readDouble();
        this.FlatCritChanceMod = in.readDouble();
        this.rFlatCritChanceModPerLevel = in.readDouble();
        this.PercentCritChanceMod = in.readDouble();
        this.FlatCritDamageMod = in.readDouble();
        this.rFlatCritDamageModPerLevel = in.readDouble();
        this.PercentCritDamageMod = in.readDouble();
        this.FlatBlockMod = in.readDouble();
        this.PercentBlockMod = in.readDouble();
        this.FlatSpellBlockMod = in.readDouble();
        this.rFlatSpellBlockModPerLevel = in.readDouble();
        this.PercentSpellBlockMod = in.readDouble();
        this.FlatEXPBonus = in.readDouble();
        this.PercentEXPBonus = in.readDouble();
        this.rPercentCoolDownMod = in.readDouble();
        this.rPercentCoolDownModPerLevel = in.readDouble();
        this.rFlatTimeDeadMod = in.readDouble();
        this.rFlatTimeDeadModPerLevel = in.readDouble();
        this.rPercentTimeDeadMod = in.readDouble();
        this.rPercentTimeDeadModPerLevel = in.readDouble();
        this.rFlatGoldPer10Mod = in.readDouble();
        this.rFlatMagicPenetrationMod = in.readDouble();
        this.rFlatMagicPenetrationModPerLevel = in.readDouble();
        this.rPercentMagicPenetrationMod = in.readDouble();
        this.rPercentMagicPenetrationModPerLevel = in.readDouble();
        this.FlatEnergyRegenMod = in.readDouble();
        this.rFlatEnergyRegenModPerLevel = in.readDouble();
        this.FlatEnergyPoolMod = in.readDouble();
        this.rFlatEnergyPoolModPerLevel = in.readDouble();
        this.PercentLifeStealMod = in.readDouble();
        this.PercentSpellVampMod = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.FlatHPPoolMod);
        dest.writeDouble(this.rFlatHPModPerLevel);
        dest.writeDouble(this.FlatMPPoolMod);
        dest.writeDouble(this.rFlatMPModPerLevel);
        dest.writeDouble(this.PercentHPPoolMod);
        dest.writeDouble(this.PercentMPPoolMod);
        dest.writeDouble(this.FlatHPRegenMod);
        dest.writeDouble(this.rFlatHPRegenModPerLevel);
        dest.writeDouble(this.PercentHPRegenMod);
        dest.writeDouble(this.FlatMPRegenMod);
        dest.writeDouble(this.rFlatMpRegenModPerLevel);
        dest.writeDouble(this.PercentMPRegenMod);
        dest.writeDouble(this.FlatArmorMod);
        dest.writeDouble(this.rFlatArmorModPerLevel);
        dest.writeDouble(this.PercentArmorMod);
        dest.writeDouble(this.rFlatArmorPenetrationMod);
        dest.writeDouble(this.rFlatArmorPenetrationModPerLevel);
        dest.writeDouble(this.rPercentArmorPenetrationMod);
        dest.writeDouble(this.rPercentArmorPenetrationModPerLevel);
        dest.writeDouble(this.FlatPhysicalDamageMod);
        dest.writeDouble(this.rFlatPhysicalDamageModPerLevel);
        dest.writeDouble(this.PercentPhysicalDamageMod);
        dest.writeDouble(this.FlatMagicDamageMod);
        dest.writeDouble(this.rFlatMagicDamageModPerLevel);
        dest.writeDouble(this.PercentMagicDamageMod);
        dest.writeDouble(this.FlatMovementSpeedMod);
        dest.writeDouble(this.rFlatMovementSpeedModPerLevel);
        dest.writeDouble(this.PercentMovementSpeedMod);
        dest.writeDouble(this.rPercentMovementSpeedModPerLevel);
        dest.writeDouble(this.FlatAttackSpeedMod);
        dest.writeDouble(this.PercentAttackSpeedMod);
        dest.writeDouble(this.rPercentAttackSpeedModPerLevel);
        dest.writeDouble(this.rFlatDodgeMod);
        dest.writeDouble(this.rFlatDodgeModPerLevel);
        dest.writeDouble(this.PercentDodgeMod);
        dest.writeDouble(this.FlatCritChanceMod);
        dest.writeDouble(this.rFlatCritChanceModPerLevel);
        dest.writeDouble(this.PercentCritChanceMod);
        dest.writeDouble(this.FlatCritDamageMod);
        dest.writeDouble(this.rFlatCritDamageModPerLevel);
        dest.writeDouble(this.PercentCritDamageMod);
        dest.writeDouble(this.FlatBlockMod);
        dest.writeDouble(this.PercentBlockMod);
        dest.writeDouble(this.FlatSpellBlockMod);
        dest.writeDouble(this.rFlatSpellBlockModPerLevel);
        dest.writeDouble(this.PercentSpellBlockMod);
        dest.writeDouble(this.FlatEXPBonus);
        dest.writeDouble(this.PercentEXPBonus);
        dest.writeDouble(this.rPercentCoolDownMod);
        dest.writeDouble(this.rPercentCoolDownModPerLevel);
        dest.writeDouble(this.rFlatTimeDeadMod);
        dest.writeDouble(this.rFlatTimeDeadModPerLevel);
        dest.writeDouble(this.rPercentTimeDeadMod);
        dest.writeDouble(this.rPercentTimeDeadModPerLevel);
        dest.writeDouble(this.rFlatGoldPer10Mod);
        dest.writeDouble(this.rFlatMagicPenetrationMod);
        dest.writeDouble(this.rFlatMagicPenetrationModPerLevel);
        dest.writeDouble(this.rPercentMagicPenetrationMod);
        dest.writeDouble(this.rPercentMagicPenetrationModPerLevel);
        dest.writeDouble(this.FlatEnergyRegenMod);
        dest.writeDouble(this.rFlatEnergyRegenModPerLevel);
        dest.writeDouble(this.FlatEnergyPoolMod);
        dest.writeDouble(this.rFlatEnergyPoolModPerLevel);
        dest.writeDouble(this.PercentLifeStealMod);
        dest.writeDouble(this.PercentSpellVampMod);
    }
}
