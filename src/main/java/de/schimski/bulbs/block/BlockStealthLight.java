package de.schimski.bulbs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityBlockLight;
import de.schimski.bulbs.tileEntity.TileEntityLightContainer;
import de.schimski.bulbs.utility.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockStealthLight extends BlockBulbsContainer {

    @SideOnly(Side.CLIENT)
    protected IIcon blockIcon;

    public BlockStealthLight() {
        super();
        this.setBlockName("stealthLight");
        this.setHardness(0.5f);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + "itemGridLight");
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (player.getHeldItem() != null) {
            this.blockIcon = player.getHeldItem(). .getItem().getIconFromDamage(player.getHeldItem().getItem().getDamage(player.getHeldItem()));
            world.notifyBlockChange(x,y,z,this);
            world.markBlockRangeForRenderUpdate(x-1,y-1,z-1,x+1, y+1, z+1);
        }
        return true;
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        return side;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}