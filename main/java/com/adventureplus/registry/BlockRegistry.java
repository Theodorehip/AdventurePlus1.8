package com.adventureplus.registry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.adventureplus.AdventurePlusMod;
import com.adventureplus.blocks.Grindstone;
import com.adventureplus.blocks.Lantern;
import com.adventureplus.blocks.Millstone;
import com.adventureplus.blocks.StainedGlass;
import com.adventureplus.blocks.StainedGlassPane;
import com.adventureplus.blocks.ores.CopperOre;
import com.adventureplus.blocks.ores.Halite;
import com.adventureplus.blocks.ores.Jade;
import com.adventureplus.blocks.ores.NetherRubyOre;
import com.adventureplus.blocks.ores.SapphireOre;
import com.adventureplus.blocks.ores.SilverOre;
import com.adventureplus.lib.References;
//import com.millstone.trees.*;

public class BlockRegistry {

	/*
	//misc
	public static Block obsidianBlock = new ObsidianBlock(Material.rock).setBlockName("obsidianBlock");
	*/
	public static Block lantern = new Lantern(Material.glass).setHardness(0.2F).setLightLevel(1.5F);
	
	//Interactive Blocks
	//public static Block millstoneIdle = new Millstone(false).setUnlocalizedName("millstoneIdle").setCreativeTab(AdventurePlusMod.tabAdventurePlus).setHardness(5F);
	//public static Block millstoneActive = new Millstone(true).setUnlocalizedName("millstoneActive").setHardness(5F);;
	
	public static Block millstone = new Millstone(true).setUnlocalizedName("millstoneActive").setHardness(5F);
	public static Block active_grindstone = new Grindstone(true);
	public static Block grindstone = new Grindstone(false);
	
//	public static Block stoneCutter = new Cutter().setBlockName("stoneCutter").setCreativeTab(MillstoneMod.tabMillstone).setHardness(5F);

