import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    public Inventory MenuInv = Bukkit.createInventory(null, 27,"Coin Menu");
    private ConfigFile coinmenu;
    private ConfigFile config;
    public final Map<UUID, Integer> map = new HashMap<>();
    public final Map<ItemStack, Integer> CoinMenuMaP = new HashMap<>();


    @Override
    public void onEnable() {
        config = new ConfigFile(this,"config");
        coinmenu = new ConfigFile(this, "coinmenu");
        Bukkit.getPluginManager().registerEvents(new MenuEvents(this, MenuInv),this);
        Bukkit.getPluginManager().registerEvents(new BreakEvent(config, this), this);

        getCommand("updatecoinmenu").setExecutor(new UpdateCoinMenu(this));
        getCommand("coinmenu").setExecutor(new CoinMenu(MenuInv, this ));
        config.getKeys(false).forEach((key) -> {

            map.put(UUID.fromString(key), config.getInt(key));
        });

        coinmenu.getKeys(false).forEach((key) -> {
            CoinMenuMaP.put(coinmenu.getItemStack(key), coinmenu.getInt(key));
        });

    }





    @Override
    public void onDisable() {

        map.forEach((uiq, i) -> {
            config.set(uiq.toString(), i.toString());
        }) ;
        config.save();

        CoinMenuMaP.forEach((k, v) -> {
            coinmenu.set(k.toString(), v.toString());
        });

        coinmenu.save();

    }


}
