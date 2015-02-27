package com.adventureplus.tileentity;

import com.adventureplus.blocks.Grindstone;
import com.adventureplus.container.ContainerGrindstone;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityGrindstone extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    private ItemStack[] ItemStacks = new ItemStack[3];
    private int BurnTime;
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    public String CustomName;
    private static final String __OBFID = "CL_00000357";

    public int getSizeInventory()
    {
        return this.ItemStacks.length;
    }

    public ItemStack getStackInSlot(int index)
    {
        return this.ItemStacks[index];
    }

    public ItemStack decrStackSize(int index, int count)
    {
        if (this.ItemStacks[index] != null)
        {
            ItemStack itemstack;

            if (this.ItemStacks[index].stackSize <= count)
            {
                itemstack = this.ItemStacks[index];
                this.ItemStacks[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.ItemStacks[index].splitStack(count);

                if (this.ItemStacks[index].stackSize == 0)
                {
                    this.ItemStacks[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.ItemStacks[index] != null)
        {
            ItemStack itemstack = this.ItemStacks[index];
            this.ItemStacks[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int index, ItemStack stack)
    {
        boolean flag = stack != null && stack.isItemEqual(this.ItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.ItemStacks[index]);
        this.ItemStacks[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag)
        {
            this.totalCookTime = this.func_174904_a(stack);
            this.cookTime = 0;
            this.markDirty();
        }
    }

    public String getName()
    {
        return this.hasCustomName() ? this.CustomName : "container.grindstone";
    }

    public boolean hasCustomName()
    {
        return this.CustomName != null && this.CustomName.length() > 0;
    }

    public void setCustomInventoryName(String p_145951_1_)
    {
        this.CustomName = p_145951_1_;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.ItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.ItemStacks.length)
            {
                this.ItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.BurnTime = compound.getShort("BurnTime");
        this.cookTime = compound.getShort("CookTime");
        this.totalCookTime = compound.getShort("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.ItemStacks[1]);

        if (compound.hasKey("CustomName", 8))
        {
            this.CustomName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.BurnTime);
        compound.setShort("CookTime", (short)this.cookTime);
        compound.setShort("CookTimeTotal", (short)this.totalCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.ItemStacks.length; ++i)
        {
            if (this.ItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.ItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.CustomName);
        }
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isBurning()
    {
        return this.BurnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory p_174903_0_)
    {
        return p_174903_0_.getField(0) > 0;
    }

    public void update()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.BurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (!this.isBurning() && (this.ItemStacks[1] == null || this.ItemStacks[0] == null))
            {
                if (!this.isBurning() && this.cookTime > 0)
                {
                    this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
                }
            }
            else
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.currentItemBurnTime = this.BurnTime = getItemBurnTime(this.ItemStacks[1]);

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (this.ItemStacks[1] != null)
                        {
                            --this.ItemStacks[1].stackSize;

                            if (this.ItemStacks[1].stackSize == 0)
                            {
                                this.ItemStacks[1] = ItemStacks[1].getItem().getContainerItem(ItemStacks[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.func_174904_a(this.ItemStacks[0]);
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                Grindstone.setState(this.isBurning(), this.worldObj, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    public int func_174904_a(ItemStack p_174904_1_)
    {
        return 200;
    }

    private boolean canSmelt()
    {
        if (this.ItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.ItemStacks[0]);
            if (itemstack == null) return false;
            if (this.ItemStacks[2] == null) return true;
            if (!this.ItemStacks[2].isItemEqual(itemstack)) return false;
            int result = ItemStacks[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.ItemStacks[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.ItemStacks[0]);

            if (this.ItemStacks[2] == null)
            {
                this.ItemStacks[2] = itemstack.copy();
            }
            else if (this.ItemStacks[2].getItem() == itemstack.getItem())
            {
                this.ItemStacks[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            if (this.ItemStacks[0].getItem() == Item.getItemFromBlock(Blocks.sponge) && this.ItemStacks[0].getMetadata() == 1 && this.ItemStacks[1] != null && this.ItemStacks[1].getItem() == Items.bucket)
            {
                this.ItemStacks[1] = new ItemStack(Items.water_bucket);
            }

            --this.ItemStacks[0].stackSize;

            if (this.ItemStacks[0].stackSize <= 0)
            {
                this.ItemStacks[0] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack p_145952_0_)
    {
        if (p_145952_0_ == null)
        {
            return 0;
        }
        else
        {
            Item item = p_145952_0_.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(p_145952_0_);
        }
    }

    public static boolean isItemFuel(ItemStack p_145954_0_)
    {
        return getItemBurnTime(p_145954_0_) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void openInventory(EntityPlayer player) {}

    public void closeInventory(EntityPlayer player) {}

    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return index == 2 ? false : (index != 1 ? true : isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack));
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.water_bucket && item != Items.bucket)
            {
                return false;
            }
        }

        return true;
    }

    public String getGuiID()
    {
        return "grindstone";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerGrindstone(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.BurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.BurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }

    public int getFieldCount()
    {
        return 4;
    }

    public void clear()
    {
        for (int i = 0; i < this.ItemStacks.length; ++i)
        {
            this.ItemStacks[i] = null;
        }
    }
}