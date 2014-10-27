package de.schimski.bulbs.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.block.BlockBulbsContainer;
import de.schimski.bulbs.creativetab.CreativeTabBulbs;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityLightContainer;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class ItemScrewDriver extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon screwDriverIcon;

    public ItemScrewDriver() {
        super();
        //this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabBulbs.BULBS_TAB);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        TileEntityLightContainer tileEntity = world.getTileEntity(x,y,z) != null ? world.getTileEntity(x,y,z) instanceof TileEntityLightContainer ? (TileEntityLightContainer)world.getTileEntity(x,y,z) : null : null;

        if (player.isSneaking() && tileEntity != null) {
            tileEntity.increaseLightLevel();
            return true;
        } else if (!player.isSneaking() && tileEntity != null && tileEntity.isRotatable()) {
            tileEntity.increaseRotation();
            return true;
        }

        return false;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", "screwDriver");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return this.screwDriverIcon;
    }


    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.screwDriverIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":"+ "ScrewDriver");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add("Has RP written on it.");
        list.add("Perhaps someone just abandoned it.");
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
