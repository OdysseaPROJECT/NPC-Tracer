package deltamine.ru.handlers;

import deltamine.ru.Jupiter;
import deltamine.ru.init.ItemInit;
import deltamine.ru.util.ConfigHandler;
import deltamine.ru.util.ModelChecker;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {

    public static void preInitRegistries(FMLPreInitializationEvent event) {
        ConfigHandler.registerConfig(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        for(Item item : ItemInit.ITEMS)
        {
            if(item instanceof ModelChecker)
            {
                ((ModelChecker)item).registerModels();
            }
        }
    }
}