	//ores
	public static Block copperOre = new CopperOre(Material.rock);
	public static Block netherRubyOre = new NetherRubyOre(Material.rock);
	public static Block sapphireOre = new SapphireOre(Material.rock);
	public static Block silverOre = new SilverOre(Material.rock);
	/*
	//NonOreSpawningBlocks
	public static Block lavaBrick  = new LavaBrick(Material.rock).setBlockName("lavaBrick").setBlockTextureName(References.MODID + ":" + "lavaBrick");
	public static Block Firerack  = new Firerack(Material.rock).setBlockName("Firerack").setBlockTextureName(References.MODID + ":" + "Firerack");
	*/
	public static Block jadeOre = new Jade(Material.rock);
	public static Block haliteOre = new Halite(Material.rock);
	/*
	//Walls
	public static Block sandstoneWall = new WallBlock(Blocks.sandstone, 0).setBlockName("sandstoneWall");
	public static Block brickWall = new WallBlock(Blocks.brick_block, 1).setBlockName("brickWall");
	public static Block stoneBrickWall = new WallBlock(Blocks.stonebrick, 2).setBlockName("stoneBrickWall");
	
	//Trees
	
	public static Block log = new LogBlock().setBlockName("log").setCreativeTab(MillstoneMod.tabMillstone).setBlockTextureName("log");
	public static Block leaf = new LeafBlock().setBlockName("leaf").setCreativeTab(MillstoneMod.tabMillstoneDeco).setBlockTextureName("leaf");
	public static Block sapling = new SaplingBlock().setBlockName("sapling").setCreativeTab(MillstoneMod.tabMillstoneDeco).setBlockTextureName("sapling");

	
	//Woods 
	public static Block planks = new PlanksBlock();
	
	//Slabs
	public static Block doubleSlab = new SlabBlock(true, Material.wood);
	public static Block singleSlab = new SlabBlock(false, Material.wood);

	//Stairs
	public static Block willowStairs = new StairsBlock(planks, 0).setBlockName("willowStairs");
	public static Block palmStairs = new StairsBlock(planks, 1).setBlockName("palmStairs");
	public static Block dreadwoodStairs = new StairsBlock(planks, 2).setBlockName("dreadwoodStairs");
	public static Block redwoodStairs = new StairsBlock(planks, 3).setBlockName("redwoodStairs");
	public static Block applewoodStairs = new StairsBlock(planks, 4).setBlockName("applewoodStairs");
	public static Block cherryStairs = new StairsBlock(planks, 5).setBlockName("cherryStairs");
	public static Block baobabStairs = new StairsBlock(planks, 6).setBlockName("baobabStairs");
	
	//Wool Stairs
	public static Block woolStairs_white = new StairsWool("woolStairs_white", Blocks.wool, 7).setBlockName("woolStairs_white");
	public static Block woolStairs_magenta = new StairsWool("woolStairs_magenta", Blocks.wool, 8).setBlockName("woolStairs_magenta");
	public static Block woolStairs_orange = new StairsWool("woolStairs_orange", Blocks.wool, 9).setBlockName("woolStairs_orange");
	public static Block woolStairs_green = new StairsWool("woolStairs_green", Blocks.wool, 10).setBlockName("woolStairs_green");
	public static Block woolStairs_purple = new StairsWool("woolStairs_purple", Blocks.wool, 11).setBlockName("woolStairs_purple");
	public static Block woolStairs_blue = new StairsWool("woolStairs_blue", Blocks.wool, 12).setBlockName("woolStairs_blue");
	public static Block woolStairs_lime = new StairsWool("woolStairs_lime", Blocks.wool, 13).setBlockName("woolStairs_lime");
	public static Block woolStairs_yellow = new StairsWool("woolStairs_yellow", Blocks.wool, 14).setBlockName("woolStairs_yellow");
	public static Block woolStairs_red = new StairsWool("woolStairs_red", Blocks.wool, 15).setBlockName("woolStairs_red");
	public static Block woolStairs_cyan = new StairsWool("woolStairs_cyan", Blocks.wool, 16).setBlockName("woolStairs_cyan");
	public static Block woolStairs_black = new StairsWool("woolStairs_black", Blocks.wool, 17).setBlockName("woolStairs_black");
	public static Block woolStairs_brown = new StairsWool("woolStairs_brown", Blocks.wool, 18).setBlockName("woolStairs_brown");
	public static Block woolStairs_gray = new StairsWool("woolStairs_gray", Blocks.wool, 19).setBlockName("woolStairs_gray");
	public static Block woolStairs_pink = new StairsWool("woolStairs_pink", Blocks.wool, 20).setBlockName("woolStairs_pink");
	public static Block woolStairs_lightblue = new StairsWool("woolStairs_lightblue", Blocks.wool, 21).setBlockName("woolStairs_lightblue");
	public static Block woolStairs_lightgray = new StairsWool("woolStairs_lightgray", Blocks.wool, 22).setBlockName("woolStairs_lightgray");
	
	public static Block[] coloredBeds;
	
	*/
	public static BlockBreakable stained_glass = new StainedGlass(Material.glass);
	public static BlockPane stained_glass_pane = new StainedGlassPane();
	
