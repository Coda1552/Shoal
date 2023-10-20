package codyhuh.shoal.client.geo.render.layer;

import codyhuh.shoal.common.entities.CollaredAnimal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class ShoalCollarRenderLayer<T extends CollaredAnimal & GeoEntity> extends GeoRenderLayer<T> {
    private final ResourceLocation collarResourceLocation;

    public ShoalCollarRenderLayer(ResourceLocation collarResourceLocation, GeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
        this.collarResourceLocation = collarResourceLocation;
    }

    @Override
    public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (animatable.isTame() && !animatable.isInvisible()) {
            RenderType collarTexture = RenderType.entityCutoutNoCull(collarResourceLocation);
            float[] colorFloat = animatable.getCollarColor().getTextureDiffuseColors();
            this.getRenderer().actuallyRender(poseStack, animatable, bakedModel, renderType, bufferSource, bufferSource.getBuffer(collarTexture), true, partialTick, packedLight, OverlayTexture.NO_OVERLAY, colorFloat[0], colorFloat[1], colorFloat[2], 1f);
        }
    }
}
