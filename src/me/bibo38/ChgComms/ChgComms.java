package me.bibo38.ChgComms;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class ChgComms extends JavaPlugin implements Listener
{
	private Logger log;
	private PluginDescriptionFile pdFile;
	
	public void onEnable()
	{
		log = this.getLogger();
		pdFile = this.getDescription();
		this.getConfig().options().copyDefaults(true);
        this.saveConfig();
		
        File updateord = this.getServer().getUpdateFolderFile();
        if(updateord.exists() && updateord.listFiles().length == 0)
		{
			updateord.delete();
		}
        
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(this, this);
		
		log.info("ChgComms Version " + pdFile.getVersion() + " von bibo38 wurde aktiviert!");
	}
	
	public void onDisable()
	{
		log.info("ChgComms Version " + pdFile.getVersion() + " von bibo38 wurde deaktiviert!");
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent evt)
	{
		String command = "";
		String params = "";
		if(evt.getMessage().indexOf(" ") >= 0)
		{
			command = evt.getMessage().substring(1, evt.getMessage().indexOf(" "));
			params = evt.getMessage().substring(evt.getMessage().indexOf(" ") + 1);
		} else
		{
			command = evt.getMessage().substring(1);
		}
		
		// log.info(command + "xxx" + params + "xxx");
		
		if(this.getConfig().getString(command) != null)
		{
			command = this.getConfig().getString(command);
		}
		
		evt.setMessage("/" + command + " " + params);
	}
}