	public static void Register() {
		
		//InteractiveBlocks
		//GameRegistry.registerBlock(millstoneIdle, "millstoneIdle");
		//GameRegistry.registerBlock(millstoneActive, "millstoneActive");
		GameRegistry.registerBlock(grindstone, "grindstone");
		/*
		GameRegistry.registerBlock(stoneCutter, "stoneCutter");
		
		//NonOreSpawningBlocks
		GameRegistry.registerBlock(lavaBrick, "lavaBrick");
		GameRegistry.registerBlock(Firerack, "Firerack");
		
		//Craftable
		GameRegistry.registerBlock(obsidianBlock, "obsidianBlock");		
		GameRegistry.registerBlock(lantern, "lantern");		
		
		//Walls
		GameRegistry.registerBlock(sandstoneWall, "sandstoneWall");
		GameRegistry.registerBlock(brickWall, "brickWall");
		GameRegistry.registerBlock(stoneBrickWall, "stoneBrickWall");
		
		//Tree Stuff
		
		GameRegistry.registerBlock(planks, PlanksBlockItem.class, (planks.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(log, LogBlockItem.class, (log.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(leaf, LeafBlockItem.class, (leaf.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(sapling, SaplingBlockItem.class, (sapling.getUnlocalizedName().substring(5)));

		
		//Slabs
		GameRegistry.registerBlock(doubleSlab, SlabBlockItem.class, "doubleSlab");
		GameRegistry.registerBlock(singleSlab, SlabBlockItem.class, "singleSlab");
		
		//Stairs
		GameRegistry.registerBlock(willowStairs, "willowStairs");
		GameRegistry.registerBlock(palmStairs, "palmStairs");
		GameRegistry.registerBlock(dreadwoodStairs, "dreadwoodStairs");
		GameRegistry.registerBlock(redwoodStairs, "redwoodStairs");
		GameRegistry.registerBlock(applewoodStairs, "applewoodStairs");
		GameRegistry.registerBlock(cherryStairs, "cherryStairs");
		GameRegistry.registerBlock(baobabStairs, "baobabStairs");
		
		//Wool Stairs
		GameRegistry.registerBlock(woolStairs_white, "woolStairs_white");
		GameRegistry.registerBlock(woolStairs_magenta, "woolStairs_magenta");
		GameRegistry.registerBlock(woolStairs_orange, "woolStairs_orange");
		GameRegistry.registerBlock(woolStairs_green, "woolStairs_green");
		GameRegistry.registerBlock(woolStairs_purple, "woolStairs_purple");
		GameRegistry.registerBlock(woolStairs_blue, "woolStairs_blue");
		GameRegistry.registerBlock(woolStairs_lime, "woolStairs_lime");
		GameRegistry.registerBlock(woolStairs_yellow, "woolStairs_yellow");
		GameRegistry.registerBlock(woolStairs_red, "woolStairs_red");
		GameRegistry.registerBlock(woolStairs_cyan, "woolStairs_cyan");
		GameRegistry.registerBlock(woolStairs_black, "woolStairs_black");
		GameRegistry.registerBlock(woolStairs_brown, "woolStairs_brown");
		GameRegistry.registerBlock(woolStairs_gray, "woolStairs_gray");
		GameRegistry.registerBlock(woolStairs_pink, "woolStairs_pink");
		GameRegistry.registerBlock(woolStairs_lightblue, "woolStairs_lightblue");
		GameRegistry.registerBlock(woolStairs_lightgray, "woolStairs_lightgray");
		
	
		//Weird thingy for beds...
		coloredBeds = new Block[16];
		for (int colorIndex = 0; colorIndex < coloredBeds.length; colorIndex++){
			String name = "coloredBed." + References.COLORS[colorIndex];
			coloredBeds[colorIndex] = new BlockColoredBed(colorIndex).setBlockName(name);
			GameRegistry.registerBlock(coloredBeds[colorIndex], name);
			
		}
				
		*/
		GameRegistry.registerBlock(stained_glass, "stained_glass");
		GameRegistry.registerBlock(stained_glass_pane, "stained_glass_pane");
		
	}
    
	//USE THIS TO REGISTER RENDER! DO NOT COPY/PASTE THE WHOLE LINE FOR EACH ITEM, IT'S EASIER TO CALL THE METHOD FROM ANOTHER ONE!
	public static void Render(){
		
		//Interactive Blocks
		registerRender(grindstone);
		//registerRender(active_grindstone);
		
		//Misc
		registerRender(lantern);
		
		//Decorative
		registerRender(stained_glass);
		registerRender(stained_glass_pane);

		
		//Ores
		registerRender(netherRubyOre);
		registerRender(sapphireOre);
		registerRender(jadeOre);
		registerRender(copperOre);
		registerRender(silverOre);
		registerRender(haliteOre);
		
		
		AdventurePlusMod.logger.info("Blocks Rendered");
	}
	
	
	public static void registerRender(Block block){
		
		//And here! Now you are telling the game to get the item from the block! 
		//Which means... No more Item class for the block!
		Item item = Item.getItemFromBlock(block);
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(References.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		
		
	}
}
