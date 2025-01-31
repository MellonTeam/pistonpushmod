package net.louis.pistonpushmod.mixin;

import net.louis.pistonpushmod.PistonPushMod;
import net.minecraft.block.piston.PistonHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PistonHandler.class)
public abstract class PistonHandlerMixin {
    @Final
    @Shadow
    private World world;

    @ModifyConstant(method = "tryMove", constant = @Constant(intValue = 12))
    private int changeConstant(int constant) {

        if (world instanceof ServerWorld serv) {
            return serv.getGameRules().getInt(PistonPushMod.PISTON_PUSHING_LIMIT);
        }
        return constant;
    }
}