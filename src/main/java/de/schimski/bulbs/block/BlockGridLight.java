package de.schimski.bulbs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.creativetab.CreativeTabBulbs;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class BlockGridLight extends BlockBulbs{

    @SideOnly(Side.CLIENT)
    private IIcon back;
    @SideOnly(Side.CLIENT)
    private IIcon front;

    public BlockGridLight()
    {
        super();
        this.setBlockName("gridLight");
        this.setLightLevel(1f);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int p_149691_2_)
    {
        int back;
        if (p_149691_2_ % 2 == 0)
        {
            back = p_149691_2_ + 1;
        }
        else
        {
            back = p_149691_2_ - 1;
        }

        return side == p_149691_2_ ? this.front : (side == back ? this.back : this.blockIcon);
        //return side == 0 ? this.b6 : (side == 1 ? this.b1 : (side == 2 ? this.b2 : (side == 3 ? this.b3 : (side == 4 ? this.b4 : this.b5))));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon(Reference.MOD_ID + ":" + "gridLight");
        this.back = p_149651_1_.registerIcon(Reference.MOD_ID + ":" + "gridLightBack");
        this.front = p_149651_1_.registerIcon(Reference.MOD_ID + ":" + "gridLightWhite");
    }

    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
    {
        return p_149660_5_;
    }

    public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_)
    {
        LogHelper.info(p_149699_5_.getHeldItem().getDisplayName());
    }

    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        LogHelper.info(p_149727_5_.getHeldItem().getDisplayName());
        return false;
    }

    //You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
        return -1;
    }

    //It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    //It's not a normal block, so you need this too.
    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityGridLight(metadata);
    }
}
