package me.raynorjames.Commands;

import me.raynorjames.MCMan;
import me.raynorjames.Utility.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;

public class Commands extends FileManager implements CommandExecutor {
    private File accountFile;
    public Commands(File root) {
        super(root);
        accountFile = getPluginFile("McManUsers.txt");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("man") ||
                command.getName().equalsIgnoreCase("mcman")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "[McMan] Invalid number of arguments, see usage.");
                return false;
            }

            switch (args.length) {
                case 1:
                    switch (args[0].toLowerCase()) {
                        case "reguser":
                            sender.sendMessage(ChatColor.YELLOW + "[McMan] reguser require a username and password");

                            break;
                        case "help":
                            sender.sendMessage(ChatColor.AQUA + "[McMan] /mcman help - display help screen");
                            sender.sendMessage(ChatColor.AQUA + "[McMan] /mcman listusers - display registered users");
                            sender.sendMessage(ChatColor.AQUA + "[McMan] /mcman reguser [username] [password] - registers account for mcman panel");
                            sender.sendMessage(ChatColor.AQUA + "[McMan] /mcman deluser [username] - deletes user for mcman panel");
                            break;
                        case "listusers":
                            sender.sendMessage(ChatColor.AQUA + "[McMan] start registered users -----------");
                            for(String user : getArray(accountFile, "user")){
                                sender.sendMessage(ChatColor.AQUA + "->" + user.split("-")[0]);
                            }
                            sender.sendMessage(ChatColor.AQUA + "[McMan] end registered users -------------");

                            break;
                        default:
                            sender.sendMessage(ChatColor.YELLOW + "[McMan] unknown command see /mcman help");
                    }
                    break;
                case 2:
                    if (args[0].equalsIgnoreCase("deluser")) {

                        String username = args[1];
                        removeLine(accountFile, username);
                        //TODO: delete user from authorized users file
                        sender.sendMessage(ChatColor.YELLOW + "[McMan] deleted user '" + username + "'");
                    } else {
                        sender.sendMessage(ChatColor.YELLOW + "[McMan] unknown command see /mcman help");
                    }
                    break;
                case 3:
                    if (args[0].equalsIgnoreCase("reguser")) {
                        String username = args[1];
                        String password = args[2];
                        writeCombo(accountFile, "user", username + "-" + password);
                        sender.sendMessage(ChatColor.YELLOW + "[McMan] registered user '" + username + "'");
                        //TODO: register user to authorized users file
                        MCMan.getLog().logNormal("Registered user: " + username);

                    }
            }
        }

        return true;
    }
}
