package io.ncbpfluffybear.supserv;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.Composter;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.ElectricPress;
import io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.OreCrusher;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.ncbpfluffybear.supserv.machines.AdvancedElectricComposter;
import io.ncbpfluffybear.supserv.machines.Baler;
import io.ncbpfluffybear.supserv.machines.ExpGenerator;
import io.ncbpfluffybear.supserv.machines.HeadGrinder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SupServItems {

    private SupServItems() {
    }

    // Category
    public static final ItemGroup SUPSERV_CATEGORY = new ItemGroup(new NamespacedKey(SupServPlugin.getInstance(),
            "supservcategory"),
            new CustomItemStack(Material.NETHERITE_BLOCK, "&a辅助物品")
    );

    public static final SlimefunItemStack LAVA_SPONGE = new SlimefunItemStack("LAVA_SPONGE",
            Material.BROWN_DYE,
            "&6熔岩海绵",
            "",
            "&7吸收熔岩的海绵",
            "&e右键 &7吸收 3x3x3 范围的岩浆",
            "&7不能重复使用"
    );

    public static final SlimefunItemStack CHIPPED_WITHER_SKELETON_SKULL = new SlimefunItemStack("CHIPPED_WITHER_SKELETON_SKULL",
            Material.MELON_SEEDS,
            "&7&l切片凋灵骷髅头骨",
            "",
            "&7一小块凋灵骷髅头骨"
    );

    public static final SlimefunItemStack FRAGMENTED_WITHER_SKELETON_SKULL = new SlimefunItemStack("FRAGMENTED_WITHER_SKELETON_SKULL",
            Material.FLINT,
            "&7&l碎块凋灵骷髅头骨",
            "",
            "&7一小块凋灵骷髅头骨"
    );

    public static final SlimefunItemStack CONDENSED_NETHER_STAR_BLOCK = new SlimefunItemStack("CONDENSED_NETHER_STAR_BLOCK",
            Material.QUARTZ_BRICKS,
            "&f&l凝聚虚空星块",
            "",
            "&7许多 &e下界之星 &7s捣碎在一起"
    );

    public static final SlimefunItemStack INGOT_OF_AFTERLIFE = new SlimefunItemStack("INGOT_OF_AFTERLIFE",
            Material.NETHER_BRICK,
            "&4来世锭",
            "",
            "&7浓缩 &4来世精华"
    );

    public static final SlimefunItemStack DRAGON_ESSENCE = new SlimefunItemStack("DRAGON_ESSENCE",
            Material.DRAGON_BREATH,
            "&4&l龙的精华",
            "",
            "&7装在瓶子里的 &4&lDragon &7的精华"
    );

    public static final SlimefunItemStack WITHER_SKELETON_SKULL = new SlimefunItemStack("WITHER_SKELETON_SKULL",
            new ItemStack(Material.WITHER_SKELETON_SKULL));

    public static final SlimefunItemStack DRAGON_EGG = new SlimefunItemStack("DRAGON_EGG",
            new ItemStack(Material.DRAGON_EGG));

    public static final SlimefunItemStack FARMERS_HOE = new SlimefunItemStack("FARMERS_HOE",
            Material.IRON_HOE,
            "&3农民锄头",
            "",
            "&7确保以耐用性为代价, ",
            "&7重新播种收获的地块"
    );

    public static final SlimefunItemStack WATERING_CAN = new SlimefunItemStack("SS_WATERING_CAN",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&b喷壶",
            "",
            "&f浇灌植物",
            "",
            "&7> &e右键 &7水来装满你的喷壶",
            "&7> &e右键 &7植物来加速其生长",
            "&7> &e右键 &7玩家使其缓慢",
            "",
            "&a使用次数剩余: &e0"
    );

    public static final SlimefunItemStack WATERING_CAN_IRON = new SlimefunItemStack("WATERING_CAN_IRON",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&b喷壶 [铁]",
            "",
            "&f浇灌植物",
            "",
            "&7> &e右键 &7水来装满你的喷壶",
            "&7> &e右键 &7植物来加速其生长",
            "&7> &e右键 &7玩家使其缓慢",
            "",
            "&a使用次数剩余: &e0"
    );

    //DUMMY ITEM FOR IRON WATERING CAN RECIPE USING THE WATERING CAN FROM FLUFFYMACHINES
    public static final SlimefunItemStack WATERING_CAN_IRON_FM = new SlimefunItemStack("WATERING_CAN_IRON_FM",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&b喷壶 [铁] &4仅用于转换",
            "",
            "&4注意: 仅用于将 ",
            "&4蓬松机器物品 转换成 辅助物品"
    );
    public static final SlimefunItemStack WATERING_CAN_GOLD = new SlimefunItemStack("WATERING_CAN_GOLD",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&b喷壶 [金]",
            "",
            "&f浇灌植物",
            "",
            "&7> &e右键 &7水来装满你的喷壶",
            "&7> &e右键 &7植物来加速其生长",
            "&7> &e右键 &7玩家使其缓慢",
            "",
            "&a使用次数剩余: &e0"
    );

    public static final SlimefunItemStack WATERING_CAN_DIAMOND = new SlimefunItemStack("WATERING_CAN_DIAMOND",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&b喷壶 [钻石]",
            "",
            "&f浇灌植物",
            "",
            "&7> &e右键 &7水来装满你的喷壶",
            "&7> &e右键 &7植物来加速其生长",
            "&7> &e右键 &7玩家使其缓慢",
            "",
            "&a使用次数剩余: &e0"
    );

    public static final SlimefunItemStack WATERING_CAN_EMERALD = new SlimefunItemStack("WATERING_CAN_EMERALD",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&b喷壶 [绿宝石]",
            "",
            "&f浇灌植物",
            "",
            "&7> &e右键 &7水来装满你的喷壶",
            "&7> &e右键 &7植物来加速其生长",
            "&7> &e右键 &7玩家使其缓慢",
            "",
            "&a使用次数剩余: &e0"
    );

    public static final SlimefunItemStack WATERING_CAN_NETHERITE = new SlimefunItemStack("WATERING_CAN_NETHERITE",
            "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
            "&b喷壶 [下界]",
            "",
            "&f浇灌植物",
            "",
            "&7> &e右键 &7水来装满你的喷壶",
            "&7> &e右键 &7植物来加速其生长",
            "&7> &e右键 &7玩家使其缓慢",
            "",
            "&a使用次数剩余: &e0"
    );

    public static final SlimefunItemStack VOLTMETER = new SlimefunItemStack("VOLTMETER", Material.CLOCK,
            "&e电压表",
            "",
            "&7该工具向您显示",
            "&7有关您的能源网络的统计数据",
            "&7&o(可能不适用于特定机器)",
            "",
            "&7> &e右键 &7你的网络组件",
            "&7> &eShift + 右键 &7改变模式"
    );

    public static final SlimefunItemStack COMPRESSED_COBBLESTONE = new SlimefunItemStack("COMPRESSED_COBBLESTONE",
            "57f99971601ee77666c05923f341a89ceba1357532279e3926aea79f55d263a0",
            "&f&l压缩圆石",
            "",
            "&7包含 9 个原石"
    );

    public static final SlimefunItemStack DOUBLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("DOUBLE_COMPRESSED_COBBLESTONE",
            "8c96220dc7b85b909a575acfaffb06c8c878a2d515dbec28bf2680346acf173f",
            "&f&l双重压缩圆石",
            "",
            "&7包含 81 个原石"
    );

    public static final SlimefunItemStack TRIPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("TRIPLE_COMPRESSED_COBBLESTONE",
            "9e2b0924aa2b424e0ff6616a93c8ef487057745af1aa5cd223c541ebd3a688a3",
            "&7&l三重压缩圆石",
            "",
            "&7包含 729 个原石"
    );

    public static final SlimefunItemStack QUADRUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("QUADRUPLE_COMPRESSED_COBBLESTONE",
            "37fba6ba0e17007ae17cb1b48f49c26a0256a7d2e8884c3cf07aaede025ebb72",
            "&7&l四重压缩圆石",
            "",
            "&7包含 6,561 个原石"
    );

    public static final SlimefunItemStack QUINTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("QUINTUPLE_COMPRESSED_COBBLESTONE",
            "b5a86541ed9cd29fcac0a801cece9c27a00549ecf41c46ded300bb012da59390",
            "&7&l五重压缩圆石",
            "",
            "&7包含 59,049 个原石"
    );

    public static final SlimefunItemStack SEXTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("SEXTUPLE_COMPRESSED_COBBLESTONE",
            "9ec69a73450820bb97b51509b334eb0d9f6c1f8a9d515fad57f3b7619aa3af9a",
            "&8&l六重压缩圆石",
            "",
            "&7包含 531,441 个原石"
    );

    public static final SlimefunItemStack SEPTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("SEPTUPLE_COMPRESSED_COBBLESTONE",
            "b6ebc30aa2edfa1991a5ba77e2f2cb9d7398d375be99b1c1eff7aef2dddf7399",
            "&8&l七重压缩圆石",
            "",
            "&7包含 4,782,969 个原石"
    );

    public static final SlimefunItemStack OCTUPLE_COMPRESSED_COBBLESTONE = new SlimefunItemStack("OCTUPLE_COMPRESSED_COBBLESTONE",
            "811b19b06813d0388eae03bb2c97621c48a78b34f735a925787934a6c304199a",
            "&8&l八重压缩圆石",
            "",
            "&7包含 43,046,721 个原石"
    );

    public static final SlimefunItemStack WITHER_PROOF_SEA_LANTERN = new SlimefunItemStack("WP_SEA_LANTERN",
            Material.SEA_LANTERN,
            "&3防枯海灯",
            "",
            "&7在你抵抗凋零伤害时",
            "&7照亮你周围的区域"
    );

    public static final SlimefunItemStack WITHER_PROOF_GLOWSTONE = new SlimefunItemStack("WP_GLOWSTONE",
            Material.GLOWSTONE,
            "&3防枯萤石",
            "",
            "&7在你抵抗凋零伤害时",
            "&7照亮你周围的区域"
    );

    public static final SlimefunItemStack WITHER_PROOF_SHROOMLIGHT = new SlimefunItemStack("WP_SHROOMLIGHT",
            Material.SHROOMLIGHT,
            "&3防枯蘑菇灯",
            "",
            "&7在你抵抗凋零伤害时",
            "&7照亮你周围的区域"
    );

    public static final SlimefunItemStack WITHER_PROOF_PEARLESCENT_FROGLIGHT = new SlimefunItemStack("WP_PEARLESCENT_FROGLIGHT",
            Material.PEARLESCENT_FROGLIGHT,
            "&3Wither-Proof Pearlescent Froglight",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by Xanolok"
    );

    public static final SlimefunItemStack WITHER_PROOF_VERDANT_FROGLIGHT = new SlimefunItemStack("WP_VERDANT_FROGLIGHT",
            Material.VERDANT_FROGLIGHT,
            "&3Wither-Proof Verdant Froglight",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by Xanolok"
    );

    public static final SlimefunItemStack WITHER_PROOF_OCHRE_FROGLIGHT = new SlimefunItemStack("WP_OCHRE_FROGLIGHT",
            Material.OCHRE_FROGLIGHT,
            "&3Wither-Proof Ochre Froglight",
            "",
            "&7Lights up your area, while resistant",
            "&7against attacks from withers",
            "",
            "&9Suggested by Xanolok"
    );

    public static final SlimefunItemStack ADVANCED_ELECTRIC_COMPOSTER = new SlimefunItemStack("ADVANCED_ELECTRIC_COMPOSTER",
            Material.RED_TERRACOTTA,
            "&c高级电力堆肥机",
            "",
            "&7堆肥异域花园树苗",
            "&7也能将灵魂土壤变成灵魂沙",
            "",
            "&a中型机器",
            LoreBuilder.powerBuffer(AdvancedElectricComposter.CAPACITY),
            LoreBuilder.powerPerSecond(AdvancedElectricComposter.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack EXP_GENERATOR = new SlimefunItemStack("EXP_GENERATOR",
            Material.LIME_STAINED_GLASS,
            "&a经验生成器",
            "",
            "&7将经验转化为电能",
            "",
            "&a中型机器",
            LoreBuilder.powerBuffer(ExpGenerator.CAPACITY),
            LoreBuilder.powerPerSecond(ExpGenerator.ENERGY_PER_BOTTLE)
    );

    public static final SlimefunItemStack BALER = new SlimefunItemStack("BALER",
            Material.BARREL,
            "&e打包机",
            "",
            "&7将小麦压缩成干草块",
            "",
            "&e基础机器",
            LoreBuilder.powerBuffer(Baler.CAPACITY),
            LoreBuilder.powerPerSecond(Baler.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack ENDERMAN_TETHER = new SlimefunItemStack("ENDERMAN_TETHER",
            Material.END_ROD,
            "&3末影人框框",
            "",
            "&7防止末影人传送出",
            "&7这个 3x3 的区域",
            "",
            "&7右键捡起"
    );

    public static final SlimefunItemStack HEAD_GRINDER = new SlimefunItemStack("HEAD_GRINDER",
            Material.SMOKER,
            "&4头颅研磨器",
            "",
            "&7将怪物头研磨成水滴",
            "",
            "&4终极机器",
            LoreBuilder.powerBuffer(HeadGrinder.CAPACITY),
            LoreBuilder.powerPerSecond(HeadGrinder.ENERGY_CONSUMPTION)
    );

    static {

        // Press Recipes
        addPressRecipe(3, new ItemStack(Material.COBBLESTONE, 9),
                COMPRESSED_COBBLESTONE
        );
        addPressRecipe(6, new SlimefunItemStack(COMPRESSED_COBBLESTONE, 9),
                DOUBLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(9, new SlimefunItemStack(DOUBLE_COMPRESSED_COBBLESTONE, 9),
                TRIPLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(12, new SlimefunItemStack(TRIPLE_COMPRESSED_COBBLESTONE, 9),
                QUADRUPLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(15, new SlimefunItemStack(QUADRUPLE_COMPRESSED_COBBLESTONE, 9),
                QUINTUPLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(18, new SlimefunItemStack(QUINTUPLE_COMPRESSED_COBBLESTONE, 9),
                SEXTUPLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(21, new SlimefunItemStack(SEXTUPLE_COMPRESSED_COBBLESTONE, 9),
                SEPTUPLE_COMPRESSED_COBBLESTONE
        );
        addPressRecipe(24, new SlimefunItemStack(SEPTUPLE_COMPRESSED_COBBLESTONE, 9),
                OCTUPLE_COMPRESSED_COBBLESTONE
        );

        // Ore Crusher Recipes
        addOreCrusherRecipe(new ItemStack[]{new ItemStack(Material.RAW_COPPER_BLOCK, 1)},
                new SlimefunItemStack(SlimefunItems.COPPER_DUST, 9)
        );
        addOreCrusherRecipe(new ItemStack[]{new ItemStack(Material.RAW_IRON_BLOCK, 1)},
                new SlimefunItemStack(SlimefunItems.IRON_DUST, 9)
        );
        addOreCrusherRecipe(new ItemStack[]{new ItemStack(Material.RAW_GOLD_BLOCK, 1)},
                new SlimefunItemStack(SlimefunItems.GOLD_DUST, 9)
        );
        addOreCrusherRecipe(new ItemStack[]{new ItemStack(Material.STONE, 8)},
                new ItemStack(Material.COBBLESTONE, 8)
        );

        // Composter Recipes
        addComposterRecipe(new ItemStack(Material.SOUL_SOIL), new ItemStack(Material.SOUL_SAND));
    }

    /**
     * Registers a recipe to both tiers of Electric Presses
     *
     * @param seconds the time it takes to run
     * @param input   the item that is inserted
     * @param output  the output item
     */
    private static void addPressRecipe(int seconds, ItemStack input, ItemStack output) {
        ((ElectricPress) SlimefunItems.ELECTRIC_PRESS.getItem()).registerRecipe(
                seconds, input, output
        );

        ((ElectricPress) SlimefunItems.ELECTRIC_PRESS_2.getItem()).registerRecipe(
                seconds, input, output
        );
    }

    /**
     * Registers a recipe to Ore Crusher
     *
     * @param input an array of items that are inserted
     * @param output the output item
     */
    private static void addOreCrusherRecipe(ItemStack[] input, ItemStack output) {
        ((OreCrusher) SlimefunItems.ORE_CRUSHER.getItem()).addRecipe(input, output);
    }

    /**
     * Register a recipe to Composter
     *
     * @param input the item that is inserted
     * @param output the output item
     */
    private static void addComposterRecipe(ItemStack input, ItemStack output) {
        ((Composter) SlimefunItems.COMPOSTER.getItem()).getDisplayRecipes().addAll(
                Arrays.asList(input, output)
        );
    }
}
