package org.bukkit.craftbukkit.v1_19_R2.block.data;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.commands.arguments.blocks.BlockStateParser;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SoundGroup;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockSupport;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_19_R2.CraftSoundGroup;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R2.block.CraftBlock;
import org.bukkit.craftbukkit.v1_19_R2.block.CraftBlockSupport;
import org.bukkit.craftbukkit.v1_19_R2.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftAmethystCluster;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftAnvil;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBamboo;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBanner;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBannerWall;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBarrel;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBed;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBeehive;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBeetroot;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBell;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBigDripleaf;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBigDripleafStem;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBlastFurnace;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBrewingStand;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftBubbleColumn;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftButtonAbstract;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCactus;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCake;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCampfire;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCandle;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCandleCake;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCarrots;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCaveVines;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCaveVinesPlant;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCeilingHangingSign;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftChain;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftChest;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftChestTrapped;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftChiseledBookShelf;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftChorusFlower;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftChorusFruit;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCobbleWall;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCocoa;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCommand;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftComposter;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftConduit;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCoralDead;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCoralFan;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCoralFanAbstract;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCoralFanWall;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCoralFanWallAbstract;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCoralPlant;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftCrops;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftDaylightDetector;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftDirtSnow;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftDispenser;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftDoor;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftDropper;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftEndRod;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftEnderChest;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftEnderPortalFrame;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftFence;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftFenceGate;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftFire;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftFloorSign;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftFluids;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftFurnaceFurace;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftGlazedTerracotta;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftGlowLichen;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftGrass;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftGrindstone;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftHangingRoots;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftHay;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftHopper;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftHugeMushroom;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftIceFrost;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftInfestedRotatedPillar;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftIronBars;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftJigsaw;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftJukeBox;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftKelp;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftLadder;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftLantern;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftLayeredCauldron;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftLeaves;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftLectern;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftLever;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftLight;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftLightningRod;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftLoom;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftMangroveLeaves;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftMangrovePropagule;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftMangroveRoots;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftMinecartDetector;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftMinecartTrack;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftMycel;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftNetherWart;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftNote;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftObserver;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPiglinWallSkull;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPiston;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPistonExtension;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPistonMoving;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPointedDripstone;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPortal;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPotatoes;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPowderSnowCauldron;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPoweredRail;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPressurePlateBinary;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPressurePlateWeighted;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftPumpkinCarved;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftRedstoneComparator;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftRedstoneLamp;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftRedstoneOre;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftRedstoneTorch;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftRedstoneTorchWall;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftRedstoneWire;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftReed;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftRepeater;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftRespawnAnchor;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftRotatable;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSapling;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftScaffolding;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSculkCatalyst;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSculkSensor;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSculkShrieker;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSculkVein;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSeaPickle;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftShulkerBox;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSkull;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSkullPlayer;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSkullPlayerWall;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSkullWall;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSmallDripleaf;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSmoker;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSnow;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSoil;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftStainedGlassPane;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftStairs;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftStem;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftStemAttached;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftStepAbstract;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftStonecutter;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftStructure;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftSweetBerryBush;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftTNT;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftTallPlant;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftTallPlantFlower;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftTallSeagrass;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftTarget;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftTorchWall;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftTrapdoor;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftTripwire;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftTripwireHook;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftTurtleEgg;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftTwistingVines;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftVine;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftWallHangingSign;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftWallSign;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftWeatheringCopperSlab;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftWeatheringCopperStair;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftWeepingVines;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftWitherSkull;
import org.bukkit.craftbukkit.v1_19_R2.block.impl.CraftWitherSkullWall;

public class CraftBlockData implements BlockData {

    private BlockState state;
    private Map<net.minecraft.world.level.block.state.properties.Property<?>, Comparable<?>> parsedStates;

    protected CraftBlockData() {
        throw new AssertionError("Template Constructor");
    }

