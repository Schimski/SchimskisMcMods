package de.schimski.bulbs.utility;

import de.schimski.bulbs.tileEntity.TileEntityLightContainer;
import net.minecraft.world.World;

public class BlockHelper {

    public static boolean checkBlockName(World world, int x, int y, int z, String unlocalizedBlockName) {
        if (world.getBlock(x, y, z).getUnlocalizedName().equals(unlocalizedBlockName)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkBlockNameAndTileState(World world, int x, int y, int z, String unlocalizedBlockName, byte tileState) {
        LogHelper.info(world.getBlock(x, y, z).getUnlocalizedName() + "  -  " + unlocalizedBlockName);
        if (world.getBlock(x, y, z).getUnlocalizedName().equals(unlocalizedBlockName)) {
            LogHelper.info(((TileEntityLightContainer)world.getTileEntity(x,y,z)).getState() + "  -  "  + tileState);
        }

        if (world.getBlock(x, y, z).getUnlocalizedName().equals(unlocalizedBlockName) && (((TileEntityLightContainer)world.getTileEntity(x,y,z)).getState() == tileState)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean[] compareBlocks4Sides(World world, int x, int y, int z, String unlocalizedName, byte tileState) {

        boolean[] r6 = {false, false, false, false, false, false};
        boolean[] result = {false, false, false, false};

        r6[0] = checkBlockNameAndTileState(world, x + 1, y, z, unlocalizedName, tileState);
        r6[1] = checkBlockNameAndTileState(world, x - 1, y, z, unlocalizedName, tileState);
        r6[2] = checkBlockNameAndTileState(world, x, y + 1, z, unlocalizedName, tileState);
        r6[3] = checkBlockNameAndTileState(world, x, y - 1, z, unlocalizedName, tileState);
        r6[4] = checkBlockNameAndTileState(world, x, y, z + 1, unlocalizedName, tileState);
        r6[5] = checkBlockNameAndTileState(world, x, y, z - 1, unlocalizedName, tileState);

        switch (tileState) {

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
                break;
        }

        return result;
    }
}


