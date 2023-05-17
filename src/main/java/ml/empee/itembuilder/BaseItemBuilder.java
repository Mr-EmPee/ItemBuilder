/**
 * MIT License
 * <p>
 * Copyright (c) 2021 TriumphTeam
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package ml.empee.itembuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import ml.empee.itembuilder.utils.ItemNbt;
import ml.empee.itembuilder.utils.VersionHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Contains all the common methods for the future ItemBuilders
 *
 * @param <B> The ItemBuilder type so the methods can cast to the subtype
 * @author GabyTM <a href="https://github.com/iGabyTM">https://github.com/iGabyTM</a>
 */
@SuppressWarnings("unchecked")
public abstract class BaseItemBuilder<B extends BaseItemBuilder<B>> {

  private static final EnumSet<Material> LEATHER_ARMOR = EnumSet.of(
      Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS,
      Material.LEATHER_BOOTS
  );

  private JavaPlugin plugin;
  private ItemStack itemStack;
  private ItemMeta meta;

  protected BaseItemBuilder(@NotNull final ItemStack itemStack) {
    Objects.requireNonNull(itemStack, "Item can't be null!");

    this.itemStack = itemStack;
    meta = itemStack.hasItemMeta() ? itemStack.getItemMeta()
        : Bukkit.getItemFactory().getItemMeta(itemStack.getType());
  }

  /**
   * Used for specify the instance that has created this item
   * <p>
   * Needed for using nbt related methods
   *
   * @param plugin instance
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public B plugin(final JavaPlugin plugin) {
    this.plugin = plugin;
    return (B) this;
  }

  /**
   * Sets the amount of items
   *
   * @param amount the amount of items
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public B amount(final int amount) {
    itemStack.setAmount(amount);
    return (B) this;
  }

  /**
   * Enchants the {@link ItemStack}
   *
   * @param enchantment            The {@link Enchantment} to add
   * @param level                  The level of the {@link Enchantment}
   * @param ignoreLevelRestriction If should or not ignore it
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_, _, _ -> this")
  public B enchant(@NotNull final Enchantment enchantment, final int level,
      final boolean ignoreLevelRestriction) {
    meta.addEnchant(enchantment, level, ignoreLevelRestriction);
    return (B) this;
  }

  /**
   * Enchants the {@link ItemStack}
   *
   * @param enchantment The {@link Enchantment} to add
   * @param level       The level of the {@link Enchantment}
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_, _ -> this")
  public B enchant(@NotNull final Enchantment enchantment, final int level) {
    return enchant(enchantment, level, true);
  }

  /**
   * Enchants the {@link ItemStack}
   *
   * @param enchantment The {@link Enchantment} to add
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public B enchant(@NotNull final Enchantment enchantment) {
    return enchant(enchantment, 1, true);
  }

  /**
   * Enchants the {@link ItemStack} with the specified map where the value is the level of the key's
   * enchantment
   *
   * @param enchantments           Enchantments to add
   * @param ignoreLevelRestriction If level restriction should be ignored
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_, _ -> this")
  public B enchant(@NotNull final Map<Enchantment, Integer> enchantments,
      final boolean ignoreLevelRestriction) {
    enchantments.forEach(
        (enchantment, level) -> this.enchant(enchantment, level, ignoreLevelRestriction));
    return (B) this;
  }

  /**
   * Enchants the {@link ItemStack} with the specified map where the value is the level of the key's
   * enchantment
   *
   * @param enchantments Enchantments to add
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public B enchant(@NotNull final Map<Enchantment, Integer> enchantments) {
    return enchant(enchantments, true);
  }

  /**
   * Disenchants a certain {@link Enchantment} from the {@link ItemStack}
   *
   * @param enchantment The {@link Enchantment} to remove
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public B disenchant(@NotNull final Enchantment enchantment) {
    itemStack.removeEnchantment(enchantment);
    return (B) this;
  }

  /**
   * Add an {@link ItemFlag} to the item
   *
   * @param flags The {@link ItemFlag} to add
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public B flags(@NotNull final ItemFlag... flags) {
    meta.addItemFlags(flags);
    return (B) this;
  }

  /**
   * Makes the {@link ItemStack} unbreakable
   *
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract(" -> this")
  public B unbreakable() {
    return unbreakable(true);
  }

  /**
   * Sets the item as unbreakable
   *
   * @param unbreakable If should or not be unbreakable
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public B unbreakable(boolean unbreakable) {
    Objects.requireNonNull(plugin, "Unable to use without specifying a plugin");

    if (VersionHelper.IS_UNBREAKABLE_LEGACY) {
      return setNbt("Unbreakable", unbreakable);
    }

    meta.setUnbreakable(unbreakable);
    return (B) this;
  }

  /**
   * Makes the {@link ItemStack} glow
   *
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract(" -> this")
  public B glow() {
    return glow(true);
  }

  /**
   * Adds or removes the {@link ItemStack} glow
   *
   * @param glow Should the item glow
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public B glow(boolean glow) {
    if (glow) {
      meta.addEnchant(Enchantment.LURE, 1, false);
      meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
      return (B) this;
    }

    for (final Enchantment enchantment : meta.getEnchants().keySet()) {
      meta.removeEnchant(enchantment);
    }

    return (B) this;
  }

  /**
   * Consumer for applying {@link PersistentDataContainer} to the item This method will only work on versions
   * above 1.14
   *
   * @param consumer The {@link Consumer} with the PDC
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public B pdc(@NotNull final Consumer<PersistentDataContainer> consumer) {
    consumer.accept(meta.getPersistentDataContainer());
    return (B) this;
  }

  /**
   * Sets the custom model data of the item Added in 1.13
   *
   * @param modelData The custom model data from the resource pack
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public B model(final int modelData) {
    if (VersionHelper.IS_CUSTOM_MODEL_DATA) {
      meta.setCustomModelData(modelData);
    }

    return (B) this;
  }

  /**
   * Color an {@link ItemStack}
   *
   * @param color color
   * @return {@link B}
   * @see LeatherArmorMeta#setColor(Color)
   * @see org.bukkit.inventory.meta.MapMeta#setColor(Color)
   */
  @NotNull
  @Contract("_ -> this")
  public B color(@NotNull final Color color) {
    if (LEATHER_ARMOR.contains(itemStack.getType())) {
      final LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) getMeta();

      leatherArmorMeta.setColor(color);
      setMeta(leatherArmorMeta);
    }