    protected CraftBlockData(BlockState state) {
        this.state = state;
    }

    @Override
    public Material getMaterial() {
        return CraftMagicNumbers.getMaterial(state.getBlock());
    }

    public BlockState getState() {
        return state;
    }

    /**
     * Get a given EnumProperty's value as its Bukkit counterpart.
     *
     * @param nms the NMS state to convert
     * @param bukkit the Bukkit class
     * @param <B> the type
     * @return the matching Bukkit type
     */
    protected <B extends Enum<B>> B get(EnumProperty<?> nms, Class<B> bukkit) {
        return toBukkit(state.getValue(nms), bukkit);
    }

    /**
     * Convert all values from the given EnumProperty to their appropriate
     * Bukkit counterpart.
     *
     * @param nms the NMS state to get values from
     * @param bukkit the bukkit class to convert the values to
     * @param <B> the bukkit class type
     * @return an immutable Set of values in their appropriate Bukkit type
     */
    @SuppressWarnings("unchecked")
    protected <B extends Enum<B>> Set<B> getValues(EnumProperty<?> nms, Class<B> bukkit) {
        ImmutableSet.Builder<B> values = ImmutableSet.builder();

        for (Enum<?> e : nms.getPossibleValues()) {
            values.add(toBukkit(e, bukkit));
        }

        return values.build();
    }

    /**
     * Set a given {@link EnumProperty} with the matching enum from Bukkit.
     *
     * @param nms the NMS EnumProperty to set
     * @param bukkit the matching Bukkit Enum
     * @param <B> the Bukkit type
     * @param <N> the NMS type
     */
    protected <B extends Enum<B>, N extends Enum<N> & StringRepresentable> void set(EnumProperty<N> nms, Enum<B> bukkit) {
        this.parsedStates = null;
        this.state = this.state.setValue(nms, toNMS(bukkit, nms.getValueClass()));
    }

    @Override
    public BlockData merge(BlockData data) {
        CraftBlockData craft = (CraftBlockData) data;
        Preconditions.checkArgument(craft.parsedStates != null, "Data not created via string parsing");
        Preconditions.checkArgument(this.state.getBlock() == craft.state.getBlock(), "States have different types (got %s, expected %s)", data, this);

        CraftBlockData clone = (CraftBlockData) this.clone();
        clone.parsedStates = null;

        for (net.minecraft.world.level.block.state.properties.Property parsed : craft.parsedStates.keySet()) {
            clone.state = clone.state.setValue(parsed, craft.state.getValue(parsed));
        }

        return clone;
    }

    @Override
    public boolean matches(BlockData data) {
        if (data == null) {
            return false;
        }
        if (!(data instanceof CraftBlockData)) {
            return false;
        }

        CraftBlockData craft = (CraftBlockData) data;
        if (this.state.getBlock() != craft.state.getBlock()) {
            return false;
        }

        // Fastpath an exact match
        boolean exactMatch = this.equals(data);

        // If that failed, do a merge and check
        if (!exactMatch && craft.parsedStates != null) {
            return this.merge(data).equals(this);
        }

        return exactMatch;
    }

    private static final Map<Class<? extends Enum<?>>, Enum<?>[]> ENUM_VALUES = new HashMap<>();

    /**
     * Convert an NMS Enum (usually a EnumProperty) to its appropriate Bukkit
     * enum from the given class.
     *
     * @throws IllegalStateException if the Enum could not be converted
     */
    @SuppressWarnings("unchecked")
    private static <B extends Enum<B>> B toBukkit(Enum<?> nms, Class<B> bukkit) {
        if (nms instanceof Direction) {
            return (B) CraftBlock.notchToBlockFace((Direction) nms);
        }
        return (B) ENUM_VALUES.computeIfAbsent(bukkit, Class::getEnumConstants)[nms.ordinal()];
    }

