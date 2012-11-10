package deathlog;

import java.util.Locale;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

@SideOnly(Side.SERVER)
public class DeathEventDispatcher {
	
	@ForgeSubscribe
	public void onEntityDeath(LivingDeathEvent event) {
		System.out.println("[DeathEventDispatcher] Handling event: " + event.toString());
		EntityLiving entity = event.entityLiving;
		try {
			EntityPlayer player = (EntityPlayer)entity;
			System.out.println("[DeathEventDispatcher] Player death: " + entity.toString());
		} catch (Exception e) {
			System.out.println("[DeathEventDispatcher] Other death: " + entity.toString());
		}
	}
}
