package com.adventureplus.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;

import com.adventureplus.AdventurePlusMod;
import com.adventureplus.lib.References;
import com.adventureplus.registry.ItemRegistry;
import com.adventureplus.items.BoneShard;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Lantern extends Block{

    
    public Lantern(Material p_i45409_1_)
    {
        super(p_i45409_1_);
        GameRegistry.registerBlock(this, "Lantern");
        this.setUnlocalizedName("Lantern");
        this.setCreativeTab(AdventurePlusMod.tabAdventurePlusDeco);
        this.setStepSound(Block.soundTypeCloth);
	}

	/**
	 * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i'
	 * (inclusive).
	 */
    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
    {
        return MathHelper.clamp_int(this.quantityDropped(p_149679_2_) + p_149679_2_.nextInt(p_149679_1_ + 1), 1, 4);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return 2 + p_149745_1_.nextInt(3);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
    	 return ItemRegistry.boneShard;
       //return ItemRegistry.ricePaper;
    }

    public MapColor getMapColor(int p_149728_1_)
    {
        return MapColor.snowColor;
    }

}
