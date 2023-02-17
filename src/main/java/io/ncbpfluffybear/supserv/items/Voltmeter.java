package io.ncbpfluffybear.supserv.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNet;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.AbstractEnergyProvider;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.Capacitor;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.generators.SolarGenerator;
import io.ncbpfluffybear.supserv.utils.Utils;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;

@SuppressWarnings({"unchecked"})
public class Voltmeter extends SimpleSlimefunItem<ItemUseHandler> {

    enum VoltmeterMode {CONSUMERS, CAPACITORS, GENERATORS, CHARGE, COMPONENTS}

    private VoltmeterMode mode;

    public Voltmeter(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.mode = VoltmeterMode.CONSUMERS;
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player player = e.getPlayer();
            ItemStack item = e.getItem();
            Optional<Block> block = e.getClickedBlock();

            if (player.isSneaking()) {
                switch (mode) {
                    case CONSUMERS:
                        mode = VoltmeterMode.CAPACITORS;
                        break;
                    case CAPACITORS:
                        mode = VoltmeterMode.GENERATORS;
                        break;
                    case GENERATORS:
                        mode = VoltmeterMode.CHARGE;
                        break;
                    case CHARGE:
                        mode = VoltmeterMode.COMPONENTS;
                        break;
                    case COMPONENTS:
                        mode = VoltmeterMode.CONSUMERS;
                        break;
                }

                Utils.sendChatMsg(player, "&5选择模式: &7" + pretifyId(mode.toString()));
                return;
            }

            if (!isItem(item) || !block.isPresent()) {
                return;
            }

            EnergyNet energyNet = EnergyNet.getNetworkFromLocation(block.get().getLocation());

            if (Objects.isNull(energyNet)) {
                return;
            }

            if (mode == VoltmeterMode.CONSUMERS) {
                consumerStatistics(player, energyNet);
            } else if (mode == VoltmeterMode.CAPACITORS) {
                capacitorStatistics(player, energyNet);
            } else if (mode == VoltmeterMode.GENERATORS) {
                generatorStatistics(player, energyNet);
            } else if (mode == VoltmeterMode.CHARGE) {
                chargeStatistics(player, energyNet);
            } else {
                elementStatistics(player, energyNet);
            }

        };
    }

    private void consumerStatistics(Player player, EnergyNet energyNet) {
        Map<Location, EnergyNetComponent> consumers = null;

        try {
            Field consumersField = energyNet.getClass().getDeclaredField("consumers");
            consumersField.setAccessible(true);
            consumers = (Map<Location, EnergyNetComponent>) consumersField.get(energyNet);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            Bukkit.getLogger().log(Level.SEVERE,
                    "An error has occurred while trying to access private fields: " + System.lineSeparator() + ex);
        }

        int numberOfConsumers = consumers.size();
        int joulesPerSecConsumed = 0;
        int minConsumption = Integer.MAX_VALUE;
        int maxConsumption = 0;

        EnergyNetComponent smallestConsumer = null;
        EnergyNetComponent largestConsumer = null;

        for (Location loc : consumers.keySet()) {
            if (consumers.get(loc) instanceof AContainer) {
                AContainer consumer = (AContainer) consumers.get(loc);

                int consumption = consumer.getEnergyConsumption();

                joulesPerSecConsumed += consumption;

                if (consumption < minConsumption) {
                    minConsumption = consumption;
                    smallestConsumer = consumer;
                }

                if (consumption > maxConsumption) {
                    maxConsumption = consumption;
                    largestConsumer = consumer;
                }
            }
        }

        Utils.sendChatMsg(player, "&7========== &5用电器 &7==========");
        Utils.sendChatMsg(player, "");
        Utils.sendChatMsg(player, "&5用电器数量: &7" + numberOfConsumers);
        Utils.sendChatMsg(player, "&5用电速度: &7" + joulesPerSecConsumed + " J/t");
        Utils.sendChatMsg(player, "&5最小用电器: &7" +
                (Objects.isNull(smallestConsumer) ? "无" : pretifyId(smallestConsumer.getId())) +
                " &5用电量为 &7" + (Objects.isNull(smallestConsumer) ? "0" : minConsumption) + " J/t");
        Utils.sendChatMsg(player, "&5Largest consumer: &7" +
                (Objects.isNull(largestConsumer) ? "无" : pretifyId(largestConsumer.getId())) +
                " &5用电量为 &7" + maxConsumption + " J/t");
        Utils.sendChatMsg(player, "");
        Utils.sendChatMsg(player, "&7===============================");
    }

    private void capacitorStatistics(Player player, EnergyNet energyNet) {
        Map<Location, EnergyNetComponent> capacitors = null;

        try {
            Field capacitorsField = energyNet.getClass().getDeclaredField("capacitors");
            capacitorsField.setAccessible(true);
            capacitors = (Map<Location, EnergyNetComponent>) capacitorsField.get(energyNet);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            Bukkit.getLogger().log(Level.SEVERE,
                    "An error has occurred while trying to access private fields: " + System.lineSeparator() + ex);
        }

        int numberOfCapacitors = capacitors.size();
        int capacity = 0;
        int charge = 0;
        int minCapacity = Integer.MAX_VALUE;
        int maxCapacity = 0;

        EnergyNetComponent smallestCapacitor = null;
        EnergyNetComponent largestCapacitor = null;

        for (Location loc : capacitors.keySet()) {
            Capacitor capacitor = (Capacitor) capacitors.get(loc);

            int currCapacity = capacitor.getCapacity();

            charge += capacitor.getCharge(loc);
            capacity += currCapacity;

            if (currCapacity < minCapacity) {
                minCapacity = currCapacity;
                smallestCapacitor = capacitor;
            }

            if (currCapacity > maxCapacity) {
                maxCapacity = currCapacity;
                largestCapacitor = capacitor;
            }
        }

        Utils.sendChatMsg(player, "&7========== &5电容器 &7==========");
        Utils.sendChatMsg(player, "");
        Utils.sendChatMsg(player, "&5电容器数量: &7" + numberOfCapacitors);
        Utils.sendChatMsg(player, "&5电容器容量: &7" + capacity + " J");
        Utils.sendChatMsg(player, "&5当前充电量: &7" + charge + " J &o("
                + ((double) Math.round(((double) charge / capacity * 100) * 100) / 100) + "%)");
        Utils.sendChatMsg(player, "&5最小电容: &7" +
                (Objects.isNull(smallestCapacitor) ? "无" : pretifyId(smallestCapacitor.getId())) +
                " &5电容量为 &7" + (Objects.isNull(smallestCapacitor) ? "0" : minCapacity) + " J");
        Utils.sendChatMsg(player, "&5最大电容 &7" +
                (Objects.isNull(largestCapacitor) ? "无" : pretifyId(largestCapacitor.getId())) +
                " &5电容量为 &7" + maxCapacity + " J");
        Utils.sendChatMsg(player, "");
        Utils.sendChatMsg(player, "&7===============================");
    }

    private void generatorStatistics(Player player, EnergyNet energyNet) {
        Map<Location, EnergyNetProvider> generators = null;

        try {
            Field generatorsField = energyNet.getClass().getDeclaredField("generators");
            generatorsField.setAccessible(true);
            generators = (Map<Location, EnergyNetProvider>) generatorsField.get(energyNet);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            Bukkit.getLogger().log(Level.SEVERE,
                    "An error has occurred while trying to access private fields: " + System.lineSeparator() + ex);
        }

        int numberOfGenerators = generators.size();
        int joulesGeneratedPerSec = 0;
        int minGeneration = Integer.MAX_VALUE;
        int maxGeneration = 0;

        EnergyNetProvider smallestGenerator = null;
        EnergyNetProvider largestGenerator = null;

        for (Location l : generators.keySet()) {
            EnergyNetProvider generator = generators.get(l);

            if (generator instanceof SolarGenerator) {
                SolarGenerator sGenerator = (SolarGenerator) generator;

                int generatedOutput = (sGenerator.getDayEnergy() + sGenerator.getNightEnergy()) / 2;

                joulesGeneratedPerSec += generatedOutput;

                if (generatedOutput > maxGeneration) {
                    maxGeneration = generatedOutput;
                    largestGenerator = sGenerator;
                }

                if (generatedOutput < minGeneration) {
                    minGeneration = generatedOutput;
                    smallestGenerator = sGenerator;
                }
            } else if (generator instanceof AbstractEnergyProvider) {
                AbstractEnergyProvider aGenerator = (AbstractEnergyProvider) generator;

                int generatedOutput = aGenerator.getEnergyProduction();

                joulesGeneratedPerSec += generatedOutput;

                if (generatedOutput > maxGeneration) {
                    maxGeneration = generatedOutput;
                    largestGenerator = aGenerator;
                }

                if (generatedOutput < minGeneration) {
                    minGeneration = generatedOutput;
                    smallestGenerator = aGenerator;
                }
            }
        }

        Utils.sendChatMsg(player, "&7========== &5发电机 &7==========");
        Utils.sendChatMsg(player, "");
        Utils.sendChatMsg(player, "&5发电机数量: &7" + numberOfGenerators);
        Utils.sendChatMsg(player, "&5发电机发电量: &7" + joulesGeneratedPerSec + " J/t");
        Utils.sendChatMsg(player, "&5最小发电机: &7" +
                (Objects.isNull(smallestGenerator) ? "无" : pretifyId(smallestGenerator.getId())) +
                " &5发电量为 &7" + (Objects.isNull(smallestGenerator) ? "0" : minGeneration) + " J/t");
        Utils.sendChatMsg(player, "&5最大发电机: &7" +
                (Objects.isNull(largestGenerator) ? "无" : pretifyId(largestGenerator.getId())) +
                " &5发电量为 &7" + maxGeneration + " J/t");
        Utils.sendChatMsg(player, "");
        Utils.sendChatMsg(player, "&7===============================");
    }

    private void chargeStatistics(Player player, EnergyNet energyNet) {
        Map<Location, EnergyNetComponent> capacitors = null;
        Map<Location, EnergyNetComponent> consumers = null;
        Map<Location, EnergyNetProvider> generators = null;

        try {
            Field capacitorsField = energyNet.getClass().getDeclaredField("capacitors");
            Field consumersField = energyNet.getClass().getDeclaredField("consumers");
            Field generatorsField = energyNet.getClass().getDeclaredField("generators");
            capacitorsField.setAccessible(true);
            consumersField.setAccessible(true);
            generatorsField.setAccessible(true);
            capacitors = (Map<Location, EnergyNetComponent>) capacitorsField.get(energyNet);
            consumers = (Map<Location, EnergyNetComponent>) consumersField.get(energyNet);
            generators = (Map<Location, EnergyNetProvider>) generatorsField.get(energyNet);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            Bukkit.getLogger().log(Level.SEVERE,
                    "An error has occurred while trying to access private fields: " + System.lineSeparator() + ex);
        }

        int numberOfComponents = capacitors.size() + consumers.size() + generators.size();
        int capacity = 0;
        int charge = 0;
        int minCharge = Integer.MAX_VALUE;
        int maxCharge = 0;

        EnergyNetComponent smallestChargeComponent = null;
        EnergyNetComponent largestChargeComponent = null;

        for (Location loc : capacitors.keySet()) {
            Capacitor capacitor = (Capacitor) capacitors.get(loc);

            int currCharge = capacitor.getCharge(loc);

            capacity += capacitor.getCapacity();
            charge += currCharge;

            if (currCharge < minCharge) {
                minCharge = currCharge;
                smallestChargeComponent = capacitor;
            }

            if (currCharge > maxCharge) {
                maxCharge = currCharge;
                largestChargeComponent = capacitor;
            }
        }

        for (Location loc : consumers.keySet()) {
            EnergyNetComponent consumer = consumers.get(loc);

            int currCharge = consumer.getCharge(loc);

            capacity += consumer.getCapacity();
            charge += currCharge;

            if (currCharge < minCharge) {
                minCharge = currCharge;
                smallestChargeComponent = consumer;
            }

            if (currCharge > maxCharge) {
                maxCharge = currCharge;
                largestChargeComponent = consumer;
            }
        }

        for (Location loc : generators.keySet()) {
            EnergyNetProvider generator = generators.get(loc);

            int currCharge = generator.getCharge(loc);

            capacity += generator.getCapacity();
            charge += currCharge;

            if (currCharge < minCharge) {
                minCharge = currCharge;
                smallestChargeComponent = generator;
            }

            if (currCharge > maxCharge) {
                maxCharge = currCharge;
                largestChargeComponent = generator;
            }
        }

        Utils.sendChatMsg(player, "&7========== &5充电器 &7==========");
        Utils.sendChatMsg(player, "");
        Utils.sendChatMsg(player, "&5充电器数量: &7" + numberOfComponents);
        Utils.sendChatMsg(player, "&5电容量: &7" + capacity + " J");
        Utils.sendChatMsg(player, "&5当前充电量: &7" + charge + " J &o("
                + ((double) Math.round(((double) charge / capacity * 100) * 100) / 100) + "%)");
        Utils.sendChatMsg(player, "&5最小充电器: &7" +
                (Objects.isNull(smallestChargeComponent) ? "无" : pretifyId(smallestChargeComponent.getId())) +
                " &5充电量为 &7" + (Objects.isNull(smallestChargeComponent) ? "0" : minCharge) + " J");
        Utils.sendChatMsg(player, "&5最大充电器: &7" +
                (Objects.isNull(largestChargeComponent) ? "None" : pretifyId(largestChargeComponent.getId())) +
                " &5充电量为 &7" + maxCharge + " J");
        Utils.sendChatMsg(player, "");
        Utils.sendChatMsg(player, "&7============================");
    }

    private void elementStatistics(Player player, EnergyNet energyNet) {
        Map<Location, EnergyNetComponent> capacitors = null;
        Map<Location, EnergyNetComponent> consumers = null;
        Map<Location, EnergyNetProvider> generators = null;

        try {
            Field capacitorsField = energyNet.getClass().getDeclaredField("capacitors");
            Field consumersField = energyNet.getClass().getDeclaredField("consumers");
            Field generatorsField = energyNet.getClass().getDeclaredField("generators");
            capacitorsField.setAccessible(true);
            consumersField.setAccessible(true);
            generatorsField.setAccessible(true);
            capacitors = (Map<Location, EnergyNetComponent>) capacitorsField.get(energyNet);
            consumers = (Map<Location, EnergyNetComponent>) consumersField.get(energyNet);
            generators = (Map<Location, EnergyNetProvider>) generatorsField.get(energyNet);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            Bukkit.getLogger().log(Level.SEVERE,
                    "An error has occurred while trying to access private fields: " + System.lineSeparator() + ex);
        }

        Map<String, Integer> consumersSorted = new HashMap<>();
        Map<String, Integer> capacitorsSorted = new HashMap<>();
        Map<String, Integer> generatorsSorted = new HashMap<>();

        int numberOfComponents = capacitors.size() + consumers.size() + generators.size();

        for (Location loc : capacitors.keySet()) {
            EnergyNetComponent capacitor = capacitors.get(loc);

            if (capacitorsSorted.containsKey(capacitor.getId())) {
                capacitorsSorted.replace(capacitor.getId(), capacitorsSorted.get(capacitor.getId()) + 1);
            } else {
                capacitorsSorted.put(capacitor.getId(), 1);
            }
        }

        for (Location loc : consumers.keySet()) {
            EnergyNetComponent consumer = consumers.get(loc);

            if (consumersSorted.containsKey(consumer.getId())) {
                consumersSorted.replace(consumer.getId(), consumersSorted.get(consumer.getId()) + 1);
            } else {
                consumersSorted.put(consumer.getId(), 1);
            }
        }

        for (Location loc : generators.keySet()) {
            EnergyNetProvider generator = generators.get(loc);

            if (generatorsSorted.containsKey(generator.getId())) {
                generatorsSorted.replace(generator.getId(), generatorsSorted.get(generator.getId()) + 1);
            } else {
                generatorsSorted.put(generator.getId(), 1);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (String key : capacitorsSorted.keySet()) {
            sb.append(pretifyId(key));
            sb.append(" &ox");
            sb.append(capacitorsSorted.get(key));
            sb.append(", &r&7");
        }

        String capacitorsList = sb.toString();
        sb.setLength(0);

        for (String key : consumersSorted.keySet()) {
            sb.append(pretifyId(key));
            sb.append(" &ox");
            sb.append(consumersSorted.get(key));
            sb.append(", &r&7");
        }

        String consumersList = sb.toString();
        sb.setLength(0);

        for (String key : generatorsSorted.keySet()) {
            sb.append(pretifyId(key));
            sb.append(" &ox");
            sb.append(generatorsSorted.get(key));
            sb.append(", &r&7");
        }

        String generatorsList = sb.toString();

        Utils.sendChatMsg(player, "&7========== &5电力组件 &7==========");
        Utils.sendChatMsg(player, "");
        Utils.sendChatMsg(player, "&5电力组件数量: &7" + numberOfComponents);
        Utils.sendChatMsg(player, "&5用电量: &7" + consumersList.substring(0, consumersList.length() - 6));
        Utils.sendChatMsg(player, "&5电容量: &7" + capacitorsList.substring(0, capacitorsList.length() - 6));
        Utils.sendChatMsg(player, "&5发电量: &7" + generatorsList.substring(0, generatorsList.length() - 6));
        Utils.sendChatMsg(player, "");
        Utils.sendChatMsg(player, "&7============================");
    }

    private String pretifyId(String id) {
        char[] arr = id.replaceAll("_", " ").toLowerCase().toCharArray();
        boolean foundSpace = true;

        for (int i = 0; i < arr.length; i++) {
            if (Character.isLetter(arr[i])) {
                if (foundSpace) {
                    arr[i] = Character.toUpperCase(arr[i]);
                    foundSpace = false;
                }
            } else {
                foundSpace = true;
            }
        }

        return String.valueOf(arr);
    }
}
