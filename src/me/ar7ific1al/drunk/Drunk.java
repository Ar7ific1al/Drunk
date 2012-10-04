package me.ar7ific1al.drunk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import net.minecraft.server.World;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Drunk extends JavaPlugin implements Listener	{
		
	
	public static Drunk plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	String pluginVersion;
	String noPerms = "You do not have permission to execute that command.";
	
	//
	public String TempString;
	public int TempInt;
	public boolean TempBool;
	public boolean drunk = false;
	
	//Chat Colors
	ChatColor BLACK = ChatColor.BLACK;
	ChatColor DBLUE = ChatColor.DARK_BLUE;
	ChatColor DGREEN = ChatColor.DARK_GREEN;
	ChatColor DAQUA = ChatColor.DARK_AQUA;
	ChatColor DRED = ChatColor.DARK_RED;
	ChatColor PURPLE = ChatColor.DARK_PURPLE;
	ChatColor GOLD = ChatColor.GOLD;
	ChatColor GRAY = ChatColor.GRAY;
	ChatColor DGRAY = ChatColor.DARK_GRAY;
	ChatColor BLUE = ChatColor.BLUE;
	ChatColor LGREEN = ChatColor.GREEN;
	ChatColor LAQUA = ChatColor.AQUA;
	ChatColor LRED = ChatColor.RED;
	ChatColor PINK = ChatColor.LIGHT_PURPLE;
	ChatColor YELLOW = ChatColor.YELLOW;
	ChatColor WHITE = ChatColor.WHITE;
	ChatColor ITALIC = ChatColor.ITALIC;
	ChatColor MAGIC = ChatColor.MAGIC;
	ChatColor STRIKE = ChatColor.STRIKETHROUGH;
	ChatColor ULINE = ChatColor.UNDERLINE;
	ChatColor ResColor = ChatColor.RESET;
	
	/**	Potion Effects
		PotionEffectType BLIND = PotionEffectType.BLINDNESS;
		PotionEffectType NAUSEA = PotionEffectType.CONFUSION;
		PotionEffectType DAMAGE_RESIST = PotionEffectType.DAMAGE_RESISTANCE;
		PotionEffectType HASTE = PotionEffectType.FAST_DIGGING;
		PotionEffectType FIRE_RESIST = PotionEffectType.FIRE_RESISTANCE;
		PotionEffectType HARM = PotionEffectType.HARM;
		PotionEffectType HEAL = PotionEffectType.HEAL;
		PotionEffectType HUNGER = PotionEffectType.HUNGER;
		PotionEffectType DAMAGE_UP = PotionEffectType.INCREASE_DAMAGE;
		PotionEffectType INVISIBLE = PotionEffectType.INVISIBILITY;
		PotionEffectType JUMP = PotionEffectType.JUMP;
		PotionEffectType NIGHTVISION = PotionEffectType.NIGHT_VISION;
		PotionEffectType POISON = PotionEffectType.POISON;
		PotionEffectType REGEN = PotionEffectType.REGENERATION;
		PotionEffectType SPEED = PotionEffectType.SPEED;
		PotionEffectType GILLS = PotionEffectType.WATER_BREATHING;
		PotionEffectType WEAK = PotionEffectType.WEAKNESS;
		PotionEffectType SLOW = PotionEffectType.SLOW;
		PotionEffectType SLOWDIGGING = PotionEffectType.SLOW_DIGGING;		
	*/
	
	//HashMap Stuff!
	public final HashMap<Player, ArrayList<String>> drunk_players = new HashMap<Player, ArrayList<String>>();
	
	/*public void saveDrunkPlayers(HashMap<Player, ArrayList<String>> map, String path)	{
		try	{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(map);
			oos.flush();
			oos.close();
			
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
	}*/
	
	//Scheduler Stuff

	
	//Config Stuff
	File drunkConfig;
	public static FileConfiguration config;
	
	public void loadConfig()	{
		//Ale
		//Primary Effect
		this.getConfig().addDefault("Ale.Effects.Primary.Effect", "CONFUSION");
		this.getConfig().addDefault("Ale.Effects.Primary.Duration", 1000);
		this.getConfig().addDefault("Ale.Effects.Primary.Amplifier", 1);
		//Secondary Effect
		this.getConfig().addDefault("Ale.Effects.Secondary.Effect", "NULL");
		this.getConfig().addDefault("Ale.Effects.Secondary.Duration", 1000);
		this.getConfig().addDefault("Ale.Effects.Secondary.Amplifier", 1);
		//Tertiary Effect
		this.getConfig().addDefault("Ale.Effects.Tertiary.Effect", "NULL");
		this.getConfig().addDefault("Ale.Effects.Tertiary.Duration", 1000);
		this.getConfig().addDefault("Ale.Effects.Tertiary.Amplifier", 1);
		
		//Ale Drunken Chatter
		this.getConfig().addDefault("Ale.Chatter.Enabled", true);
		this.getConfig().addDefault("Ale.Chatter.1", "Gaiz, I swear to drunk I'm not god...");
		this.getConfig().addDefault("Ale.Chatter.2", "I'm okay! I'm okay... *hic* I'm okay!");
		this.getConfig().addDefault("Ale.Chatter.3", "I got this! I got this... *hic* I don't got this...");
		this.getConfig().addDefault("Ale.Chatter.4", "You're too drunk to drive, gimme the.. gimme the.. keys! *hic*");
		this.getConfig().addDefault("Ale.Chatter.5", "Get out of my boat! What are you doing in my boat!? *hic*");
		
		//Ale Drink Message
		this.getConfig().addDefault("Ale.Drink Message", "You take a swig of Ale and begin to feel a little woozy!");
		
		//Lager
		//Primary Effect
		this.getConfig().addDefault("Lager.Effects.Primary.Effect", "CONFUSION");
		this.getConfig().addDefault("Lager.Effects.Primary.Duration", 1000);
		this.getConfig().addDefault("Lager.Effects.Primary.Amplifier", 1);
		//Secondary Effect
		this.getConfig().addDefault("Lager.Effects.Secondary.Effect", "NULL");
		this.getConfig().addDefault("Lager.Effects.Secondary.Duration", 1000);
		this.getConfig().addDefault("Lager.Effects.Secondary.Amplifier", 1);
		//Tertiary Effect
		this.getConfig().addDefault("Lager.Effects.Tertiary.Effect", "NULL");
		this.getConfig().addDefault("Lager.Effects.Tertiary.Duration", 1000);
		this.getConfig().addDefault("Lager.Effects.Tertiary.Amplifier", 1);
		
		//Lager Drunken Chatter
		this.getConfig().addDefault("Lager.Chatter.Enabled", true);
		this.getConfig().addDefault("Lager.Chatter.1", "Gaiz, I swear to drunk I'm not god...");
		this.getConfig().addDefault("Lager.Chatter.2", "I'm okay! I'm okay... *hic* I'm okay!");
		this.getConfig().addDefault("Lager.Chatter.3", "I got this! I got this... *hic* I don't got this...");
		this.getConfig().addDefault("Lager.Chatter.4", "You're too drunk to drive, gimme the.. gimme the.. keys! *hic*");
		this.getConfig().addDefault("Lager.Chatter.5", "Get out of my boat! What are you doing in my boat!? *hic*");

		//Lager Drink Message
		this.getConfig().addDefault("Lager.Drink Message", "You take a swig of Lager and begin to feel a little sluggish...");
		
		this.getConfig().addDefault("Global.Cooldown Time", 6000);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void saveYamls()	{
		try	{
			config.save(drunkConfig);
		}	catch (IOException e)	{
			e.printStackTrace();
		}
	}
	//End Config Stuff
	
	@Override
	public void onEnable()	{
		PluginDescriptionFile pdFile = this.getDescription();
		drunkConfig = new File(getDataFolder(), "config.yml");
		config = getConfig();
		loadConfig();
		
		pluginVersion = pdFile.getVersion();
		this.logger.info(LRED + "[Drunk] " + ChatColor.WHITE + " version " + ChatColor.GOLD +
				pluginVersion + " is now providing alcoholic beverages.");
	}
	
	@Override
	public void onDisable()	{
		PluginDescriptionFile pdFile = this.getDescription();
		this.logger.info(LRED + "[Drunk] " + YELLOW + " version " + GOLD +
				pluginVersion + " is no longer providing alcoholic beverages.");
	}
	
	//Commands Stuff
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args)	{
		
		//final Player player = (Player) sender;
		
		if (sender instanceof Player)	{
			final Player player = (Player) sender;
			
			//Drink Commands
			if (cmdLabel.equalsIgnoreCase("drink"))	{
				try	{
					if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help")
							|| args[0].equalsIgnoreCase("info"))	{
						if (player.isOp() || player.hasPermission("drunk.info"))	{
							player.sendMessage(LRED + "Drunk" + YELLOW + " version "
									+ GOLD + pluginVersion + YELLOW + "");
							player.sendMessage(GRAY + "  - /drink <help|info|?>: Where you are now, plugin help");
							player.sendMessage(GRAY + "  - /drink list: Shows you the list of available drinks");
							player.sendMessage(GRAY + "  - /drink <ale|lager>: Take a swig 'o Ale or Lager, friend!");
							if (player.getName().matches("Ar7ific1al"))	{
								player.sendMessage(LRED + "[Drunk] " + YELLOW + "I see you are an admin! More commands for you:");
								player.sendMessage(GRAY + "  - /drunk reload: Reload config. Use only if you changed the config manually");
								player.sendMessage(GRAY + "  - /drunk report <ale|lager>: Full report on settings for the specified drink");
								player.sendMessage(GRAY + "  - /drunk <ale|lager> <primary|secondary|tertiary> <effect|duration|amplifier> value: Set the chosen value to the one specified");
							}
						}
						else if (!player.isOp() || !player.hasPermission("drunk.info"))	{
							player.sendMessage(LRED + "[Drunk] " + YELLOW + noPerms);
						}
					}
					if (args[0].equalsIgnoreCase("list"))	{
						if (player.isOp() || player.hasPermission("drunk.drinklist"))	{
							player.sendMessage(LGREEN + "These are the available drinks:" +
									GRAY + "\n  - Ale" +
									GRAY + "\n  - Lager");
						}
						else if (!player.isOp() || !player.hasPermission("drunk.drinklist"))	{
							player.sendMessage(LRED + "[Drunk] " + YELLOW + noPerms);
						}
					}
					//Drink Command
					//Ale
					if (args[0].equalsIgnoreCase("ale"))	{
						if (player.isOp() || player.hasPermission("drunk.drink.ale"))	{
							if (!drunk_players.containsKey(player))	{	

								player.sendMessage(LGREEN + "" + MAGIC + "::" + ResColor + LGREEN + config.getString("Ale.Drink Message") +
										MAGIC + "::");
								//Add Potion Effects
								if (!config.getString("Ale.Effects.Primary.Effect").equalsIgnoreCase("null"))	{
									player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(config.getString("Ale.Effects.Primary.Effect")),
											config.getInt("Ale.Effects.Primary.Duration"), config.getInt("Ale.Effects.Primary.Amplifier")));
								}
								if (!config.getString("Ale.Effects.Secondary.Effect").equalsIgnoreCase("null"))	{
									player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(config.getString("Ale.Effects.Secondary.Effect")),
											config.getInt("Ale.Effects.Secondary.Duration"), config.getInt("Ale.Effects.Secondary.Amplifier")));
								}
								if (!config.getString("Ale.Effects.Tertiary.Effect").equalsIgnoreCase("null"))	{
									player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(config.getString("Ale.Effects.Tertiary.Effect")),
											config.getInt("Ale.Effects.Tertiary.Duration"), config.getInt("Ale.Effects.Tertiary.Amplifier")));
								}
								drunk_players.put(player, null);
								//saveDrunkPlayers(drunk_players, getDataFolder() + File.separator + "drunk_players.bin");
								getServer().broadcastMessage(LRED + "" + player.getName() + YELLOW + " is drunk on Ale!");
								
								//Add Schedulers for player
								//Drink cooldown
								
								this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()	{
									public void run()	{
										getServer().broadcastMessage(LRED + "" + player.getName() + YELLOW + " is sober now.");
										drunk_players.remove(player);
										//saveDrunkPlayers(drunk_players, getDataFolder() + File.separator + "drunk_players.bin");
									}
								}, config.getLong("Global.Cooldown Time"));
								
								//Drunken Chat Spam!! :D
								this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()	{
									public void run()	{
										if (drunk_players.containsKey(player) && config.getBoolean("Ale.Chatter.Enabled") == true)	{
											Random rand = new Random();
											int randNum = rand.nextInt(100) + 1;
											if (randNum >=90)	{
												Random talk = new Random();
												int randTalk = talk.nextInt(4) + 1;
												if (randTalk == 1)	{
													player.chat(config.getString("Ale.Chatter.1"));
												}
												else if (randTalk == 2)	{
													player.chat(config.getString("Ale.Chatter.2"));
												}
												else if (randTalk == 3)	{
													player.chat(config.getString("Ale.Chatter.3"));
												}
												else if (randTalk == 4)	{
													player.chat(config.getString("Ale.Chatter.4"));
												}
												else if (randTalk == 5)	{
													player.chat(config.getString("Ale.Chatter.5"));
												}
												
											}
											
										}
										
									}
									
								}, 20L, 100L);
								//Drunk Effect
								/*this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()	{
									public void run()	{
										if (drunk_players.containsKey(player))	{
											player.getWorld().playEffect(player.getLocation(), Effect.POTION_BREAK, 5);
											
										}
									}
								}, 10L, 10L);*/
							
								
								
								//end of player scheduler
								drunk_players.put(player, null);
								//saveDrunkPlayers(drunk_players, getDataFolder() + File.separator + "drunk_players.bin");
							}
							else if(drunk_players.containsKey(player))	{
								
								player.sendMessage(LRED + "[Drunk] " + YELLOW + "You're still drunk! You cannot drink until you are sober!");
							}
								
								

								//player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
								//player.getWorld().strikeLightningEffect(player.getLocation());
								//player.playNote(loc, instrument, note)
						}
						else if (!player.isOp() || !player.hasPermission("drunk.drink.ale"))	{
							player.sendMessage(LRED + "[Drunk] " + YELLOW + noPerms);
						}
					}
					//Lager
					if (args[0].equalsIgnoreCase("lager"))	{
						if (player.isOp() || player.hasPermission("drunk.drink.lager"))	{
							if (!drunk_players.containsKey(player))	{	

								player.sendMessage(LGREEN + "" + MAGIC + "::" + ResColor + LGREEN + config.getString("Lager.Drink Message") +
										MAGIC + "::");
								//Add Potion Effects
								if (!config.getString("Lager.Effects.Primary.Effect").equalsIgnoreCase("null"))	{
									player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(config.getString("Lager.Effects.Primary.Effect")),
											config.getInt("Lager.Effects.Primary.Duration"), config.getInt("Lager.Effects.Primary.Amplifier")));
								}
								if (!config.getString("Lager.Effects.Secondary.Effect").equalsIgnoreCase("null"))	{
									player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(config.getString("Lager.Effects.Secondary.Effect")),
											config.getInt("Lager.Effects.Secondary.Duration"), config.getInt("Lager.Effects.Secondary.Amplifier")));
								}
								if (!config.getString("Lager.Effects.Tertiary.Effect").equalsIgnoreCase("null"))	{
									player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(config.getString("Lager.Effects.Tertiary.Effect")),
											config.getInt("Lager.Effects.Tertiary.Duration"), config.getInt("Lager.Effects.Tertiary.Amplifier")));
								}
								drunk_players.put(player, null);
								//saveDrunkPlayers(drunk_players, getDataFolder() + File.separator + "drunk_players.bin");
								getServer().broadcastMessage(LRED + "" + player.getName() + YELLOW + " is drunk on Lager!");
								
								//Add Schedulers for player
								//Drink cooldown
								
								this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()	{
									public void run()	{
										getServer().broadcastMessage(LRED + "" + player.getName() + YELLOW + " is sober now.");
										drunk_players.remove(player);
										//saveDrunkPlayers(drunk_players, getDataFolder() + File.separator + "drunk_players.bin");
									}
								}, config.getLong("Global.Cooldown Time"));
								
								//Drunken Chat Spam!! :D
								this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()	{
									public void run()	{
										if (drunk_players.containsKey(player) && config.getBoolean("Lager.Chatter.Enabled") == true)	{
											Random rand = new Random();
											int randNum = rand.nextInt(100) + 1;
											if (randNum >=90)	{
												Random talk = new Random();
												int randTalk = talk.nextInt(4) + 1;
												if (randTalk == 1)	{
													player.chat(config.getString("Lager.Chatter.1"));
												}
												else if (randTalk == 2)	{
													player.chat(config.getString("Lager.Chatter.2"));
												}
												else if (randTalk == 3)	{
													player.chat(config.getString("Lager.Chatter.3"));
												}
												else if (randTalk == 4)	{
													player.chat(config.getString("Lager.Chatter.4"));
												}
												else if (randTalk == 5)	{
													player.chat(config.getString("Lager.Chatter.5"));
												}
												
											}
											
										}
										
									}
									
								}, 20L, 100L);
								//Drunk Effect
								/*this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()	{
									public void run()	{
										if (drunk_players.containsKey(player))	{
											player.getWorld().playEffect(player.getLocation(), Effect.POTION_BREAK, 5);
											
										}
									}
								}, 10L, 10L);*/
							
								
								
								//end of player scheduler
								drunk_players.put(player, null);
								//saveDrunkPlayers(drunk_players, getDataFolder() + File.separator + "drunk_players.bin");
							}
							else if(drunk_players.containsKey(player))	{
								
								player.sendMessage(LRED + "[Drunk] " + YELLOW + "You're still drunk! You cannot drink until you are sober!");
							}
								
								

								//player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
								//player.getWorld().strikeLightningEffect(player.getLocation());
								//player.playNote(loc, instrument, note)
						}
						else if (!player.isOp() || !player.hasPermission("drunk.drink.lager"))	{
							player.sendMessage(LRED + "[Drunk] " + YELLOW + noPerms);
						}
					}

				}
				catch (ArrayIndexOutOfBoundsException ex)	{
					return false;
				}
			}
			//Admin Commands
			if (cmdLabel.equalsIgnoreCase("drunk"))	{
				try	{
					//Ale Report
					if (args[0].equalsIgnoreCase("report") && args[1].equalsIgnoreCase("ale"))	{
						player.sendMessage(LRED + "[Drunk] " + YELLOW + "Current settings for Ale");
						//Primary Effect Report
						player.sendMessage(GRAY + "  - Primary");
						player.sendMessage(GRAY + "       Effect Type: " + config.getString("Ale.Effects.Primary.Effect"));
						player.sendMessage(GRAY + "       Effect Duration: " + config.getString("Ale.Effects.Primary.Duration"));
						player.sendMessage(GRAY + "       Effect Amplifier: " + config.getString("Ale.Effects.Primary.Amplifier"));
						
						//Secondary Effect Report
						player.sendMessage(GRAY + "  - Secondary");
						player.sendMessage(GRAY + "       Effect Type: " + config.getString("Ale.Effects.Secondary.Effect"));
						player.sendMessage(GRAY + "       Effect Duration: " + config.getString("Ale.Effects.Secondary.Duration"));
						player.sendMessage(GRAY + "       Effect Amplifier: " + config.getString("Ale.Effects.Secondary.Amplifier"));
						
						//Tertiary Effect Report
						player.sendMessage(GRAY + "  - Tertiary");
						player.sendMessage(GRAY + "       Effect Type: " + config.getString("Ale.Effects.Tertiary.Effect"));
						player.sendMessage(GRAY + "       Effect Duration: " + config.getString("Ale.Effects.Tertiary.Duration"));
						player.sendMessage(GRAY + "       Effect Amplifier: " + config.getString("Ale.Effects.Tertiary.Amplifier"));
					}
					//Lager Report
					if (args[0].equalsIgnoreCase("report") && args[1].equalsIgnoreCase("lager"))	{
						player.sendMessage(LRED + "[Drunk] " + YELLOW + "Current settings for Lager");
						//Primary Effect Report
						player.sendMessage(GRAY + "  - Primary");
						player.sendMessage(GRAY + "       Effect Type: " + config.getString("Lager.Effects.Primary.Effect"));
						player.sendMessage(GRAY + "       Effect Duration: " + config.getString("Lager.Effects.Primary.Duration"));
						player.sendMessage(GRAY + "       Effect Amplifier: " + config.getString("Lager.Effects.Primary.Amplifier"));
						
						//Secondary Effect Report
						player.sendMessage(GRAY + "  - Secondary");
						player.sendMessage(GRAY + "       Effect Type: " + config.getString("Lager.Effects.Secondary.Effect"));
						player.sendMessage(GRAY + "       Effect Duration: " + config.getString("Lager.Effects.Secondary.Duration"));
						player.sendMessage(GRAY + "       Effect Amplifier: " + config.getString("Lager.Effects.Secondary.Amplifier"));
						
						//Tertiary Effect Report
						player.sendMessage(GRAY + "  - Tertiary");
						player.sendMessage(GRAY + "       Effect Type: " + config.getString("Lager.Effects.Tertiary.Effect"));
						player.sendMessage(GRAY + "       Effect Duration: " + config.getString("Lager.Effects.Tertiary.Duration"));
						player.sendMessage(GRAY + "       Effect Amplifier: " + config.getString("Lager.Effects.Tertiary.Amplifier"));
					}
					//Reload Config
					if (args[0].equalsIgnoreCase("reload"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + " successfully reloaded.");
						}
						else if (!player.isOp() || !player.hasPermission("drunk.admin"))	{
							player.sendMessage(LRED + "[Drunk] " + YELLOW + noPerms);
						}
				}
					
					//Set Ale Primary Effect
					if (args[0].equalsIgnoreCase("ale") && args[1].equalsIgnoreCase("primary") 
							&& args[2].equalsIgnoreCase("effect"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3].toUpperCase();
							this.logger.info("[Drunk] " + TempString);
							config.set("Ale.Effects.Primary.Effect", TempString);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Primary Ale effect set to " +
							config.getString("Ale.Effects.Primary.Effect"));
							
						}

					}
					//Set Ale Primary Duration
					if (args[0].equalsIgnoreCase("ale") && args[1].equalsIgnoreCase("primary") &&
							args[2].equalsIgnoreCase("duration"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							this.logger.info("[Drunk] " + TempInt);
							config.set("Ale.Effects.Primary.Duration", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Primary Ale effect duration set to " +
							config.getString("Ale.Effects.Primary.Duration"));
						}

					}
					//Set Ale Primary Amplifier
					if (args[0].equalsIgnoreCase("ale") && args[1].equalsIgnoreCase("primary") &&
							args[2].equalsIgnoreCase("amplifier"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							if (TempInt > 4)	{
								player.sendMessage(LRED + "[Drunk] " + YELLOW + "Amplifier is over four;"
										+ " This may have extreme side effects for players.");
							}
							this.logger.info("[Drunk] " + TempInt);
							config.set("Ale.Effects.Primary.Amplifier", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Primary Ale effect duration set to " +
							config.getString("Ale.Effects.Primary.Amplifier"));
						}

					}
					
					//Set Ale Secondary Effect
					if (args[0].equalsIgnoreCase("ale") && args[1].equalsIgnoreCase("secondary") 
							&& args[2].equalsIgnoreCase("effect"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3].toUpperCase();
							this.logger.info("[Drunk] " + TempString);
							config.set("Ale.Effects.Secondary.Effect", TempString);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Secondary Ale effect set to " +
							config.getString("Ale.Effects.Secondary.Effect"));
							
						}

					}
					//Set Ale Secondary Duration
					if (args[0].equalsIgnoreCase("ale") && args[1].equalsIgnoreCase("secondary") &&
							args[2].equalsIgnoreCase("duration"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							this.logger.info("[Drunk] " + TempInt);
							config.set("Ale.Effects.Secondary.Duration", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Secondary Ale effect duration set to " +
							config.getString("Ale.Effects.Secondary.Duration"));
						}

					}
					//Set Ale Secondary Amplifier
					if (args[0].equalsIgnoreCase("ale") && args[1].equalsIgnoreCase("secondary") &&
							args[2].equalsIgnoreCase("amplifier"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							if (TempInt > 4)	{
								player.sendMessage(LRED + "[Drunk] " + YELLOW + "Amplifier is over four;"
										+ " This may have extreme side effects for players.");
							}
							this.logger.info("[Drunk] " + TempInt);
							config.set("Ale.Effects.Secondary.Amplifier", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Secondary Ale effect duration set to " +
							config.getString("Ale.Effects.Secondary.Amplifier"));
						}

					}
					
					//Set Ale Tertiary Effect
					if (args[0].equalsIgnoreCase("ale") && args[1].equalsIgnoreCase("tertiary") 
							&& args[2].equalsIgnoreCase("effect"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3].toUpperCase();
							this.logger.info("[Drunk] " + TempString);
							config.set("Ale.Effects.Tertiary.Effect", TempString);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Tertiary Ale effect set to " +
							config.getString("Ale.Effects.Tertiary.Effect"));
							
						}

					}
					//Set Ale Tertiary Duration
					if (args[0].equalsIgnoreCase("ale") && args[1].equalsIgnoreCase("tertiary") &&
							args[2].equalsIgnoreCase("duration"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							this.logger.info("[Drunk] " + TempInt);
							config.set("Ale.Effects.Tertiary.Duration", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Tertiary Ale effect duration set to " +
							config.getString("Ale.Effects.Tertiary.Duration"));
						}

					}
					//Set Ale Tertiary Amplifier
					if (args[0].equalsIgnoreCase("ale") && args[1].equalsIgnoreCase("tertiary") &&
							args[2].equalsIgnoreCase("amplifier"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							if (TempInt > 4)	{
								player.sendMessage(LRED + "[Drunk] " + YELLOW + "Amplifier is over four;"
										+ " This may have extreme side effects for players.");
							}
							this.logger.info("[Drunk] " + TempInt);
							config.set("Ale.Effects.Tertiary.Amplifier", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Tertiary Ale effect duration set to " +
							config.getString("Ale.Effects.Tertiary.Amplifier"));
						}

					}
					
					//Lager Commands
					//Set Lager Primary Effect
					if (args[0].equalsIgnoreCase("lager") && args[1].equalsIgnoreCase("primary") 
							&& args[2].equalsIgnoreCase("effect"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3].toUpperCase();
							this.logger.info("[Drunk] " + TempString);
							config.set("Lager.Effects.Primary.Effect", TempString);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Primary Lager effect set to " +
							config.getString("Lager.Effects.Primary.Effect"));
							
						}

					}
					//Set Lager Primary Duration
					if (args[0].equalsIgnoreCase("lager") && args[1].equalsIgnoreCase("primary") &&
							args[2].equalsIgnoreCase("duration"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							this.logger.info("[Drunk] " + TempInt);
							config.set("Lager.Effects.Primary.Duration", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Primary Lager effect duration set to " +
							config.getString("Lager.Effects.Primary.Duration"));
						}

					}
					//Set Lager Primary Amplifier
					if (args[0].equalsIgnoreCase("lager") && args[1].equalsIgnoreCase("primary") &&
							args[2].equalsIgnoreCase("amplifier"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							if (TempInt > 4)	{
								player.sendMessage(LRED + "[Drunk] " + YELLOW + "Amplifier is over four;"
										+ " This may have extreme side effects for players.");
							}
							this.logger.info("[Drunk] " + TempInt);
							config.set("Lager.Effects.Primary.Amplifier", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Primary Lager effect duration set to " +
							config.getString("Lager.Effects.Primary.Amplifier"));
						}

					}
					
					//Set Lager Secondary Effect
					if (args[0].equalsIgnoreCase("lager") && args[1].equalsIgnoreCase("secondary") 
							&& args[2].equalsIgnoreCase("effect"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3].toUpperCase();
							this.logger.info("[Drunk] " + TempString);
							config.set("Lager.Effects.Secondary.Effect", TempString);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Secondary Lager effect set to " +
							config.getString("Lager.Effects.Secondary.Effect"));
							
						}

					}
					//Set Lager Secondary Duration
					if (args[0].equalsIgnoreCase("lager") && args[1].equalsIgnoreCase("secondary") &&
							args[2].equalsIgnoreCase("duration"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							this.logger.info("[Drunk] " + TempInt);
							config.set("Lager.Effects.Secondary.Duration", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Secondary Lager effect duration set to " +
							config.getString("Lager.Effects.Secondary.Duration"));
						}

					}
					//Set Lager Secondary Amplifier
					if (args[0].equalsIgnoreCase("lager") && args[1].equalsIgnoreCase("secondary") &&
							args[2].equalsIgnoreCase("amplifier"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							if (TempInt > 4)	{
								player.sendMessage(LRED + "[Drunk] " + YELLOW + "Amplifier is over four;"
										+ " This may have extreme side effects for players.");
							}
							this.logger.info("[Drunk] " + TempInt);
							config.set("Lager.Effects.Secondary.Amplifier", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Secondary Lager effect duration set to " +
							config.getString("Lager.Effects.Secondary.Amplifier"));
						}

					}
					
					//Set Lager Tertiary Effect
					if (args[0].equalsIgnoreCase("lager") && args[1].equalsIgnoreCase("tertiary") 
							&& args[2].equalsIgnoreCase("effect"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3].toUpperCase();
							this.logger.info("[Drunk] " + TempString);
							config.set("Lager.Effects.Tertiary.Effect", TempString);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Tertiary Lager effect set to " +
							config.getString("Lager.Effects.Tertiary.Effect"));
							
						}

					}
					//Set Lager Tertiary Duration
					if (args[0].equalsIgnoreCase("lager") && args[1].equalsIgnoreCase("tertiary") &&
							args[2].equalsIgnoreCase("duration"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							this.logger.info("[Drunk] " + TempInt);
							config.set("Lager.Effects.Tertiary.Duration", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Tertiary Lager effect duration set to " +
							config.getString("Lager.Effects.Tertiary.Duration"));
						}

					}
					//Set Lager Tertiary Amplifier
					if (args[0].equalsIgnoreCase("lager") && args[1].equalsIgnoreCase("tertiary") &&
							args[2].equalsIgnoreCase("amplifier"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[3];
							TempInt = Integer.parseInt(TempString);
							if (TempInt > 4)	{
								player.sendMessage(LRED + "[Drunk] " + YELLOW + "Amplifier is over four;"
										+ " This may have extreme side effects for players.");
							}
							this.logger.info("[Drunk] " + TempInt);
							config.set("Lager.Effects.Tertiary.Amplifier", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Tertiary Lager effect duration set to " +
							config.getString("Lager.Effects.Tertiary.Amplifier"));
						}

					}
					//Set Drink Cooldown
					if (args[0].equalsIgnoreCase("cooldown"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[1];
							TempInt = Integer.parseInt(TempString);
							this.logger.info("[Drunk] " + TempInt);
							config.set("Global.Cooldown Time", TempInt);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + " Cooldown time set to " +
									config.getInt("Global.Cooldown Time"));
						}
					}
					//Enable or Disable Drunken Chatter
					//Ale
					if (args[0].equalsIgnoreCase("ale") && args[1].equalsIgnoreCase("chatter"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[2];
							TempBool = Boolean.parseBoolean(TempString);
							this.logger.info("[Drunk] " + TempString);
							config.set("Ale.Chatter.Enabled", TempBool);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Drunken Chatter for Ale is now set to "
									+ config.getBoolean("Ale.Chatter.Enabled"));
						}
					}
					//Lager
					if (args[0].equalsIgnoreCase("lager") && args[1].equalsIgnoreCase("chatter"))	{
						if (player.isOp() || player.hasPermission("drunk.admin"))	{
							TempString = args[2];
							TempBool = Boolean.parseBoolean(TempString);
							this.logger.info("[Drunk] " + TempString);
							config.set("Lager.Chatter.Enabled", TempBool);
							saveYamls();
							reloadConfig();
							config = getConfig();
							player.sendMessage(LRED + "[Drunk] " + YELLOW + "Drunken Chatter for Lager is now set to "
									+ config.getBoolean("Lager.Chatter.Enabled"));
						}
					}
				}
				catch (ArrayIndexOutOfBoundsException ex)	{
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	
}
