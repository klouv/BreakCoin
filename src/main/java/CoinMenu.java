import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CoinMenu implements CommandExecutor {

    private Map<ItemStack, Integer> CoinMenuMap = new HashMap<>();
    private Inventory MenuInv;

    public CoinMenu(Inventory MenuInv, Main main)  {
        this.CoinMenuMap = main.CoinMenuMaP;
        this.MenuInv = main.MenuInv;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            int size = CoinMenuMap.size();
                CoinMenuMap.forEach((k, v) -> {
                    ItemMeta meta = k.getItemMeta();
                    ArrayList<String> lore = new ArrayList<>();
                    String lor = "price: " + v;
                    lore.add(lor);
                    meta.setLore(lore);
                    k.setItemMeta(meta);
                    MenuInv.addItem(k);
                });




                //CoinMenuMap.forEach((k, v) -> {
                    //List<String> lor = new ArrayList<String>();
                    //lor.add("price: " + v.toString());
                    //lor.add(v.toString());
                    //k.getItemMeta().setLore(lor);
                    //MenuInv.addItem(k);
                //});
            }
            Player player = (Player) sender;
            player.openInventory(MenuInv);

        return true;
    }
}
