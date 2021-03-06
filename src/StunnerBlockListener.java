/**
 *
 * @author Somners
 */
public class StunnerBlockListener extends PluginListener{
    FiveStarTowns plugin;
    
    /**
     *
     */
    public StunnerBlockListener(){
        plugin = FiveStarTowns.getInstance();
    }
    
        /**
     *
     * @param player
     * @param block
     * @return
     */
    public boolean onBlockDestroy(Player player, Block block){
            int x = (int)block.getX() >> 4;
            int z = (int)block.getZ() >> 4;
            String chunky = x + ":" + z;
            String town = "none";
            TownPlayer tp = plugin.getManager().getTownPlayer(player.getOfflineName());
            if(tp != null){
                town = tp.getTownName();
            }
            if(!plugin.getManager().containsKey(chunky)){
                return false;
            }
            if(plugin.getManager().get(chunky).equalsIgnoreCase("Wilderness")){
                return false;
            }
            if(!plugin.getManager().get(chunky).equalsIgnoreCase(town) && !player.isAdmin()){
                player.sendMessage("§a[§b" + plugin.getConfig().getServerName() + "§a] §f This is §b" + plugin.getManager().get(chunky) + " Territory §f you can't §bBuild §fhere.");
                return true;
            }
        return false;
    }
    
        /**
     *
     * @param player
     * @param blockPlaced
     * @param blockClicked
     * @param itemInHand
     * @return
     */
    public boolean onBlockCreate(Player player, Block blockPlaced, Block blockClicked, int itemInHand){
            int x = (int)blockClicked.getX() >> 4;
            int z = (int)blockClicked.getZ() >> 4;
            String chunky = x + ":" + z;
            String town = "none";
            TownPlayer tp = plugin.getManager().getTownPlayer(player.getOfflineName());
            if(tp != null){
                town = tp.getTownName();
            }
            if(!plugin.getManager().containsKey(chunky)){
                return false;
            }
            if(plugin.getManager().get(chunky).equalsIgnoreCase("Wilderness")){
                return false;
            }
            if(!plugin.getManager().get(chunky).equalsIgnoreCase(town) && !player.isAdmin()){
                player.sendMessage("§a[§b" + plugin.getConfig().getServerName() + "§a] §f This is §b" + plugin.getManager().get(chunky) + " Territory §f you can't §bBuild §fhere.");
                if (plugin.getConfig().getPillarProtection()){ //"AntiHaxPillarBypass" prevention. Teleports user to the ground if the server says they are two blocks high.
                    int py = (int)player.getY();
		        for (int by = py; by > 1; by--){
		            if (player.getWorld().getBlockIdAt((int)player.getX(), by, (int)player.getZ()) > 0){
		                if (py - by > 2){
		                    player.teleportTo(player.getX(), by+1.1, player.getZ(), player.getRotation(), player.getPitch());
		                }
		                break;
		            }
		        }
                    }
                    return true;
                }
            return false;
    }
}
