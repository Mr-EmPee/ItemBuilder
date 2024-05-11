package io.github.empee.itembuilder.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * A utility class to easily modify items
 *
 * @author Redempt
 */
public class ItemstackUtils {

  /**
   * Renames an ItemStack, functionally identical to {@link ItemstackUtils#setName(ItemStack, String)} but kept for legacy reasons. This has no effect on items without meta such as Air
   *
   * @param item The ItemStack to be renamed
   * @param name The name to give the ItemStack
   * @return The renamed ItemStack
   */
  public static ItemStack rename(ItemStack item, String name) {
    ItemMeta meta = item.getItemMeta();
    if (meta == null) {
      return item;
    }
    meta.setDisplayName(name);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Renames an ItemStack
   *
   * @param item The ItemStack to be renamed
   * @param name The name to give the ItemStack
   * @return The renamed ItemStack
   */
  public static ItemStack setName(ItemStack item, String name) {
    return rename(item, name);
  }

  /**
   * Set multiple lines of lore for an ItemStack
   *
   * @param item The ItemStack to be given lore
   * @param lore The lines of lore to be given
   * @return The modified ItemStack
   */
  public static ItemStack setLore(ItemStack item, List<String> lore) {
    ItemMeta meta = item.getItemMeta();
    meta.setLore(lore);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Add a line of lore to an ItemStack
   *
   * @param item The ItemStack to be given lore
   * @param line The line of lore to add
   * @return The modified ItemStack
   */
  public static ItemStack addLore(ItemStack item, String line) {
    ItemMeta meta = item.getItemMeta();
    List<String> lore = meta.getLore();
    lore = lore == null ? new ArrayList<>() : lore;
    lore.add(line);
    meta.setLore(lore);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Adds multiple lines of lore to an ItemStack
   *
   * @param item  The ItemStack to be given lore
   * @param lines The lines or lore to add
   * @return The modified ItemStack
   */
  public static ItemStack addLore(ItemStack item, Iterable<String> lines) {
    ItemMeta meta = item.getItemMeta();
    List<String> lore = meta.getLore();
    lore = lore == null ? new ArrayList<>() : lore;
    lines.forEach(lore::add);
    meta.setLore(lore);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Remove a specific line of lore from an ItemStack if present in an ItemStack
   *
   * @param item The ItemStack to remove lore from
   * @param line The line of lore to remove
   * @return The modified ItemStack
   */
  public static ItemStack removeLoreLine(ItemStack item, String line) {
    ItemMeta meta = item.getItemMeta();
    List<String> lore = meta.getLore();
    lore.remove(line);
    meta.setLore(lore);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Removes a specific index-line of lore from an ItemStack if present in an ItemStack
   *
   * @param item  The ItemStack to remove lore from
   * @param index The index of the line of lore to remove
   * @return The modified ItemStack
   */
  public static ItemStack removeLoreLine(ItemStack item, int index) {
    ItemMeta meta = item.getItemMeta();
    List<String> lore = meta.getLore();
    if (index < 0 || index > lore.size()) {
      throw new IllegalArgumentException("Value out of bounds (" + index + ")");
    }
    lore.remove(index);
    meta.setLore(lore);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Set multiple lines of lore for an ItemStack
   *
   * @param item The ItemStack to be given lore
   * @param lore The lines of lore to be given
   * @return The modified ItemStack
   */
  public static ItemStack setLore(ItemStack item, String... lore) {
    return setLore(item, Arrays.asList(lore));
  }

  /**
   * Sets an item to be unbreakable
   *
   * @param item The item to make unbreakable
   * @return The unbreakable item
   */
  public static ItemStack setUnbreakable(ItemStack item) {
    item = item.clone();
    ItemMeta meta = item.getItemMeta();
    meta.setUnbreakable(true);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Add an enchantment to an ItemStack
   *
   * @param item    The ItemStack to be enchanted
   * @param enchant The Enchantment to add to the ItemStack
   * @param level   The level of the Enchantment
   * @return The enchanted ItemStack
   */
  public static ItemStack addEnchant(ItemStack item, Enchantment enchant, int level) {
    ItemMeta meta = item.getItemMeta();
    meta.addEnchant(enchant, level, true);
    if (level == 0) {
      meta.removeEnchant(enchant);
    }
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Add an attribute to the item
   *
   * @param item      The item to have an attribute added
   * @param attribute The Attribute to be added
   * @param modifier  The AttributeModifier to be added
   * @return The modified ItemStack
   */
  public static ItemStack addAttribute(ItemStack item, Attribute attribute, AttributeModifier modifier) {
    ItemMeta meta = item.getItemMeta();
    meta.addAttributeModifier(attribute, modifier);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Add an attribute to the item
   *
   * @param item      The item to have an attribute added
   * @param attribute The Attribute to be added
   * @param amount    The amount to modify it by
   * @param operation The operation by which the value will be modified
   * @return The modified item
   */
  public static ItemStack addAttribute(ItemStack item, Attribute attribute, double amount, Operation operation) {
    ItemMeta meta = item.getItemMeta();
    AttributeModifier modifier = new AttributeModifier(attribute.toString(), amount, operation);
    meta.addAttributeModifier(attribute, modifier);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Adds ItemFlags to the item
   *
   * @param item  The item to add ItemFlags to
   * @param flags The ItemFlags to add
   * @return The modified item
   */
  public static ItemStack addItemFlags(ItemStack item, ItemFlag... flags) {
    ItemMeta meta = item.getItemMeta();
    meta.addItemFlags(flags);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Sets the custom model data of the item
   *
   * @param item            The item to set the custom model data for
   * @param customModelData The custom model data to set
   * @return The modified item
   */
  public static ItemStack setCustomModelData(ItemStack item, int customModelData) {
    ItemMeta meta = item.getItemMeta();
    meta.setCustomModelData(customModelData);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Adds persistent data to the item
   *
   * @param item The item to add persistent data to
   * @param key  The key to add the data under
   * @param type The type of the data
   * @param data The data to store
   * @param <T>  The primary object type
   * @param <Z>  The retrieved object type
   * @return The modified item
   */
  public static <T, Z> ItemStack addPersistentTag(ItemStack item, NamespacedKey key, PersistentDataType<T, Z> type, Z data) {
    ItemMeta meta = item.getItemMeta();
    meta.getPersistentDataContainer().set(key, type, data);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Add an attribute to the item
   *
   * @param item      The item to have an attribute added
   * @param attribute The Attribute to be added
   * @param amount    The amount to modify it by
   * @param operation The operation by which the value will be modified
   * @param slot      The slot this attribute will be effective in
   * @return The modified item
   */
  public static ItemStack addAttribute(ItemStack item, Attribute attribute, double amount, Operation operation, EquipmentSlot slot) {
    ItemMeta meta = item.getItemMeta();
    AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), attribute.toString(), amount, operation, slot);
    meta.addAttributeModifier(attribute, modifier);
    item.setItemMeta(meta);
    return item;
  }

  /**
   * Damages an item
   *
   * @param item   The item to damage
   * @param amount How much damage to apply
   * @return The damaged item
   * @throws IllegalArgumentException if the item is not damageable
   */
  public static ItemStack damage(ItemStack item, int amount) {
    if (VersionUtils.MID_VERSION >= 13) {
      ItemMeta meta = item.getItemMeta();
      if (!(meta instanceof Damageable)) {
        throw new IllegalArgumentException("Item must be damageable");
      }
      Damageable d = (Damageable) meta;
      d.setDamage(d.getDamage() + amount);
      item.setItemMeta(meta);
      return item;
    }
    item.setDurability((short) (item.getDurability() + amount));
    return item;
  }

}