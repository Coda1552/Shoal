package codyhuh.shoal.core;

import codyhuh.shoal.core.registry.ShoalStructureModifiers;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Shoal.MOD_ID)
public class Shoal {
    public static final String MOD_ID = "shoal";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Shoal() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        MinecraftForge.EVENT_BUS.register(this);

        ShoalStructureModifiers.STRUCTURE_MODIFIERS.register(modBus);
    }
}
