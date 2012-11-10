package foursee.deathlog;

import java.util.Locale;
import java.util.Date;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayerMP;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public final class DeathEventHandler {

	private Logger logger;

	public DeathEventHandler(Logger logger) {
		this.logger = logger;
		// Initialize
	}

	@ForgeSubscribe
	public void onEntityDeath(LivingDeathEvent event) {
		EntityLiving entity = event.entityLiving;
		DamageSource source = event.source;
		Date time = new Date();
		
		if (entity instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP)entity;
			String deathMessage = source.getDeathMessage(player) + "," + (int)player.posX + "," + (int)player.posY + "," + (int)player.posZ + "," + player.dimension + "," + time.getTime();
			logger.info(deathMessage);
			try {
				FileWriter deathLog = new FileWriter("playerDeaths.log", true);
				deathLog.write(deathMessage + "\n");
				deathLog.close();
			} catch (IOException e){}
		}
	}
}
