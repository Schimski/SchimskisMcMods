package de.schimski.bulbs.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.creativetab.CreativeTabBulbs;
import de.schimski.bulbs.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemScrewDriver extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon screwDriverIcon;

    public ItemScrewDriver() {
        super();
        //this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabBulbs.BULBS_TAB);
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

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
