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

        // SIGN GUI API
        this.signGUIAPI = new SignGUIApi(JavaPlugin.getProvidingPlugin(NickLib.class));

    }
}
