package de.schimski.bulbs.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.creativetab.CreativeTabBulbs;
import de.schimski.bulbs.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;

public class ItemBulbDimmable extends ItemBulbs{

    public static final String[] bulbTypes = new String[] {"lightBulbDimmableBlack", "lightBulbDimmableRed", "lightBulbDimmableGreen", "lightBulbDimmableBrown", "lightBulbDimmableBlue", "lightBulbDimmablePurple", "lightBulbDimmableCyan", "lightBulbDimmableLightGray", "lightBulbDimmableGray", "lightBulbDimmablePink", "lightBulbDimmableLimeGreen", "lightBulbDimmableYellow", "lightBulbDimmableLightBlue", "lightBulbDimmableMagenta", "lightBulbDimmableOrange", "lightBulbDimmableWhite"};

    @SideOnly(Side.CLIENT)
    private IIcon[] bulbIcons;

    public ItemBulbDimmable()
    {
        super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabBulbs.BULBS_TAB);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 15);
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", bulbTypes[i]);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        int j = MathHelper.clamp_int(damage, 0, 15);
        return this.bulbIcons[j];
    }


    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));

    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        for (int i = 0; i < 16; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        this.bulbIcons = new IIcon[bulbTypes.length];

        for (int i = 0; i < bulbTypes.length; ++i)
        {
            this.bulbIcons[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":"+ bulbTypes[i]);
        }
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
