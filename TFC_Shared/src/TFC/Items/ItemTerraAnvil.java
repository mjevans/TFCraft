package TFC.Items;

import TFC.Core.AnvilReq;
import TFC.Core.Helper;
import TFC.TileEntities.TileEntityTerraAnvil;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.TFCBlocks;
import net.minecraft.src.World;

public class ItemTerraAnvil extends Item
{
	public int anvilId;
	public AnvilReq req;

	public ItemTerraAnvil(int i, int id, AnvilReq Areq) 
	{
		super(i);
		maxStackSize = 1;
		anvilId = id;
		req = Areq;
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int i) 
	{		
		return i;
	}

	@Override
	public String getTextureFile()
	{
		return "/bioxx/terratools.png";
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
	{
		MovingObjectPosition objectMouseOver = Helper.getMouseOverObject(entityplayer, world);
		if(objectMouseOver == null) 
		{
			return false;
		}		
		int x = objectMouseOver.blockX;
		int y = objectMouseOver.blockY;
		int z = objectMouseOver.blockZ;
		int side = objectMouseOver.sideHit;
		int meta = MathHelper.floor_double((double)(entityplayer.rotationYaw * 4F / 360F) + 0.5D) & 3;
		if(side == 1 && world.isBlockNormalCube(x, y, z) && world.isBlockOpaqueCube(x, y, z) && 
				world.getBlockId(x, y+1, z) == 0)
		{
			byte byte0 = 0;
			if(meta == 0)//+z
			{
				byte0 = 8;
			}
			if(meta == 1)//-x
			{
				byte0 = 0;
			}
			if(meta == 2)//-z
			{
				byte0 = 8;
			}
			if(meta == 3)//+x
			{
				byte0 = 0;
			}
			int id = TFCBlocks.terraAnvil.blockID;
			id = req == AnvilReq.BISMUTHBRONZE || req == AnvilReq.BLACKBRONZE || req == AnvilReq.ROSEGOLD ? TFCBlocks.terraAnvil2.blockID : TFCBlocks.terraAnvil.blockID;
			world.setBlockAndMetadataWithNotify( x, y+1, z, id, byte0+anvilId);
            world.markBlockNeedsUpdate(x, y+1, z);
			if(world.getBlockTileEntity(x, y+1, z) != null)
			{
			    TileEntityTerraAnvil te = (TileEntityTerraAnvil)world.getBlockTileEntity(x, y+1, z);
			    te.AnvilTier = req.Tier;
			}
			
			
			
			itemstack.stackSize = itemstack.stackSize-1;
			return true;
		}

		return false;
	}
}
