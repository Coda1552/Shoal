package codyhuh.shoal.client.geo.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class ShoalGlowRenderLayer<T extends LivingEntity & GeoEntity> extends GeoRenderLayer<T> {
    private final ResourceLocation glowOverlayResourceLocation;

    public ShoalGlowRenderLayer(ResourceLocation glowOverlayResourceLocation, GeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
        this.glowOverlayResourceLocation = glowOverlayResourceLocation;
    }

    @Override
    public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.eyes(glowOverlayResourceLocation));

        this.getRenderer().actuallyRender(poseStack, animatable, bakedModel, renderType, bufferSource, vertexConsumer, true, partialTick, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
