package foursee.deathlog;

import java.util.logging.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import net.minecraftforge.event.EventBus;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid="DeathLog", name="Death Log", version="0.3.3", dependencies = "after:Mystcraft")
@NetworkMod(clientSideRequired=false, serverSideRequired=false)
public class DeathLog {
	public static final String ID = "DeathLog";

        // The instance of your mod that Forge uses.
	@Instance("DeathLog")
	public static DeathLog instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="foursee.deathlog.CommonProxy", serverSide="foursee.deathlog.CommonProxy")
	public static CommonProxy proxy;
	
	public static Logger logger;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		logger = Logger.getLogger(ID);
		logger.setParent(FMLLog.getLogger());
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		MinecraftForge.EVENT_BUS.register(new DeathEventHandler(logger));
		logger.info("Registered Death Event Handler with Forge");
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
	
}