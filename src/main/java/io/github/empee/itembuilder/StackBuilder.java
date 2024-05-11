package io.github.empee.itembuilder;

import io.github.empee.itembuilder.utils.ItemstackUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

/**
 * A utility class to easily create items
 *
 * @author Redempt
 */
public class StackBuilder extends ItemStack {

  /**
   * Constructs a new ItemBuilder. An ItemBuilder extends ItemStack, an can be used as such.
   *
   * @param material The type of the item
   * @param amount   The amount of the item
   */
  public StackBuilder(Material material, int amount) {
    super(material, amount);
  }

  /**
   * Constructs a new ItemBuilder. An ItemBuilder extends ItemStack, an can be used as such.
   *
   * @param material The type of the item
   */
  public StackBuilder(Material material) {
    super(material);
  }

  /**
   * Constructs an ItemBuilder using a pre-existing item
   *
   * @param item The item to copy
   */
  public StackBuilder(ItemStack item) {
    super(item);
  }

  /**
   * Sets the stack size of this ItemBuilder
   *
   * @param amount The number of items in the stack
   * @return The ItemBuilder with the new stack size
   */
  public StackBuilder setCount(int amount) {
    setAmount(amount);
    return this;
  }

  /**
   * Adds an enchantment to this ItemBuilder
   *
   * @param enchant The enchantment to add
   * @param level   The level of the enchantment
   * @return The enchanted ItemBuilder
   */
  public StackBuilder withEnchant(Enchantment enchant, int level) {
    ItemstackUtils.addEnchant(this, enchant, level);
    return this;
  }

  /**
   * Converts this ItemBuilder to a normal ItemStack. Useful because there are some inconsistencies within Spigot using this class.
   *
   * @return An ItemStack copy of this ItemBuilder
   */
  public ItemStack toItemStack() {
    return new ItemStack(this);
  }

  /**
   * Set the lore of this ItemBuilder
   *
   * @param lore The lines of lore
   * @return The ItemBuilder with lore added
   */
  public StackBuilder withLore(String... lore) {
    ItemstackUtils.setLore(this, lore);
    return this;
  }

  /**
   * Add a line of lore to this ItemBuilder
   *
   * @param line The line of lore
   * @return The ItemBuilder with lore added
   */
  public StackBuilder withLore(String line) {
    ItemstackUtils.addLore(this, line);
    return this;
  }

  /**
   * Add multiple lines of lore to this ItemBuilder
   *
   * @param lines The lines of lore
   * @return The ItemBuilder with lore added
   */
  public StackBuilder withLore(Iterable<String> lines) {
    ItemstackUtils.addLore(this, lines);
    return this;
  }

  /**
   * Remove a String of lore if present from this ItemBuilder
   *
   * @param line The line of lore to remove
   * @return The ItemBuilder with lore removed if present
   */
  public StackBuilder removeLore(String line) {
    ItemstackUtils.removeLoreLine(this, line);
    return this;
  }

  /**
   * Remove a line of lore if present from this ItemBuilder
   *
   * @param index The index of the line of lore to remove
   * @return The ItemBuilder with lore removed if present
   */
  public StackBuilder removeLore(int index) {
    ItemstackUtils.removeLoreLine(this, index);
    return this;
  }

  /**
   * Renames this ItemBuilder
   *
   * @param name The name to set
   * @return The renamed ItemBuilder
   */
  public StackBuilder withName(String name) {
    ItemstackUtils.rename(this, name);
    return this;
  }

  /**
   * Set the durability (damage) of the ItemBuilder
   *
   * @param durability The durability to set
   * @return The ItemBuilder with its durability changed
   */
  @SuppressWarnings("deprecation")
  public StackBuilder withDurability(int durability) {
    this.setDurability((short) durability);
    return this;
  }

  /**
   * Adds an attribute to this ItemBuilder
   *
   * @param attribute The Attribute to be added
   * @param modifier  The AttributeModifier to be added
   * @return The ItemBuilder with the attribute added
   */
  public StackBuilder withAttribute(Attribute attribute, AttributeModifier modifier) {
    ItemstackUtils.addAttribute(this, attribute, modifier);
    return this;
  }

  /**
   * Adds an attribute to this ItemBuilder
   *
   * @param attribute The attribute to be added
   * @param amount    The amount of the modifier
   * @param operation The operation of the modifier
   * @return The ItemBuilder with the attribute added
   */
  public StackBuilder withAttribute(Attribute attribute, double amount, Operation operation) {
    ItemstackUtils.addAttribute(this, attribute, amount, operation);
    return this;
  }

  /**
   * Adds an attribute to this ItemBuilder
   *
   * @param attribute The attribute to be added
   * @param amount    The amount of the modifier
   * @param operation The operation of the modifier
   * @param slot      The slot the modifier affects
   * @return The ItemBuilder with the attribute added
   */
  public StackBuilder withAttribute(Attribute attribute, double amount, Operation operation, EquipmentSlot slot) {
    ItemstackUtils.addAttribute(this, attribute, amount, operation, slot);
    return this;
  }

  /**
   * Adds ItemFlags to this ItemBuilder
   *
   * @param flags The ItemFlags to add
   * @return The ItemBuilder with the flags added
   */
  public StackBuilder withItemFlags(ItemFlag... flags) {
    ItemstackUtils.addItemFlags(this, flags);
    return this;
  }

  /**
   * Adds damage to this ItemBuilder
   *
   * @param damage The amount of damage to apply
   * @return The ItemBuilder with the damage applied
   */
  public StackBuilder withDamage(int damage) {
    ItemstackUtils.damage(this, damage);
    return this;
  }

  /**
   * Sets the custom model data of this ItemBuilder
   *
   * @param customModelData The custom model data to set
   * @return The ItemBuilder with the custom model data set
   */
  public StackBuilder withCustomModelData(int customModelData) {
    ItemstackUtils.setCustomModelData(this, customModelData);
    return this;
  }

  /**
   * Add persistent tags to this ItemBuilder
   *
   * @param key  The key to add the data under
   * @param type The type of the data
   * @param data The data to store
   * @param <T>  The primary object type
   * @param <Z>  The retrieved object type
   * @return The ItemBuilder with the persistent data added
   */
  public <T, Z> StackBuilder withPersistentTag(NamespacedKey key, PersistentDataType<T, Z> type, Z data) {
    ItemstackUtils.addPersistentTag(this, key, type, data);
    return this;
  }

  /**
   * Sets this ItemBuilder to be unbreakable
   *
   * @return The ItemBuilder with the unbreakable tag added
   */
  public StackBuilder unbreakable() {
    ItemstackUtils.setUnbreakable(this);
    return this;
  }

}