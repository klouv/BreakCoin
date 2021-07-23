import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BreakEvent implements Listener {

    private ConfigFile config;
    private Main main;
    private Map<UUID, Integer> map = new HashMap<>();

    public BreakEvent(ConfigFile config, Main main)  {
        this.config = config;
        this.map = main.map;
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        player.sendMessage("kırdın");

        if (map.containsKey(player.getUniqueId()) == false) {
            map.put(player.getUniqueId(), 0);

            player.sendMessage("stack oluşturuldu: 0 ");
            return;
        }

        Integer stack = map.get(uuid);
        if (map.containsKey(player.getUniqueId()) == true){
            stack = stack + 1;
            player.sendMessage("stack: " + stack);
            if (stack > 10) {
                player.sendMessage("başardınnn");
                stack = stack - 10;
                player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT));

            }
        }
        map.put(player.getUniqueId(), stack);


    }

}
