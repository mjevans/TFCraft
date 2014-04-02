package TFC.TileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TEFruitLeaves extends TileEntity
{
	public int dayHarvested = -1000;
	public int dayFruited = -1000;
	public boolean hasFruit = false;

	public TEFruitLeaves()
	{
//		this.shouldSendInitData = true;
	}

	@Override
	public boolean canUpdate()
	{
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) 
	{
		super.readFromNBT(nbt);
		dayHarvested = nbt.getInteger("dayHarvested");
		dayFruited = nbt.getInteger("dayFruited");
		hasFruit = nbt.getBoolean("hasFruit");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) 
	{
		super.writeToNBT(nbt);
		nbt.setInteger("dayHarvested", dayHarvested);
		nbt.setInteger("dayFruited", dayFruited);
		nbt.setBoolean("hasFruit", hasFruit);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.func_148857_g());
	}
	
//	@Override
//	public void handleDataPacket(DataInputStream inStream) throws IOException 
//	{
//		handleInitPacket(inStream);
//		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
//	}
//
//	public Packet createUpdatePacket() {
//		ByteArrayOutputStream bos=new ByteArrayOutputStream(15);
//		DataOutputStream dos=new DataOutputStream(bos);
//		try {
//			dos.writeByte(PacketHandler.Packet_Data_Block_Client);
//			dos.writeInt(xCoord);
//			dos.writeInt(yCoord);
//			dos.writeInt(zCoord);
//			dos.writeBoolean(hasFruit);
//		} catch (IOException e) {
//		}
//		return this.setupCustomPacketData(bos.toByteArray(), bos.size());
//	}
//
//	@Override
//	public void handleDataPacketServer(DataInputStream inStream) throws IOException 
//	{
//
//	}
//
//	@Override
//	public void createInitPacket(DataOutputStream outStream) throws IOException 
//	{
//		outStream.writeBoolean(hasFruit);
//	}
//
//	@Override
//	public void handleInitPacket(DataInputStream inStream) throws IOException 
//	{
//		hasFruit = inStream.readBoolean();
//	}
}
