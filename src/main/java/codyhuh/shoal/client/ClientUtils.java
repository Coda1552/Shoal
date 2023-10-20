package codyhuh.shoal.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ClientUtils {

    public static ModelLayerLocation newModelLayer(ResourceLocation location) {
        return new ModelLayerLocation(location, "main");
    }
}