    /**
     * Convert a given Bukkit enum to its matching NMS enum type.
     *
     * @param bukkit the Bukkit enum to convert
     * @param nms the NMS class
     * @return the matching NMS type
     * @throws IllegalStateException if the Enum could not be converted
     */
    @SuppressWarnings("unchecked")
    private static <N extends Enum<N> & StringRepresentable> N toNMS(Enum<?> bukkit, Class<N> nms) {
        if (bukkit instanceof BlockFace) {
            return (N) CraftBlock.blockFaceToNotch((BlockFace) bukkit);
        }
        return (N) ENUM_VALUES.computeIfAbsent(nms, Class::getEnumConstants)[bukkit.ordinal()];
    }

    /**
     * Get the current value of a given state.
     *
     * @param ibs the state to check
     * @param <T> the type
     * @return the current value of the given state
     */
    protected <T extends Comparable<T>> T get(net.minecraft.world.level.block.state.properties.Property<T> ibs) {
        // Straight integer or boolean getter
        return this.state.getValue(ibs);
    }

    /**
     * Set the specified state's value.
     *
     * @param ibs the state to set
     * @param v the new value
     * @param <T> the state's type
     * @param <V> the value's type. Must match the state's type.
     */
    public <T extends Comparable<T>, V extends T> void set(net.minecraft.world.level.block.state.properties.Property<T> ibs, V v) {
        // Straight integer or boolean setter
        this.parsedStates = null;
        this.state = this.state.setValue(ibs, v);
    }

    @Override
    public String getAsString() {
        return toString(state.getValues());
    }

    @Override
    public String getAsString(boolean hideUnspecified) {
        return (hideUnspecified && parsedStates != null) ? toString(parsedStates) : getAsString();
    }

    @Override
    public BlockData clone() {
        try {
            return (BlockData) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError("Clone not supported", ex);
        }
    }

    @Override
    public String toString() {
        return "CraftBlockData{" + getAsString() + "}";
    }

    // Mimicked from BlockDataAbstract#toString()
    public String toString(Map<net.minecraft.world.level.block.state.properties.Property<?>, Comparable<?>> states) {
        StringBuilder stateString = new StringBuilder(BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString());

        if (!states.isEmpty()) {
            stateString.append('[');
            stateString.append(states.entrySet().stream().map(StateHolder.PROPERTY_ENTRY_TO_STRING_FUNCTION).collect(Collectors.joining(",")));
            stateString.append(']');
        }

        return stateString.toString();
    }

