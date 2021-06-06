package deltamine.ru.objects.items;

import deltamine.ru.Jupiter;
import deltamine.ru.init.ItemInit;
import deltamine.ru.proxy.ClientProxy;
import deltamine.ru.util.ModelChecker;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandTestFor;
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
        setCreativeTab(CreativeTabs.MISC);
        setMaxStackSize(1);

        ItemInit.ITEMS.add(this);
    }
    @Override
    public void registerModels() {
        ClientProxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ICommandSender commandSender = (ICommandSender)worldIn.getMinecraftServer().getCommandSenderEntity();
        try {
            Jupiter.getLogger().info(CommandTestFor.getEntityList(worldIn.getMinecraftServer(), commandSender, "/testfor @e[c=1,type=!Player,rm=2]"));
        } catch (CommandException e) {
            e.printStackTrace();
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
