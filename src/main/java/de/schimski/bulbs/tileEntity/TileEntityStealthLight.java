package de.schimski.bulbs.tileEntity;


import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;

public class TileEntityStealthLight extends TileEntityLightContainer {

    private ItemStack stealthStack;
    protected IIcon[] blockIcon;
    private String blockClass;

    public TileEntityStealthLight() {
        this(-1);
    }

    public TileEntityStealthLight(int metadata) {
        super(metadata);
        this.canRotate = false;
        blockIcon = new IIcon[6];
    }

    public IIcon getIcon(int side) {
        return blockIcon[side];
    }

    public Block getBlock() {
        Block block = Block.getBlockFromItem(stealthStack.getItem());
        LogHelper.info(stealthStack.getItemDamage());
        return block;
    }

    @Override
    public String getInventoryName() {
        return "bulbs.stealthLight";
    }

    public void saveStealthStack(ItemStack stack) {
        stealthStack = stack;

        Item item = stack.getItem();
        if (item instanceof ItemBlock) {

            int blockMeta = item.getDamage(stack);
            Block block = Block.getBlockFromItem(item);
            blockClass = block.getUnlocalizedName();
//          isOpaque = block.isOpaqueCube();
            for (int i=0; i<6; i++) {
                blockIcon[i] = block.getIcon(i, blockMeta);
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagList itemList = new NBTTagList();
        ItemStack stack = stealthStack;
        if (stack != null) {
            NBTTagCompound tag = new NBTTagCompound();
            stack.writeToNBT(tag);
            itemList.appendTag(tag);
        }
        nbt.setTag("stealthStack", itemList);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList tagList = nbt.getTagList("stealthStack",10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            stealthStack = ItemStack.loadItemStackFromNBT(tag);
        }
    }

/*
    @Override
    public boolean shouldRenderInPass(int pass)
    {
        ClientProxy.renderPass = pass;
        return true;
    }*/

}