package com.github.nicklib;

import com.github.nicklib.utils.SignGUIApi;
import org.bukkit.plugin.java.JavaPlugin;

public class NickLib extends JavaPlugin {

    private SignGUIApi signGUIAPI;


    @Override
    public void onEnable() {
        init();
    }


    public void init() {


        if(!(getServer().getPluginManager().getPlugin("ProtocolLib") == null)) {
            this.signGUIAPI = new SignGUIApi(JavaPlugin.getProvidingPlugin(NickLib.class));
            return;
        }
    }
}
