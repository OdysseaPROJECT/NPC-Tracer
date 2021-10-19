package tracingnpc.items;

import tracingnpc.NPCTracer;
import tracingnpc.init.ItemInit;
import tracingnpc.proxy.ClientProxy;
import tracingnpc.util.ModelChecker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBase extends Item implements ModelChecker {
    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        NPCTracer.proxy.registerModel(this, 0);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        worldIn.updateEntities();

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
