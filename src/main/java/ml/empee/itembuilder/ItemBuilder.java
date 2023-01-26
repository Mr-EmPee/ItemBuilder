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

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Main ItemBuilder
 */
public class ItemBuilder extends BaseItemBuilder<ItemBuilder> {

  /**
   * Constructor of the item builder
   *
   * @param itemStack The {@link ItemStack} of the item
   */
  ItemBuilder(@NotNull final ItemStack itemStack) {
    super(itemStack);
  }

  /**
   * Main method to create {@link ItemBuilder}
   *
   * @param itemStack The {@link ItemStack} you want to edit
   * @return A new {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> new")
  public static ItemBuilder from(@NotNull final ItemStack itemStack) {
    return new ItemBuilder(itemStack);
  }


  /**
   * Alternative method to create {@link ItemBuilder}
   *
   * @param material The {@link Material} you want to create an item from
   * @return A new {@link ItemBuilder}
   */
  @NotNull
  @Contract("_ -> new")
  public static ItemBuilder from(@NotNull final Material material) {
    return new ItemBuilder(new ItemStack(material));
  }

  /**
   * Method for creating a {@link BannerBuilder} which will have BANNER specific methods
   *
   * @return A new {@link BannerBuilder}
   */
  @NotNull
  @Contract(" -> new")
  public static BannerBuilder banner() {
    return new BannerBuilder();
  }

  /**
   * Method for creating a {@link BannerBuilder} which will have BANNER specific methods
   *
   * @param itemStack An existing BANNER {@link ItemStack}
   * @return A new {@link BannerBuilder}
   * @throws RuntimeException if the item is not a BANNER
   */
  @NotNull
  @Contract("_ -> new")
  public static BannerBuilder banner(@NotNull final ItemStack itemStack) {
    return new BannerBuilder(itemStack);
  }

  /**
   * Method for creating a {@link BookBuilder} which will have {@link Material#WRITABLE_BOOK} /
   * {@link Material#WRITTEN_BOOK} specific methods
   *
   * @param itemStack an existing {@link Material#WRITABLE_BOOK} / {@link Material#WRITTEN_BOOK}
   *                  {@link ItemStack}
   * @return A new {@link FireworkBuilder}
   * @throws RuntimeException if the item type is not {@link Material#WRITABLE_BOOK} or
   *                          {@link Material#WRITTEN_BOOK}
   */
  @NotNull
  @Contract("_ -> new")
  public static BookBuilder book(@NotNull final ItemStack itemStack) {
    return new BookBuilder(itemStack);
  }

  /**
   * Method for creating a {@link FireworkBuilder} which will have {@link Material#FIREWORK_ROCKET} specific
   * methods
   *
   * @return A new {@link FireworkBuilder}
   */
  @NotNull
  @Contract(" -> new")
  public static FireworkBuilder firework() {
    return new FireworkBuilder(new ItemStack(Material.FIREWORK_ROCKET));
  }

  /**
   * Method for creating a {@link FireworkBuilder} which will have {@link Material#FIREWORK_ROCKET} specific
   * methods
   *
   * @param itemStack an existing {@link Material#FIREWORK_ROCKET} {@link ItemStack}
   * @return A new {@link FireworkBuilder}
   * @throws RuntimeException if the item type is not {@link Material#FIREWORK_ROCKET}
   */
  @NotNull
  @Contract("_ -> new")
  public static FireworkBuilder firework(@NotNull final ItemStack itemStack) {
    return new FireworkBuilder(itemStack);
  }

  /**
   * Method for creating a {@link MapBuilder} which will have {@link Material#MAP} specific methods
   *
   * @return A new {@link MapBuilder}
   */
  @NotNull
  @Contract(" -> new")
  public static MapBuilder map() {
    return new MapBuilder();
  }

  /**
   * Method for creating a {@link MapBuilder} which will have @link Material#MAP} specific methods
   *
   * @param itemStack An existing {@link Material#MAP} {@link ItemStack}
   * @return A new {@link MapBuilder}
   * @throws RuntimeException if the item type is not {@link Material#MAP}
   */
  @NotNull
  @Contract("_ -> new")
  public static MapBuilder map(@NotNull final ItemStack itemStack) {
    return new MapBuilder(itemStack);
  }

  /**
   * Method for creating a {@link SkullBuilder} which will have PLAYER_HEAD specific methods
   *
   * @return A new {@link SkullBuilder}
   */
  @NotNull
  @Contract(" -> new")
  public static SkullBuilder skull() {
    return new SkullBuilder();
  }

  /**
   * Method for creating a {@link SkullBuilder} which will have PLAYER_HEAD specific methods
   *
   * @param itemStack An existing PLAYER_HEAD {@link ItemStack}
   * @return A new {@link SkullBuilder}
   * @throws RuntimeException if the item is not a player head
   */
  @NotNull
  @Contract("_ -> new")
  public static SkullBuilder skull(@NotNull final ItemStack itemStack) {
    return new SkullBuilder(itemStack);
  }

  /**
   * Method for creating a {@link FireworkBuilder} which will have {@link Material#FIREWORK_STAR} specific
   * methods
   *
   * @return A new {@link FireworkBuilder}
   */
  @NotNull
  @Contract(" -> new")
  public static FireworkBuilder star() {
    return new FireworkBuilder(new ItemStack(Material.FIREWORK_STAR));
  }

  /**
   * Method for creating a {@link FireworkBuilder} which will have {@link Material#FIREWORK_STAR} specific
   * methods
   *
   * @param itemStack an existing {@link Material#FIREWORK_STAR} {@link ItemStack}
   * @return A new {@link FireworkBuilder}
   * @throws RuntimeException if the item type is not {@link Material#FIREWORK_STAR}
   */
  @NotNull
  @Contract("_ -> new")
  public static FireworkBuilder star(@NotNull final ItemStack itemStack) {
    return new FireworkBuilder(itemStack);
  }

}