    return (B) this;
  }

  /**
   * Sets NBT tag to the {@link ItemStack}
   *
   * @param key   The NBT key
   * @param value The NBT value
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_, _ -> this")
  public B setNbt(@NotNull final String key, @NotNull final String value) {
    Objects.requireNonNull(plugin, "Unable to use without specifying a plugin");

    itemStack.setItemMeta(meta);
    itemStack = ItemNbt.setString(plugin, itemStack, key, value);
    meta = itemStack.getItemMeta();
    return (B) this;
  }

  /**
   * Sets NBT tag to the {@link ItemStack}
   *
   * @param key   The NBT key
   * @param value The NBT value
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_, _ -> this")
  public B setNbt(@NotNull final String key, final boolean value) {
    Objects.requireNonNull(plugin, "Unable to use without specifying a plugin");

    itemStack.setItemMeta(meta);
    itemStack = ItemNbt.setBoolean(plugin, itemStack, key, value);
    meta = itemStack.getItemMeta();
    return (B) this;
  }

  /**
   * Removes NBT tag from the {@link ItemStack}
   *
   * @param key The NBT key
   * @return {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> this")
  public B removeNbt(@NotNull final String key) {
    Objects.requireNonNull(plugin, "Unable to use without specifying a plugin");

    itemStack.setItemMeta(meta);
    itemStack = ItemNbt.removeTag(plugin, itemStack, key);
    meta = itemStack.getItemMeta();
    return (B) this;
  }

  /**
   * Builds the item into {@link ItemStack}
   *
   * @return The fully built {@link ItemStack}
   */
  @NotNull
  public ItemStack build() {
    itemStack.setItemMeta(meta);
    return itemStack;
  }

  /**
   * Package private getter for extended builders
   *
   * @return The ItemStack
   */
  @NotNull
  protected ItemStack getItemStack() {
    return itemStack;
  }

  /**
   * Package private setter for the extended builders
   *
   * @param itemStack The ItemStack
   */
  protected void setItemStack(@NotNull final ItemStack itemStack) {
    this.itemStack = itemStack;
  }

  /**
   * Package private getter for extended builders
   *
   * @return The ItemMeta
   */
  @NotNull
  protected ItemMeta getMeta() {
    return meta;
  }

  /**
   * Package private setter for the extended builders
   *
   * @param meta The ItemMeta
   */
  protected void setMeta(@NotNull final ItemMeta meta) {
    this.meta = meta;
  }


  /**
   * Set display name of the item
   *
   * @param name the display name of the item
   * @return {@link ItemBuilder}
   */
  public B setName(@NotNull final String name) {
    getMeta().setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
    return (B) this;
  }

  /**
   * Add lore lines of an item
   *
   * @param lore the lore lines to add
   * @return {@link ItemBuilder}
   */
  public B addLore(@NotNull final String... lore) {
    return addLore(Arrays.asList(lore));
  }

  /**
   * Set lore lines of an item
   *
   * @param lore A {@link List} with the lore lines to add
   * @return {@link ItemBuilder}
   */
  public B addLore(@NotNull final List<String> lore) {
    final List<String> newLore = getMeta().hasLore() ? getMeta().getLore() : new ArrayList<>();

    newLore.addAll(
        lore.stream()
            .map(s -> ChatColor.translateAlternateColorCodes('&', s))
            .collect(Collectors.toList())
    );

    return setLore(newLore);
  }

  /**
   * Set the lore lines of an item
   *
   * @param lore the lore lines to set
   * @return {@link ItemBuilder}
   */
  public B setLore(@NotNull final String... lore) {
    return setLore(Arrays.asList(lore));
  }

  /**
   * Set the lore lines of an item
   *
   * @param lore A {@link List} with the lore lines
   * @return {@link ItemBuilder}
   */
  public B setLore(@NotNull final List<String> lore) {
    getMeta().setLore(
        lore.stream()
            .map(s -> ChatColor.translateAlternateColorCodes('&', s))
            .collect(Collectors.toList())
    );
    return (B) this;
  }

}
