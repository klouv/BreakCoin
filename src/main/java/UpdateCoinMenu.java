import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class UpdateCoinMenu implements CommandExecutor {

    private Map<ItemStack, Integer> CoinMenuMap;

    public UpdateCoinMenu(Main main) {
        this.CoinMenuMap = main.CoinMenuMaP;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!(args.length == 1)) {
                player.sendMessage("you need a arg. type:int");
            }
            if (args.length == 1) {
                int price = Integer.parseInt(args[0]);
                ItemStack itemHand = player.getItemInHand();
                CoinMenuMap.put(itemHand, price);
                player.sendMessage("güncelleme başarılı");
            }

        }
        return true;
    }
}