    public CompoundTag toStates() {
        CompoundTag compound = new CompoundTag();

        for (Map.Entry<net.minecraft.world.level.block.state.properties.Property<?>, Comparable<?>> entry : state.getValues().entrySet()) {
            net.minecraft.world.level.block.state.properties.Property iblockstate = (net.minecraft.world.level.block.state.properties.Property) entry.getKey();

            compound.putString(iblockstate.getName(), iblockstate.getName(entry.getValue()));
        }

        return compound;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CraftBlockData && state.equals(((CraftBlockData) obj).state);
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    protected static BooleanProperty getBoolean(String name) {
        throw new AssertionError("Template Method");
    }

    protected static BooleanProperty getBoolean(String name, boolean optional) {
        throw new AssertionError("Template Method");
    }

    protected static EnumProperty<?> getEnum(String name) {
        throw new AssertionError("Template Method");
    }

    protected static IntegerProperty getInteger(String name) {
        throw new AssertionError("Template Method");
    }

    protected static BooleanProperty getBoolean(Class<? extends Block> block, String name) {
        return (BooleanProperty) getState(block, name, false);
    }

    protected static BooleanProperty getBoolean(Class<? extends Block> block, String name, boolean optional) {
        return (BooleanProperty) getState(block, name, optional);
    }

    protected static EnumProperty<?> getEnum(Class<? extends Block> block, String name) {
        return (EnumProperty<?>) getState(block, name, false);
    }

    protected static IntegerProperty getInteger(Class<? extends Block> block, String name) {
        return (IntegerProperty) getState(block, name, false);
    }

    /**
     * Get a specified {@link net.minecraft.world.level.block.state.properties.Property} from a given block's class with a
     * given name
     *
     * @param block the class to retrieve the state from
     * @param name the name of the state to retrieve
     * @param optional if the state can be null
     * @return the specified state or null
     * @throws IllegalStateException if the state is null and {@code optional}
     * is false.
     */
    private static net.minecraft.world.level.block.state.properties.Property<?> getState(Class<? extends Block> block, String name, boolean optional) {
        net.minecraft.world.level.block.state.properties.Property<?> state = null;

        for (Block instance : BuiltInRegistries.BLOCK) {
            if (instance.getClass() == block) {
                if (state == null) {
                    state = instance.getStateDefinition().getProperty(name);
                } else {
                    net.minecraft.world.level.block.state.properties.Property<?> newState = instance.getStateDefinition().getProperty(name);

                    Preconditions.checkState(state == newState, "State mistmatch %s,%s", state, newState);
                }
            }
        }

        Preconditions.checkState(optional || state != null, "Null state for %s,%s", block, name);

        return state;
    }

    /**
     * Get the minimum value allowed by the IntegerProperty.
     *
     * @param state the state to check
     * @return the minimum value allowed
     */
    protected static int getMin(IntegerProperty state) {
        return state.min;
    }

    /**
     * Get the maximum value allowed by the IntegerProperty.
     *
     * @param state the state to check
     * @return the maximum value allowed
     */
    protected static int getMax(IntegerProperty state) {
        return state.max;
    }

    //
    private static final Map<Class<? extends Block>, Function<BlockState, CraftBlockData>> MAP = new HashMap<>();

    static {
        //<editor-fold desc="CraftBlockData Registration" defaultstate="collapsed">
        register(net.minecraft.world.level.block.AmethystClusterBlock.class, CraftAmethystCluster::new);
        register(net.minecraft.world.level.block.BigDripleafBlock.class, CraftBigDripleaf::new);
        register(net.minecraft.world.level.block.BigDripleafStemBlock.class, CraftBigDripleafStem::new);
        register(net.minecraft.world.level.block.AnvilBlock.class, CraftAnvil::new);
        register(net.minecraft.world.level.block.BambooStalkBlock.class, CraftBamboo::new);
        register(net.minecraft.world.level.block.BannerBlock.class, CraftBanner::new);
        register(net.minecraft.world.level.block.WallBannerBlock.class, CraftBannerWall::new);
        register(net.minecraft.world.level.block.BarrelBlock.class, CraftBarrel::new);
        register(net.minecraft.world.level.block.BedBlock.class, CraftBed::new);
        register(net.minecraft.world.level.block.BeehiveBlock.class, CraftBeehive::new);
        register(net.minecraft.world.level.block.BeetrootBlock.class, CraftBeetroot::new);
        register(net.minecraft.world.level.block.BellBlock.class, CraftBell::new);
        register(net.minecraft.world.level.block.BlastFurnaceBlock.class, CraftBlastFurnace::new);
        register(net.minecraft.world.level.block.BrewingStandBlock.class, CraftBrewingStand::new);
        register(net.minecraft.world.level.block.BubbleColumnBlock.class, CraftBubbleColumn::new);
        register(net.minecraft.world.level.block.ButtonBlock.class, CraftButtonAbstract::new);
        register(net.minecraft.world.level.block.CactusBlock.class, CraftCactus::new);
        register(net.minecraft.world.level.block.CakeBlock.class, CraftCake::new);
        register(net.minecraft.world.level.block.CampfireBlock.class, CraftCampfire::new);
        register(net.minecraft.world.level.block.CarrotBlock.class, CraftCarrots::new);
        register(net.minecraft.world.level.block.ChainBlock.class, CraftChain::new);
        register(net.minecraft.world.level.block.ChestBlock.class, CraftChest::new);
        register(net.minecraft.world.level.block.TrappedChestBlock.class, CraftChestTrapped::new);
        register(net.minecraft.world.level.block.ChorusFlowerBlock.class, CraftChorusFlower::new);
        register(net.minecraft.world.level.block.ChorusPlantBlock.class, CraftChorusFruit::new);
        register(net.minecraft.world.level.block.WallBlock.class, CraftCobbleWall::new);
        register(net.minecraft.world.level.block.CocoaBlock.class, CraftCocoa::new);
        register(net.minecraft.world.level.block.CommandBlock.class, CraftCommand::new);
        register(net.minecraft.world.level.block.ComposterBlock.class, CraftComposter::new);
        register(net.minecraft.world.level.block.ConduitBlock.class, CraftConduit::new);
        register(net.minecraft.world.level.block.BaseCoralPlantBlock.class, CraftCoralDead::new);
        register(net.minecraft.world.level.block.CoralFanBlock.class, CraftCoralFan::new);
        register(net.minecraft.world.level.block.BaseCoralFanBlock.class, CraftCoralFanAbstract::new);
        register(net.minecraft.world.level.block.CoralWallFanBlock.class, CraftCoralFanWall::new);
        register(net.minecraft.world.level.block.BaseCoralWallFanBlock.class, CraftCoralFanWallAbstract::new);
        register(net.minecraft.world.level.block.CoralPlantBlock.class, CraftCoralPlant::new);
        register(net.minecraft.world.level.block.CropBlock.class, CraftCrops::new);
        register(net.minecraft.world.level.block.DaylightDetectorBlock.class, CraftDaylightDetector::new);
        register(net.minecraft.world.level.block.SnowyDirtBlock.class, CraftDirtSnow::new);
        register(net.minecraft.world.level.block.DispenserBlock.class, CraftDispenser::new);
        register(net.minecraft.world.level.block.DoorBlock.class, CraftDoor::new);
        register(net.minecraft.world.level.block.DropperBlock.class, CraftDropper::new);
        register(net.minecraft.world.level.block.EndRodBlock.class, CraftEndRod::new);
        register(net.minecraft.world.level.block.EnderChestBlock.class, CraftEnderChest::new);
        register(net.minecraft.world.level.block.EndPortalFrameBlock.class, CraftEnderPortalFrame::new);
        register(net.minecraft.world.level.block.FenceBlock.class, CraftFence::new);
        register(net.minecraft.world.level.block.FenceGateBlock.class, CraftFenceGate::new);
        register(net.minecraft.world.level.block.FireBlock.class, CraftFire::new);
        register(net.minecraft.world.level.block.StandingSignBlock.class, CraftFloorSign::new);
        register(net.minecraft.world.level.block.LiquidBlock.class, CraftFluids::new);
        register(net.minecraft.world.level.block.FurnaceBlock.class, CraftFurnaceFurace::new);
        register(net.minecraft.world.level.block.GlazedTerracottaBlock.class, CraftGlazedTerracotta::new);
        register(net.minecraft.world.level.block.GrassBlock.class, CraftGrass::new);
        register(net.minecraft.world.level.block.GrindstoneBlock.class, CraftGrindstone::new);
        register(net.minecraft.world.level.block.HayBlock.class, CraftHay::new);
        register(net.minecraft.world.level.block.HopperBlock.class, CraftHopper::new);
        register(net.minecraft.world.level.block.HugeMushroomBlock.class, CraftHugeMushroom::new);
        register(net.minecraft.world.level.block.FrostedIceBlock.class, CraftIceFrost::new);
        register(net.minecraft.world.level.block.IronBarsBlock.class, CraftIronBars::new);
        register(net.minecraft.world.level.block.JigsawBlock.class, CraftJigsaw::new);
        register(net.minecraft.world.level.block.JukeboxBlock.class, CraftJukeBox::new);
        register(net.minecraft.world.level.block.KelpBlock.class, CraftKelp::new);
        register(net.minecraft.world.level.block.LadderBlock.class, CraftLadder::new);
        register(net.minecraft.world.level.block.LanternBlock.class, CraftLantern::new);
        register(net.minecraft.world.level.block.LeavesBlock.class, CraftLeaves::new);
        register(net.minecraft.world.level.block.LecternBlock.class, CraftLectern::new);
        register(net.minecraft.world.level.block.LeverBlock.class, CraftLever::new);
        register(net.minecraft.world.level.block.LoomBlock.class, CraftLoom::new);
        register(net.minecraft.world.level.block.DetectorRailBlock.class, CraftMinecartDetector::new);
        register(net.minecraft.world.level.block.RailBlock.class, CraftMinecartTrack::new);
        register(net.minecraft.world.level.block.MyceliumBlock.class, CraftMycel::new);
        register(net.minecraft.world.level.block.NetherWartBlock.class, CraftNetherWart::new);
        register(net.minecraft.world.level.block.NoteBlock.class, CraftNote::new);
        register(net.minecraft.world.level.block.ObserverBlock.class, CraftObserver::new);
        register(net.minecraft.world.level.block.NetherPortalBlock.class, CraftPortal::new);
        register(net.minecraft.world.level.block.PotatoBlock.class, CraftPotatoes::new);
        register(net.minecraft.world.level.block.PoweredRailBlock.class, CraftPoweredRail::new);
        register(net.minecraft.world.level.block.PressurePlateBlock.class, CraftPressurePlateBinary::new);
        register(net.minecraft.world.level.block.WeightedPressurePlateBlock.class, CraftPressurePlateWeighted::new);
        register(net.minecraft.world.level.block.CarvedPumpkinBlock.class, CraftPumpkinCarved::new);
        register(net.minecraft.world.level.block.ComparatorBlock.class, CraftRedstoneComparator::new);
        register(net.minecraft.world.level.block.RedstoneLampBlock.class, CraftRedstoneLamp::new);
        register(net.minecraft.world.level.block.RedStoneOreBlock.class, CraftRedstoneOre::new);
        register(net.minecraft.world.level.block.RedstoneTorchBlock.class, CraftRedstoneTorch::new);
        register(net.minecraft.world.level.block.RedstoneWallTorchBlock.class, CraftRedstoneTorchWall::new);
        register(net.minecraft.world.level.block.RedStoneWireBlock.class, CraftRedstoneWire::new);
        register(net.minecraft.world.level.block.SugarCaneBlock.class, CraftReed::new);
        register(net.minecraft.world.level.block.RepeaterBlock.class, CraftRepeater::new);
        register(net.minecraft.world.level.block.RespawnAnchorBlock.class, CraftRespawnAnchor::new);
        register(net.minecraft.world.level.block.RotatedPillarBlock.class, CraftRotatable::new);
        register(net.minecraft.world.level.block.SaplingBlock.class, CraftSapling::new);
        register(net.minecraft.world.level.block.ScaffoldingBlock.class, CraftScaffolding::new);
        register(net.minecraft.world.level.block.SeaPickleBlock.class, CraftSeaPickle::new);
        register(net.minecraft.world.level.block.ShulkerBoxBlock.class, CraftShulkerBox::new);
        register(net.minecraft.world.level.block.SkullBlock.class, CraftSkull::new);
        register(net.minecraft.world.level.block.PlayerHeadBlock.class, CraftSkullPlayer::new);
        register(net.minecraft.world.level.block.PlayerWallHeadBlock.class, CraftSkullPlayerWall::new);
        register(net.minecraft.world.level.block.WallSkullBlock.class, CraftSkullWall::new);
        register(net.minecraft.world.level.block.SmokerBlock.class, CraftSmoker::new);
        register(net.minecraft.world.level.block.SnowLayerBlock.class, CraftSnow::new);
        register(net.minecraft.world.level.block.FarmBlock.class, CraftSoil::new);
        register(net.minecraft.world.level.block.StainedGlassPaneBlock.class, CraftStainedGlassPane::new);
        register(net.minecraft.world.level.block.StairBlock.class, CraftStairs::new);
        register(net.minecraft.world.level.block.StemBlock.class, CraftStem::new);
        register(net.minecraft.world.level.block.AttachedStemBlock.class, CraftStemAttached::new);
        register(net.minecraft.world.level.block.SlabBlock.class, CraftStepAbstract::new);
        register(net.minecraft.world.level.block.StonecutterBlock.class, CraftStonecutter::new);
        register(net.minecraft.world.level.block.StructureBlock.class, CraftStructure::new);
        register(net.minecraft.world.level.block.SweetBerryBushBlock.class, CraftSweetBerryBush::new);
        register(net.minecraft.world.level.block.TntBlock.class, CraftTNT::new);
        register(net.minecraft.world.level.block.DoublePlantBlock.class, CraftTallPlant::new);
        register(net.minecraft.world.level.block.TallFlowerBlock.class, CraftTallPlantFlower::new);
        register(net.minecraft.world.level.block.TargetBlock.class, CraftTarget::new);
        register(net.minecraft.world.level.block.WallTorchBlock.class, CraftTorchWall::new);
        register(net.minecraft.world.level.block.TrapDoorBlock.class, CraftTrapdoor::new);
        register(net.minecraft.world.level.block.TripWireBlock.class, CraftTripwire::new);
        register(net.minecraft.world.level.block.TripWireHookBlock.class, CraftTripwireHook::new);
        register(net.minecraft.world.level.block.TurtleEggBlock.class, CraftTurtleEgg::new);
        register(net.minecraft.world.level.block.TwistingVinesBlock.class, CraftTwistingVines::new);
        register(net.minecraft.world.level.block.VineBlock.class, CraftVine::new);
        register(net.minecraft.world.level.block.WallSignBlock.class, CraftWallSign::new);
        register(net.minecraft.world.level.block.WeepingVinesBlock.class, CraftWeepingVines::new);
        register(net.minecraft.world.level.block.WitherSkullBlock.class, CraftWitherSkull::new);
        register(net.minecraft.world.level.block.WitherWallSkullBlock.class, CraftWitherSkullWall::new);
        register(net.minecraft.world.level.block.CandleBlock.class, CraftCandle::new);
        register(net.minecraft.world.level.block.CandleCakeBlock.class, CraftCandleCake::new);
        register(net.minecraft.world.level.block.CaveVinesBlock.class, CraftCaveVines::new);
        register(net.minecraft.world.level.block.CaveVinesPlantBlock.class, CraftCaveVinesPlant::new);
        register(net.minecraft.world.level.block.CeilingHangingSignBlock.class, CraftCeilingHangingSign::new);
        register(net.minecraft.world.level.block.ChiseledBookShelfBlock.class, CraftChiseledBookShelf::new);
        register(net.minecraft.world.level.block.GlowLichenBlock.class, CraftGlowLichen::new);
        register(net.minecraft.world.level.block.HangingRootsBlock.class, CraftHangingRoots::new);
        register(net.minecraft.world.level.block.InfestedRotatedPillarBlock.class, CraftInfestedRotatedPillar::new);
        register(net.minecraft.world.level.block.LayeredCauldronBlock.class, CraftLayeredCauldron::new);
        register(net.minecraft.world.level.block.LightBlock.class, CraftLight::new);
        register(net.minecraft.world.level.block.LightningRodBlock.class, CraftLightningRod::new);
        register(net.minecraft.world.level.block.MangroveLeavesBlock.class, CraftMangroveLeaves::new);
        register(net.minecraft.world.level.block.MangrovePropaguleBlock.class, CraftMangrovePropagule::new);
        register(net.minecraft.world.level.block.MangroveRootsBlock.class, CraftMangroveRoots::new);
        register(net.minecraft.world.level.block.PiglinWallSkullBlock.class, CraftPiglinWallSkull::new);
        register(net.minecraft.world.level.block.PointedDripstoneBlock.class, CraftPointedDripstone::new);
        register(net.minecraft.world.level.block.PowderSnowCauldronBlock.class, CraftPowderSnowCauldron::new);
        register(net.minecraft.world.level.block.SculkCatalystBlock.class, CraftSculkCatalyst::new);
        register(net.minecraft.world.level.block.SculkSensorBlock.class, CraftSculkSensor::new);
        register(net.minecraft.world.level.block.SculkShriekerBlock.class, CraftSculkShrieker::new);
        register(net.minecraft.world.level.block.SculkVeinBlock.class, CraftSculkVein::new);
        register(net.minecraft.world.level.block.SmallDripleafBlock.class, CraftSmallDripleaf::new);
        register(net.minecraft.world.level.block.TallSeagrassBlock.class, CraftTallSeagrass::new);
        register(net.minecraft.world.level.block.WallHangingSignBlock.class, CraftWallHangingSign::new);
        register(net.minecraft.world.level.block.WeatheringCopperSlabBlock.class, CraftWeatheringCopperSlab::new);
        register(net.minecraft.world.level.block.WeatheringCopperStairBlock.class, CraftWeatheringCopperStair::new);
        register(net.minecraft.world.level.block.piston.PistonBaseBlock.class, CraftPiston::new);
        register(net.minecraft.world.level.block.piston.PistonHeadBlock.class, CraftPistonExtension::new);
        register(net.minecraft.world.level.block.piston.MovingPistonBlock.class, CraftPistonMoving::new);
        //</editor-fold>
    }

    private static void register(Class<? extends Block> nms, Function<BlockState, CraftBlockData> bukkit) {
        Preconditions.checkState(MAP.put(nms, bukkit) == null, "Duplicate mapping %s->%s", nms, bukkit);
    }

    public static CraftBlockData newData(Material material, String data) {
        Preconditions.checkArgument(material == null || material.isBlock(), "Cannot get data for not block %s", material);

        BlockState blockData;
        Block block = CraftMagicNumbers.getBlock(material);
        Map<net.minecraft.world.level.block.state.properties.Property<?>, Comparable<?>> parsed = null;

        // Data provided, use it
        if (data != null) {
            try {
                // Material provided, force that material in
                if (block != null) {
                    data = BuiltInRegistries.BLOCK.getKey(block) + data;
                }

                StringReader reader = new StringReader(data);
                BlockStateParser.BlockResult arg = BlockStateParser.parseForBlock(BuiltInRegistries.BLOCK.asLookup(), reader, false);
                Preconditions.checkArgument(!reader.canRead(), "Spurious trailing data: " + data);

                blockData = arg.blockState();
                parsed = arg.properties();
            } catch (CommandSyntaxException ex) {
                throw new IllegalArgumentException("Could not parse data: " + data, ex);
            }
        } else {
            blockData = block.defaultBlockState();
        }

        CraftBlockData craft = fromData(blockData);
        craft.parsedStates = parsed;
        return craft;
    }

    public static CraftBlockData fromData(BlockState data) {
        return MAP.getOrDefault(data.getBlock().getClass(), CraftBlockData::new).apply(data);
    }

    public static Class<?> getClosestBlockDataClass(Class<? extends Block> blockClass) {
        if (MAP.containsKey(blockClass))
            return MAP.get(blockClass).apply(null).getClass();

        // Try obtaining closest CraftBlockData subclass
        Class<?> superClass = blockClass.getSuperclass();
        Class<?> matchedClass = null;
        Function<BlockState, CraftBlockData> matchedFunction = null;

        while (superClass != null) {
            if (MAP.containsKey(superClass)) {
                matchedFunction = MAP.get(superClass);
                matchedClass = matchedFunction.apply(null).getClass();
                break;
            }
            superClass = superClass.getSuperclass();
        }
        if (matchedClass == null)
            return null;
        register(blockClass, matchedFunction);
        return matchedClass;
    }

    @Override
    public SoundGroup getSoundGroup() {
        return CraftSoundGroup.getSoundGroup(state.getSoundType());
    }

    @Override
    public boolean isSupported(org.bukkit.block.Block block) {
        Preconditions.checkArgument(block != null, "block must not be null");

        CraftBlock craftBlock = (CraftBlock) block;
        return state.canSurvive(craftBlock.getCraftWorld().getHandle(), craftBlock.getPosition());
    }

    @Override
    public boolean isSupported(Location location) {
        Preconditions.checkArgument(location != null, "location must not be null");

        CraftWorld world = (CraftWorld) location.getWorld();
        Preconditions.checkArgument(world != null, "location must not have a null world");

        BlockPos position = new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        return state.canSurvive(world.getHandle(), position);
    }

    @Override
    public boolean isFaceSturdy(BlockFace face, BlockSupport support) {
        Preconditions.checkArgument(face != null, "face must not be null");
        Preconditions.checkArgument(support != null, "support must not be null");

        return state.isFaceSturdy(EmptyBlockGetter.INSTANCE, BlockPos.ZERO, CraftBlock.blockFaceToNotch(face), CraftBlockSupport.toNMS(support));
    }
}
