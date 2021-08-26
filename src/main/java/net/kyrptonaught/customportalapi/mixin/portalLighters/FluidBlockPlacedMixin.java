package net.kyrptonaught.customportalapi.mixin.portalLighters;

import net.kyrptonaught.customportalapi.portal.PortalIgnitionSource;
import net.kyrptonaught.customportalapi.portal.PortalPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(FluidBlock.class)
public abstract class FluidBlockPlacedMixin {

    @Inject(method = "onBlockAdded", at = @At("HEAD"))
    public void fluidPlacedAttemptPortalLight(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        if (state.getFluidState().isStill())
            PortalPlacer.attemptPortalLight(world, pos, pos.down(), PortalIgnitionSource.FluidSource(state.getFluidState().getFluid()));
    }
}
