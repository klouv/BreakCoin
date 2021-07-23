import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class MenuEvents implements Listener {
    private Inventory MenuInv;
    private Map<ItemStack, Integer> CoinMenuMap;

    public MenuEvents(Main main, Inventory MenuInv) {
        this.MenuInv = main.MenuInv;
        this. CoinMenuMap = main.CoinMenuMaP;
    }

    @EventHandler
    public void onMenuItemClickEvent(InventoryClickEvent e) {
        if (e.getInventory() == MenuInv) {
            Player player = (Player) e.getWhoClicked();
            e.setCancelled(false);
            ItemStack item = e.getCurrentItem();
            if (CoinMenuMap.containsKey(item) == true) {
                int price = CoinMenuMap.get(item);

                if(player.getInventory().contains(Material.GOLD_INGOT, price)) {
                    player.sendMessage(price + "altın karşılığında " + item + " alındı");
                    player.sendMessage("oyuncu envanterinden eşyayı silemedim iboya sor");
                    player.getInventory().addItem(item);
                    player.closeInventory();
                }
                else {
                    player.closeInventory();
                    player.sendMessage("knk paran yetmiyo");
                }

            }
            else {
                player.closeInventory();
                player.sendMessage("hata");
                return;
            }

        }
        else {
            return;
        }

    }

}
