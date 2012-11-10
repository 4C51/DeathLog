package deathlog;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import net.minecraftforge.event.EventBus;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

@Mod(modid="DeathLog", name="Death Log", version="0.1.0")
@NetworkMod(clientSideRequired=false, serverSideRequired=true)
public class DeathLog {

        // The instance of your mod that Forge uses.
	@Instance("DeathLog")
	public static DeathLog instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(serverSide="deathlog.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
	
	@SideOnly(Side.SERVER)
	private void serverInit() {
		EventBus DeathEvent = new EventBus();
		DeathEvent.register(new DeathEventDispatcher());
		System.out.println("[DeathLog] Registered Death Event Dispatcher with Forge");
	}
}