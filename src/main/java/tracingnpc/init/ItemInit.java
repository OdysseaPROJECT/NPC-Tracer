package tracingnpc.init;

import net.minecraft.item.Item;
import tracingnpc.items.ItemBase;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item DEBUGGER_STICK = new ItemBase("debug_stick_npc");
}
