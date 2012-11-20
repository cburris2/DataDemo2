package edu.unca.rbruce.DataDemo;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.google.common.base.Joiner;

/*
 * This is a sample CommandExectuor
 */
public class DataDemoCommandExecutor implements CommandExecutor {
	private final DataDemo plugin;

	/*
	 * This command executor needs to know about its plugin from which it came
	 * from
	 */
	public DataDemoCommandExecutor(DataDemo plugin) {
		this.plugin = plugin;
	}

	/*
	 * On command set the sample message
	 */
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED
					+ "you do not currently have permissions to use these commands");
			return false;
		} else if (command.getName().equalsIgnoreCase("god")
				&& sender.hasPermission("DataDemo.god")) {
			Player fred = (Player) sender;
			plugin.setMetadata(fred, "god", true, plugin);
			sender.sendMessage(ChatColor.RED + fred.getName()
					+ " you are a god now");
			plugin.logger.info(fred.getName() + " has been made a god");
			return true;
		} else if (command.getName().equalsIgnoreCase("human")
				&& sender.hasPermission("DataDemo.god")) {
			Player fred = (Player) sender;
			plugin.setMetadata(fred, "god", false, plugin);
			sender.sendMessage(ChatColor.RED + fred.getName()
					+ " you are human now");
			plugin.logger.info(fred.getName() + " is no longer a god");
			return true;
		} else if (command.getName().equalsIgnoreCase("message")
				&& sender.hasPermission("DataDemo.message") && args.length > 0) {
			this.plugin.getConfig().set("sample.message",
					Joiner.on(' ').join(args));
			return true;

		} else if (command.getName().equalsIgnoreCase("spider") 
				&& sender.hasPermission("DataDemo.spider")) {
			Player p = (Player) sender;
			 // player gets a spider's eye
			p.getInventory().addItem(new ItemStack(Material.SPIDER_EYE, 4));
			plugin.setMetadata(p, "spider_eye", true, plugin);
			return true;
			// the stored message now always begins with
			// the word "message"--do you know how to easily
			// fix that problem?
		} else if (command.getName().equalsIgnoreCase("xp")
				&& sender.hasPermission("DataDemo.xp")) {
			Player p = (Player)sender;
			plugin.setMetadata(p, "xp", true, plugin);
			//send a message to the player about how much experience is needed to level up 
			p.sendMessage("Experience total is " + p.getTotalExperience());
			
			p.getExpToLevel();
			return true;
		} else if (command.getName().equalsIgnoreCase("sword")
				&& sender.hasPermission("DataDemo.sword")) {
			
			Player p = (Player)sender;
			plugin.setMetadata(p, "sword", true, plugin);
			//give player an iron sword
			p.setItemInHand(new ItemStack(Material.IRON_SWORD, 1));
	        // send a message to inform player they received a sword
			p.sendMessage(ChatColor.RED + "A sword for battle... "); 
			
			return true;

		//playNote(Location loc, byte instrument, byte note); play sound method


	}else if (command.getName().equalsIgnoreCase("armor")
			&& sender.hasPermission("DataDemo.sword")) {
		Item.class.getMethods();

		Player p = (Player)sender;
		plugin.setMetadata(p, "sword", false, plugin);
		PlayerInventory inventory = p.getInventory();
		//give the player an iron set of armor 
		inventory.addItem(new ItemStack(Material.IRON_HELMET, 1));
		inventory.addItem(new ItemStack(Material.IRON_CHESTPLATE, 1));
		inventory.addItem(new ItemStack(Material.IRON_LEGGINGS, 1));
		inventory.addItem(new ItemStack(Material.IRON_HELMET, 1));


		InventoryView ce = p.openInventory(inventory);
		ce.getCursor();
		//send a message to inform player they received a set of armor
		p.sendMessage(ChatColor.RED + "Armorset is in Inventory... ");
		

		return true;


	}else if (command.getName().equalsIgnoreCase("bed")
				&& sender.hasPermission("DataDemo.bed")) {
			Player p = (Player)sender;
			Location l = p.getBedSpawnLocation();
			p.teleport(l);
			p.sendMessage("It's Bedtime");
			plugin.setMetadata(p, "bed", true, plugin);
			return true;
		}
		
		
		
		
		
		else {
			return false;
		}
	}

}
