package codyhuh.shoal.core.utils;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.NotNull;

public class TradingUtils {
    public static class ItemsForCurrency implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final ItemStack currency;
        private final int cost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForCurrency(Item item, Item currency, int cost, int numberOfItems, int maxUses, int xpGranted) {
            this(new ItemStack(item), new ItemStack(currency), cost, numberOfItems, maxUses, xpGranted);
        }

        public ItemsForCurrency(ItemStack stack, ItemStack currency, int cost, int numberOfItems, int maxUses, int xpGranted) {
            this(stack, currency, cost, numberOfItems, maxUses, xpGranted, 0.05F);
        }

        public ItemsForCurrency(ItemStack stack, ItemStack currency, int cost, int numberOfItems, int maxUses, int xpGranted, float priceMultiplier) {
            this.itemStack = stack;
            this.currency = currency;
            this.cost = cost;
            this.numberOfItems = numberOfItems;
            this.maxUses = maxUses;
            this.villagerXp = xpGranted;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(@NotNull Entity trader, @NotNull RandomSource source) {
            return new MerchantOffer(new ItemStack(this.currency.getItem(), this.cost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class EnchantedItemForCurrency implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final ItemStack currency;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EnchantedItemForCurrency(Item item, Item currency, int cost, int maxUses, int xp) {
            this(item, new ItemStack(currency), cost, maxUses, xp, 0.05F);
        }

        public EnchantedItemForCurrency(Item item, ItemStack currency, int cost, int maxUses, int xp, float priceMultiplier) {
            this.itemStack = new ItemStack(item);
            this.currency = currency;
            this.cost = cost;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity entity, RandomSource rand) {
            ItemStack enchantItem = EnchantmentHelper.enchantItem(rand, new ItemStack(this.itemStack.getItem()), 30, false);
            return new MerchantOffer(new ItemStack(currency.getItem(), cost), enchantItem, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

}
