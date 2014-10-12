package de.schimski.bulbs.utility;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BlockHelper {

    public static boolean checkBlockName(World world, int x, int y, int z, String unlocalizedBlockName)
    {
        if (world.getBlock(x, y, z).getUnlocalizedName().equals(unlocalizedBlockName)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkBlockNameAndMetadata(World world, int x, int y, int z, String unlocalizedBlockName, int metadata)
    {
        LogHelper.info(world.getBlock(x, y, z).getUnlocalizedName());
        LogHelper.info(world.getBlockMetadata(x,y,z));
        if (world.getBlock(x, y, z).getUnlocalizedName().equals(unlocalizedBlockName) && (world.getBlockMetadata(x,y,z) == metadata)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean[] compareBlocks4Sides(World world, int x, int y, int z, String unlocalizedName, int blockMetadata) {

        boolean[] r6 = {false, false, false, false, false, false};
        boolean[] result = {false, false, false, false};
        //String unlocalizedName = world.getBlock(x,y,z).getUnlocalizedName();
        //int blockMetadata = world.getBlockMetadata(x,y,z);

        /*
        case 0/1 (Y-Axis): check x and z
        case 2/3 (Z-Axis): check x and y
        case 4/5 (X-Axis): check z and y
        */

        r6[0] = checkBlockNameAndMetadata(world,x+1, y, z, unlocalizedName, blockMetadata);
        r6[1] = checkBlockNameAndMetadata(world,x-1, y, z, unlocalizedName, blockMetadata);
        r6[2] = checkBlockNameAndMetadata(world,x, y+1, z, unlocalizedName, blockMetadata);
        r6[3] = checkBlockNameAndMetadata(world,x, y-1, z, unlocalizedName, blockMetadata);
        r6[4] = checkBlockNameAndMetadata(world,x, y, z+1, unlocalizedName, blockMetadata);
        r6[5] = checkBlockNameAndMetadata(world,x, y, z-1, unlocalizedName, blockMetadata);

        switch (blockMetadata)
        {
            case 0: case 1:
                result[0] = r6[0];
                result[1] = r6[4];
                result[2] = r6[1];
                result[3] = r6[5];
                break;
            case 2:
                result[0] = r6[1];
                result[1] = r6[3];
                result[2] = r6[0];
                result[3] = r6[2];
                break;
            case 3:
                result[0] = r6[0];
                result[1] = r6[3];
                result[2] = r6[1];
                result[3] = r6[2];
                break;
            case 4:
                result[0] = r6[4];
                result[1] = r6[3];
                result[2] = r6[5];
                result[3] = r6[2];
                break;
            case 5:
                result[0] = r6[5];
                result[1] = r6[3];
                result[2] = r6[4];
                result[3] = r6[2];
                break;
            default:
                //default route
                break;
        }

        return result;
    }
}


